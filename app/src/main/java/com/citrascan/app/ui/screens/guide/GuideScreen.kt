// ui/screens/guide/GuideScreen.kt — Informational guide for diseases
package com.citrascan.app.ui.screens.guide

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.citrascan.app.ui.screens.result.ResultViewModel

@Composable
fun GuideScreen(
    diseaseKey: String,
    onNavigateBack: () -> Unit,
    onScanNow: () -> Unit,
    viewModel: ResultViewModel = hiltViewModel()
) {
    // We reuse ResultViewModel as it already handles fetching disease info by key
    LaunchedEffect(diseaseKey) {
        // If we needed to force a specific disease, we could do it here, 
        // but ResultViewModel likely gets it from SavedStateHandle
    }

    val state by viewModel.uiState.collectAsState()
    val extra = CitraScanTheme.extraColors
    val disease = state.disease ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        // Nav header — matches .nav-hdr
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(extra.accentBg)
                    .border(1.5.dp, extra.accentBorder, CircleShape)
                    .clickable(onClick = onNavigateBack),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.ArrowBackIos, null, Modifier.size(16.dp).padding(start = 3.dp), tint = extra.accent)
            }
            Text(
                stringResource(R.string.guide_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.3).sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(36.dp))
        }

        // Guide hero
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(bottom = 9.dp)) {
                SeverityPill(text = disease.severity.label, severity = disease.severity)
                SeverityPill(text = disease.treatment.label, severity = disease.severity)
            }
            Text(
                disease.name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp,
                    lineHeight = 28.sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 9.dp)
            )
            // No confidence bar in guide mode
        }

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            // About card
            GlassCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(horizontal = 17.dp, vertical = 15.dp)) {
                    Text(
                        stringResource(R.string.result_about_label),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        ),
                        color = extra.accent.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 9.dp)
                    )
                    Text(
                        disease.about,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            lineHeight = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (disease.chips.isNotEmpty()) {
                        Row(
                            modifier = Modifier.padding(top = 9.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            disease.chips.forEach { chip ->
                                SeverityPill(text = chip.text, severity = chip.severity)
                            }
                        }
                    }
                }
            }

            // Prevention & Control card (reusing Actions label)
            GlassCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(horizontal = 17.dp, vertical = 15.dp)) {
                    Text(
                        stringResource(R.string.guide_prevention_label),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        ),
                        color = extra.accent.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 9.dp)
                    )
                    state.actions.forEachIndexed { i, action ->
                        Row(
                            modifier = Modifier.padding(bottom = 9.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
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
                                    "${i + 1}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.ExtraBold
                                    ),
                                    color = extra.accent
                                )
                            }
                            Text(
                                action,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 20.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Info buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                Button(
                    onClick = onScanNow,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ForestGreen,
                        contentColor = BoneWhite
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        stringResource(R.string.guide_scan_now),
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }
            }
        }
    }
}
