package com.ast.kinoshka.frontend.gwt.utils;

/**
 * Utility for String.
 * @author Aleh_Stsiapanau
 *
 */
public class StringUtil {

  private StringUtil() { /* prevent instantiation. */}

  public static final boolean isEmpty(String test) {
    return (test == null || test.length() == 0);
  }
}
