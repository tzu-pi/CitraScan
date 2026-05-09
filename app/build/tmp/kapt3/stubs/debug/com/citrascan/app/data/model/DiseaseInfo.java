package com.citrascan.app.data.model;

/**
 * Complete information about a detectable citrus disease.
 * Mirrors the `DB` object from the HTML prototype.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0015J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0012H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003J\t\u00101\u001a\u00020\nH\u00c6\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\u0016\u00103\u001a\u00020\u000fH\u00c6\u0003\u00f8\u0001\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b4\u0010\u0019J\u0016\u00105\u001a\u00020\u000fH\u00c6\u0003\u00f8\u0001\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b6\u0010\u0019J\u0091\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u0003H\u00c6\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b8\u00109J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010=\u001a\u00020>H\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0010\u001a\u00020\u000f\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010\u000e\u001a\u00020\u000f\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b \u0010\u0019R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0017R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006@"}, d2 = {"Lcom/citrascan/app/data/model/DiseaseInfo;", "", "key", "", "name", "subtitle", "about", "severity", "Lcom/citrascan/app/data/model/Severity;", "treatment", "Lcom/citrascan/app/data/model/TreatmentStatus;", "chips", "", "Lcom/citrascan/app/data/model/DiseaseChip;", "dotColor", "Landroidx/compose/ui/graphics/Color;", "barColor", "defaultConfidence", "", "inferenceTime", "boundingBoxCount", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/citrascan/app/data/model/Severity;Lcom/citrascan/app/data/model/TreatmentStatus;Ljava/util/List;JJFLjava/lang/String;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAbout", "()Ljava/lang/String;", "getBarColor-0d7_KjU", "()J", "J", "getBoundingBoxCount", "getChips", "()Ljava/util/List;", "getDefaultConfidence", "()F", "getDotColor-0d7_KjU", "getInferenceTime", "getKey", "getName", "getSeverity", "()Lcom/citrascan/app/data/model/Severity;", "getSubtitle", "getTreatment", "()Lcom/citrascan/app/data/model/TreatmentStatus;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component8-0d7_KjU", "component9", "component9-0d7_KjU", "copy", "copy-0nD-MI0", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/citrascan/app/data/model/Severity;Lcom/citrascan/app/data/model/TreatmentStatus;Ljava/util/List;JJFLjava/lang/String;Ljava/lang/String;)Lcom/citrascan/app/data/model/DiseaseInfo;", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class DiseaseInfo {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String key = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String subtitle = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String about = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.model.Severity severity = null;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.model.TreatmentStatus treatment = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.citrascan.app.data.model.DiseaseChip> chips = null;
    private final long dotColor = 0L;
    private final long barColor = 0L;
    private final float defaultConfidence = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String inferenceTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String boundingBoxCount = null;
    
    private DiseaseInfo(java.lang.String key, java.lang.String name, java.lang.String subtitle, java.lang.String about, com.citrascan.app.data.model.Severity severity, com.citrascan.app.data.model.TreatmentStatus treatment, java.util.List<com.citrascan.app.data.model.DiseaseChip> chips, long dotColor, long barColor, float defaultConfidence, java.lang.String inferenceTime, java.lang.String boundingBoxCount) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSubtitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAbout() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.Severity getSeverity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.TreatmentStatus getTreatment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.DiseaseChip> getChips() {
        return null;
    }
    
    public final float getDefaultConfidence() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInferenceTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBoundingBoxCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final float component10() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.Severity component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.TreatmentStatus component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.DiseaseChip> component7() {
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