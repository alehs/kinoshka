package com.ast.kinoshka.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Film {

  Integer id;
  Integer year;
  Integer time;
  Integer box;
  Integer disk;

  String name;
  String originalName;
  String imageName;
  String description;

  List<Attribute> genres = new ArrayList<Attribute>();
  List<Attribute> actors = new ArrayList<Attribute>();
  List<Attribute> directors = new ArrayList<Attribute>();
  List<Attribute> countries = new ArrayList<Attribute>();

  public Film() {/* default constructor to ensure serialization */}

  public Film(Integer id, String name) {
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
  public List<Attribute> getGenres() {
    return genres;
  }
  public void setGenres(List<Attribute> genres) {
    this.genres = genres;
  }
  public List<Attribute> getActors() {
    return actors;
  }
  public void setActors(List<Attribute> actors) {
    this.actors = actors;
  }
  public List<Attribute> getDirectors() {
    return directors;
  }
  public void setDirectors(List<Attribute> directors) {
    this.directors = directors;
  }
  public List<Attribute> getCountries() {
    return countries;
  }
  public void setCountries(List<Attribute> countries) {
    this.countries = countries;
  }

  @Override
  public String toString() {
    return id + "#" + name;
  }
}
