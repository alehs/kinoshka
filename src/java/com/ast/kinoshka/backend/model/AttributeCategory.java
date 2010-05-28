package com.ast.kinoshka.backend.model;

import java.util.logging.Logger;

/**
 * Categories of film attributes.
 * @author Aleh_Stsiapanau
 */
public enum AttributeCategory {

  GENRE,
  ACTOR,
  DIRECTOR,
  COUNTRY,
  YEAR,
  BOX,
  DISK;

  /** Logger. */
  private static final Logger LOG = Logger.getLogger(AttributeCategory.class.getName());

  /**
   * Provides save valueOf method. Returns null if nothing was found.
   * @param name attribute category name to get value for
   * @return attribute category for the given name or null if nothing was found
   */
  public static AttributeCategory getByName(String name) {
    AttributeCategory result = null;
    try {
      result = AttributeCategory.valueOf(name.toUpperCase());
    } catch (IllegalArgumentException ex) {
      LOG.warning("Attemp to get unknown value: " + name);
    }
    return result;
  }

}
