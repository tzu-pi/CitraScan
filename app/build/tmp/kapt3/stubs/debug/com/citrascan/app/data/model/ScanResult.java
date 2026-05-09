package com.citrascan.app.data.model;

import android.graphics.Bitmap;

/**
 * Complete result of a scan operation.
 *
 * @property diseaseKey Key identifier matching [DiseaseInfo.key].
 * @property detections List of individual bounding box detections.
 * @property inferenceTimeMs Inference duration in milliseconds.
 * @property capturedImage The original captured bitmap (nullable if from gallery).
 * @property timestamp Unix timestamp of when the scan was performed.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003JC\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014\u00a8\u0006\""}, d2 = {"Lcom/citrascan/app/data/model/ScanResult;", "", "diseaseKey", "", "detections", "", "Lcom/citrascan/app/data/model/Detection;", "inferenceTimeMs", "", "capturedImage", "Landroid/graphics/Bitmap;", "timestamp", "(Ljava/lang/String;Ljava/util/List;JLandroid/graphics/Bitmap;J)V", "getCapturedImage", "()Landroid/graphics/Bitmap;", "getDetections", "()Ljava/util/List;", "getDiseaseKey", "()Ljava/lang/String;", "getInferenceTimeMs", "()J", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class ScanResult {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String diseaseKey = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.citrascan.app.data.model.Detection> detections = null;
    private final long inferenceTimeMs = 0L;
    @org.jetbrains.annotations.Nullable()
    private final android.graphics.Bitmap capturedImage = null;
    private final long timestamp = 0L;
    
    public ScanResult(@org.jetbrains.annotations.NotNull()
    java.lang.String diseaseKey, @org.jetbrains.annotations.NotNull()
    java.util.List<com.citrascan.app.data.model.Detection> detections, long inferenceTimeMs, @org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap capturedImage, long timestamp) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDiseaseKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.Detection> getDetections() {
        return null;
    }
    
    public final long getInferenceTimeMs() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap getCapturedImage() {
        return null;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.Detection> component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap component4() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.ScanResult copy(@org.jetbrains.annotations.NotNull()
    java.lang.String diseaseKey, @org.jetbrains.annotations.NotNull()
    java.util.List<com.citrascan.app.data.model.Detection> detections, long inferenceTimeMs, @org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap capturedImage, long timestamp) {
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