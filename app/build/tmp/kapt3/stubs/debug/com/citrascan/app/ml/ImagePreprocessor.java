package com.citrascan.app.ml;

import android.graphics.Bitmap;
import java.nio.FloatBuffer;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Preprocesses camera/gallery images for YOLOv8 inference.
 *
 * Performs:
 * 1. Resize to 640×640
 * 2. Normalize pixel values to [0, 1]
 * 3. Convert from HWC to CHW layout (channels first)
 * 4. Pack into a FloatBuffer suitable for OnnxTensor creation
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\n"}, d2 = {"Lcom/citrascan/app/ml/ImagePreprocessor;", "", "()V", "getInputShape", "", "preprocess", "Ljava/nio/FloatBuffer;", "bitmap", "Landroid/graphics/Bitmap;", "Companion", "app_debug"})
public final class ImagePreprocessor {
    
    /**
     * YOLOv8 input size.
     */
    public static final int INPUT_SIZE = 640;
    
    /**
     * Number of channels (RGB).
     */
    public static final int CHANNELS = 3;
    @org.jetbrains.annotations.NotNull()
    public static final com.citrascan.app.ml.ImagePreprocessor.Companion Companion = null;
    
    @javax.inject.Inject()
    public ImagePreprocessor() {
        super();
    }
    
    /**
     * Converts a Bitmap into a float array in CHW format, normalized to [0, 1].
     *
     * @param bitmap Source image (any size).
     * @return FloatBuffer of shape [1, 3, 640, 640] ready for ONNX Runtime.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.nio.FloatBuffer preprocess(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    /**
     * Returns the input tensor shape for the ONNX model.
     */
    @org.jetbrains.annotations.NotNull()
    public final long[] getInputShape() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/citrascan/app/ml/ImagePreprocessor$Companion;", "", "()V", "CHANNELS", "", "INPUT_SIZE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}