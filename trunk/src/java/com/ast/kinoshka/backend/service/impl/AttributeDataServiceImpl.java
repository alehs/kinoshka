package com.ast.kinoshka.backend.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import com.ast.kinoshka.backend.common.ServiceException;
import com.ast.kinoshka.backend.data.AttributeMapper;
import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.backend.model.PageConfig;
import com.ast.kinoshka.backend.service.AttributeDataService;
import com.ast.kinoshka.backend.service.util.AttributeMapperUtil;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import org.apache.ibatis.exceptions.IbatisException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * CoreAttrDataService Default implementation
 * @author Aleh_Stsiapanau
 *
 */
public class AttributeDataServiceImpl implements AttributeDataService {

  private SqlSessionFactory factory;

  @Inject
  public AttributeDataServiceImpl(SqlSessionFactory factory) {
    this.factory = factory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Attribute> getAttributeList(AttributeCategory attributeCategory) {
    checkNotNull(attributeCategory);

    List<Attribute> result = null;
    SqlSession session = factory.openSession();
    try {
      AttributeMapper mapper = AttributeMapperUtil.getMapperByCategory(session, attributeCategory);
      result = mapper.getList();
    } finally {
      session.close();
    }

    return skipNulls(result);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Attribute addAttribute(AttributeCategory attributeCategory, Attribute attribute) {
    checkNotNull(attributeCategory);
    checkNotNull(attribute);

    SqlSession session = factory.openSession(true);
    try {
      AttributeMapper mapper = AttributeMapperUtil.getMapperByCategory(session, attributeCategory);
      mapper.add(attribute);
    } finally {
      session.close();
    }
    return attribute;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteAttribute(AttributeCategory attributeCategory, Integer attributeId) {
    checkNotNull(attributeCategory);
    checkNotNull(attributeId);

    SqlSession session = factory.openSession(true);
    try {
      AttributeMapper mapper = AttributeMapperUtil.getMapperByCategory(session, attributeCategory);
      mapper.delete(attributeId);
    } catch (IbatisException e) {
      throw new ServiceException("Error deleting item", e);
    } finally {
      session.close();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Attribute> getAttributes(AttributeCategory attributeCategory) {
    checkNotNull(attributeCategory);

    List<Attribute> result = null;
    SqlSession session = factory.openSession();
    try {
      AttributeMapper mapper = AttributeMapperUtil.getMapperByCategory(session, attributeCategory);
      result = mapper.getListWithCount();
    } finally {
      session.close();
    }

    return skipNulls(result);
  }

  @Override
  public List<Attribute> getAttributesPage(AttributeCategory attributeCategory, PageConfig config) {
    checkNotNull(attributeCategory);
    List<Attribute> result = null;
    SqlSession session = factory.openSession();
    try {
      // TODO: add getPage implementation
      //AttributeMapper mapper = AttributeMapperUtil.getMapperByCategory(session, attributeCategory);
      //result = mapper.getPage(config);
    } finally {
      session.close();
    }

    return skipNulls(result);
  }

  /*
   * hack for bug - returns 1 element with null id when should return none
   */
  private List<Attribute> skipNulls(List<Attribute> result) {
    if (result == null ||
        (result.size() == 1 && result.get(0) == null)) {
      result = Lists.newArrayList();
    }
    return result;
  }

}
