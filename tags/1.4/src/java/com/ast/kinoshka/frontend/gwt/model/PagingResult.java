package com.ast.kinoshka.frontend.gwt.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides paginated film list result.
 * @author Aleh_Stsiapanau
 */
public class PagingResult implements IsSerializable {

  private ArrayList<FilmInfo> list;
  protected int offset = 0;
  protected int totalLength = 0;

  /**
   * Creates a new paging load result.
   * @param data list of films
   */
  public PagingResult(ArrayList<FilmInfo> data) {
    this.list = data;
  }

  /**
   * Creates a new paging load result.
   * 
   * @param data list of films
   * @param offset the offset
   * @param totalLength the total length
   */
  public PagingResult(ArrayList<FilmInfo> data, int offset, int totalLength) {
    this(data);
    this.offset = offset;
    this.totalLength = totalLength;
  }

  /** For serialization. */
  PagingResult() {}

  public int getOffset() {
    return offset;
  }

  public int getTotalLength() {
    return totalLength;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public void setTotalLength(int totalLength) {
    this.totalLength = totalLength;
  }

  public List<FilmInfo> getData() {
    return list;
  }

  public void setData(ArrayList<FilmInfo> list) {
    this.list = list;
  }

}
