package com.ast.kinoshka.frontend.service;

import com.ast.kinoshka.common.AppConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Injector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * Base Servlet for application services. Provides member injection.
 * @author Aleh_Stsiapanau
 */
@SuppressWarnings("serial")
public class BaseServiceServlet extends RemoteServiceServlet {

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    this.init(AppConfig.getInstance().getInjector());
  }

  /** For UnitTest only: initializes servlet members. */
  protected void init(Injector injector) throws ServletException {
    injector.injectMembers(this);
  }

}
