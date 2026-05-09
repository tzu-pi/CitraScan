// ui/screens/result/ResultScreen.kt
package com.citrascan.app.ui.screens.result

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

@Composable
fun ResultScreen(
    onNavigateBack: () -> Unit,
    onScanAgain: () -> Unit,
    viewModel: ResultViewModel = hiltViewModel()
) {
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
                stringResource(R.string.result_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.3).sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(36.dp))
        }

        // Result hero — matches .res-hero
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
            ConfidenceBar(
                confidence = state.actualConfidence ?: disease.defaultConfidence,
                barColor = disease.barColor
            )
        }

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            // About card — matches .rcard
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

            // Recommended actions card
            GlassCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(horizontal = 17.dp, vertical = 15.dp)) {
                    Text(
                        stringResource(R.string.result_actions_label),
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
                            // Step number — matches .step-n
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

            // Detection details card
            GlassCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(horizontal = 17.dp, vertical = 15.dp)) {
                    Text(
                        stringResource(R.string.result_details_label),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        ),
                        color = extra.accent.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 9.dp)
                    )
                    DetailRow(stringResource(R.string.detail_model), stringResource(R.string.result_model))
                    DetailRow(stringResource(R.string.detail_inference), disease.inferenceTime)
                    DetailRow(stringResource(R.string.detail_resolution), stringResource(R.string.result_resolution))
                    DetailRow(stringResource(R.string.detail_bounding_boxes), disease.boundingBoxCount, showDivider = false)
                }
            }

            // Buttons — matches .res-btns
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                // Primary — matches .rb-p
                Button(
                    onClick = onScanAgain,
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ForestGreen,
                        contentColor = BoneWhite
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        stringResource(R.string.result_scan_again),
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }
                // Ghost — matches .rb-g
                OutlinedButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.5.dp, extra.accentBorder),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = extra.accent)
                ) {
                    Text(
                        stringResource(R.string.result_go_home),
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String, showDivider: Boolean = true) {
    val extra = CitraScanTheme.extraColors
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.ExtraBold),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    if (showDivider) {
        HorizontalDivider(
            thickness = 1.5.dp,
            color = extra.glassBorder
        )
    }
}
