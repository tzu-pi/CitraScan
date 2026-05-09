// ui/screens/history/HistoryViewModel.kt
package com.citrascan.app.ui.screens.history

import androidx.lifecycle.ViewModel
import com.citrascan.app.data.model.ScanHistory
import com.citrascan.app.data.repository.ScanHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/** UI state for the history screen. */
data class HistoryUiState(
    val entries: List<ScanHistory> = emptyList(),
    val searchQuery: String = ""
)

/** ViewModel for the History screen. */
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: ScanHistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init { loadHistory() }

    private fun loadHistory() {
        _uiState.value = _uiState.value.copy(entries = historyRepository.history.value)
    }

    /** Updates search query and filters history. */
    fun onSearchChanged(query: String) {
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            entries = historyRepository.search(query)
        )
    }
}
