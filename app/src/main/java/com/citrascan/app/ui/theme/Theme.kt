// ui/theme/Theme.kt — CitraScan Material3 theme with light/dark support
package com.citrascan.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class CitraScanColors(
    val glass: Color,
    val glassBorder: Color,
    val accent: Color,
    val accentDark: Color,
    val accentBg: Color,
    val accentBorder: Color,
    val navBar: Color,
    val severityOk: Color,
    val severityWarn: Color,
    val severityBad: Color,
    val modalCard: Color,
)

val LocalCitraScanColors = compositionLocalOf {
    CitraScanColors(
        glass = GlassLight,
        glassBorder = GlassBorderLight,
        accent = ForestGreen,
        accentDark = ForestGreenDark,
        accentBg = AccentBgLight,
        accentBorder = AccentBorderLight,
        navBar = NavBarLight,
        severityOk = SeverityOk,
        severityWarn = SeverityWarn,
        severityBad = SeverityBad,
        modalCard = BoneWhite,
    )
}

private val LightColorScheme = lightColorScheme(
    primary = ForestGreen,
    onPrimary = BoneWhite,
    primaryContainer = AccentBgLight,
    onPrimaryContainer = ForestGreen,
    background = BoneWhite,
    onBackground = TextPrimary,
    surface = BoneWhite,
    onSurface = TextPrimary,
    surfaceVariant = Color(0xFFF3F3EC),
    onSurfaceVariant = TextSecondary,
    secondary = ForestGreen,
    onSecondary = BoneWhite,
    error = SeverityBad,
    onError = Color.White,
    outline = GlassBorderLight,
    outlineVariant = Color(0x14386641),
)

private val DarkColorScheme = darkColorScheme(
    primary = BrightLime,
    onPrimary = MidnightForest,
    primaryContainer = AccentBgDark,
    onPrimaryContainer = BrightLime,
    background = MidnightForest,
    onBackground = TextPrimaryDark,
    surface = MidnightForest,
    onSurface = TextPrimaryDark,
    surfaceVariant = Color(0xFF1A231C),
    onSurfaceVariant = TextSecondaryDark,
    secondary = BrightLime,
    onSecondary = MidnightForest,
    error = SeverityBad,
    onError = Color.White,
    outline = GlassBorderDark,
    outlineVariant = Color(0x1A4ADE80),
)

private val LightExtraColors = CitraScanColors(
    glass = GlassLight,
    glassBorder = GlassBorderLight,
    accent = ForestGreen,
    accentDark = ForestGreenDark,
    accentBg = AccentBgLight,
    accentBorder = AccentBorderLight,
    navBar = NavBarLight,
    severityOk = SeverityOk,
    severityWarn = SeverityWarn,
    severityBad = SeverityBad,
    modalCard = BoneWhite,
)

private val DarkExtraColors = CitraScanColors(
    glass = GlassDark,
    glassBorder = GlassBorderDark,
    accent = BrightLime,
    accentDark = ForestGreenDark, // Still deep green for better contrast in cards
    accentBg = AccentBgDark,
    accentBorder = AccentBorderDark,
    navBar = NavBarDark,
    severityOk = BrightLime,
    severityWarn = BrightLime,
    severityBad = SeverityBad,
    modalCard = ModalCardDark,
)

@Composable
fun CitraScanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extraColors = if (darkTheme) DarkExtraColors else LightExtraColors

    CompositionLocalProvider(LocalCitraScanColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CitraScanTypography,
            content = content
        )
    }
}

object CitraScanTheme {
    val extraColors: CitraScanColors
        @Composable
        get() = LocalCitraScanColors.current
}
