package com.ast.kinoshka.common.util;

import com.google.common.base.Preconditions;

/**
 * Provides utility methods to work with paths.
 * @author Aleh_Stsiapanau
 */
public final class PathUtils {

  /**
   * Hidden constructor.
   */
  private PathUtils() {
  } // uninstantiable

  /**
   * Construct path from the given peaces
   * @param first first part of the path
   * @param second second part of the path
   * @return
   */
  public static String append(final String first, final String second) {
    Preconditions.checkNotNull(first);
    return trim(first) + "/" + trim(second);
  }

  private static String trim(final String path) {
    String result = path;
    if (opened(path)) {
      result = path.substring(1);
    }
    if (closed(path)) {
      result = path.substring(0, path.length() - 1);
    }
    return result;
  }

  private static boolean closed(final String path) {
    return path.endsWith("/") || path.endsWith("\\");
  }

  private static boolean opened(final String path) {
    return path.charAt(0) == '/' || path.charAt(0) == '\\';
  }

}
