package com.ast.kinoshka.frontend.gwt.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Provides pagination request properties.
 * @author Aleh_Stsiapanau
 */
public class PagingConfig implements IsSerializable {

  public static final int DEFAULT_PAGE_SIZE = 10;

  private String sortField;
  private Integer limit;
  private Integer offset;

  /**
   * Default constructor.
   */
  public PagingConfig() {
    setOffset(0);
    setLimit(DEFAULT_PAGE_SIZE);
  }

  /**
   * Creates new PagingConfig.
   * @param offset the offset
   * @param limit the limit
   */
  public PagingConfig(int offset, int limit) {
    setOffset(offset);
    setLimit(limit);
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public String getSortField() {
    return sortField;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getOffset() {
    return offset;
  }

  
}
