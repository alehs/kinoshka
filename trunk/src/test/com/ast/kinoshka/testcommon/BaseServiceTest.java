package com.ast.kinoshka.testcommon;

import com.ast.kinoshka.backend.inject.CoreServiceModule;
import com.ast.kinoshka.common.inject.AppConfigModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class BaseServiceTest {

  protected Injector injector;

  public BaseServiceTest() {
    injector = Guice.createInjector(new AppConfigModule("test/web", "test/db/storage", 0),
        new CoreServiceModule()); 
  }

}