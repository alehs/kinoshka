package com.ast.kinoshka.common;

import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_DB;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_DB_VAL;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WEB;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WEB_VAL;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WRONG_PORT;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WRONG_WC;
import static com.ast.kinoshka.testcommon.CommonTestUtil.PARAM1;
import static com.ast.kinoshka.testcommon.CommonTestUtil.PARAM2;

import com.ast.kinoshka.common.exception.ApplicationConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import junit.framework.Assert;

/**
 * Test for AppConfig
 * @author Aleh_Stsiapanau
 *
 */
public class AppConfigTest {

  private File webDir = new File(ARG_DB_VAL);
  private File dbDir = new File(ARG_WEB_VAL);

  @Before
  public void setUp() {
    webDir.mkdir();
    dbDir.mkdir();
  }

  @After
  public void tearDown() {
    cleanDir(webDir);
    cleanDir(dbDir);
  }

  @Test
  public void testInit() {
    Assert.assertNull(AppConfig.getInstance());
    AppConfig.init(new String[] { ARG_WEB, ARG_DB, PARAM1, PARAM2 });

    Assert.assertNotNull(AppConfig.getInstance());
    Assert.assertEquals(ARG_WEB_VAL, AppConfig.getInstance().getWebContext());
    Assert.assertEquals(ARG_DB_VAL, AppConfig.getInstance().getDbContext());
  }

  @Test(expected = ApplicationConfigurationException.class)
  public void testInitWrongParam() {
    AppConfig.init(new String[] { ARG_WRONG_WC });
  }

  @Test
  public void testDefaultPort() {
    AppConfig.init(new String[] {ARG_WEB, ARG_DB});
    Assert.assertEquals(8080, AppConfig.getInstance().getPort());
  }

  @Test (expected = NumberFormatException.class)
  public void testWrongPort() {
    AppConfig.init(new String[] {ARG_WEB, ARG_DB, ARG_WRONG_PORT});
    Assert.assertEquals(8080, AppConfig.getInstance().getPort());
  }


  private void cleanDir(File dir) {
    if (dir.isDirectory() && dir.list().length == 0) {
      dir.delete();
    }
  }
}
