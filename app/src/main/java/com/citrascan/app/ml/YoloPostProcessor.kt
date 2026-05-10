// ml/YoloPostProcessor.kt — YOLOv8 output parsing and NMS
package com.citrascan.app.ml

import com.citrascan.app.data.model.BoundingBox
import com.citrascan.app.data.model.Detection
import com.citrascan.app.data.repository.DiseaseRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.min

/**
 * Post-processes raw YOLOv8 model output into [Detection] objects.
 *
 * YOLOv8 output shape: [1, 7, 8400] where:
 * - 7 = 4 bbox coords (cx, cy, w, h) + 3 class scores
 * - 8400 = number of candidate detections
 *
 * Steps:
 * 1. Parse raw output into candidate detections
 * 2. Filter by confidence threshold
 * 3. Apply Non-Maximum Suppression (NMS)
 */
@Singleton
class YoloPostProcessor @Inject constructor(
    private val diseaseRepository: DiseaseRepository
) {

    companion object {
        /** Minimum confidence to keep a detection. */
        const val CONFIDENCE_THRESHOLD = 0.45f
        /** IoU threshold for NMS. */
        const val IOU_THRESHOLD = 0.45f
        /** Number of classes in the model. */
        const val NUM_CLASSES = 3
        /** Number of candidate detections from YOLOv8. */
        const val NUM_DETECTIONS = 8400
    }

    /**
     * Processes raw model output into a list of detections after NMS.
     *
     * @param output Raw float array from ONNX model, shape [1, 7, 8400].
     * @return List of [Detection] objects passing confidence and NMS filters.
     */
    fun process(output: FloatArray): List<Detection> {
        val candidates = mutableListOf<Detection>()
        val stride = NUM_DETECTIONS // 8400

        for (i in 0 until NUM_DETECTIONS) {
            // Extract bbox in center format (cx, cy, w, h) — normalized to 640
            val cx = output[0 * stride + i]
            val cy = output[1 * stride + i]
            val w = output[2 * stride + i]
            val h = output[3 * stride + i]

            // Find best class
            var bestClassIdx = 0
            var bestScore = 0f
            for (c in 0 until NUM_CLASSES) {
                val score = output[(4 + c) * stride + i]
                if (score > bestScore) {
                    bestScore = score
                    bestClassIdx = c
                }
            }

            if (bestScore < CONFIDENCE_THRESHOLD) continue

            // Convert center format to corner format and normalize to [0, 1]
            val inputSize = ImagePreprocessor.INPUT_SIZE.toFloat()
            val x1 = ((cx - w / 2f) / inputSize).coerceIn(0f, 1f)
            val y1 = ((cy - h / 2f) / inputSize).coerceIn(0f, 1f)
            val x2 = ((cx + w / 2f) / inputSize).coerceIn(0f, 1f)
            val y2 = ((cy + h / 2f) / inputSize).coerceIn(0f, 1f)

            candidates.add(
                Detection(
                    classIndex = bestClassIdx,
                    className = diseaseRepository.classIndexToName(bestClassIdx),
                    confidence = bestScore,
                    boundingBox = BoundingBox(x1, y1, x2, y2)
                )
            )
        }

        return applyNms(candidates)
    }

    /**
     * Applies Non-Maximum Suppression to remove overlapping detections.
     */
    private fun applyNms(detections: List<Detection>): List<Detection> {
        if (detections.isEmpty()) return emptyList()

        // Sort by confidence descending
        val sorted = detections.sortedByDescending { it.confidence }.toMutableList()
        val result = mutableListOf<Detection>()

        while (sorted.isNotEmpty()) {
            val best = sorted.removeFirst()
            result.add(best)

            sorted.removeAll { other ->
                other.classIndex == best.classIndex &&
                    computeIoU(best.boundingBox, other.boundingBox) > IOU_THRESHOLD
            }
        }

        return result
    }

    /**
     * Computes Intersection over Union between two bounding boxes.
     */
    private fun computeIoU(a: BoundingBox, b: BoundingBox): Float {
        val interX1 = max(a.x1, b.x1)
        val interY1 = max(a.y1, b.y1)
        val interX2 = min(a.x2, b.x2)
        val interY2 = min(a.y2, b.y2)

        val interArea = max(0f, interX2 - interX1) * max(0f, interY2 - interY1)
        val areaA = a.width * a.height
        val areaB = b.width * b.height
        val unionArea = areaA + areaB - interArea

        return if (unionArea > 0f) interArea / unionArea else 0f
    }
}
