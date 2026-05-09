// ui/components/BottomNavBar.kt — 3-tab bottom navigation
package com.citrascan.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.citrascan.app.R
import com.citrascan.app.ui.theme.CitraScanTheme
import com.citrascan.app.ui.theme.NavInactive

/**
 * Bottom navigation bar with Home, Scan, and History tabs.
 * Matches the HTML `.bnav` component.
 */
@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val extra = CitraScanTheme.extraColors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(extra.navBar)
            .navigationBarsPadding()
            .padding(top = 9.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavItem(
            icon = Icons.Outlined.Home,
            label = stringResource(R.string.nav_home),
            isSelected = currentRoute == "home",
            onClick = { onNavigate("home") }
        )
        NavItem(
            icon = Icons.Outlined.CameraAlt,
            label = stringResource(R.string.nav_scan),
            isSelected = currentRoute == "scanner",
            onClick = { onNavigate("scanner") }
        )
        NavItem(
            icon = Icons.Outlined.Description,
            label = stringResource(R.string.nav_history),
            isSelected = currentRoute == "history",
            showBadge = currentRoute != "history",
            onClick = { onNavigate("history") }
        )
    }
}

@Composable
private fun NavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    showBadge: Boolean = false
) {
    val extra = CitraScanTheme.extraColors
    val color = if (isSelected) extra.accent else NavInactive.copy(alpha = 0.6f)

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(20.dp),
            tint = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                letterSpacing = 0.2.sp
            ),
            color = color
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(extra.accent)
            )
        }
    }
}
