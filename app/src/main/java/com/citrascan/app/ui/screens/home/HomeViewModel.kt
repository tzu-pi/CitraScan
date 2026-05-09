// ui/screens/home/HomeViewModel.kt — Home screen business logic
package com.citrascan.app.ui.screens.home

import androidx.lifecycle.ViewModel
import com.citrascan.app.data.model.DiseaseInfo
import com.citrascan.app.data.model.ModelMetrics
import com.citrascan.app.data.repository.DiseaseRepository
import com.citrascan.app.data.repository.ScanHistoryRepository
import com.citrascan.app.util.GreetingHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Holds the UI state for the Home screen.
 */
data class HomeUiState(
    val greeting: String = GreetingHelper.getGreeting(),
    val totalScans: Int = 0,
    val avgAccuracy: String = "—",
    val diseasesFound: Int = 0,
    val healthyScans: Int = 0,
    val detectableDiseases: List<DiseaseInfo> = emptyList(),
    val showMetricsModal: Boolean = false,
    val metrics: ModelMetrics = ModelMetrics(
        precision = "0.94",
        recall = "0.93",
        f1Score = "0.93",
        mAP = "0.95"
    )
)

/**
 * ViewModel for the Home screen.
 * Manages greeting, stats, and detectable disease list.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val diseaseRepository: DiseaseRepository,
    private val historyRepository: ScanHistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    /** Observable home screen UI state. */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val diseases = diseaseRepository.getDetectableDiseases()
        val metrics = diseaseRepository.getModelMetrics()
        val total = historyRepository.totalScans()
        val avgAcc = if (total == 0) {
            "—"
        } else {
            val historyList = historyRepository.history.value
            if (historyList.isEmpty()) {
                "—"
            } else {
                val avg = historyList.map { it.confidence }.average()
                "%.2f".format(avg.toFloat())
            }
        }
        _uiState.value = _uiState.value.copy(
            greeting = GreetingHelper.getGreeting(),
            totalScans = total,
            avgAccuracy = avgAcc,
            diseasesFound = historyRepository.diseasesFound(),
            healthyScans = historyRepository.healthyScans(),
            detectableDiseases = diseases,
            metrics = metrics
        )
    }

    /** Shows the metrics modal dialog. */
    fun showMetrics() {
        _uiState.value = _uiState.value.copy(showMetricsModal = true)
    }

    /** Hides the metrics modal dialog. */
    fun hideMetrics() {
        _uiState.value = _uiState.value.copy(showMetricsModal = false)
    }

    /** Refreshes the greeting based on current time. */
    fun refreshGreeting() {
        _uiState.value = _uiState.value.copy(greeting = GreetingHelper.getGreeting())
    }
}
