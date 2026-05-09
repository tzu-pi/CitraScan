// ui/components/ConfidenceBar.kt — Animated confidence progress bar
package com.citrascan.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.citrascan.app.ui.theme.CitraScanTheme

/**
 * An animated confidence bar matching the HTML `.conf-row` component.
 *
 * @param confidence Confidence value as a float (0.0–1.0).
 * @param barColor Color of the filled portion.
 * @param modifier Modifier.
 */
@Composable
fun ConfidenceBar(
    confidence: Float,
    barColor: Color,
    modifier: Modifier = Modifier
) {
    val extra = CitraScanTheme.extraColors
    var targetProgress by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 1200),
        label = "confidence_animation"
    )

    LaunchedEffect(confidence) {
        targetProgress = confidence.coerceIn(0f, 1f)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Track
        Box(
            modifier = Modifier
                .weight(1f)
                .height(5.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(extra.accent.copy(alpha = 0.1f))
        ) {
            // Fill
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress)
                    .clip(RoundedCornerShape(2.dp))
                    .background(barColor)
            )
        }

        // Percentage text — 2 decimal places
        Text(
            text = "%.2f".format(confidence),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
            ),
            color = extra.accent
        )
    }
}
