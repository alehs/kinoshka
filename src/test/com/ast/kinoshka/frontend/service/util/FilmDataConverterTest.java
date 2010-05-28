package com.ast.kinoshka.frontend.service.util;

import static junit.framework.Assert.assertEquals;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.testcommon.ServiceTestUtil;
import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for FilmDataConverter
 * @author Aleh_Stsiapanau
 */
public class FilmDataConverterTest {

  private static final String FILM_NAME = "Film name";
  private static final String FILM_ORIG_NAME = "original name";
  private static final String FILM_DESCRIPTION = "desc";
  private static final String FILM_IMAGE_NAME = "image.gif";

  @Test
  public void testToModel() {
    Film data = new Film(0, FILM_ORIG_NAME);
    data.getActors().add(new Attribute(0, "a1"));
    data.getDirectors().add(new Attribute(0, "d1"));
    data.getDirectors().add(new Attribute(1, "d2"));
    data.getGenres().add(new Attribute(1, "g1"));

    FilmInfo model = FilmDataConverter.toModel.apply(data);
    compareFilms(data, model);

    data = ServiceTestUtil.createFilm(FILM_NAME, FILM_ORIG_NAME, FILM_DESCRIPTION, 100, 2001, 10, 4);
    model = FilmDataConverter.toModel.apply(data);
  }

  @Test
  public void testFromModel() {
    FilmInfo model = new FilmInfo(0, FILM_ORIG_NAME);
    model.setDescription(FILM_DESCRIPTION);
    model.setOriginalName(FILM_ORIG_NAME);
    model.setImageName(FILM_IMAGE_NAME);
    model.setBox(0);
    model.setTime(120);
    model.setDisk(10);
    model.setYear(1120);
    model.getGenres().add(new FilmAttributeInfo("0", "g1"));
    model.getGenres().add(new FilmAttributeInfo("1", "g2"));
    model.getGenres().add(new FilmAttributeInfo("0", "g3"));
    model.getActors().add(new FilmAttributeInfo("0", "a1"));

    Film data = FilmDataConverter.fromModel.apply(model);
    compareFilms(data, model);
  }

  @Test
  public void testToModelList() {
    ArrayList<Film> attrs = Lists.newArrayList();
    for (int i = 0; i < 5; i++) {
      attrs.add(new Film(i, FILM_ORIG_NAME));
    }
    ArrayList<FilmInfo> models = FilmDataConverter.toModel(attrs);
    assertEquals(attrs.size(), models.size());
    for (int i = 0; i < attrs.size(); i++) {
      compareFilms(attrs.get(i), models.get(i));
    }
  }

  @Test
  public void testFromModelList() {
    ArrayList<FilmInfo> models = Lists.newArrayList();
    for (int i = 0; i < 5; i++) {
      models.add(new FilmInfo(i, FILM_ORIG_NAME));
    }
    List<Film> attrs = FilmDataConverter.fromModel(models);
    assertEquals(attrs.size(), models.size());
    for (int i = 0; i < models.size(); i++) {
      compareFilms(attrs.get(i), models.get(i));
    }
  }

  public static void compareFilms(Film data, FilmInfo model) {
    assertEquals(data.getId(), model.getId());
    assertEquals(data.getName(), model.getName());
    assertEquals(data.getOriginalName(), model.getOriginalName());
    assertEquals(data.getImageName(), model.getImageName());
    assertEquals(data.getDescription(), model.getDescription());
    assertEquals(data.getYear(), model.getYear());
    assertEquals(data.getTime(), model.getTime());
    assertEquals(data.getBox(), model.getBox());
    assertEquals(data.getDisk(), model.getDisk());
    compareAttributes(data.getGenres(), model.getGenres());
    compareAttributes(data.getActors(), model.getActors());
    compareAttributes(data.getDirectors(), model.getDirectors());
    compareAttributes(data.getCountries(), model.getCountries());
  }

  public static void compareAttributes(List<Attribute> data, List<FilmAttributeInfo> models) {
    for (int i = 0; i < models.size(); i++) {
      assertEquals(models.get(i).getId(), data.get(i).getId().toString());
      assertEquals(models.get(i).getName(), data.get(i).getName());
      assertEquals(models.get(i).getItemsCount(), data.get(i).getItemsCount());
      assertEquals(models.get(i).getParam1(), data.get(i).getParam1());
    }
  }
}
