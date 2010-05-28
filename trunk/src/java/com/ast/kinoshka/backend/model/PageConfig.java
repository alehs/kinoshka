package com.ast.kinoshka.backend.model;

/**
 * Hack: Provides parameters for iBatis mapping
 * @author Aleh_Stsiapanau
 *
 */
public class PageConfig {

  private int attributeId;
  private int minEdge;
  private int maxEdge;

  public PageConfig(int min, int max) {
    this.minEdge = min;
    this.maxEdge = max;
  }

  public PageConfig(int attributeId, int min, int max) {
    this.attributeId = attributeId;
    this.minEdge = min;
    this.maxEdge = max;
  }

  public int getAttributeId() {
    return attributeId;
  }
  public int getMaxEdge() {
    return maxEdge;
  }
  public int getMinEdge() {
    return minEdge;
  }

  
}

