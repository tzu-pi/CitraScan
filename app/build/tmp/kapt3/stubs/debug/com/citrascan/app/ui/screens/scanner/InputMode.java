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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/citrascan/app/ui/screens/scanner/InputMode;", "", "(Ljava/lang/String;I)V", "CAMERA", "UPLOAD", "app_debug"})
public enum InputMode {
    /*public static final*/ CAMERA /* = new CAMERA() */,
    /*public static final*/ UPLOAD /* = new UPLOAD() */;
    
    InputMode() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.citrascan.app.ui.screens.scanner.InputMode> getEntries() {
        return null;
    }
}