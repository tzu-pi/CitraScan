package com.citrascan.app.ml;

import android.graphics.Bitmap;
import com.citrascan.app.data.model.Detection;
import com.citrascan.app.data.model.ScanResult;
import com.citrascan.app.data.repository.DiseaseRepository;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Orchestrates the end-to-end detection pipeline:
 * preprocess → ONNX inference → post-process → result mapping.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/citrascan/app/ml/DetectionEngine;", "", "preprocessor", "Lcom/citrascan/app/ml/ImagePreprocessor;", "modelManager", "Lcom/citrascan/app/ml/OnnxModelManager;", "postProcessor", "Lcom/citrascan/app/ml/YoloPostProcessor;", "diseaseRepository", "Lcom/citrascan/app/data/repository/DiseaseRepository;", "(Lcom/citrascan/app/ml/ImagePreprocessor;Lcom/citrascan/app/ml/OnnxModelManager;Lcom/citrascan/app/ml/YoloPostProcessor;Lcom/citrascan/app/data/repository/DiseaseRepository;)V", "detect", "Lcom/citrascan/app/data/model/ScanResult;", "bitmap", "Landroid/graphics/Bitmap;", "scanMode", "Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;", "(Landroid/graphics/Bitmap;Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "determinePrimaryDisease", "", "detections", "", "Lcom/citrascan/app/data/model/Detection;", "app_debug"})
public final class DetectionEngine {
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.ml.ImagePreprocessor preprocessor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.ml.OnnxModelManager modelManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.ml.YoloPostProcessor postProcessor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.repository.DiseaseRepository diseaseRepository = null;
    
    @javax.inject.Inject()
    public DetectionEngine(@org.jetbrains.annotations.NotNull()
    com.citrascan.app.ml.ImagePreprocessor preprocessor, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.ml.OnnxModelManager modelManager, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.ml.YoloPostProcessor postProcessor, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.repository.DiseaseRepository diseaseRepository) {
        super();
    }
    
    /**
     * Runs the full detection pipeline on a captured image.
     *
     * @param bitmap The source image to analyze.
     * @param scanMode Whether the user selected fruit or leaf scanning mode.
     * @return A [ScanResult] containing all detections and metadata.
     * @throws ModelLoadException If the model cannot be loaded.
     * @throws InferenceException If inference fails.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object detect(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.ml.OnnxModelManager.ScanMode scanMode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.citrascan.app.data.model.ScanResult> $completion) {
        return null;
    }
    
    /**
     * Determines the primary disease from a list of detections.
     * Uses the highest-confidence detection. Returns "healthy" if no detections.
     */
    private final java.lang.String determinePrimaryDisease(java.util.List<com.citrascan.app.data.model.Detection> detections) {
        return null;
    }
}