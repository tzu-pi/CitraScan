// CitraScanApplication.kt — Hilt application entry point
package com.citrascan.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class annotated with [HiltAndroidApp] to enable Hilt dependency injection.
 */
@HiltAndroidApp
class CitraScanApplication : Application()
