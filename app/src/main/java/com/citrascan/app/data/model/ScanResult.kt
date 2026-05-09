// data/model/ScanResult.kt — Full scan result with detections
package com.citrascan.app.data.model

import android.graphics.Bitmap

/**
 * Complete result of a scan operation.
 *
 * @property diseaseKey Key identifier matching [DiseaseInfo.key].
 * @property detections List of individual bounding box detections.
 * @property inferenceTimeMs Inference duration in milliseconds.
 * @property capturedImage The original captured bitmap (nullable if from gallery).
 * @property timestamp Unix timestamp of when the scan was performed.
 */
data class ScanResult(
    val diseaseKey: String,
    val detections: List<Detection>,
    val inferenceTimeMs: Long,
    val capturedImage: Bitmap? = null,
    val timestamp: Long = System.currentTimeMillis()
)
