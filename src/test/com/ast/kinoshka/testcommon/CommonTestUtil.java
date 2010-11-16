package com.ast.kinoshka.testcommon;

public abstract class CommonTestUtil {
  public static final String ARG_WEB_NAME = "web";
  public static final String ARG_WEB_VAL = "target";
  public static final String ARG_WEB = "-" + ARG_WEB_NAME + "=" + ARG_WEB_VAL;

  public static final String ARG_DB_NAME = "db";
  public static final String ARG_DB_VAL = "test/db";
  public static final String ARG_DB = "-" + ARG_DB_NAME + "=" + ARG_DB_VAL;

  public static final String ARG_PORT_NAME = "port";
  public static final String ARG_PORT_VAL = "8484";
  public static final String ARG_PORT = "-" + ARG_PORT_NAME + "=" + ARG_PORT_VAL;

  public static final String ARG_WRONG_WC_NAME = "app-else";
  public static final String ARG_WRONG_WC_VAL = "not-exist";
  public static final String ARG_WRONG_WC = "-" + ARG_WRONG_WC_NAME + "=" + ARG_WRONG_WC_VAL;

  public static final String ARG_WRONG_PORT = "-port=wron";
  
  public static final String PARAM1 = "param1";
  public static final String PARAM2 = "param2";
}
