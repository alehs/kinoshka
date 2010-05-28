package com.ast.kinoshka.common;

import com.ast.kinoshka.backend.inject.CoreServiceModule;
import com.ast.kinoshka.common.exception.ApplicationConfigurationException;
import com.ast.kinoshka.common.inject.AppConfigModule;
import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.File;
import java.util.Properties;

/**
 * Processes application configuration parameters and provides them for
 * application components initialization.
 * 
 * @author Aleh_Stsiapanau
 */
public class AppConfig {

  /*
   * Application constants
   */
  public final static String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

  /* Service constants */
  private static final String WEB_CONTEXT_PARAM = "web";
  private static final String DB_CONTEXT_PARAM = "db";
  private static final String PORT_PARAM = "port";

  private static final String DEFAULT_WEB_CONTEXT_DIR = "web";
  private static final String DEFAULT_DB_CONTEXT_DIR = "db";
  private static final int DEFAULT_PORT = 8080;

  private static AppConfig instance;
  private String webContext;
  private String dbContext;
  private int port;
  //private String dbConnectionString;
  private Injector injector;

  /**
   * Initializes AppConfiguration object.
   */
  public static void init(String[] args) {
    Flags fs = Flags.parse(args);
    instance = new AppConfig(fs.getOptions());
  }

  /**
   * Hidden constructor.
   * Checks application web and database contexts and initializes injector.
   *
   * @param webContext
   *          path to application web resources
   * @param dbContext
   *          path to application DB resources
   */
  private AppConfig(Properties properties) {
    try {
      this.port = Integer.parseInt(properties.getProperty(PORT_PARAM, String.valueOf(DEFAULT_PORT)));
    } catch (NumberFormatException e) {
      this.port = DEFAULT_PORT;
    }

    this.webContext = properties.getProperty(WEB_CONTEXT_PARAM, DEFAULT_WEB_CONTEXT_DIR);
    this.dbContext = properties.getProperty(DB_CONTEXT_PARAM, DEFAULT_DB_CONTEXT_DIR);

    checkDirectoryExists(this.webContext);
    checkDirectoryExists(this.dbContext);

    //this.dbConnectionString = "jdbc:derby:" + this.dbContext + ";create=false";
    this.injector = Guice.createInjector(new AppConfigModule(this.webContext, this.dbContext, this.port),
        new CoreServiceModule());
  }

  private static void checkDirectoryExists(String dirPath) {
    Preconditions.checkNotNull(dirPath);

    File targetDir = new File(dirPath);
    if (!targetDir.exists() || !targetDir.isDirectory()) {
      throw new ApplicationConfigurationException("Target directory was not found: "
          + targetDir.getPath() + ". Maby you have cpecified wrong param? " + usage());
    }
  }

  private static final String usage() {
    return "\nUsage: \n\nparams:\n\t-" + DB_CONTEXT_PARAM
        + "=<path_to_dir> - database context dir (default './db')\n\t-" + WEB_CONTEXT_PARAM
        + "=<path_to_dir> - web context dir (default './web')\n";
  }

  public static AppConfig getInstance() {
    return instance;
  }

  public Injector getInjector() {
    return injector;
  }
  
  /** FOR UNIT TEST ONLY */
  String getWebContext() {
    return webContext;
  }

  /** FOR UNIT TEST ONLY */
  String getDbContext() {
    return dbContext;
  }

  /** FOR UNIT TEST ONLY */
  int getPort() {
    return port;
  }

}
