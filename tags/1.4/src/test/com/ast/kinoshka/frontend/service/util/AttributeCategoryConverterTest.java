package com.ast.kinoshka.frontend.service.util;

import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.frontend.gwt.model.Category;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Unit Test for AttributeCategoryConverter
 * @author Aleh_Stsiapanau
 *
 */
public class AttributeCategoryConverterTest {

  @Test
  public void testToModel() {
    Assert.assertEquals(Category.ACTOR, AttributeCategoryConverter.toModel(AttributeCategory.ACTOR));
    Assert.assertEquals(Category.BOX, AttributeCategoryConverter.toModel(AttributeCategory.BOX));
    Assert.assertEquals(Category.COUNTRY, AttributeCategoryConverter.toModel(AttributeCategory.COUNTRY));
    Assert.assertEquals(Category.DIRECTOR, AttributeCategoryConverter.toModel(AttributeCategory.DIRECTOR));
    Assert.assertEquals(Category.DISK, AttributeCategoryConverter.toModel(AttributeCategory.DISK));
    Assert.assertEquals(Category.GENRE, AttributeCategoryConverter.toModel(AttributeCategory.GENRE));
    Assert.assertEquals(Category.YEAR, AttributeCategoryConverter.toModel(AttributeCategory.YEAR));
  }

  @Test
  public void testFromModel() {
    Assert.assertEquals(AttributeCategory.ACTOR, AttributeCategoryConverter.fromModel(Category.ACTOR));
    Assert.assertEquals(AttributeCategory.BOX, AttributeCategoryConverter.fromModel(Category.BOX));
    Assert.assertEquals(AttributeCategory.COUNTRY, AttributeCategoryConverter.fromModel(Category.COUNTRY));
    Assert.assertEquals(AttributeCategory.DIRECTOR, AttributeCategoryConverter.fromModel(Category.DIRECTOR));
    Assert.assertEquals(AttributeCategory.DISK, AttributeCategoryConverter.fromModel(Category.DISK));
    Assert.assertEquals(AttributeCategory.GENRE, AttributeCategoryConverter.fromModel(Category.GENRE));
    Assert.assertEquals(AttributeCategory.YEAR, AttributeCategoryConverter.fromModel(Category.YEAR));
    Assert.assertEquals(null, AttributeCategoryConverter.fromModel(Category.ALL));
  }

}

