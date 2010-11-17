package com.ast.kinoshka.backend.service;

import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.model.PageConfig;

import java.util.List;

/**
 * Service that manipulates film data.
 * @author Aleh_Stsiapanau
 */
public interface FilmDataService {

  /**
   * Returns total count of films.
   * @return total count of films
   */
  int getFilmsCount();

  /**
   * Returns page of films in descending order.
   * @param config paging configuration
   * @return page of films
   */
  List<Film> getFilms(PageConfig config);

  /**
   * Returns list of films with specified attribute.
   * @param attributeCategory film's attribute category
   * @param attributeId film's attribute id
   * @return film list by specified attribute
   */
  List<Film> getFilmsByAttribute(AttributeCategory attributeCategory, Integer attributeId);

  /**
   * Returns page of films with specified attribute.
   * @param attributeCategory film's attribute category
   * @param attributeId film's attribute id
   * @param config paging configuration
   * @return films page
   */
  List<Film> getFilmsByAttribute(AttributeCategory attributeCategory, Integer attributeId,
      PageConfig config);

  /**
   * Returns film by id.
   * @param filmId film id
   * @return film with specified id or null if nothing is found
   */
  Film getFilm(Integer filmId);

  /**
   * Stores new film item.
   * @param film film to store
   * @return stored film
   */
  Film addFilm(Film film);

  /**
   * Updates specified film data.
   * @param film to update
   * @return updated film
   */
  Film updateFilm(Film film);

  /**
   * Deletes film with specified id.
   * @param filmId to delete
   */
  void deleteFilm(Integer filmId);

  /**
   * Searches film by specified keyword.
   * @param filmId to delete
   */
  List<Film> search(String keyword);

}
