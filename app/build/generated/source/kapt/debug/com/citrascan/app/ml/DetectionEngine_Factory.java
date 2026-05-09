package com.citrascan.app.ml;

import com.citrascan.app.data.repository.DiseaseRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DetectionEngine_Factory implements Factory<DetectionEngine> {
  private final Provider<ImagePreprocessor> preprocessorProvider;

  private final Provider<OnnxModelManager> modelManagerProvider;

  private final Provider<YoloPostProcessor> postProcessorProvider;

  private final Provider<DiseaseRepository> diseaseRepositoryProvider;

  public DetectionEngine_Factory(Provider<ImagePreprocessor> preprocessorProvider,
      Provider<OnnxModelManager> modelManagerProvider,
      Provider<YoloPostProcessor> postProcessorProvider,
      Provider<DiseaseRepository> diseaseRepositoryProvider) {
    this.preprocessorProvider = preprocessorProvider;
    this.modelManagerProvider = modelManagerProvider;
    this.postProcessorProvider = postProcessorProvider;
    this.diseaseRepositoryProvider = diseaseRepositoryProvider;
  }

  @Override
  public DetectionEngine get() {
    return newInstance(preprocessorProvider.get(), modelManagerProvider.get(), postProcessorProvider.get(), diseaseRepositoryProvider.get());
  }

  public static DetectionEngine_Factory create(Provider<ImagePreprocessor> preprocessorProvider,
      Provider<OnnxModelManager> modelManagerProvider,
      Provider<YoloPostProcessor> postProcessorProvider,
      Provider<DiseaseRepository> diseaseRepositoryProvider) {
    return new DetectionEngine_Factory(preprocessorProvider, modelManagerProvider, postProcessorProvider, diseaseRepositoryProvider);
  }

  public static DetectionEngine newInstance(ImagePreprocessor preprocessor,
      OnnxModelManager modelManager, YoloPostProcessor postProcessor,
      DiseaseRepository diseaseRepository) {
    return new DetectionEngine(preprocessor, modelManager, postProcessor, diseaseRepository);
  }
}
