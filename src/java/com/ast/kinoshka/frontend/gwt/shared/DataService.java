package com.ast.kinoshka.frontend.gwt.shared;

import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.model.PagingConfig;
import com.ast.kinoshka.frontend.gwt.model.PagingResult;
import com.ast.kinoshka.frontend.gwt.model.SearchResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

/**
 * Common data service.
 * @author Aleh_Stsiapanau
 */
@RemoteServiceRelativePath( "dataService" )
public interface DataService extends RemoteService {

  /**
   * Returns film attribute list of specified category with service data.
   * @param category to get list for
   */
  ArrayList<FilmAttributeInfo> getAttributeList(Category category);

  /**
   * Returns film list for specified attribute. 
   * @param category film attribute category
   * @param attributeId film attribute ID
   */
  ArrayList<FilmInfo> getFilmsByCategory(Category category, String categoryItemId);

  /**
   * Returns paginated film list.
   * @param config pagination configuration
   */
  PagingResult getFilms(PagingConfig config);
  
  /**
   * Deletes film attribute
   * @param categery attribute category
   * @param attibuteId attribute id
   */
  void deleteAttribute(Category categery, String attibuteId);

  /**
   * Returns films search results. 
   * @param keyword to search for
   */
  SearchResult search(String keyword);

}
