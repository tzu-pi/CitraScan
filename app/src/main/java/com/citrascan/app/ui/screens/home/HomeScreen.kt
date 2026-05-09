// ui/screens/home/HomeScreen.kt — Main home screen
package com.citrascan.app.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.citrascan.app.R
import com.citrascan.app.ui.components.*
import com.citrascan.app.ui.theme.BoneWhite
import com.citrascan.app.ui.theme.CitraScanTheme
import com.citrascan.app.ui.theme.ForestGreen

@Composable
fun HomeScreen(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToResult: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val extra = CitraScanTheme.extraColors

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {
            // ── Header — matches .home-greet ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        state.greeting,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        stringResource(R.string.greeting_name),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.6).sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                // Theme Toggle — matches .theme-btn
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(extra.accentBg)
                        .border(1.5.dp, extra.accentBorder, CircleShape)
                        .clickable(onClick = onToggleTheme),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isDarkMode) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                        contentDescription = "Toggle theme",
                        tint = extra.accent,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // ── Hero Card — matches .hero.glass ──
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(extra.glass)
                    .border(1.5.dp, extra.glassBorder, RoundedCornerShape(28.dp))
                    .clickable(onClick = onNavigateToScanner)
            ) {
                // Hero top content — matches .hero-top
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp)) {
                    // Model status eyebrow — matches .hero-eye
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(extra.accent.copy(alpha = 0.8f))
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            stringResource(R.string.hero_model_status),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.W600,
                                letterSpacing = 0.5.sp
                            ),
                            color = extra.accent.copy(alpha = 0.75f)
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    // Headline — matches .hero-h
                    Text(
                        stringResource(R.string.hero_headline),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.4).sp,
                            lineHeight = 26.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    // Subtitle — matches .hero-sub
                    Text(
                        stringResource(R.string.hero_subtext),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
                // Scan strip — matches .hero-strip (always ForestGreen)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ForestGreen)
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Camera icon — matches .hss-icon
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Outlined.PhotoCamera,
                            contentDescription = null,
                            tint = BoneWhite,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    // Label — matches .hss-label / .hss-sub
                    Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                        Text(
                            stringResource(R.string.hero_strip_label),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = (-0.2).sp
                            ),
                            color = BoneWhite
                        )
                        Text(
                            stringResource(R.string.hero_strip_sub),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.padding(top = 1.dp)
                        )
                    }
                    // Arrow — matches .hss-arrow
                    Text(
                        "›",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W300),
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }

            // ── Stats Section — matches .sec-head + .stat-row ──
            Text(
                stringResource(R.string.section_this_season),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 20.dp, top = 18.dp, bottom = 8.dp)
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCard(
                    value = state.totalScans.toString(),
                    label = stringResource(R.string.stat_scans_done),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    value = state.avgAccuracy,
                    label = stringResource(R.string.stat_avg_accuracy),
                    onClick = { viewModel.showMetrics() },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCard(
                    value = state.diseasesFound.toString(),
                    label = stringResource(R.string.stat_diseases_found),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    value = state.healthyScans.toString(),
                    label = stringResource(R.string.stat_healthy_scans),
                    modifier = Modifier.weight(1f)
                )
            }

            // ── Detectable Diseases — matches .dis-group (informational) ──
            Text(
                stringResource(R.string.section_detectable),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 20.dp, top = 18.dp, bottom = 8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.5.dp, extra.glassBorder, RoundedCornerShape(20.dp))
            ) {
                state.detectableDiseases.forEachIndexed { index, disease ->
                    DiseaseRow(
                        name = disease.name,
                        subtitle = disease.subtitle,
                        dotColor = disease.dotColor,
                        onClick = { onNavigateToResult(disease.key) }
                    )
                    if (index < state.detectableDiseases.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 1.5.dp,
                            color = extra.glassBorder
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }

        // ── Bottom nav ──
        BottomNavBar(
            currentRoute = "home",
            onNavigate = { route ->
                when (route) {
                    "scanner" -> onNavigateToScanner()
                    "history" -> onNavigateToHistory()
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        // Metrics modal
        if (state.showMetricsModal) {
            MetricsModal(
                metrics = state.metrics,
                onDismiss = { viewModel.hideMetrics() }
            )
        }
    }
}
