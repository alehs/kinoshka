package com.ast.kinoshka.common.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Manages test resources.
 * @author Aleh_Stsiapanau
 */
public final class Messages {

  private static final ResourceBundle RESOURCE_BUNDLE = PropertyResourceBundle
      .getBundle("messages");

  public static final String ERROR_MISSEDDIR = "error.missed.directory";
  public static final String USAGE = "msg.usage";

  private Messages() {
    // Utility class
  }

  /**
   * Searches for specified message through ResourceBundle. Return key if
   * resource is absent.
   *
   * @param key
   *          message key
   * @param args
   *          arguments
   * @return message text for specified key
   */
  public static String getText(final String key, final Object... args) {
    try {
      String text = RESOURCE_BUNDLE.getString(key);
      if (args != null) {
        text = MessageFormat.format(text, args);
      }
      return text;
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }

}
