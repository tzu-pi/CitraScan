// data/repository/DiseaseRepository.kt — Static disease info provider
package com.citrascan.app.data.repository

import com.citrascan.app.data.model.*
import com.citrascan.app.ui.theme.DotCanker
import com.citrascan.app.ui.theme.DotHlb
import com.citrascan.app.ui.theme.DotBlackspot
import com.citrascan.app.ui.theme.DotHealthy
import com.citrascan.app.ui.theme.ForestGreen
import com.citrascan.app.ui.theme.SeverityBad
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides static disease information data.
 * Mirrors the `DB` JavaScript object from the HTML prototype.
 */
@Singleton
class DiseaseRepository @Inject constructor() {

    private val diseases = mapOf(
        "canker" to DiseaseInfo(
            key = "canker",
            name = "Citrus canker",
            subtitle = "Bacterial · treatable",
            about = "Citrus canker is a bacterial disease caused by Xanthomonas citri subsp. citri. It produces raised, corky lesions on leaves, stems, and fruit, reducing marketability.",
            severity = Severity.WARN,
            treatment = TreatmentStatus.TREATABLE,
            chips = listOf(
                DiseaseChip("Bacterial", Severity.WARN),
                DiseaseChip("Contagious", Severity.WARN),
                DiseaseChip("Treatable", Severity.OK)
            ),
            dotColor = DotCanker,
            barColor = ForestGreen,
            defaultConfidence = 0.94f,
            inferenceTime = "47 ms",
            boundingBoxCount = "3 detected"
        ),
        "hlb" to DiseaseInfo(
            key = "hlb",
            name = "Huanglongbing (HLB)",
            subtitle = "Bacterial · no cure",
            about = "HLB (citrus greening) is one of the most destructive citrus diseases globally. Caused by Candidatus Liberibacter spp., it has no known cure.",
            severity = Severity.BAD,
            treatment = TreatmentStatus.NO_CURE,
            chips = listOf(
                DiseaseChip("Bacterial", Severity.BAD),
                DiseaseChip("Incurable", Severity.BAD),
                DiseaseChip("Highly contagious", Severity.BAD)
            ),
            dotColor = DotHlb,
            barColor = SeverityBad,
            defaultConfidence = 0.91f,
            inferenceTime = "52 ms",
            boundingBoxCount = "5 detected"
        ),
        "cbs" to DiseaseInfo(
            key = "cbs",
            name = "Citrus black spot",
            subtitle = "Fungal · manageable",
            about = "Citrus black spot is a fungal disease caused by Phyllosticta citricarpa. It produces dark lesions on fruit rind, reducing commercial value.",
            severity = Severity.WARN,
            treatment = TreatmentStatus.MANAGEABLE,
            chips = listOf(
                DiseaseChip("Fungal", Severity.WARN),
                DiseaseChip("Post-harvest risk", Severity.WARN),
                DiseaseChip("Manageable", Severity.OK)
            ),
            dotColor = DotBlackspot,
            barColor = ForestGreen,
            defaultConfidence = 0.88f,
            inferenceTime = "44 ms",
            boundingBoxCount = "2 detected"
        ),
        "healthy" to DiseaseInfo(
            key = "healthy",
            name = "No disease detected",
            subtitle = "Healthy",
            about = "Your citrus plant shows no signs of canker, HLB, or black spot. Continue regular monitoring to maintain plant health.",
            severity = Severity.OK,
            treatment = TreatmentStatus.NO_ACTION,
            chips = listOf(
                DiseaseChip("Healthy", Severity.OK),
                DiseaseChip("No action needed", Severity.OK)
            ),
            dotColor = DotHealthy,
            barColor = ForestGreen,
            defaultConfidence = 0.97f,
            inferenceTime = "39 ms",
            boundingBoxCount = "0 detected"
        )
    )

    /** Returns all disease info entries as a list. */
    fun getAllDiseases(): List<DiseaseInfo> = diseases.values.toList()

    /** Returns the detectable diseases (excluding healthy). */
    fun getDetectableDiseases(): List<DiseaseInfo> =
        diseases.values.filter { it.key != "healthy" }

    /** Returns disease info by key, or null if not found. */
    fun getDiseaseByKey(key: String): DiseaseInfo? = diseases[key]

    /** Returns the model performance metrics from YOLOv8s training evaluation. */
    fun getModelMetrics(): ModelMetrics = ModelMetrics(
        precision = "0.94",
        recall = "0.93",
        f1Score = "0.93",
        mAP = "0.95"
    )

    /**
     * Maps a class index from the ONNX model to a disease key.
     * Class indices: 0=Canker, 1=Black Spot, 2=Greening (HLB).
     */
    fun classIndexToKey(classIndex: Int): String = when (classIndex) {
        0 -> "canker"
        1 -> "cbs"
        2 -> "hlb"
        else -> "healthy"
    }

    /**
     * Maps a class index to a human-readable class name.
     */
    fun classIndexToName(classIndex: Int): String = when (classIndex) {
        0 -> "Canker"
        1 -> "Black Spot"
        2 -> "Greening"
        else -> "Unknown"
    }
}
