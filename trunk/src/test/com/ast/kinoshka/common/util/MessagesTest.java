package com.ast.kinoshka.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for Messages utility class.
 *
 * @author Aleh_Stsiapanau
 */
public class MessagesTest {

  /**
   * Tests getText method.
   */
  @Test
  public void testMessages() {
    Assert.assertEquals("!wrong_key!", Messages.getText("wrong_key"));
    Assert.assertFalse(Messages.getText(Messages.USAGE).contains(Messages.USAGE));
    Assert.assertFalse((Messages.getText(Messages.ERROR_MISSEDDIR))
        .contains(Messages.ERROR_MISSEDDIR));
  }

}
