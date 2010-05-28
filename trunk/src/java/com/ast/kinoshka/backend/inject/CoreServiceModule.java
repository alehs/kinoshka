package com.ast.kinoshka.backend.inject;

import com.ast.kinoshka.backend.service.AttributeDataService;
import com.ast.kinoshka.backend.service.FilmDataService;
import com.ast.kinoshka.backend.service.ImageService;
import com.ast.kinoshka.backend.service.impl.AttributeDataServiceImpl;
import com.ast.kinoshka.backend.service.impl.FilmDataServiceImpl;
import com.ast.kinoshka.backend.service.impl.ImageServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;

import org.apache.ibatis.session.SqlSessionFactory;

public class CoreServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AttributeDataService.class).to(AttributeDataServiceImpl.class);
    bind(FilmDataService.class).to(FilmDataServiceImpl.class);
    bind(ImageService.class).to(ImageServiceImpl.class);

    bind(SqlSessionFactory.class).toProvider(SqlSessionFactoryProvider.class).in(Scopes.SINGLETON);

    bindInterceptor(Matchers.inSubpackage("com.ast.kinoshka.backend"),
        Matchers.any(), new CoreServiceLogger());
  }

}
