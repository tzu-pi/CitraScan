package com.citrascan.app.di;

import ai.onnxruntime.OrtEnvironment;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideOrtEnvironmentFactory implements Factory<OrtEnvironment> {
  @Override
  public OrtEnvironment get() {
    return provideOrtEnvironment();
  }

  public static AppModule_ProvideOrtEnvironmentFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OrtEnvironment provideOrtEnvironment() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideOrtEnvironment());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideOrtEnvironmentFactory INSTANCE = new AppModule_ProvideOrtEnvironmentFactory();
  }
}
