package com.ast.kinoshka.common.inject;

import com.ast.kinoshka.common.util.PathUtils;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Application configuration module.
 * @author Aleh_Stsiapanau
 *
 */
public class AppConfigModule extends AbstractModule {

  private final static String IMAGE_TEMP_DIR = "images/tmp";
  private final static String IMAGE_COVERS_DIR = "images/covers";
  private final static String IMAGE_BACKGROUND_DIR = "images/backgrounds";

  private final String webContext;
  private final String dbContext;
  private final int port;

  public AppConfigModule(String webContext, String dbContext, int port) {
    this.webContext = webContext;
    this.dbContext = dbContext;
    this.port = port;
  }

  @Override
  protected void configure() {
    bindConstant().annotatedWith(WebContext.class).to(webContext);
    bindConstant().annotatedWith(DbContext.class).to(dbContext);
    bindConstant().annotatedWith(Names.named("port")).to(port);
    bindConstant().annotatedWith(Names.named("images.tmp")).to(PathUtils.append(webContext, IMAGE_TEMP_DIR));
    bindConstant().annotatedWith(Names.named("images.covers")).to(PathUtils.append(webContext, IMAGE_COVERS_DIR));
    bindConstant().annotatedWith(Names.named("images.bgs")).to(PathUtils.append(webContext, IMAGE_BACKGROUND_DIR));
  }

}

