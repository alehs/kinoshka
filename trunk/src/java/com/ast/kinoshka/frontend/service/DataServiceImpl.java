package com.ast.kinoshka.frontend.service;

import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.service.AttributeDataService;
import com.ast.kinoshka.backend.service.FilmDataService;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.model.PagingConfig;
import com.ast.kinoshka.frontend.gwt.model.PagingResult;
import com.ast.kinoshka.frontend.gwt.model.SearchResult;
import com.ast.kinoshka.frontend.gwt.shared.DataService;
import com.ast.kinoshka.frontend.service.util.AttributeCategoryConverter;
import com.ast.kinoshka.frontend.service.util.AttributeDataConverter;
import com.ast.kinoshka.frontend.service.util.FilmDataConverter;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DataServiceImpl extends BaseServiceServlet implements DataService {
  public static final String ACTION = "dataService";

  @Inject
  private AttributeDataService attrService;
  @Inject
  private FilmDataService filmService;

  /**
   * {@inheritDoc}
   */
  @Override
  public PagingResult getFilms(PagingConfig config) {
    int count = filmService.getFilmsCount();
    List<Film> films = filmService.getFilms(config.getOffset(), config.getLimit(), count);
    return new PagingResult(FilmDataConverter.toModel(films), config.getOffset(), count);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ArrayList<FilmInfo> getFilmsByCategory(Category category, String categoryItemId) {
    return FilmDataConverter.toModel(filmService.getFilmsByAttribute(AttributeCategoryConverter
        .fromModel(category), Integer.valueOf(categoryItemId)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ArrayList<FilmAttributeInfo> getAttributeList(Category category) {
    return AttributeDataConverter.toModel(attrService
        .getAttributesFullInfo(AttributeCategoryConverter.fromModel(category)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteAttribute(Category category, String categoryItemId) {
    attrService.deleteAttribute(AttributeCategoryConverter.fromModel(category), Integer
        .valueOf(categoryItemId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SearchResult search(String keyword) {
    List<Film> films = filmService.search(keyword);
    return new SearchResult(FilmDataConverter.toModel(films));
  }

}
