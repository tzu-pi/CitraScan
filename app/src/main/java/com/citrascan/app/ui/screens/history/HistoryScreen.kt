// ui/screens/history/HistoryScreen.kt
package com.citrascan.app.ui.screens.history

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Search
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
import com.citrascan.app.ui.components.BottomNavBar
import com.citrascan.app.ui.components.HistoryItem
import com.citrascan.app.ui.theme.CitraScanTheme

@Composable
fun HistoryScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToScanner: () -> Unit,
    onNavigateToResult: (String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val extra = CitraScanTheme.extraColors

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Header — matches home-greet style
            Text(
                stringResource(R.string.history_title),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.6).sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
            )

            // Search bar — matches .hist-search
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(extra.accentBg)
                    .border(1.5.dp, extra.accentBorder, RoundedCornerShape(13.dp))
                    .padding(horizontal = 13.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Search, null,
                    modifier = Modifier.size(13.dp),
                    tint = extra.accent.copy(alpha = 0.7f)
                )
                TextField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.onSearchChanged(it) },
                    placeholder = {
                        Text(
                            stringResource(R.string.history_search_placeholder),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        )
                    },
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = extra.accent
                    ),
                    singleLine = true
                )
            }

            Spacer(Modifier.height(12.dp))

            // History list
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 100.dp)
            ) {
                if (state.entries.isEmpty()) {
                    // Empty state
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(extra.accentBg)
                                .border(1.5.dp, extra.accentBorder, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Outlined.Description, null,
                                modifier = Modifier.size(21.dp),
                                tint = extra.accent.copy(alpha = 0.5f)
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "No scans yet",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            "Your scan history will appear here.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                } else {
                    Text(
                        stringResource(R.string.history_section_recent),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                    // History group — matches .hist-group
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(1.5.dp, extra.glassBorder, RoundedCornerShape(20.dp))
                    ) {
                        state.entries.forEachIndexed { index, entry ->
                            HistoryItem(entry = entry, onClick = { onNavigateToResult(entry.diseaseKey) })
                            if (index < state.entries.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    thickness = 1.5.dp,
                                    color = extra.glassBorder
                                )
                            }
                        }
                    }
                }
            }
        }

        // Bottom nav
        BottomNavBar(
            currentRoute = "history",
            onNavigate = { route ->
                when (route) {
                    "home" -> onNavigateToHome()
                    "scanner" -> onNavigateToScanner()
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
