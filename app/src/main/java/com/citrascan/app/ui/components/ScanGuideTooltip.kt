// ui/components/ScanGuideTooltip.kt — Scanning guide popover
package com.citrascan.app.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import com.citrascan.app.R
import com.citrascan.app.ui.theme.CitraScanTheme

@Composable
fun ScanGuideTooltip(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    val extra = CitraScanTheme.extraColors

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn() + slideInVertically(initialOffsetY = { 20 }) + scaleIn(initialScale = 0.95f),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { 20 }) + scaleOut(targetScale = 0.95f)
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = extra.modalCard,
            border = BorderStroke(1.5.dp, extra.accentBorder),
            shadowElevation = 15.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.guide_title),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                GuideStep(number = "1", text = stringResource(R.string.guide_step_1))
                Spacer(modifier = Modifier.height(8.dp))
                GuideStep(number = "2", text = stringResource(R.string.guide_step_2))
                Spacer(modifier = Modifier.height(8.dp))
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
                .size(16.dp)
                .clip(CircleShape)
                .background(extra.accentBg),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.sp
                ),
                color = extra.accent
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
