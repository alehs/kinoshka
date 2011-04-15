package com.ast.kinoshka.backend.model;


/**
 * Categories of film attributes.
 * @author Aleh_Stsiapanau
 */
public enum AttributeCategory {

  UNKNOWN,
  GENRE,
  ACTOR,
  DIRECTOR,
  COUNTRY,
  YEAR,
  BOX,
  DISK;

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
      result = UNKNOWN;
    }
    return result;
  }

}
