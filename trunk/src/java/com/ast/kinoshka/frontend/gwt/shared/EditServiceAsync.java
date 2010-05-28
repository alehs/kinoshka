package com.ast.kinoshka.frontend.gwt.shared;

import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

/**
 * Provides asynchronous version of EditService. 
 * @author Aleh_Stsiapanau
 */
public interface EditServiceAsync {

  /**
   * Saves film data.
   * @param film data to save
   * @return saved film id
   */
  void saveFilm(FilmInfo film, AsyncCallback<Integer> callback);

  /**
   * Returns list of distinct film attributes for specified category.
   * @param category to get list for
   * @param callback handler
   */
  void getAttributeDictionary(Category category,
      AsyncCallback<ArrayList<FilmAttributeInfo>> callback);

  /**
   * Deletes film with specified id and it's image
   * @param filmId id of film to delete
   * @param image image name
   */
  void deleteFilm(Integer filmId, String image, AsyncCallback<Void> callback);

  /**
   * Adds new film attribute to dictionary
   * @param category attribute category
   * @param name attribute name
   * @return new added attribute
   */
  void addAttribute(Category category, String name, AsyncCallback<FilmAttributeInfo> callback);

}
