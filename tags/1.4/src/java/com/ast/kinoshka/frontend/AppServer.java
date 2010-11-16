package com.ast.kinoshka.frontend;

import com.ast.kinoshka.common.AppConfig;
import com.ast.kinoshka.common.exception.ApplicationException;
import com.ast.kinoshka.common.inject.WebContext;
import com.ast.kinoshka.frontend.service.DataServiceImpl;
import com.ast.kinoshka.frontend.service.EditServiceImpl;
import com.ast.kinoshka.frontend.service.ImageUploadServlet;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.servlet.GuiceFilter;
import com.google.opengse.ServletEngine;
import com.google.opengse.ServletEngineConfiguration;
import com.google.opengse.ServletEngineConfigurationImpl;
import com.google.opengse.core.ServletEngineImpl;
import com.google.opengse.webapp.WebAppCollection;
import com.google.opengse.webapp.WebAppCollectionFactory;
import com.google.opengse.webapp.WebAppConfigurationBuilder;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

/**
 * Main class.
 * @author Aleh_Stsiapanau
 */
public class AppServer {

  private static final int MAX_THREADS = 5;

  private static AppServer instance;
  private ServletEngine engine;

  @Inject
  @WebContext
  private String webContext;

  @Inject
  @Named(value="port")
  private int port;

  /**
   * Returns the application server instance.
   * @return the application server instance
   */
  public static AppServer getInstance() {
    if (instance == null) {
      instance = AppConfig.getInstance().getInjector().getInstance(AppServer.class);
    }
    return instance;
  }

  /**
   * Application entry point. Creates the application server and runs it.
   * @param args
   *          server's initial parameters
   */
  public static void main(String[] args) {
    //CommonsLoggerAdapter.activate();
    AppConfig.init(args); // initialize application configurator with command line arguments
    getInstance().start();
  }

  /**
   * Starts the server.
   * @param args
   *          server's initial parameters
   */
  public void start() {

    // This object will create our webapp's configuration for us
    WebAppConfigurationBuilder configBuilder = new WebAppConfigurationBuilder();
    configBuilder.addFilter(GuiceFilter.class, "/*");

    configBuilder.addServlet(DataServiceImpl.class, DataServiceImpl.ACTION);
    configBuilder.addServlet(EditServiceImpl.class, EditServiceImpl.ACTION);
    configBuilder.addServlet(ImageUploadServlet.class, ImageUploadServlet.ACTION);

    File contextDir = new File(webContext);

    // We want "" to be returned by
    // javax.servlet.http.HttpServletRequest.getContextPath() (ie. the root
    // context)
    // so we use the "ROOT" context name (this is so we can have files called
    // ROOT.war and not .war
    String context = "ROOT";
    // create a collection of webapps that just contains a single webapp
    WebAppCollection webapps = null;
    try {
      webapps = WebAppCollectionFactory.createWebAppCollectionWithOneContext(contextDir, context,
          configBuilder.getConfiguration());
    } catch (Exception e) {
      throw new ApplicationException("Failed to create web application", e);
    }

    // start all of the webapps
    try {
      webapps.startAll();
    } catch (ServletException e) {
      throw new ApplicationException("Failed to start the webapp", e);
    }

    // create servlet engine configuration object
    ServletEngineConfiguration config = ServletEngineConfigurationImpl.create(port, MAX_THREADS);

    // create an engine using the enging configuration and send all requests
    // to the webapps
    try {
      engine = ServletEngineImpl.create(webapps, config);
    } catch (Exception e) {
      throw new ApplicationException("Failed to create the application engine", e);
    }
    // run it
    engine.run();
  }

  /**
   * Stops the server.
   */
  public void stop() {
    try {
      engine.quit(0);
    } catch (IOException e) {
      throw new ApplicationException("Failed to stop the application engine", e);
    }
  }
}
