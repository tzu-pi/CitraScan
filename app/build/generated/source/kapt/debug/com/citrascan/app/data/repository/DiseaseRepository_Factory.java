package com.citrascan.app.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DiseaseRepository_Factory implements Factory<DiseaseRepository> {
  @Override
  public DiseaseRepository get() {
    return newInstance();
  }

  public static DiseaseRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DiseaseRepository newInstance() {
    return new DiseaseRepository();
  }

  private static final class InstanceHolder {
    private static final DiseaseRepository_Factory INSTANCE = new DiseaseRepository_Factory();
  }
}
