package com.citrascan.app.ui.screens.scanner;

import android.graphics.Bitmap;
import androidx.lifecycle.ViewModel;
import com.citrascan.app.data.model.ScanResult;
import com.citrascan.app.data.repository.DiseaseRepository;
import com.citrascan.app.data.repository.ScanHistoryRepository;
import com.citrascan.app.ml.DetectionEngine;
import com.citrascan.app.ml.OnnxModelManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

/**
 * ViewModel for the Scanner screen.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0011J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0011R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/citrascan/app/ui/screens/scanner/ScannerViewModel;", "Landroidx/lifecycle/ViewModel;", "detectionEngine", "Lcom/citrascan/app/ml/DetectionEngine;", "diseaseRepository", "Lcom/citrascan/app/data/repository/DiseaseRepository;", "historyRepository", "Lcom/citrascan/app/data/repository/ScanHistoryRepository;", "(Lcom/citrascan/app/ml/DetectionEngine;Lcom/citrascan/app/data/repository/DiseaseRepository;Lcom/citrascan/app/data/repository/ScanHistoryRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "analyze", "", "bitmap", "Landroid/graphics/Bitmap;", "reset", "setInputMode", "mode", "Lcom/citrascan/app/ui/screens/scanner/InputMode;", "setScanMode", "Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;", "toggleGuide", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ScannerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.ml.DetectionEngine detectionEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.repository.DiseaseRepository diseaseRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.repository.ScanHistoryRepository historyRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.citrascan.app.ui.screens.scanner.ScannerUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.citrascan.app.ui.screens.scanner.ScannerUiState> uiState = null;
    
    @javax.inject.Inject()
    public ScannerViewModel(@org.jetbrains.annotations.NotNull()
    com.citrascan.app.ml.DetectionEngine detectionEngine, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.repository.DiseaseRepository diseaseRepository, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.repository.ScanHistoryRepository historyRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.citrascan.app.ui.screens.scanner.ScannerUiState> getUiState() {
        return null;
    }
    
    public final void setScanMode(@org.jetbrains.annotations.NotNull()
    com.citrascan.app.ml.OnnxModelManager.ScanMode mode) {
    }
    
    public final void setInputMode(@org.jetbrains.annotations.NotNull()
    com.citrascan.app.ui.screens.scanner.InputMode mode) {
    }
    
    public final void toggleGuide() {
    }
    
    /**
     * Runs the detection pipeline on the provided bitmap.
     */
    public final void analyze(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
    }
    
    public final void reset() {
    }
}