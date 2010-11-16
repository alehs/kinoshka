package com.ast.kinoshka.backend.inject;

import com.ast.kinoshka.backend.data.ActorMapper;
import com.ast.kinoshka.backend.data.BoxMapper;
import com.ast.kinoshka.backend.data.DirectorMapper;
import com.ast.kinoshka.backend.data.DiskMapper;
import com.ast.kinoshka.backend.data.FilmMapper;
import com.ast.kinoshka.backend.data.GenreMapper;
import com.ast.kinoshka.backend.data.RegionMapper;
import com.ast.kinoshka.backend.data.YearMapper;
import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.common.inject.DbContext;
import com.google.inject.Inject;
import com.google.inject.Provider;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class SqlSessionFactoryProvider implements Provider<SqlSessionFactory> {

  @Inject
  @DbContext
  private String dbContext;

  @Override
  public SqlSessionFactory get() {
    EmbeddedDataSource xads = new EmbeddedDataSource();
    xads.setDatabaseName(dbContext); //AppConfig.getInstance().getDbContext()
    DataSource dataSource = xads;

    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);

    configuration.getTypeAliasRegistry().registerAlias("Attribute", Attribute.class);
    configuration.getTypeAliasRegistry().registerAlias("Film", Film.class);

    // register data mappers
    configuration.addMapper(FilmMapper.class);
    configuration.addMapper(GenreMapper.class);
    configuration.addMapper(ActorMapper.class);
    configuration.addMapper(DirectorMapper.class);
    configuration.addMapper(RegionMapper.class);
    configuration.addMapper(YearMapper.class);
    configuration.addMapper(BoxMapper.class);
    configuration.addMapper(DiskMapper.class);

    return new SqlSessionFactoryBuilder().build(configuration);
  }

}
