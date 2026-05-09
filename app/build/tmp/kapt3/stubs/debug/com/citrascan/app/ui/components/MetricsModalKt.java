package com.citrascan.app.ui.components;

import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import com.citrascan.app.R;
import com.citrascan.app.data.model.ModelMetrics;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0003\u001a\u001e\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u00a8\u0006\f"}, d2 = {"MetricRow", "", "label", "", "value", "showDivider", "", "MetricsModal", "metrics", "Lcom/citrascan/app/data/model/ModelMetrics;", "onDismiss", "Lkotlin/Function0;", "app_debug"})
public final class MetricsModalKt {
    
    /**
     * Modal dialog showing model performance metrics.
     * Matches the HTML `#m-metrics` modal overlay.
     *
     * @param metrics The model metrics to display.
     * @param onDismiss Callback to close the dialog.
     */
    @androidx.compose.runtime.Composable()
    public static final void MetricsModal(@org.jetbrains.annotations.NotNull()
    com.citrascan.app.data.model.ModelMetrics metrics, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MetricRow(java.lang.String label, java.lang.String value, boolean showDivider) {
    }
}