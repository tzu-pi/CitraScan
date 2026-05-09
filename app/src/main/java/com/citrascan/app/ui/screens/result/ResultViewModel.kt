// ui/screens/result/ResultViewModel.kt
package com.citrascan.app.ui.screens.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.citrascan.app.data.model.DiseaseInfo
import com.citrascan.app.data.model.SeverityActions
import com.citrascan.app.data.repository.DiseaseRepository
import com.citrascan.app.data.repository.ScanHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/** UI state for the result screen. */
data class ResultUiState(
    val disease: DiseaseInfo? = null,
    val actions: List<String> = emptyList(),
    val actualConfidence: Float? = null,
    val isLoading: Boolean = true
)

/** ViewModel for the Result screen. Loads disease info by key from nav args. */
@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val diseaseRepository: DiseaseRepository,
    private val historyRepository: ScanHistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    init {
        val key = savedStateHandle.get<String>("diseaseKey") ?: "healthy"
        loadDisease(key)
    }

    private fun loadDisease(key: String) {
        val disease = diseaseRepository.getDiseaseByKey(key)
        val actions = disease?.let { SeverityActions.forSeverity(it.severity) } ?: emptyList()
        // Try to get the actual confidence from the most recent scan of this disease
        val lastScan = historyRepository.history.value
            .filter { it.diseaseKey == key }
            .maxByOrNull { it.timestamp }
        _uiState.value = ResultUiState(
            disease = disease,
            actions = actions,
            actualConfidence = lastScan?.confidence,
            isLoading = false
        )
    }
}
