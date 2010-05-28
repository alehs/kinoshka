package com.ast.kinoshka.frontend.gwt.model;

/**
 * Movie categories for grouping.
 * @author Aleh_Stsiapanau
 */
public enum Category {

  ALL,
  GENRE,
  ACTOR,
  DIRECTOR,
  COUNTRY,
  YEAR,
  BOX,
  DISK;

  /**
   * Provides save valueOf method. Returns default ALL value if nothing was found.
   * @param name category name to get value for
   * @return category for given name
   */
  public static Category getByName(String name) {
    Category result;
    try {
      result = Category.valueOf(name.toUpperCase());
    } catch (IllegalArgumentException ex) {
      result = Category.ALL;
    }
    return result;
  }

}
