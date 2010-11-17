package com.ast.kinoshka.backend.service;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.testcommon.BaseServiceTest;
import com.ast.kinoshka.testcommon.ServiceTestUtil;
import com.google.inject.internal.Lists;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for CoreDataService class.
 * @author Aleh_Stsiapanau
 *
 */
public class AttributeDataServiceTest extends BaseServiceTest {
  private static final String ATTRIBUTE_NAME = "_attribute_1";
  public static final String FILM_NAME = "new film";

  private AttributeDataService service;

  @Before
  public void setUp() {
    this.service = injector.getInstance(AttributeDataService.class); 
  }

  @Test
  public void testGenreAttrLifeCycle() {
    testAttributes(AttributeCategory.GENRE, new Attribute(null, AttributeCategory.GENRE.name()
        + ATTRIBUTE_NAME));
  }

  @Test
  public void testActorAttrLifeCycle() {
    testAttributes(AttributeCategory.ACTOR, new Attribute(null, AttributeCategory.ACTOR.name()
        + ATTRIBUTE_NAME));
  }

  @Test
  public void testDirectorAttrLifeCycle() {
    testAttributes(AttributeCategory.DIRECTOR, new Attribute(null, AttributeCategory.DIRECTOR
        .name()
        + ATTRIBUTE_NAME));
  }

  @Test
  public void testCountryAttrLifeCycle() {
    Attribute toInsert = new Attribute(null, AttributeCategory.COUNTRY.name() + ATTRIBUTE_NAME);
    toInsert.setParam1("param");

    testAttributes(AttributeCategory.COUNTRY, toInsert);
  }

