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
     * Uses the highest-confidence detection. Returns "healthy" if no detections.
     */
    private fun determinePrimaryDisease(detections: List<Detection>): String {
        if (detections.isEmpty()) return "healthy"
        val best = detections.maxByOrNull { it.confidence } ?: return "healthy"
        return diseaseRepository.classIndexToKey(best.classIndex)
    }
}
