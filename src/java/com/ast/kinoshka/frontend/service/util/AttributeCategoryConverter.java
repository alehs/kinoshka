package com.ast.kinoshka.frontend.service.util;

import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.frontend.gwt.model.Category;

/**
 * Utility class to convert AttributeCategory and UI Category model.
 * @author Aleh_Stsiapanau
 */
public class AttributeCategoryConverter {
  private AttributeCategoryConverter() {/*prevent instantiation.*/}

  /**
   * Converts AttributeCategory to UI Category model.
   * @param attributeCategory to convert
   * @return UI Category model
   */
  public static Category toModel(AttributeCategory attributeCategory) {
    return Category.getByName(attributeCategory.name());
  }

  /**
   * Converts to Category UI model to AttributeCategory.
   * @param category to convert
   * @return AttributeCategory
   */
  public static AttributeCategory fromModel(Category category) {
    return AttributeCategory.getByName(category.name());
  }
  
}
