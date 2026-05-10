// ml/DetectionEngine.kt — Orchestrates the full detection pipeline
package com.citrascan.app.ml

import android.graphics.Bitmap
import com.citrascan.app.data.model.Detection
import com.citrascan.app.data.model.ScanResult
import com.citrascan.app.data.repository.DiseaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Orchestrates the end-to-end detection pipeline:
 * preprocess → ONNX inference → post-process → result mapping.
 */
@Singleton
class DetectionEngine @Inject constructor(
    private val preprocessor: ImagePreprocessor,
    private val modelManager: OnnxModelManager,
    private val postProcessor: YoloPostProcessor,
    private val diseaseRepository: DiseaseRepository
) {

    /**
     * Runs the full detection pipeline on a captured image.
     *
     * @param bitmap The source image to analyze.
     * @param scanMode Whether the user selected fruit or leaf scanning mode.
     * @return A [ScanResult] containing all detections and metadata.
     * @throws ModelLoadException If the model cannot be loaded.
     * @throws InferenceException If inference fails.
     */
    suspend fun detect(
        bitmap: Bitmap,
        scanMode: OnnxModelManager.ScanMode
    ): ScanResult = withContext(Dispatchers.Default) {
        val startTime = System.currentTimeMillis()

        // 1. Preprocess image
        val inputBuffer = preprocessor.preprocess(bitmap)
        val inputShape = preprocessor.getInputShape()

        // 2. Run ONNX inference
        val rawOutput = modelManager.runInference(inputBuffer, inputShape, scanMode)

        // 3. Post-process (parse + NMS)
        val detections = postProcessor.process(rawOutput)

        val inferenceTime = System.currentTimeMillis() - startTime

        // 4. Determine the primary disease from highest-confidence detection
        val diseaseKey = determinePrimaryDisease(detections)

        ScanResult(
            diseaseKey = diseaseKey,
            detections = detections,
            inferenceTimeMs = inferenceTime,
            capturedImage = bitmap,
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Determines the primary disease from a list of detections.
     * Uses the highest-confidence detection. Returns "healthy" if no detections
     * or if the detections are likely false positives (low confidence or invalid size).
     */
    private fun determinePrimaryDisease(detections: List<Detection>): String {
        if (detections.isEmpty()) return "healthy"

        // Filter for detections that meet a stricter confidence threshold (e.g., 0.55)
        // and have a reasonable size (not too tiny, not covering the entire image)
        val validDetections = detections.filter { det ->
            val box = det.boundingBox
            val area = (box.x2 - box.x1) * (box.y2 - box.y1)
            
            // Sanity check: 
            // 1. Confidence > 0.55
            // 2. Area is between 0.5% and 90% of the image
            det.confidence > 0.55f && area > 0.005f && area < 0.90f
        }

        if (validDetections.isEmpty()) return "healthy"

        val best = validDetections.maxByOrNull { it.confidence } ?: return "healthy"
        return diseaseRepository.classIndexToKey(best.classIndex)
    }
}
