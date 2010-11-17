package com.ast.kinoshka.common.util;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Tests for {@link PathUtils} class methods.
 * @author Aleh_Stsiapanau
 */
public class PathUtilsTest {

  private static final String PART1 = "part1";
  private static final String PART2 = "part2";
  private static final String PART1_2 = PART1 + "/" + PART2;
  private static final String PART0_1_2 = "part0/" + PART1_2;

  @Test
  public void testAppend() {
    Assert.assertEquals(PART1_2, PathUtils.append("part1/", PART2));
    Assert.assertEquals(PART1_2, PathUtils.append(PART1, PART2));
    Assert.assertEquals(PART1_2, PathUtils.append(PART1, "/part2"));
    Assert.assertEquals(PART1_2, PathUtils.append("part1/", "/part2"));
    Assert.assertEquals(PART0_1_2, PathUtils.append("part0", "/part1/part2"));
    Assert.assertEquals(PART0_1_2, PathUtils.append("part0/", "/part1/part2"));
    Assert.assertEquals(PART0_1_2, PathUtils.append("part0/", PART1_2));
  }

}
