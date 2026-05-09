// ui/components/MetricsModal.kt — Model performance dialog
package com.citrascan.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.citrascan.app.R
import com.citrascan.app.data.model.ModelMetrics
import com.citrascan.app.ui.theme.CitraScanTheme

/**
 * Modal dialog showing model performance metrics.
 * Matches the HTML `#m-metrics` modal overlay.
 *
 * @param metrics The model metrics to display.
 * @param onDismiss Callback to close the dialog.
 */
@Composable
fun MetricsModal(
    metrics: ModelMetrics,
    onDismiss: () -> Unit
) {
    val extra = CitraScanTheme.extraColors

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = extra.modalCard,
            border = androidx.compose.foundation.BorderStroke(1.5.dp, extra.accentBorder),
            shadowElevation = 25.dp
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                // Header with close button
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.metrics_title),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(extra.accentBg)
                            .clickable(onClick = onDismiss),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "×",
                            style = MaterialTheme.typography.headlineSmall,
                            color = extra.accent
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                MetricRow(
                    label = stringResource(R.string.metric_precision),
                    value = metrics.precision
                )
                MetricRow(
                    label = stringResource(R.string.metric_recall),
                    value = metrics.recall
                )
                MetricRow(
                    label = stringResource(R.string.metric_f1),
                    value = metrics.f1Score
                )
                MetricRow(
                    label = stringResource(R.string.metric_map),
                    value = metrics.mAP,
                    showDivider = false
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.metrics_footnote),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun MetricRow(
    label: String,
    value: String,
    showDivider: Boolean = true
) {
    val extra = CitraScanTheme.extraColors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.W600
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = extra.accent
        )
    }

    if (showDivider) {
        HorizontalDivider(
            thickness = 1.5.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f)
        )
    }
}
