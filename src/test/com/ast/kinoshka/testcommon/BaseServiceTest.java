package com.ast.kinoshka.testcommon;

import com.ast.kinoshka.backend.inject.CoreServiceModule;
import com.ast.kinoshka.common.inject.AppConfigModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class BaseServiceTest {

  public final static String WEB_CONTEXT = "target/test/web";
  public final static String DB_CONTEXT = "target/db";
  protected Injector injector;

  public BaseServiceTest() {
    injector = Guice.createInjector(new AppConfigModule(WEB_CONTEXT, DB_CONTEXT, 0),
        new CoreServiceModule()); 
  }

}
