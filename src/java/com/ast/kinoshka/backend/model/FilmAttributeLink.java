package com.ast.kinoshka.backend.model;

public class FilmAttributeLink {

  private Integer filmId;
  private Integer attributeId;

  public FilmAttributeLink(Integer filmid, Integer attrid) {
    this.filmId = filmid;
    this.attributeId = attrid;
  }

  public Integer getFilmId() {
    return filmId;
  }
  public void setFilmId(Integer filmId) {
    this.filmId = filmId;
  }
  public Integer getAttributeId() {
    return attributeId;
  }
  public void setAttributeId(Integer attributeId) {
    this.attributeId = attributeId;
  }

}
