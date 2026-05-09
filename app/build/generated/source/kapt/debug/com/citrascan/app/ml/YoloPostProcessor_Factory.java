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
public final class YoloPostProcessor_Factory implements Factory<YoloPostProcessor> {
  private final Provider<DiseaseRepository> diseaseRepositoryProvider;

  public YoloPostProcessor_Factory(Provider<DiseaseRepository> diseaseRepositoryProvider) {
    this.diseaseRepositoryProvider = diseaseRepositoryProvider;
  }

  @Override
  public YoloPostProcessor get() {
    return newInstance(diseaseRepositoryProvider.get());
  }

  public static YoloPostProcessor_Factory create(
      Provider<DiseaseRepository> diseaseRepositoryProvider) {
    return new YoloPostProcessor_Factory(diseaseRepositoryProvider);
  }

  public static YoloPostProcessor newInstance(DiseaseRepository diseaseRepository) {
    return new YoloPostProcessor(diseaseRepository);
  }
}
