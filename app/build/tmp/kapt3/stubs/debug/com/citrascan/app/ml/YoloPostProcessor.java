package com.citrascan.app.ml;

import com.citrascan.app.data.model.BoundingBox;
import com.citrascan.app.data.model.Detection;
import com.citrascan.app.data.repository.DiseaseRepository;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Post-processes raw YOLOv8 model output into [Detection] objects.
 *
 * YOLOv8 output shape: [1, 7, 8400] where:
 * - 7 = 4 bbox coords (cx, cy, w, h) + 3 class scores
 * - 8400 = number of candidate detections
 *
 * Steps:
 * 1. Parse raw output into candidate detections
 * 2. Filter by confidence threshold
 * 3. Apply Non-Maximum Suppression (NMS)
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/citrascan/app/ml/YoloPostProcessor;", "", "diseaseRepository", "Lcom/citrascan/app/data/repository/DiseaseRepository;", "(Lcom/citrascan/app/data/repository/DiseaseRepository;)V", "applyNms", "", "Lcom/citrascan/app/data/model/Detection;", "detections", "computeIoU", "", "a", "Lcom/citrascan/app/data/model/BoundingBox;", "b", "process", "output", "", "Companion", "app_debug"})
public final class YoloPostProcessor {
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.repository.DiseaseRepository diseaseRepository = null;
    
    /**
     * Minimum confidence to keep a detection.
     */
    public static final float CONFIDENCE_THRESHOLD = 0.25F;
    
    /**
     * IoU threshold for NMS.
     */
    public static final float IOU_THRESHOLD = 0.45F;
    
    /**
     * Number of classes in the model.
     */
    public static final int NUM_CLASSES = 3;
    
    /**
     * Number of candidate detections from YOLOv8.
     */
    public static final int NUM_DETECTIONS = 8400;
    @org.jetbrains.annotations.NotNull()
    public static final com.citrascan.app.ml.YoloPostProcessor.Companion Companion = null;
    
    @javax.inject.Inject()
    public YoloPostProcessor(@org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.repository.DiseaseRepository diseaseRepository) {
        super();
    }
    
    /**
     * Processes raw model output into a list of detections after NMS.
     *
     * @param output Raw float array from ONNX model, shape [1, 7, 8400].
     * @return List of [Detection] objects passing confidence and NMS filters.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.Detection> process(@org.jetbrains.annotations.NotNull()
    float[] output) {
        return null;
    }
    
    /**
     * Applies Non-Maximum Suppression to remove overlapping detections.
     */
    private final java.util.List<com.citrascan.app.data.model.Detection> applyNms(java.util.List<com.citrascan.app.data.model.Detection> detections) {
        return null;
    }
    
    /**
     * Computes Intersection over Union between two bounding boxes.
     */
    private final float computeIoU(com.citrascan.app.data.model.BoundingBox a, com.citrascan.app.data.model.BoundingBox b) {
        return 0.0F;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/citrascan/app/ml/YoloPostProcessor$Companion;", "", "()V", "CONFIDENCE_THRESHOLD", "", "IOU_THRESHOLD", "NUM_CLASSES", "", "NUM_DETECTIONS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}