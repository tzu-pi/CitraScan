// ui/components/HistoryItem.kt — History list item
package com.citrascan.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.citrascan.app.data.model.ScanHistory
import com.citrascan.app.data.model.Severity
import com.citrascan.app.ui.theme.CitraScanTheme
import java.text.SimpleDateFormat
import java.util.*

/**
 * A single history list item matching the HTML `.h-item` component.
 *
 * @param entry The scan history data.
 * @param onClick Click handler for viewing the result.
 * @param modifier Modifier.
 */
@Composable
fun HistoryItem(
    entry: ScanHistory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val extra = CitraScanTheme.extraColors
    val thumbColor = when (entry.severity) {
        Severity.BAD -> extra.severityBad.copy(alpha = 0.1f)
        else -> extra.accent.copy(alpha = 0.1f)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(extra.accent.copy(alpha = 0.03f))
            .clickable(onClick = onClick)
            .padding(horizontal = 15.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {
        // Thumbnail placeholder
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(thumbColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Eco,
                contentDescription = null,
                tint = extra.accent,
                modifier = Modifier.size(24.dp)
            )
        }

        // Name and date
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = entry.diseaseName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = formatDate(entry.timestamp) + " · ${"%.2f".format(entry.confidence)} conf.",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 1.dp)
            )
        }

        // Severity pill
        SeverityPill(
            text = entry.severity.label,
            severity = entry.severity
        )
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    return sdf.format(Date(timestamp))
}
