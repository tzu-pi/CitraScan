package com.citrascan.app.ui.screens.scanner;

import com.citrascan.app.data.repository.DiseaseRepository;
import com.citrascan.app.data.repository.ScanHistoryRepository;
import com.citrascan.app.ml.DetectionEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ScannerViewModel_Factory implements Factory<ScannerViewModel> {
  private final Provider<DetectionEngine> detectionEngineProvider;

  private final Provider<DiseaseRepository> diseaseRepositoryProvider;

  private final Provider<ScanHistoryRepository> historyRepositoryProvider;

  public ScannerViewModel_Factory(Provider<DetectionEngine> detectionEngineProvider,
      Provider<DiseaseRepository> diseaseRepositoryProvider,
      Provider<ScanHistoryRepository> historyRepositoryProvider) {
    this.detectionEngineProvider = detectionEngineProvider;
    this.diseaseRepositoryProvider = diseaseRepositoryProvider;
    this.historyRepositoryProvider = historyRepositoryProvider;
  }

  @Override
  public ScannerViewModel get() {
    return newInstance(detectionEngineProvider.get(), diseaseRepositoryProvider.get(), historyRepositoryProvider.get());
  }

  public static ScannerViewModel_Factory create(Provider<DetectionEngine> detectionEngineProvider,
      Provider<DiseaseRepository> diseaseRepositoryProvider,
      Provider<ScanHistoryRepository> historyRepositoryProvider) {
    return new ScannerViewModel_Factory(detectionEngineProvider, diseaseRepositoryProvider, historyRepositoryProvider);
  }

  public static ScannerViewModel newInstance(DetectionEngine detectionEngine,
      DiseaseRepository diseaseRepository, ScanHistoryRepository historyRepository) {
    return new ScannerViewModel(detectionEngine, diseaseRepository, historyRepository);
  }
}
