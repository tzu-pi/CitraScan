package com.citrascan.app.ui.screens.home;

import com.citrascan.app.data.repository.DiseaseRepository;
import com.citrascan.app.data.repository.ScanHistoryRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<DiseaseRepository> diseaseRepositoryProvider;

  private final Provider<ScanHistoryRepository> historyRepositoryProvider;

  public HomeViewModel_Factory(Provider<DiseaseRepository> diseaseRepositoryProvider,
      Provider<ScanHistoryRepository> historyRepositoryProvider) {
    this.diseaseRepositoryProvider = diseaseRepositoryProvider;
    this.historyRepositoryProvider = historyRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(diseaseRepositoryProvider.get(), historyRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<DiseaseRepository> diseaseRepositoryProvider,
      Provider<ScanHistoryRepository> historyRepositoryProvider) {
    return new HomeViewModel_Factory(diseaseRepositoryProvider, historyRepositoryProvider);
  }

  public static HomeViewModel newInstance(DiseaseRepository diseaseRepository,
      ScanHistoryRepository historyRepository) {
    return new HomeViewModel(diseaseRepository, historyRepository);
  }
}
