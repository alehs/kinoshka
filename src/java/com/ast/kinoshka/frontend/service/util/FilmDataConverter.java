package com.ast.kinoshka.frontend.service.util;

import static com.google.common.collect.Iterables.transform;

import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that converts film data domain model to UI model. 
 * @author Aleh_Stsiapanau
 */
public class FilmDataConverter {

  private FilmDataConverter() { /* prevent instantiation.*/}

  public static final Function<Film, FilmInfo> toModel = new Function<Film, FilmInfo>() {
    @Override
    public FilmInfo apply(Film attr) {
      FilmInfo model = new FilmInfo(attr.getId(), attr.getName());
      model.setOriginalName(attr.getOriginalName());
      model.setDescription(attr.getDescription());
      model.setImageName(attr.getImageName());
      model.setBox(attr.getBox());
      model.setYear(attr.getYear());
      model.setTime(attr.getTime());
      model.setDisk(attr.getDisk());
      model.setActors(AttributeDataConverter.toModel(attr.getActors()));
      model.setDirectors(AttributeDataConverter.toModel(attr.getDirectors()));
      model.setGenres(AttributeDataConverter.toModel(attr.getGenres()));
      model.setCountries(AttributeDataConverter.toModel(attr.getCountries()));
      return model;
    }
  };

  public static final Function<FilmInfo, Film> fromModel = new Function<FilmInfo, Film>() {
    @Override
    public Film apply(FilmInfo attr) {
      Film model = new Film(attr.getId(), attr.getName());
      model.setOriginalName(attr.getOriginalName());
      model.setDescription(attr.getDescription());
      model.setImageName(attr.getImageName());
      model.setBox(attr.getBox());
      model.setYear(attr.getYear());
      model.setTime(attr.getTime());
      model.setDisk(attr.getDisk());
      model.setActors(AttributeDataConverter.fromModel(attr.getActors()));
      model.setDirectors(AttributeDataConverter.fromModel(attr.getDirectors()));
      model.setGenres(AttributeDataConverter.fromModel(attr.getGenres()));
      model.setCountries(AttributeDataConverter.fromModel(attr.getCountries()));
      return model;
    }
  };

  /**
   * Converts films data domain model to UI model.
   * @param films list of films to convert to list of UI film data
   * @return list of UI film data
   */
  public static ArrayList<FilmInfo> toModel(List<Film> films) {
    return Lists.newArrayList(transform(films, toModel));
  }

  /**
   * Converts list of film UI models to domain models.
   * @param films list of films data to convert to list of domain models
   * @return list of domain models
   */
  public static List<Film> fromModel(ArrayList<FilmInfo> films) {
    return Lists.newArrayList(transform(films, fromModel));
  }
}
