package com.citrascan.app.ml;

import ai.onnxruntime.OrtEnvironment;
import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class OnnxModelManager_Factory implements Factory<OnnxModelManager> {
  private final Provider<Context> contextProvider;

  private final Provider<OrtEnvironment> ortEnvironmentProvider;

  public OnnxModelManager_Factory(Provider<Context> contextProvider,
      Provider<OrtEnvironment> ortEnvironmentProvider) {
    this.contextProvider = contextProvider;
    this.ortEnvironmentProvider = ortEnvironmentProvider;
  }

  @Override
  public OnnxModelManager get() {
    return newInstance(contextProvider.get(), ortEnvironmentProvider.get());
  }

  public static OnnxModelManager_Factory create(Provider<Context> contextProvider,
      Provider<OrtEnvironment> ortEnvironmentProvider) {
    return new OnnxModelManager_Factory(contextProvider, ortEnvironmentProvider);
  }

  public static OnnxModelManager newInstance(Context context, OrtEnvironment ortEnvironment) {
    return new OnnxModelManager(context, ortEnvironment);
  }
}
