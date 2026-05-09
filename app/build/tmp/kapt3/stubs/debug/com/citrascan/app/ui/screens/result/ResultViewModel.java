package com.citrascan.app.ui.screens.result;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.citrascan.app.data.model.DiseaseInfo;
import com.citrascan.app.data.model.SeverityActions;
import com.citrascan.app.data.repository.DiseaseRepository;
import com.citrascan.app.data.repository.ScanHistoryRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

/**
 * ViewModel for the Result screen. Loads disease info by key from nav args.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lcom/citrascan/app/ui/screens/result/ResultViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "diseaseRepository", "Lcom/citrascan/app/data/repository/DiseaseRepository;", "historyRepository", "Lcom/citrascan/app/data/repository/ScanHistoryRepository;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/citrascan/app/data/repository/DiseaseRepository;Lcom/citrascan/app/data/repository/ScanHistoryRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/citrascan/app/ui/screens/result/ResultUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadDisease", "", "key", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ResultViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.repository.DiseaseRepository diseaseRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.repository.ScanHistoryRepository historyRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.citrascan.app.ui.screens.result.ResultUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.citrascan.app.ui.screens.result.ResultUiState> uiState = null;
    
    @javax.inject.Inject()
    public ResultViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.repository.DiseaseRepository diseaseRepository, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.repository.ScanHistoryRepository historyRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.citrascan.app.ui.screens.result.ResultUiState> getUiState() {
        return null;
    }
    
    private final void loadDisease(java.lang.String key) {
    }
}