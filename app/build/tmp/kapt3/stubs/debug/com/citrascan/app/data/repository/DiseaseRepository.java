package com.citrascan.app.data.repository;

import com.citrascan.app.data.model.*;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Provides static disease information data.
 * Mirrors the `DB` JavaScript object from the HTML prototype.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\fJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0005J\u0006\u0010\u0010\u001a\u00020\u0011R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/citrascan/app/data/repository/DiseaseRepository;", "", "()V", "diseases", "", "", "Lcom/citrascan/app/data/model/DiseaseInfo;", "classIndexToKey", "classIndex", "", "classIndexToName", "getAllDiseases", "", "getDetectableDiseases", "getDiseaseByKey", "key", "getModelMetrics", "Lcom/citrascan/app/data/model/ModelMetrics;", "app_debug"})
public final class DiseaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.citrascan.app.data.model.DiseaseInfo> diseases = null;
    
    @javax.inject.Inject()
    public DiseaseRepository() {
        super();
    }
    
    /**
     * Returns all disease info entries as a list.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.DiseaseInfo> getAllDiseases() {
        return null;
    }
    
    /**
     * Returns the detectable diseases (excluding healthy).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.DiseaseInfo> getDetectableDiseases() {
        return null;
    }
    
    /**
     * Returns disease info by key, or null if not found.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.citrascan.app.data.model.DiseaseInfo getDiseaseByKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    /**
     * Returns the model performance metrics from YOLOv8s training evaluation.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.ModelMetrics getModelMetrics() {
        return null;
    }
    
    /**
     * Maps a class index from the ONNX model to a disease key.
     * Class indices: 0=Canker, 1=Black Spot, 2=Greening (HLB).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String classIndexToKey(int classIndex) {
        return null;
    }
    
    /**
     * Maps a class index to a human-readable class name.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String classIndexToName(int classIndex) {
        return null;
    }
}