  @Test
  public void testEmptyAtts() {
    FilmDataService filmService = injector.getInstance(FilmDataService.class);
    Film film = ServiceTestUtil.createFilm(FILM_NAME);

    try {
      filmService.addFilm(film);

      List<Attribute> list= service.getAttributeList(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.YEAR);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.YEAR);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.GENRE);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.GENRE);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.ACTOR);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.ACTOR);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.COUNTRY);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.COUNTRY);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.DIRECTOR);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.DIRECTOR);
      assertEquals(0, list.size());

    } finally {
      filmService.deleteFilm(film.getId());
    }

  }
  
  @Test
  public void testGetYears() {
    FilmDataService filmService = injector.getInstance(FilmDataService.class);
    Film film1 = ServiceTestUtil.createFilm(FILM_NAME);
    film1.setYear(1111);
    Film film2 = ServiceTestUtil.createFilm(FILM_NAME);
    film2.setYear(2222);

    try {
      filmService.addFilm(film1);
      filmService.addFilm(film2);

      List<Attribute> list = service.getAttributeList(AttributeCategory.YEAR);
      assertEquals(2, list.size());

      list = service.getAttributes(AttributeCategory.YEAR);
      assertEquals(2, list.size());
      assertEquals(Integer.valueOf(1), list.get(0).getItemsCount());
      assertEquals(Integer.valueOf(1111), list.get(0).getId());
      assertEquals(list.get(0).getId().toString(), list.get(0).getName());

      assertEquals(Integer.valueOf(1), list.get(1).getItemsCount());
      assertEquals(Integer.valueOf(2222), list.get(1).getId());
      assertEquals(list.get(1).getId().toString(), list.get(1).getName());

      list = service.getAttributeList(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.DISK);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.DISK);
      assertEquals(0, list.size());

    } finally {
      if (film1 != null) {filmService.deleteFilm(film1.getId());}
      if (film2 != null) {filmService.deleteFilm(film2.getId());}
    }
  }

  @Test
  public void testGetYearsUnique() {
    FilmDataService filmService = injector.getInstance(FilmDataService.class);
    Film film1 = ServiceTestUtil.createFilm(FILM_NAME);
    film1.setYear(1111);
    Film film2 = ServiceTestUtil.createFilm(FILM_NAME);
    film2.setYear(1111);

    try {
      filmService.addFilm(film1);
      filmService.addFilm(film2);

      List<Attribute> list = service.getAttributeList(AttributeCategory.YEAR);
      assertEquals(1, list.size());

      list = service.getAttributes(AttributeCategory.YEAR);
      assertEquals(1, list.size());
      assertEquals(Integer.valueOf(2), list.get(0).getItemsCount());
      assertEquals(Integer.valueOf(1111), list.get(0).getId());
      assertEquals(list.get(0).getId().toString(), list.get(0).getName());

      list = service.getAttributeList(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.DISK);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.DISK);
      assertEquals(0, list.size());

    } finally {
      if (film1 != null) {filmService.deleteFilm(film1.getId());}
      if (film2 != null) {filmService.deleteFilm(film2.getId());}
    }
  }

  @Test
  public void testGetDisksUnique() {
    FilmDataService filmService = injector.getInstance(FilmDataService.class);
    Film film1 = ServiceTestUtil.createFilm(FILM_NAME);
    film1.setDisk(1111);
    Film film2 = ServiceTestUtil.createFilm(FILM_NAME);
    film2.setDisk(1111);

    try {
      filmService.addFilm(film1);
      filmService.addFilm(film2);

      List<Attribute> list = service.getAttributeList(AttributeCategory.DISK);
      assertEquals(1, list.size());

      list = service.getAttributes(AttributeCategory.DISK);
      assertEquals(1, list.size());
      assertEquals(Integer.valueOf(2), list.get(0).getItemsCount());
      assertEquals(Integer.valueOf(1111), list.get(0).getId());
      assertEquals(list.get(0).getId().toString(), list.get(0).getName());

      list = service.getAttributeList(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.BOX);
      assertEquals(0, list.size());
      list = service.getAttributeList(AttributeCategory.YEAR);
      assertEquals(0, list.size());
      list = service.getAttributes(AttributeCategory.YEAR);
      assertEquals(0, list.size());

    } finally {
      if (film1 != null) {filmService.deleteFilm(film1.getId());}
      if (film2 != null) {filmService.deleteFilm(film2.getId());}
    }
  }

  @Test
  public void testGetBoxes() {
    FilmDataService filmService = injector.getInstance(FilmDataService.class);
    List<Film> films = Lists.newArrayList();

    Film film = ServiceTestUtil.createFilm(FILM_NAME);
    film.setBox(1111);
    films.add(film);
    film = ServiceTestUtil.createFilm(FILM_NAME);
    film.setBox(2222);
    films.add(film);
    film = ServiceTestUtil.createFilm(FILM_NAME);
    film.setBox(2222);
    films.add(film);

    try {
      for (Film f: films) {
        filmService.addFilm(f);
      }

      List<Attribute> list = service.getAttributeList(AttributeCategory.BOX);
      assertEquals(2, list.size());

      list = service.getAttributes(AttributeCategory.BOX);
      assertEquals(2, list.size());
      assertEquals(Integer.valueOf(1), list.get(0).getItemsCount());
      assertEquals(Integer.valueOf(1111), list.get(0).getId());
      assertEquals(list.get(0).getId().toString(), list.get(0).getName());

      assertEquals(Integer.valueOf(2), list.get(1).getItemsCount());
      assertEquals(Integer.valueOf(2222), list.get(1).getId());
      assertEquals(list.get(1).getId().toString(), list.get(1).getName());

      list = service.getAttributes(AttributeCategory.YEAR);
      assertEquals(0, list.size());

    } finally {
      for (Film f : films) {
        if (film != null) {
          filmService.deleteFilm(f.getId());
        }
      }
    }
  }

  @Test
  public void testGetBoxesUnique() {
    FilmDataService filmService = injector.getInstance(FilmDataService.class);
    Film film1 = ServiceTestUtil.createFilm(FILM_NAME);
    film1.setBox(2222);
    Film film2 = ServiceTestUtil.createFilm(FILM_NAME);
    film2.setBox(2222);

    try {
      filmService.addFilm(film1);
      filmService.addFilm(film2);

      List<Attribute> list = service.getAttributeList(AttributeCategory.BOX);
      assertEquals(1, list.size());

      list = service.getAttributes(AttributeCategory.BOX);
      assertEquals(1, list.size());
      assertEquals(Integer.valueOf(2), list.get(0).getItemsCount());
      assertEquals(Integer.valueOf(2222), list.get(0).getId());
      assertEquals(list.get(0).getId().toString(), list.get(0).getName());

      list = service.getAttributes(AttributeCategory.YEAR);
      assertEquals(0, list.size());

    } finally {
      if (film1 != null) {filmService.deleteFilm(film1.getId());}
      if (film2 != null) {filmService.deleteFilm(film2.getId());}
    }
  }

  @Test
  public void testGetBoxesWithNull() {
    FilmDataService filmService = injector.getInstance(FilmDataService.class);
    Film film1 = ServiceTestUtil.createFilm(FILM_NAME);
    film1.setBox(2222);
    film1.setYear(1111);
    Film film2 = ServiceTestUtil.createFilm(FILM_NAME);
    film2.setDisk(1111);
    film2.setYear(1111);

    try {
      filmService.addFilm(film1);
      filmService.addFilm(film2);

      List<Attribute> list = service.getAttributeList(AttributeCategory.BOX);
      assertEquals(1, list.size());
      list = service.getAttributes(AttributeCategory.BOX);
      assertEquals(1, list.size());

      list = service.getAttributeList(AttributeCategory.DISK);
      assertEquals(1, list.size());
      list = service.getAttributes(AttributeCategory.DISK);
      assertEquals(1, list.size());

      list = service.getAttributeList(AttributeCategory.YEAR);
      assertEquals(1, list.size());
      list = service.getAttributes(AttributeCategory.YEAR);
      assertEquals(1, list.size());

    } finally {
      if (film1 != null) {filmService.deleteFilm(film1.getId());}
      if (film2 != null) {filmService.deleteFilm(film2.getId());}
    }
  }

  private void testAttributes(AttributeCategory category, Attribute toInsert) {
    Attribute inserted = null; 
    try {
      inserted = service.addAttribute(category, toInsert);
      assertNotNull(inserted.getId());
  
      List<Attribute> list = service.getAttributeList(category);
      assertEquals(1, list.size());
  
      list = service.getAttributes(category);
      assertEquals(1, list.size());
      for (Attribute attribute : list) {
        assertEquals(Integer.valueOf(0), attribute.getItemsCount());
      }
    } finally {
      if (inserted != null) {
        service.deleteAttribute(category, inserted.getId());
        List<Attribute> list = service.getAttributeList(category);
        assertEquals(0, list.size());
      }
    }

  }
  
}
