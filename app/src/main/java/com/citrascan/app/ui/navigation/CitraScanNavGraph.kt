// ui/navigation/CitraScanNavGraph.kt
package com.citrascan.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.citrascan.app.ui.screens.history.HistoryScreen
import com.citrascan.app.ui.screens.home.HomeScreen
import com.citrascan.app.ui.screens.result.ResultScreen
import com.citrascan.app.ui.screens.guide.GuideScreen
import com.citrascan.app.ui.screens.scanner.ScannerScreen
import com.citrascan.app.ui.screens.splash.SplashScreen

/**
 * Navigation routes for CitraScan.
 */
object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val SCANNER = "scanner"
    const val HISTORY = "history"
    const val RESULT = "result/{diseaseKey}"
    const val GUIDE = "guide/{diseaseKey}"

    fun result(diseaseKey: String) = "result/$diseaseKey"
    fun guide(diseaseKey: String) = "guide/$diseaseKey"
}

/**
 * Main navigation graph composable hosting all screens.
 *
 * @param isDarkMode Current dark mode state.
 * @param onToggleTheme Callback to toggle the theme.
 */
@Composable
fun CitraScanNavGraph(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onGetStarted = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },
                onViewHistory = {
                    navController.navigate(Routes.HISTORY) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                isDarkMode = isDarkMode,
                onToggleTheme = onToggleTheme,
                onNavigateToScanner = { navController.navigate(Routes.SCANNER) },
                onNavigateToHistory = { navController.navigate(Routes.HISTORY) },
                onNavigateToGuide = { key -> navController.navigate(Routes.guide(key)) }
            )
        }

        composable(Routes.SCANNER) {
            ScannerScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToResult = { key ->
                    navController.navigate(Routes.result(key)) {
                        popUpTo(Routes.SCANNER) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.RESULT,
            arguments = listOf(navArgument("diseaseKey") { type = NavType.StringType })
        ) {
            ResultScreen(
                onNavigateBack = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onScanAgain = {
                    navController.navigate(Routes.SCANNER) {
                        popUpTo(Routes.HOME)
                    }
                }
            )
        }

        composable(Routes.HISTORY) {
            HistoryScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onNavigateToScanner = { navController.navigate(Routes.SCANNER) },
                onNavigateToResult = { key -> navController.navigate(Routes.result(key)) }
            )
        }
        composable(
            route = Routes.GUIDE,
            arguments = listOf(navArgument("diseaseKey") { type = NavType.StringType })
        ) { backStackEntry ->
            val diseaseKey = backStackEntry.arguments?.getString("diseaseKey") ?: ""
            GuideScreen(
                diseaseKey = diseaseKey,
                onNavigateBack = { navController.popBackStack() },
                onScanNow = {
                    navController.navigate(Routes.SCANNER) {
                        popUpTo(Routes.HOME)
                    }
                }
            )
        }
    }
}
