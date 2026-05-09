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
 * Sealed hierarchy for scanner UI states.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/citrascan/app/ui/screens/scanner/ScannerUiState;", "", "()V", "Analyzing", "Error", "Ready", "Success", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Analyzing;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Error;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Ready;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Success;", "app_debug"})
public abstract class ScannerUiState {
    
    private ScannerUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Analyzing;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState;", "progress", "", "stepLabel", "", "(FLjava/lang/String;)V", "getProgress", "()F", "getStepLabel", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Analyzing extends com.citrascan.app.ui.screens.scanner.ScannerUiState {
        private final float progress = 0.0F;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String stepLabel = null;
        
        public Analyzing(float progress, @org.jetbrains.annotations.NotNull()
        java.lang.String stepLabel) {
        }
        
        public final float getProgress() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getStepLabel() {
            return null;
        }
        
        public Analyzing() {
        }
        
        public final float component1() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ui.screens.scanner.ScannerUiState.Analyzing copy(float progress, @org.jetbrains.annotations.NotNull()
        java.lang.String stepLabel) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Error;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Error extends com.citrascan.app.ui.screens.scanner.ScannerUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String message = null;
        
        public Error(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ui.screens.scanner.ScannerUiState.Error copy(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Ready;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState;", "scanMode", "Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;", "inputMode", "Lcom/citrascan/app/ui/screens/scanner/InputMode;", "showGuide", "", "(Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;Lcom/citrascan/app/ui/screens/scanner/InputMode;Z)V", "getInputMode", "()Lcom/citrascan/app/ui/screens/scanner/InputMode;", "getScanMode", "()Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;", "getShowGuide", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Ready extends com.citrascan.app.ui.screens.scanner.ScannerUiState {
        @org.jetbrains.annotations.NotNull()
        private final com.citrascan.app.ml.OnnxModelManager.ScanMode scanMode = null;
        @org.jetbrains.annotations.NotNull()
        private final com.citrascan.app.ui.screens.scanner.InputMode inputMode = null;
        private final boolean showGuide = false;
        
        public Ready(@org.jetbrains.annotations.NotNull()
        com.citrascan.app.ml.OnnxModelManager.ScanMode scanMode, @org.jetbrains.annotations.NotNull()
        com.citrascan.app.ui.screens.scanner.InputMode inputMode, boolean showGuide) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ml.OnnxModelManager.ScanMode getScanMode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ui.screens.scanner.InputMode getInputMode() {
            return null;
        }
        
        public final boolean getShowGuide() {
            return false;
        }
        
        public Ready() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ml.OnnxModelManager.ScanMode component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ui.screens.scanner.InputMode component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ui.screens.scanner.ScannerUiState.Ready copy(@org.jetbrains.annotations.NotNull()
        com.citrascan.app.ml.OnnxModelManager.ScanMode scanMode, @org.jetbrains.annotations.NotNull()
        com.citrascan.app.ui.screens.scanner.InputMode inputMode, boolean showGuide) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Success;", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState;", "result", "Lcom/citrascan/app/data/model/ScanResult;", "diseaseKey", "", "(Lcom/citrascan/app/data/model/ScanResult;Ljava/lang/String;)V", "getDiseaseKey", "()Ljava/lang/String;", "getResult", "()Lcom/citrascan/app/data/model/ScanResult;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Success extends com.citrascan.app.ui.screens.scanner.ScannerUiState {
        @org.jetbrains.annotations.NotNull()
        private final com.citrascan.app.data.model.ScanResult result = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String diseaseKey = null;
        
        public Success(@org.jetbrains.annotations.NotNull()
        com.citrascan.app.data.model.ScanResult result, @org.jetbrains.annotations.NotNull()
        java.lang.String diseaseKey) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.data.model.ScanResult getResult() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDiseaseKey() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.data.model.ScanResult component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.citrascan.app.ui.screens.scanner.ScannerUiState.Success copy(@org.jetbrains.annotations.NotNull()
        com.citrascan.app.data.model.ScanResult result, @org.jetbrains.annotations.NotNull()
        java.lang.String diseaseKey) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}