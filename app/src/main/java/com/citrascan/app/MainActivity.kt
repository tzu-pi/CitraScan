// MainActivity.kt — Single activity hosting the Compose navigation graph
package com.citrascan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.citrascan.app.ui.navigation.CitraScanNavGraph
import com.citrascan.app.ui.theme.CitraScanTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single activity entry point for CitraScan.
 * Hosts the Compose UI and navigation graph.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkMode by remember { mutableStateOf(systemDark) }

            CitraScanTheme(darkTheme = isDarkMode) {
                CitraScanNavGraph(
                    isDarkMode = isDarkMode,
                    onToggleTheme = { isDarkMode = !isDarkMode }
                )
            }
        }
    }
}
