package com.ast.kinoshka.frontend.gwt.shared;

import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.model.PagingConfig;
import com.ast.kinoshka.frontend.gwt.model.PagingResult;
import com.ast.kinoshka.frontend.gwt.model.SearchResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

/**
 * Provides asynchronous version of DataService.
 * @author Aleh_Stsiapanau
 */
public interface DataServiceAsync {

  /**
   * Returns film attribute list of specified category with service data.
   * @param category to get list for
   * @param callback handler
   */
  void getAttributeList(Category category, AsyncCallback<ArrayList<FilmAttributeInfo>> callback);

  /**
   * Returns film list for specified attribute.
   * @param category film attribute category
   * @param attributeId film attribute ID
   * @param callback handler
   */
  void getFilmsByCategory(Category category, String attributeId,
      AsyncCallback<ArrayList<FilmInfo>> callback);

  /**
   * Returns paginated film list for specified attribute.
   * @param config pagination configuration
   * @param callback handler 
   */
  void getFilms(PagingConfig config, AsyncCallback<PagingResult> callback);

  /**
   * Deletes film attribute
   * @param categery attribute category
   * @param attibuteId attribute id
   */
  void deleteAttribute(Category categery, String categoryItemId, AsyncCallback<Void> callback);

  /**
   * Returns films search results. 
   * @param keyword to search for
   * @param callback handler
   */
  void search(String keyword, AsyncCallback<SearchResult> callback);

}
