package com.ast.kinoshka.frontend.gwt.utils;

import com.google.gwt.core.client.GWT;

/**
 * Utility that instantiates the message bundle.
 */
public class Messages {

  private static final MessagesBundle MESSAGES = (MessagesBundle) GWT.create(MessagesBundle.class);

  private Messages() {
    // prevent instantiation
  }

  /**
   * Return client messages bundle.
   * @return messages bundle.
   */
  public static MessagesBundle get() {
    return MESSAGES;
  }

}
