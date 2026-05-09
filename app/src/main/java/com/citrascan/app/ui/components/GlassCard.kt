// ui/components/GlassCard.kt — Glassmorphism card component
package com.citrascan.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.citrascan.app.ui.theme.CitraScanTheme

/**
 * A card with glassmorphism styling matching the HTML `.glass` class.
 *
 * @param modifier Modifier for the card.
 * @param cornerRadius Corner radius in dp (default 20dp matches `.rcard`).
 * @param content Composable content inside the card.
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 20.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val extra = CitraScanTheme.extraColors

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = extra.glass,
        ),
        border = BorderStroke(1.5.dp, extra.glassBorder),
        content = content
    )
}
