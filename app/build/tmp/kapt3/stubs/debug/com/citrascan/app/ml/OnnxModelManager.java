package com.citrascan.app.ml;

import android.content.Context;
import android.util.Log;
import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.nio.FloatBuffer;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Manages ONNX Runtime model loading and inference sessions.
 *
 * Models are copied from assets to internal storage on first use,
 * then loaded from the file system for reliable, memory-efficient access.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/citrascan/app/ml/OnnxModelManager;", "", "context", "Landroid/content/Context;", "ortEnvironment", "Lai/onnxruntime/OrtEnvironment;", "(Landroid/content/Context;Lai/onnxruntime/OrtEnvironment;)V", "createModelSession", "Lai/onnxruntime/OrtSession;", "assetPath", "", "getModelFile", "Ljava/io/File;", "runInference", "", "inputBuffer", "Ljava/nio/FloatBuffer;", "inputShape", "", "scanMode", "Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;", "Companion", "ScanMode", "app_debug"})
public final class OnnxModelManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final ai.onnxruntime.OrtEnvironment ortEnvironment = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "OnnxModelManager";
    
    /**
     * Fruit detection model filename.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FRUITS_MODEL = "models/citrus_yolov8s_fruits_best.onnx";
    
    /**
     * Leaf detection model filename.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LEAVES_MODEL = "models/citrus_yolov8s_leaves_best.onnx";
    @org.jetbrains.annotations.NotNull()
    public static final com.citrascan.app.ml.OnnxModelManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public OnnxModelManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    ai.onnxruntime.OrtEnvironment ortEnvironment) {
        super();
    }
    
    /**
     * Copies the model from assets to internal storage if it doesn't already exist
     * or if the existing file is empty/corrupted, then returns the file.
     */
    private final java.io.File getModelFile(java.lang.String assetPath) {
        return null;
    }
    
    /**
     * Creates an ONNX session, trying file-path loading first, then byte-array fallback.
     */
    private final ai.onnxruntime.OrtSession createModelSession(java.lang.String assetPath) {
        return null;
    }
    
    /**
     * Runs inference on the given input buffer using the specified model.
     *
     * @param inputBuffer Preprocessed image data as a FloatBuffer.
     * @param inputShape Shape of the input tensor (e.g., [1, 3, 640, 640]).
     * @param scanMode Whether to use the fruit or leaf model.
     * @return Raw float array output from the model.
     * @throws ModelLoadException If the model file is missing or fails to load.
     * @throws InferenceException If inference execution fails.
     */
    @org.jetbrains.annotations.NotNull()
    public final float[] runInference(@org.jetbrains.annotations.NotNull()
    java.nio.FloatBuffer inputBuffer, @org.jetbrains.annotations.NotNull()
    long[] inputShape, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.ml.OnnxModelManager.ScanMode scanMode) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/citrascan/app/ml/OnnxModelManager$Companion;", "", "()V", "FRUITS_MODEL", "", "LEAVES_MODEL", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    /**
     * The scan mode determining which model to load.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/citrascan/app/ml/OnnxModelManager$ScanMode;", "", "(Ljava/lang/String;I)V", "FRUIT", "LEAF", "app_debug"})
    public static enum ScanMode {
        /*public static final*/ FRUIT /* = new FRUIT() */,
        /*public static final*/ LEAF /* = new LEAF() */;
        
        ScanMode() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.citrascan.app.ml.OnnxModelManager.ScanMode> getEntries() {
            return null;
        }
    }
}