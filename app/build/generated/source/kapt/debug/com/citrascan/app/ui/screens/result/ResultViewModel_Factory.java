package com.citrascan.app.ui.screens.result;

import androidx.lifecycle.SavedStateHandle;
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
public final class ResultViewModel_Factory implements Factory<ResultViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<DiseaseRepository> diseaseRepositoryProvider;

  private final Provider<ScanHistoryRepository> historyRepositoryProvider;

  public ResultViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<DiseaseRepository> diseaseRepositoryProvider,
      Provider<ScanHistoryRepository> historyRepositoryProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.diseaseRepositoryProvider = diseaseRepositoryProvider;
    this.historyRepositoryProvider = historyRepositoryProvider;
  }

  @Override
  public ResultViewModel get() {
    return newInstance(savedStateHandleProvider.get(), diseaseRepositoryProvider.get(), historyRepositoryProvider.get());
  }

  public static ResultViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<DiseaseRepository> diseaseRepositoryProvider,
      Provider<ScanHistoryRepository> historyRepositoryProvider) {
    return new ResultViewModel_Factory(savedStateHandleProvider, diseaseRepositoryProvider, historyRepositoryProvider);
  }

  public static ResultViewModel newInstance(SavedStateHandle savedStateHandle,
      DiseaseRepository diseaseRepository, ScanHistoryRepository historyRepository) {
    return new ResultViewModel(savedStateHandle, diseaseRepository, historyRepository);
  }
}
