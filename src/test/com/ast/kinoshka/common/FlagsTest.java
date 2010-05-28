package com.ast.kinoshka.common;

import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_DB;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_DB_NAME;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_DB_VAL;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_PORT;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_PORT_NAME;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_PORT_VAL;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WEB;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WEB_NAME;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WEB_VAL;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WRONG_WC;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WRONG_WC_NAME;
import static com.ast.kinoshka.testcommon.CommonTestUtil.ARG_WRONG_WC_VAL;
import static com.ast.kinoshka.testcommon.CommonTestUtil.PARAM1;
import static com.ast.kinoshka.testcommon.CommonTestUtil.PARAM2;

import org.junit.Test;

import java.util.Properties;

import junit.framework.Assert;

/**
 * Tests Flags class.
 * @author Aleh_Stsiapanau
 *
 */
public class FlagsTest {

  @Test
  public void testParse() {
    Flags flags = Flags.parse(new String[] {ARG_WEB, ARG_DB, ARG_PORT, PARAM1, ARG_WRONG_WC, PARAM2});

    Assert.assertEquals(ARG_WEB_VAL, flags.getOption(ARG_WEB_NAME));
    Assert.assertEquals(ARG_DB_VAL, flags.getOption(ARG_DB_NAME));
    Assert.assertEquals(ARG_PORT_VAL, flags.getOption(ARG_PORT_NAME));
    Assert.assertEquals(ARG_WRONG_WC_VAL, flags.getOption(ARG_WRONG_WC_NAME));
    Assert.assertNull(flags.getOption("not-exist"));

    Assert.assertEquals(PARAM1, flags.nextParam());
    Assert.assertEquals(PARAM2, flags.nextParam());
    Assert.assertNull(flags.nextParam());
  }

  @Test
  public void testGetOptions() {
    Flags flags = Flags.parse(new String[] {ARG_WEB, ARG_DB, ARG_PORT, PARAM1, ARG_WRONG_WC, PARAM2});
    Properties props = flags.getOptions();

    Assert.assertTrue("missed property", props.containsKey(ARG_WEB_NAME));
    Assert.assertTrue("missed property", props.containsKey(ARG_DB_NAME));
    Assert.assertTrue("missed property", props.containsKey(ARG_PORT_NAME));
    Assert.assertEquals(ARG_WEB_VAL, props.getProperty(ARG_WEB_NAME));
    Assert.assertEquals(ARG_DB_VAL, props.getProperty(ARG_DB_NAME));
    Assert.assertEquals(ARG_PORT_VAL, props.getProperty(ARG_PORT_NAME));

  }

}
