// ui/screens/scanner/ScannerViewModel.kt
package com.citrascan.app.ui.screens.scanner

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citrascan.app.data.model.ScanResult
import com.citrascan.app.data.repository.DiseaseRepository
import com.citrascan.app.data.repository.ScanHistoryRepository
import com.citrascan.app.ml.DetectionEngine
import com.citrascan.app.ml.OnnxModelManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Sealed hierarchy for scanner UI states. */
sealed class ScannerUiState {
    data class Ready(
        val scanMode: OnnxModelManager.ScanMode = OnnxModelManager.ScanMode.FRUIT,
        val inputMode: InputMode = InputMode.CAMERA,
        val showGuide: Boolean = false
    ) : ScannerUiState()

    data class Analyzing(
        val progress: Float = 0f,
        val stepLabel: String = ""
    ) : ScannerUiState()

    data class Success(val result: ScanResult, val diseaseKey: String) : ScannerUiState()
    data class Error(val message: String) : ScannerUiState()
}

enum class InputMode { CAMERA, UPLOAD }

/** ViewModel for the Scanner screen. */
@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val detectionEngine: DetectionEngine,
    private val diseaseRepository: DiseaseRepository,
    private val historyRepository: ScanHistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScannerUiState>(ScannerUiState.Ready())
    val uiState: StateFlow<ScannerUiState> = _uiState.asStateFlow()

    fun setScanMode(mode: OnnxModelManager.ScanMode) {
        val cur = _uiState.value
        if (cur is ScannerUiState.Ready) _uiState.value = cur.copy(scanMode = mode)
    }

    fun setInputMode(mode: InputMode) {
        val cur = _uiState.value
        if (cur is ScannerUiState.Ready) _uiState.value = cur.copy(inputMode = mode)
    }

    fun toggleGuide() {
        val cur = _uiState.value
        if (cur is ScannerUiState.Ready) _uiState.value = cur.copy(showGuide = !cur.showGuide)
    }

    /** Runs the detection pipeline on the provided bitmap. */
    fun analyze(bitmap: Bitmap) {
        val scanMode = (_uiState.value as? ScannerUiState.Ready)?.scanMode
            ?: OnnxModelManager.ScanMode.FRUIT
        viewModelScope.launch {
            try {
                val steps = listOf(
                    "Preprocessing image…", "Running YOLOv8 inference…",
                    "Classifying disease type…", "Generating report…", "Complete"
                )
                for ((i, step) in steps.withIndex()) {
                    _uiState.value = ScannerUiState.Analyzing(
                        progress = (i + 1f) / steps.size, stepLabel = step
                    )
                    if (i < steps.size - 1) delay(560)
                }
                val result = detectionEngine.detect(bitmap, scanMode)
                val info = diseaseRepository.getDiseaseByKey(result.diseaseKey)
                if (info != null) {
                    val conf = result.detections.maxByOrNull { it.confidence }
                        ?.confidence ?: info.defaultConfidence
                    historyRepository.addEntry(result.diseaseKey, info.name, conf, info.severity)
                }
                delay(350)
                _uiState.value = ScannerUiState.Success(result, result.diseaseKey)
            } catch (e: Exception) {
                // Show detailed error including root cause
                val rootCause = generateSequence(e as Throwable) { it.cause }.last()
                val errorMsg = if (rootCause !== e) {
                    "${e.message}\n\nRoot cause: ${rootCause.javaClass.simpleName}: ${rootCause.message}"
                } else {
                    "${e.javaClass.simpleName}: ${e.message}"
                }
                android.util.Log.e("ScannerVM", "Analysis failed", e)
                _uiState.value = ScannerUiState.Error(errorMsg)
            }
        }
    }

    fun reset() { _uiState.value = ScannerUiState.Ready() }
}
