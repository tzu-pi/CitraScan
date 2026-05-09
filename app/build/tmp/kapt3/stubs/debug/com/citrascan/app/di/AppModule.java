package com.citrascan.app.di;

import ai.onnxruntime.OrtEnvironment;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/**
 * Hilt module providing application-scoped dependencies.
 */
@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007\u00a8\u0006\u0005"}, d2 = {"Lcom/citrascan/app/di/AppModule;", "", "()V", "provideOrtEnvironment", "Lai/onnxruntime/OrtEnvironment;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.citrascan.app.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    /**
     * Provides the ONNX Runtime environment singleton.
     * This is a heavyweight object that should be shared across the app.
     */
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final ai.onnxruntime.OrtEnvironment provideOrtEnvironment() {
        return null;
    }
}