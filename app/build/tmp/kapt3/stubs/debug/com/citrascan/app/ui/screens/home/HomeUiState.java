package com.citrascan.app.ui.screens.home;

import androidx.lifecycle.ViewModel;
import com.citrascan.app.data.model.DiseaseInfo;
import com.citrascan.app.data.model.ModelMetrics;
import com.citrascan.app.data.repository.DiseaseRepository;
import com.citrascan.app.data.repository.ScanHistoryRepository;
import com.citrascan.app.util.GreetingHelper;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

/**
 * Holds the UI state for the Home screen.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u0010$\u001a\u00020\rH\u00c6\u0003J\t\u0010%\u001a\u00020\u000fH\u00c6\u0003J_\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00c6\u0001J\u0013\u0010\'\u001a\u00020\r2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020\u0005H\u00d6\u0001J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016\u00a8\u0006+"}, d2 = {"Lcom/citrascan/app/ui/screens/home/HomeUiState;", "", "greeting", "", "totalScans", "", "avgAccuracy", "diseasesFound", "healthyScans", "detectableDiseases", "", "Lcom/citrascan/app/data/model/DiseaseInfo;", "showMetricsModal", "", "metrics", "Lcom/citrascan/app/data/model/ModelMetrics;", "(Ljava/lang/String;ILjava/lang/String;IILjava/util/List;ZLcom/citrascan/app/data/model/ModelMetrics;)V", "getAvgAccuracy", "()Ljava/lang/String;", "getDetectableDiseases", "()Ljava/util/List;", "getDiseasesFound", "()I", "getGreeting", "getHealthyScans", "getMetrics", "()Lcom/citrascan/app/data/model/ModelMetrics;", "getShowMetricsModal", "()Z", "getTotalScans", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class HomeUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String greeting = null;
    private final int totalScans = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String avgAccuracy = null;
    private final int diseasesFound = 0;
    private final int healthyScans = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.citrascan.app.data.model.DiseaseInfo> detectableDiseases = null;
    private final boolean showMetricsModal = false;
    @org.jetbrains.annotations.NotNull()
    private final com.citrascan.app.data.model.ModelMetrics metrics = null;
    
    public HomeUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String greeting, int totalScans, @org.jetbrains.annotations.NotNull()
    java.lang.String avgAccuracy, int diseasesFound, int healthyScans, @org.jetbrains.annotations.NotNull()
    java.util.List<com.citrascan.app.data.model.DiseaseInfo> detectableDiseases, boolean showMetricsModal, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.model.ModelMetrics metrics) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGreeting() {
        return null;
    }
    
    public final int getTotalScans() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAvgAccuracy() {
        return null;
    }
    
    public final int getDiseasesFound() {
        return 0;
    }
    
    public final int getHealthyScans() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.DiseaseInfo> getDetectableDiseases() {
        return null;
    }
    
    public final boolean getShowMetricsModal() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.ModelMetrics getMetrics() {
        return null;
    }
    
    public HomeUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.citrascan.app.data.model.DiseaseInfo> component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.data.model.ModelMetrics component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.ui.screens.home.HomeUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String greeting, int totalScans, @org.jetbrains.annotations.NotNull()
    java.lang.String avgAccuracy, int diseasesFound, int healthyScans, @org.jetbrains.annotations.NotNull()
    java.util.List<com.citrascan.app.data.model.DiseaseInfo> detectableDiseases, boolean showMetricsModal, @org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.model.ModelMetrics metrics) {
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