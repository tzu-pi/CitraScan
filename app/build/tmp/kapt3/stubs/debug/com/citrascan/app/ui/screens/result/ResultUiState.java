package com.citrascan.app.ui.screens.result;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.citrascan.app.data.model.DiseaseInfo;
import com.citrascan.app.data.model.SeverityActions;
import com.citrascan.app.data.repository.DiseaseRepository;
import com.citrascan.app.data.repository.ScanHistoryRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

/**
 * UI state for the result screen.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u0017\u001a\u00020\nH\u00c6\u0003J@\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lcom/citrascan/app/ui/screens/result/ResultUiState;", "", "disease", "Lcom/citrascan/app/data/model/DiseaseInfo;", "actions", "", "", "actualConfidence", "", "isLoading", "", "(Lcom/citrascan/app/data/model/DiseaseInfo;Ljava/util/List;Ljava/lang/Float;Z)V", "getActions", "()Ljava/util/List;", "getActualConfidence", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getDisease", "()Lcom/citrascan/app/data/model/DiseaseInfo;", "()Z", "component1", "component2", "component3", "component4", "copy", "(Lcom/citrascan/app/data/model/DiseaseInfo;Ljava/util/List;Ljava/lang/Float;Z)Lcom/citrascan/app/ui/screens/result/ResultUiState;", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class ResultUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.citrascan.app.data.model.DiseaseInfo disease = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> actions = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float actualConfidence = null;
    private final boolean isLoading = false;
    
    public ResultUiState(@org.jetbrains.annotations.Nullable()
    com.citrascan.app.data.model.DiseaseInfo disease, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> actions, @org.jetbrains.annotations.Nullable()
    java.lang.Float actualConfidence, boolean isLoading) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.citrascan.app.data.model.DiseaseInfo getDisease() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getActions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getActualConfidence() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public ResultUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.citrascan.app.data.model.DiseaseInfo component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.citrascan.app.ui.screens.result.ResultUiState copy(@org.jetbrains.annotations.Nullable()
    com.citrascan.app.data.model.DiseaseInfo disease, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> actions, @org.jetbrains.annotations.Nullable()
    java.lang.Float actualConfidence, boolean isLoading) {
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