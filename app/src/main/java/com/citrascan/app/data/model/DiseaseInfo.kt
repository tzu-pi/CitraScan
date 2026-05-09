// data/model/DiseaseInfo.kt — Static disease data matching the HTML DB object
package com.citrascan.app.data.model

import androidx.compose.ui.graphics.Color
import com.citrascan.app.ui.theme.SeverityBad
import com.citrascan.app.ui.theme.SeverityOk

/**
 * Severity level for a detected disease.
 */
enum class Severity(val label: String) {
    OK("Healthy"),
    WARN("Moderate"),
    BAD("Severe")
}

/**
 * Treatment status for a detected disease.
 */
enum class TreatmentStatus(val label: String) {
    TREATABLE("Treatable"),
    MANAGEABLE("Manageable"),
    NO_CURE("No cure"),
    NO_ACTION("No action needed")
}

/**
 * A chip tag displayed on the result screen (e.g., "Bacterial", "Contagious").
 */
data class DiseaseChip(
    val text: String,
    val severity: Severity
)

/**
 * Complete information about a detectable citrus disease.
 * Mirrors the `DB` object from the HTML prototype.
 */
data class DiseaseInfo(
    val key: String,
    val name: String,
    val subtitle: String,
    val about: String,
    val severity: Severity,
    val treatment: TreatmentStatus,
    val chips: List<DiseaseChip>,
    val dotColor: Color,
    val barColor: Color,
    val defaultConfidence: Float,
    val inferenceTime: String,
    val boundingBoxCount: String
)

/**
 * Recommended actions grouped by severity level.
 * Mirrors the `SeverityActions` object from the HTML prototype.
 */
object SeverityActions {
    val ok = listOf(
        "Continue regular inspection every 7–14 days.",
        "Maintain proper irrigation and fertilization.",
        "Monitor for early signs of discoloration or lesions.",
        "Keep scan records in the app history for comparison."
    )
    val warn = listOf(
        "Prune and destroy all infected leaves or small branches immediately.",
        "Apply copper-based bactericide or registered fungicide to the area.",
        "Avoid overhead irrigation to keep foliage dry and limit spread.",
        "Monitor neighboring trees weekly for any new symptoms."
    )
    val bad = listOf(
        "QUARANTINE: Mark the area as a high-risk zone and restrict access.",
        "MANDATORY REMOVAL: Uproot and burn severely infected trees at once.",
        "INTENSIVE TREATMENT: Apply systemic insecticides to control vectors.",
        "REPORT: Notify local agricultural extension office of the outbreak."
    )

    /**
     * Returns the action list for the given severity.
     */
    fun forSeverity(severity: Severity): List<String> = when (severity) {
        Severity.OK -> ok
        Severity.WARN -> warn
        Severity.BAD -> bad
    }
}

/**
 * Model performance metrics displayed in the metrics modal.
 */
data class ModelMetrics(
    val precision: String,
    val recall: String,
    val f1Score: String,
    val mAP: String
)
