package com.citrascan.app.data.model;

/**
 * Recommended actions grouped by severity level.
 * Mirrors the `SeverityActions` object from the HTML prototype.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\r\u001a\u00020\u000eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007\u00a8\u0006\u000f"}, d2 = {"Lcom/citrascan/app/data/model/SeverityActions;", "", "()V", "bad", "", "", "getBad", "()Ljava/util/List;", "ok", "getOk", "warn", "getWarn", "forSeverity", "severity", "Lcom/citrascan/app/data/model/Severity;", "app_debug"})
public final class SeverityActions {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> ok = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> warn = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> bad = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.citrascan.app.data.model.SeverityActions INSTANCE = null;
    
    private SeverityActions() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getOk() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getWarn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getBad() {
        return null;
    }
    
    /**
     * Returns the action list for the given severity.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> forSeverity(@org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.model.Severity severity) {
        return null;
    }
}