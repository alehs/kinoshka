package com.ast.kinoshka.backend.service;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.backend.model.PageConfig;

import java.util.List;

/**
 * Service that manipulates film attributes.
 * @author Aleh_Stsiapanau
 */
public interface AttributeDataService {

  /**
   * Returns film attribute list of specified category.
   * @param category film attribute category
   * @return list of attributes
   */
  List<Attribute> getAttributeList(AttributeCategory attributeCategory);

  /**
   * Returns full info of film attribute list of specified category.
   * @param category film attribute category
   * @return full attributes info
   */
  List<Attribute> getAttributes(AttributeCategory attributeCategory);

  /**
   * Returns full info of film attribute list of specified category.
   * @param category film attribute category
   * @param config pagination configuration
   * @return full attributes info
   */
  List<Attribute> getAttributesPage(AttributeCategory attributeCategory, PageConfig config);

  /**
   * Inserts new attribute.
   * @param attribteCategory attribute category
   * @param attribute to insert
   * @return inserted attribute
   */
  Attribute addAttribute(AttributeCategory attributeCategory, Attribute attribute);

  /**
   * Deletes attribute with specified id.
   * @param attribteCategory attribute category
   * @param attributeId attribute id
   */
  void deleteAttribute(AttributeCategory attributeCategory, Integer attributeId);

}
