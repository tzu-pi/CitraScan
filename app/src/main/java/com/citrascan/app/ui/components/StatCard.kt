// ui/components/StatCard.kt — Stats grid item matching .stat-card
package com.citrascan.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.citrascan.app.ui.theme.CitraScanTheme

/**
 * A stat card for the home screen grid, matching the HTML `.stat-card` component.
 */
@Composable
fun StatCard(
    value: String,
    label: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val extra = CitraScanTheme.extraColors
    val cardShape = RoundedCornerShape(18.dp)
    val cardColors = CardDefaults.cardColors(containerColor = extra.glass)
    val cardBorder = BorderStroke(1.5.dp, extra.glassBorder)

    val content: @Composable ColumnScope.() -> Unit = {
        Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 14.dp)) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.W600
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = cardShape,
            colors = cardColors,
            border = cardBorder,
            content = content
        )
    } else {
        Card(
            modifier = modifier,
            shape = cardShape,
            colors = cardColors,
            border = cardBorder,
            content = content
        )
    }
}
