// ui/components/ScanGuideModal.kt — Scanning guide modal dialog
package com.citrascan.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.citrascan.app.R
import com.citrascan.app.ui.theme.CitraScanTheme

/**
 * Scanning guide presented as a full modal dialog.
 * Replaces the old tooltip approach for better usability.
 */
@Composable
fun ScanGuideModal(
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
                // Header with close
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.guide_title),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.5.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(extra.accentBg)
                            .border(1.5.dp, extra.accentBorder, CircleShape)
                            .clickable(onClick = onDismiss),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("×", style = MaterialTheme.typography.titleMedium, color = extra.accent)
                    }
                }

                Spacer(Modifier.height(16.dp))

                GuideStep(number = "1", text = stringResource(R.string.guide_step_1))
                Spacer(Modifier.height(12.dp))
                GuideStep(number = "2", text = stringResource(R.string.guide_step_2))
                Spacer(Modifier.height(12.dp))
                GuideStep(number = "3", text = stringResource(R.string.guide_step_3))
            }
        }
    }
}

@Composable
private fun GuideStep(number: String, text: String) {
    val extra = CitraScanTheme.extraColors

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(extra.accentBg)
                .border(1.5.dp, extra.accentBorder, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                color = extra.accent
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                lineHeight = 18.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
    }
}
