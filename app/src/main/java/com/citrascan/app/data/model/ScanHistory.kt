// data/model/ScanHistory.kt — History entry for a past scan
package com.citrascan.app.data.model

/**
 * Represents a single scan history entry.
 *
 * @property id Unique identifier for this history entry.
 * @property diseaseKey Key identifier matching [DiseaseInfo.key].
 * @property diseaseName Human-readable disease name.
 * @property confidence Confidence score as a float (0.0–1.0).
 * @property severity Severity level of the detection.
 * @property timestamp Unix timestamp of the scan.
 */
data class ScanHistory(
    val id: String,
    val diseaseKey: String,
    val diseaseName: String,
    val confidence: Float,
    val severity: Severity,
    val timestamp: Long
)
