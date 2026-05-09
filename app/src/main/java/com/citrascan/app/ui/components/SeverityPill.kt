// ui/components/SeverityPill.kt — Colored severity/treatment badge
package com.citrascan.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.citrascan.app.data.model.Severity
import com.citrascan.app.ui.theme.CitraScanTheme

@Composable
fun SeverityPill(
    text: String,
    severity: Severity,
    modifier: Modifier = Modifier
) {
    val extra = CitraScanTheme.extraColors
    val (bgColor, textColor) = when (severity) {
        Severity.OK -> extra.severityOk.copy(alpha = 0.1f) to extra.severityOk
        Severity.WARN -> extra.severityWarn.copy(alpha = 0.1f) to extra.severityWarn
        Severity.BAD -> extra.severityBad.copy(alpha = 0.1f) to extra.severityBad
    }
    val borderColor = when (severity) {
        Severity.OK -> extra.severityOk.copy(alpha = 0.2f)
        Severity.WARN -> extra.severityWarn.copy(alpha = 0.2f)
        Severity.BAD -> extra.severityBad.copy(alpha = 0.2f)
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = bgColor,
        border = BorderStroke(1.5.dp, borderColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                letterSpacing = 0.3.sp
            ),
            color = textColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
