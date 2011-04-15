package com.ast.kinoshka.backend.model;

/**
 * Hack: Provides parameters for iBatis mapping
 * @author Aleh_Stsiapanau
 */
public class PageConfig {

  private int attributeId;
  private int from;
  private int to;

  public PageConfig(int from, int to) {
    this.from = from;
    this.to = to;
  }

  public PageConfig(int attributeId, int from, int to) {
    this.attributeId = attributeId;
    this.from = from;
    this.to = to;
  }

  public int getAttributeId() {
    return attributeId;
  }
  public int getTo() {
    return to;
  }
  public int getFrom() {
    return from;
  }

  public static PageConfig valueOf(int from, int to) {
    return new PageConfig(from, to);
  }

  @Override
  public String toString() {
    return from + ":" + to;
  }

}

