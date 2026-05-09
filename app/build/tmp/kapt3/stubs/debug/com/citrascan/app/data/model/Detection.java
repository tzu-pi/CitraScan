package com.citrascan.app.data.model;

/**
 * Represents a single object detection from the YOLOv8 model.
 *
 * @property classIndex The predicted class index (0=Canker, 1=Black Spot, 2=Greening).
 * @property className Human-readable class name.
 * @property confidence Detection confidence score in [0, 1].
 * @property boundingBox Normalized bounding box coordinates [x1, y1, x2, y2] in [0, 1].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001d"}, d2 = {"Lcom/citrascan/app/data/model/Detection;", "", "classIndex", "", "className", "", "confidence", "", "boundingBox", "Lcom/citrascan/app/data/model/BoundingBox;", "(ILjava/lang/String;FLcom/citrascan/app/data/model/BoundingBox;)V", "getBoundingBox", "()Lcom/citrascan/app/data/model/BoundingBox;", "getClassIndex", "()I", "getClassName", "()Ljava/lang/String;", "getConfidence", "()F", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class Detection {
    private final int classIndex = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String className = null;
    private final float confidence = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.model.BoundingBox boundingBox = null;
    
    public Detection(int classIndex, @org.jetbrains.annotations.NotNull()
    java.lang.String className, float confidence, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.model.BoundingBox boundingBox) {
        super();
    }
    
    public final int getClassIndex() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getClassName() {
        return null;
    }
    
    public final float getConfidence() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.BoundingBox getBoundingBox() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.BoundingBox component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.Detection copy(int classIndex, @org.jetbrains.annotations.NotNull()
    java.lang.String className, float confidence, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.model.BoundingBox boundingBox) {
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