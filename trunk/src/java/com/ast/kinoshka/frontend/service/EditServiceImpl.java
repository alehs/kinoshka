package com.ast.kinoshka.frontend.service;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.service.AttributeDataService;
import com.ast.kinoshka.backend.service.FilmDataService;
import com.ast.kinoshka.backend.service.ImageService;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.shared.EditService;
import com.ast.kinoshka.frontend.service.util.AttributeCategoryConverter;
import com.ast.kinoshka.frontend.service.util.AttributeDataConverter;
import com.ast.kinoshka.frontend.service.util.FilmDataConverter;
import com.google.inject.Inject;

import java.util.ArrayList;

/**
 * EditService servlet.
 * @author Aleh_Stsiapanau
 */
@SuppressWarnings("serial")
public class EditServiceImpl extends BaseServiceServlet implements EditService {
  public static final String ACTION = "editService";

  @Inject
  private AttributeDataService attrService;
  @Inject
  private FilmDataService filmService;
  @Inject
  private ImageService imageService;

  @Override
  public void deleteFilm(Integer filmId, String image) {
    filmService.deleteFilm(filmId);
    if (image != null) {
      imageService.deleteImage(image);
    }
  }

  @Override
  public FilmAttributeInfo addAttribute(final Category category, final String name) {
    return AttributeDataConverter.toModel.apply(attrService.addAttribute(AttributeCategoryConverter
        .fromModel(category), new Attribute(null, name)));
  }

  @Override
  public Integer saveFilm(final FilmInfo film) {
    Film toProcess = FilmDataConverter.fromModel.apply(film);

    // save uploaded image
    if (film.getUploaded()) {
      if (film.getId() != null && film.getImageName() != null) {
        imageService.deleteImage(film.getImageName());
      }
      toProcess.setImageName(imageService.acceptTmpImage());
    }

    if (film.getId() == null) {
      toProcess = filmService.addFilm(toProcess);
    } else {
      toProcess = filmService.updateFilm(toProcess);
    }
    return toProcess.getId();
  }

  @Override
  public ArrayList<FilmAttributeInfo> getAttributeDictionary(Category category) {
    return AttributeDataConverter.toModel(attrService.getAttributeList(AttributeCategoryConverter
        .fromModel(category)));
  }

}
