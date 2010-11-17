package com.ast.kinoshka.frontend.service.util;

import com.ast.kinoshka.backend.model.PageConfig;
import com.ast.kinoshka.frontend.gwt.model.PagingConfig;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link PagingUtil} methods.
 * @author Aleh_Stsiapanau
 */
public class PagingUtilTest {

  @Test
  public void testId() {
    PageConfig result = PagingUtil.evalDesc(new PagingConfig(0, 5), 10);
    Assert.assertEquals(6, result.getFrom());
    Assert.assertEquals(10, result.getTo());

    result = PagingUtil.evalDesc(new PagingConfig(2, 5), 10);
    Assert.assertEquals(4, result.getFrom());
    Assert.assertEquals(8, result.getTo());

    result = PagingUtil.evalDesc(new PagingConfig(8, 5), 10);
    Assert.assertEquals(-2, result.getFrom());
    Assert.assertEquals(2, result.getTo());
  }
}
