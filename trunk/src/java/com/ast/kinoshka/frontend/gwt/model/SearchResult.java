package com.ast.kinoshka.frontend.gwt.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * Holds film search result.
 * @author Aleh_Stsiapanau
 */
public class SearchResult implements IsSerializable {

  ArrayList<FilmInfo> filmList;

  /**
   * Creates film search result.
   * @param data found films
   */
  public SearchResult(ArrayList<FilmInfo> data) {
    this.filmList = data;
  }

  public SearchResult() {}

  public ArrayList<FilmInfo> getFilmList() {
    return filmList;
  }

  public void setFilmList(ArrayList<FilmInfo> filmList) {
    this.filmList = filmList;
  }

}
