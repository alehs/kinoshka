package com.ast.kinoshka.frontend.gwt.shared;

import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

/**
 * Film edit service
 * @author Aleh_Stsiapanau
 */
@RemoteServiceRelativePath( "editService" )
public interface EditService extends RemoteService{

  /**
   * Deletes film with specified id and it's image
   * @param filmId id of film to delete
   * @param image image name
   */
  void deleteFilm(Integer filmId, String image);

  /**
   * Saves film data.
   * @param film data to save
   * @return saved film id
   */
  Integer saveFilm(FilmInfo film);

  /**
   * Returns list of distinct film attributes for specified category.
   * @param category to get list for
   */
  ArrayList<FilmAttributeInfo> getAttributeDictionary(Category category);

  /**
   * Adds new film attribute to dictionary
   * @param category attribute category
   * @param name attribute name
   * @return new added attribute
   */
  FilmAttributeInfo addAttribute(Category category, String name);
}
