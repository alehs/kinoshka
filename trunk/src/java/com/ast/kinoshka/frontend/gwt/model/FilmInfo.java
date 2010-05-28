package com.ast.kinoshka.frontend.gwt.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * Film detail info.
 * @author Aleh_Stsiapanau
 */
public class FilmInfo implements IsSerializable {

  Integer id;
  Integer year;
  Integer time;
  Integer box;
  Integer disk;

  String name;
  String originalName;
  String imageName;
  String description;

  ArrayList<FilmAttributeInfo> genres = new ArrayList<FilmAttributeInfo>();
  ArrayList<FilmAttributeInfo> actors = new ArrayList<FilmAttributeInfo>();
  ArrayList<FilmAttributeInfo> directors = new ArrayList<FilmAttributeInfo>();
  ArrayList<FilmAttributeInfo> countries = new ArrayList<FilmAttributeInfo>();

  boolean uploaded;

  public FilmInfo() {/* default constructor to ensure serialization */}

  public FilmInfo(Integer id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getId() {
    return id;
  }
  public String getImageName() {
    return imageName;
  }
  public void setImageName(String image) {
    this.imageName = image;
  }
  public Integer getYear() {
    return year;
  }
  public void setYear(Integer year) {
    this.year = year;
  }
  public Integer getTime() {
    return time;
  }
  public void setTime(Integer time) {
    this.time = time;
  }
  public Integer getBox() {
    return box;
  }
  public void setBox(Integer box) {
    this.box = box;
  }
  public Integer getDisk() {
    return disk;
  }
  public void setDisk(Integer disk) {
    this.disk = disk;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getOriginalName() {
    return originalName;
  }
  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public ArrayList<FilmAttributeInfo> getGenres() {
    return genres;
  }
  public void setGenres(ArrayList<FilmAttributeInfo> genres) {
    this.genres = genres;
  }
  public ArrayList<FilmAttributeInfo> getActors() {
    return actors;
  }
  public void setActors(ArrayList<FilmAttributeInfo> actors) {
    this.actors = actors;
  }
  public ArrayList<FilmAttributeInfo> getDirectors() {
    return directors;
  }
  public void setDirectors(ArrayList<FilmAttributeInfo> directors) {
    this.directors = directors;
  }
  public ArrayList<FilmAttributeInfo> getCountries() {
    return countries;
  }
  public void setCountries(ArrayList<FilmAttributeInfo> countries) {
    this.countries = countries;
  }
  public boolean getUploaded() {
    return uploaded;
  }
  public void setUploaded(boolean uploaded) {
    this.uploaded = uploaded;
  }

}
