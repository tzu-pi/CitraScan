package com.citrascan.app.ui.screens.history;

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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<ScanHistoryRepository> historyRepositoryProvider;

  public HistoryViewModel_Factory(Provider<ScanHistoryRepository> historyRepositoryProvider) {
    this.historyRepositoryProvider = historyRepositoryProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(historyRepositoryProvider.get());
  }

  public static HistoryViewModel_Factory create(
      Provider<ScanHistoryRepository> historyRepositoryProvider) {
    return new HistoryViewModel_Factory(historyRepositoryProvider);
  }

  public static HistoryViewModel newInstance(ScanHistoryRepository historyRepository) {
    return new HistoryViewModel(historyRepository);
  }
}
