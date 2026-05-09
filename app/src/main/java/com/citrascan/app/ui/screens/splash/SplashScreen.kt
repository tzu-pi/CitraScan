// ui/screens/splash/SplashScreen.kt — Splash/onboarding screen
package com.citrascan.app.ui.screens.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.citrascan.app.R
import com.citrascan.app.ui.theme.CitraScanTheme
import com.citrascan.app.ui.theme.ForestGreen

/**
 * Splash/onboarding screen matching the HTML `s-splash`.
 * Displays the app logo, headline, and CTA buttons.
 */
@Composable
fun SplashScreen(
    onGetStarted: () -> Unit,
    onViewHistory: () -> Unit
) {
    val extra = CitraScanTheme.extraColors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo icon — matches HTML .splash-icon
        Box(
            modifier = Modifier
                .padding(top = 24.dp)
                .size(72.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(extra.accentBg)
                .border(1.5.dp, extra.accentBorder, RoundedCornerShape(22.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Eco,
                contentDescription = "CitraScan",
                tint = extra.accent,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Content section
        Column {
            // Eyebrow — matches .splash-eyebrow
            Text(
                text = stringResource(R.string.splash_eyebrow).uppercase(),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = androidx.compose.ui.unit.TextUnit(0.9f, androidx.compose.ui.unit.TextUnitType.Sp)
                ),
                color = extra.accent,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Headline with styled "early."
            val headline = buildAnnotatedString {
                append("Detect\n")
                withStyle(SpanStyle(color = extra.accent.copy(alpha = 0.8f))) {
                    append("early.")
                }
                append("\nHarvest\nbetter.")
            }
            Text(
                text = headline,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // Description
            Text(
                text = stringResource(R.string.splash_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            // Primary CTA — matches .splash-cta
            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ForestGreen,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 4.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.splash_cta),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Ghost button — matches .splash-ghost
            OutlinedButton(
                onClick = onViewHistory,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = extra.accent
                ),
                border = BorderStroke(1.5.dp, extra.accentBorder)
            ) {
                Text(
                    text = stringResource(R.string.splash_ghost),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W600
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
