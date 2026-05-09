package com.citrascan.app.ui.screens.scanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.font.FontWeight;
import androidx.core.content.ContextCompat;
import com.citrascan.app.R;
import com.citrascan.app.ml.OnnxModelManager;
import com.google.accompanist.permissions.*;
import java.util.concurrent.Executors;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001aB\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a\u001e\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0003\u001a\u001a\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a~\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u00122\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0003\u001a4\u0010%\u001a\u00020\u00012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010\u00122\u0012\u0010\'\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010(\u001a\u00020)H\u0007\u001a\u001a\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0015H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010\u0017\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006-"}, d2 = {"AnalyzingContent", "", "state", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Analyzing;", "CameraPreview", "isFlashOn", "", "onImageCaptureReady", "Lkotlin/Function1;", "Landroidx/camera/core/ImageCapture;", "onCameraReady", "Landroidx/camera/core/Camera;", "modifier", "Landroidx/compose/ui/Modifier;", "ErrorContent", "message", "", "onRetry", "Lkotlin/Function0;", "ScanLineAnimation", "color", "Landroidx/compose/ui/graphics/Color;", "ScanLineAnimation-8_81llA", "(J)V", "ScannerContent", "Lcom/citrascan/app/ui/screens/scanner/ScannerUiState$Ready;", "cameraPermission", "Lcom/google/accompanist/permissions/PermissionState;", "onBack", "onToggleGuide", "onSetInputMode", "Lcom/citrascan/app/ui/screens/scanner/InputMode;", "onSetScanMode", "Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;", "onCapture", "Landroid/graphics/Bitmap;", "onPickGallery", "ScannerScreen", "onNavigateBack", "onNavigateToResult", "viewModel", "Lcom/citrascan/app/ui/screens/scanner/ScannerViewModel;", "ViewfinderCorners", "accentColor", "ViewfinderCorners-8_81llA", "app_debug"})
public final class ScannerScreenKt {
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    public static final void ScannerScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigateToResult, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.ui.screens.scanner.ScannerViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    private static final void ScannerContent(com.citrascan.app.ui.screens.scanner.ScannerUiState.Ready state, com.google.accompanist.permissions.PermissionState cameraPermission, kotlin.jvm.functions.Function0<kotlin.Unit> onBack, kotlin.jvm.functions.Function0<kotlin.Unit> onToggleGuide, kotlin.jvm.functions.Function1<? super com.citrascan.app.ui.screens.scanner.InputMode, kotlin.Unit> onSetInputMode, kotlin.jvm.functions.Function1<? super com.citrascan.app.ml.OnnxModelManager.ScanMode, kotlin.Unit> onSetScanMode, kotlin.jvm.functions.Function1<? super android.graphics.Bitmap, kotlin.Unit> onCapture, kotlin.jvm.functions.Function0<kotlin.Unit> onPickGallery) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CameraPreview(boolean isFlashOn, kotlin.jvm.functions.Function1<? super androidx.camera.core.ImageCapture, kotlin.Unit> onImageCaptureReady, kotlin.jvm.functions.Function1<? super androidx.camera.core.Camera, kotlin.Unit> onCameraReady, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AnalyzingContent(com.citrascan.app.ui.screens.scanner.ScannerUiState.Analyzing state) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ErrorContent(java.lang.String message, kotlin.jvm.functions.Function0<kotlin.Unit> onRetry) {
    }
}