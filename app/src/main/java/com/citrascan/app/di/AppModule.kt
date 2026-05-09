// di/AppModule.kt — Hilt dependency injection module
package com.citrascan.app.di

import ai.onnxruntime.OrtEnvironment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing application-scoped dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides the ONNX Runtime environment singleton.
     * This is a heavyweight object that should be shared across the app.
     */
    @Provides
    @Singleton
    fun provideOrtEnvironment(): OrtEnvironment = OrtEnvironment.getEnvironment()
}
