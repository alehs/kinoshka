package com.ast.kinoshka.frontend.service.util;

import static com.google.common.collect.Iterables.transform;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.google.common.base.Function;
import com.google.inject.internal.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that converts data domain model to UI model.
 * @author Aleh_Stsiapanau
 */
public class AttributeDataConverter {

  private AttributeDataConverter() { /* prevent instantiation.*/}

  public static final Function<Attribute, FilmAttributeInfo> toModel = new Function<Attribute, FilmAttributeInfo>() {
    @Override
    public FilmAttributeInfo apply(Attribute attr) {
      FilmAttributeInfo model = new FilmAttributeInfo(String.valueOf(attr.getId()), attr.getName());
      model.setItemsCount(attr.getItemsCount());
      model.setParam1(attr.getParam1());
      return model;
    }
  };

  public static final Function<FilmAttributeInfo, Attribute> fromModel = new Function<FilmAttributeInfo, Attribute>() {
    @Override
    public Attribute apply(FilmAttributeInfo attr) {
      Attribute model = null;
      try {
        model = new Attribute(Integer.valueOf(attr.getId()), attr.getName());
        model.setItemsCount(attr.getItemsCount());
        model.setParam1(attr.getParam1());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Wrong attribute id specified:" + attr.getId());
      }
      return model;
    }
  };

  /**
   * Converts data domain model to UI model.
   * @param attributes list of attributes to convert to list of UI attributeInfos
   * @return list of UI attributeInfos
   */
  public static ArrayList<FilmAttributeInfo> toModel(List<Attribute> attributes) {
    return Lists.newArrayList(transform(attributes, toModel));
  }

  /**
   * Converts attributes UI model to domain model.
   * @param models list of attributes to convert to list of domain models
   * @return list of domain models
   */
  public static List<Attribute> fromModel(ArrayList<FilmAttributeInfo> models) {
    return Lists.newArrayList(transform(models, fromModel));
  }

}
