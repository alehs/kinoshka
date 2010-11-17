package com.ast.kinoshka.testcommon;

import com.ast.kinoshka.backend.inject.CoreServiceModule;
import com.ast.kinoshka.common.inject.AppConfigModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public abstract class BaseServiceTest {

  public final static String WEB_CONTEXT = "bin/test/web";
  public final static String DB_CONTEXT = "memory:db";
  protected Injector injector;

  static {

    // create and initialize in-memory database for unit tests;

    try {
      Connection conn = DriverManager.getConnection("jdbc:derby:" + DB_CONTEXT + ";create=true");

      Scanner s = new Scanner(new File("sql/create_tables.sql"));
      s.useDelimiter(";");

      try {
        while (s.hasNext()) {
          String sql = s.next().trim();
          if (sql.length() > 0) {
            executeStatement(conn, sql);
          }
        }
      } finally {
        conn.close();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public BaseServiceTest() {
    injector = Guice.createInjector(new AppConfigModule(WEB_CONTEXT, DB_CONTEXT, 0),
        new CoreServiceModule());
  }

  private static void executeStatement(Connection conn, String str) throws SQLException {
    Statement stt = conn.createStatement();
    try {
      stt.execute(str.toString());
    } finally {
      stt.close();
    }
  }

}
