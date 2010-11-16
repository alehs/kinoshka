package com.ast.kinoshka.frontend.service.util;

import static junit.framework.Assert.assertEquals;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.testcommon.ServiceTestUtil;
import com.google.inject.internal.Lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for AttributeDataConverter.
 * @author Aleh_Stsiapanau
 * 
 */
public class AttributeDataConverterTest {

  private static final String ATTRIBUTE_PARAM = "Param1";
  private static final String ATTRIBUTE_NAME = "Name";

  @Test
  public void testToModel() {
    Attribute attr = new Attribute(0, ATTRIBUTE_NAME);
    FilmAttributeInfo model = AttributeDataConverter.toModel.apply(attr);
    compareAttributes(attr, model);

    attr = ServiceTestUtil.createAttribute(0, ATTRIBUTE_NAME, 10, ATTRIBUTE_PARAM);
    model = AttributeDataConverter.toModel.apply(attr);
    compareAttributes(attr, model);
  }

  @Test
  public void testFromModel() {
    FilmAttributeInfo model = new FilmAttributeInfo("0", ATTRIBUTE_NAME);
    Attribute attr = AttributeDataConverter.fromModel.apply(model);
    compareAttributes(attr, model);

    model = ServiceTestUtil.createAttributeModel("-1", ATTRIBUTE_NAME, 10, ATTRIBUTE_PARAM);
    attr = AttributeDataConverter.fromModel.apply(model);
    compareAttributes(attr, model);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testFromModelWrong() {
    FilmAttributeInfo model = new FilmAttributeInfo("A", ATTRIBUTE_NAME);
    AttributeDataConverter.fromModel.apply(model);
  }

  @Test
  public void testToModelList() {
    List<Attribute> attrs = Lists.newArrayList();
    for (int i = 0; i < 5; i++) {
      attrs.add(new Attribute(i, ATTRIBUTE_NAME));
    }
    ArrayList<FilmAttributeInfo> models = AttributeDataConverter.toModel(attrs);
    assertEquals(attrs.size(), models.size());
    for (int i = 0; i < attrs.size(); i++) {
      compareAttributes(attrs.get(i), models.get(i));
    }
  }

  @Test
  public void testFromModelList() {
    ArrayList<FilmAttributeInfo> models = Lists.newArrayList();
    for (int i = 0; i < 5; i++) {
      models.add(new FilmAttributeInfo(String.valueOf(i), ATTRIBUTE_NAME));
    }
    List<Attribute> attrs = AttributeDataConverter.fromModel(models);
    assertEquals(attrs.size(), models.size());
    for (int i = 0; i < models.size(); i++) {
      compareAttributes(attrs.get(i), models.get(i));
    }
  }

  public static void compareAttributes(Attribute attr, FilmAttributeInfo model) {
    assertEquals(attr.getId().toString(), model.getId());
    assertEquals(attr.getName(), model.getName());
    assertEquals(attr.getParam1(), model.getParam1());
    assertEquals(attr.getItemsCount(), model.getItemsCount());
  }

}
