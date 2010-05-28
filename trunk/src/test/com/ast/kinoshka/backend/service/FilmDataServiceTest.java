package com.ast.kinoshka.backend.service;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.testcommon.BaseServiceTest;
import com.ast.kinoshka.testcommon.ServiceTestUtil;
import com.google.inject.internal.Lists;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit tests for CoreFilmDataService.
 * 
 * @author Aleh_Stsiapanau
 * 
 */
public class FilmDataServiceTest extends BaseServiceTest {

  public static final String FILM_NAME = "new film";
  public static final String FILM_UPDATED_NAME = "updated film";

  private FilmDataService service;
  private AttributeDataService attrService;

  @Before
  public void setUp() {
    this.service = injector.getInstance(FilmDataService.class);
    this.attrService = injector.getInstance(AttributeDataService.class);
  }

  @After
  public void tearDown() {
    // ((CoreFilmDataServiceImpl)service).deleteAll();
  }

  @Test
  public void testAdd() {
    Film f = insertFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 0, 0, null);
    try {
      // check added
      assertNotNull(f.getId());

      Film added = service.getFilm(f.getId());
      assertEquals(FILM_NAME, added.getName());
      assertEquals(FILM_NAME, added.getImageName());
      assertEquals(FILM_NAME, added.getOriginalName());
      assertEquals(FILM_NAME, added.getDescription());
      assertEquals(Integer.valueOf(0), added.getBox());
      assertEquals(Integer.valueOf(0), added.getYear());
      assertEquals(Integer.valueOf(0), added.getTime());
      assertNull("Should be null", added.getDisk());

    } finally {
      service.deleteFilm(f.getId());
    }

  }

  @Test
  public void testUpdate() {
    Film f = insertFilm(FILM_NAME, FILM_NAME, null, null, null, 0, null);
    try {
      // update film
      f.setName(FILM_UPDATED_NAME);
      f.setBox(-1);
      f.setDisk(-1);
      f.setDescription(null);
      service.updateFilm(f);

      assertEquals(FILM_UPDATED_NAME, f.getName());
      assertNull(f.getDescription());
      assertEquals(Integer.valueOf(-1), f.getBox());
      assertEquals(Integer.valueOf(-1), f.getDisk());
      assertNull(f.getYear());
      assertNull(f.getTime());

      Film updated = service.getFilm(f.getId());
      // check updated
      assertEquals(f.getId(), updated.getId());
      assertNull(updated.getDescription());
      assertEquals(Integer.valueOf(-1), updated.getBox());
      assertEquals(Integer.valueOf(-1), updated.getDisk());
      assertNull(updated.getYear());
      assertNull(updated.getTime());

    } finally {
      service.deleteFilm(f.getId());
    }
  }

  @Test
  public void testUpdateAttributes() {
    Attribute actor1 = null;
    Attribute actor2 = null;
    Film film = null;
    try {
      actor1 = attrService.addAttribute(AttributeCategory.ACTOR, new Attribute(null, "Actor_Map1"));
      actor2 = attrService.addAttribute(AttributeCategory.ACTOR, new Attribute(null, "Actor_Map2"));

      film = insertFilm(FILM_NAME, null, null, null, null, null, null);
      film.getActors().add(actor1);
      film.getActors().add(actor2);

      service.updateFilm(film);

      Film updated = service.getFilm(film.getId());
      assertEquals(2, updated.getActors().size());
      assertEquals(0, updated.getGenres().size());
      assertEquals(0, updated.getDirectors().size());
      assertEquals(0, updated.getCountries().size());

      assertEquals(actor1.getId(), updated.getActors().get(0).getId());
      assertEquals(actor2.getId(), updated.getActors().get(1).getId());

    } finally {
      service.deleteFilm(film.getId());
      attrService.deleteAttribute(AttributeCategory.ACTOR, actor1.getId());
      attrService.deleteAttribute(AttributeCategory.ACTOR, actor2.getId());
    }
  }

  @Test
  public void testDelete() {
    Film f = insertFilm(FILM_NAME, FILM_NAME, null, null, null, 0, null);
    service.deleteFilm(f.getId());
    Film deleted = service.getFilm(f.getId());
    assertNull(deleted);
  }

  @Test
  public void testGetFilmsCount() {
    assertEquals(0, service.getFilmsCount());

    Film f1 = insertFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 0, 0, null);
    Film f2 = null;
    try {
      assertEquals(1, service.getFilmsCount());
      f2 = insertFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 0, 0, null);
      assertEquals(2, service.getFilmsCount());
    } finally {
      if (f1!= null) {service.deleteFilm(f1.getId()); }
      if (f2!= null) {service.deleteFilm(f2.getId()); }
    }
    assertEquals(0, service.getFilmsCount());
  }
  

  @Test(expected = NullPointerException.class)
  public void testGetFilmFail() {
    Film selected = service.getFilm(-1);
    assertNull(selected);
    service.getFilm(null);
  }

  @Test
  public void testGetFilmsByActor() {
    testGetFilmsByMappedAttribute(AttributeCategory.ACTOR);
  }

  @Test
  public void testGetFilmsByGenre() {
    testGetFilmsByMappedAttribute(AttributeCategory.GENRE);
  }

  @Test
  public void testGetFilmsByDirector() {
    testGetFilmsByMappedAttribute(AttributeCategory.DIRECTOR);
  }

  @Test
  public void testGetFilmsByCountry() {
    testGetFilmsByMappedAttribute(AttributeCategory.COUNTRY);
  }

  @Test
  public void testGetFilmsByYear() {
    Film film1 = null;
    Film film2 = null;
    Film film3 = null;

    try {
      film1 = ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2000, 0, 0);
      film2 = ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2004, 0, 0);
      film3 = ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2000, 0, 0);
      service.addFilm(film1);
      service.addFilm(film2);
      service.addFilm(film3);

      List<Film> films = service.getFilmsByAttribute(AttributeCategory.YEAR, 2000);
      assertEquals(2, films.size());
      assertEquals(film1.getId(), films.get(0).getId());
      assertEquals(film3.getId(), films.get(1).getId());
      films = service.getFilmsByAttribute(AttributeCategory.YEAR, 2003);
      assertEquals(0, films.size());
      films = service.getFilmsByAttribute(AttributeCategory.YEAR, 2004);
      assertEquals(1, films.size());
      assertEquals(film2.getId(), films.get(0).getId());

    } finally {
      if (film1 != null) {
        service.deleteFilm(film1.getId());
      }
      if (film2 != null) {
        service.deleteFilm(film2.getId());
      }
      if (film3 != null) {
        service.deleteFilm(film3.getId());
      }
    }
  }

  @Test
  public void testGetFilmsByBox() {
    Film film1 = null;
    Film film2 = null;
    Film film3 = null;

    try {
      film1 = ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2000, 10, 0);
      film2 = ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2004, 10, 0);
      film3 = ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2000, 12, 0);
      service.addFilm(film1);
      service.addFilm(film2);
      service.addFilm(film3);

      List<Film> films = service.getFilmsByAttribute(AttributeCategory.BOX, 10);
      assertEquals(2, films.size());
      assertEquals(film1.getId(), films.get(0).getId());
      assertEquals(film2.getId(), films.get(1).getId());
      films = service.getFilmsByAttribute(AttributeCategory.BOX, 11);
      assertEquals(0, films.size());
      films = service.getFilmsByAttribute(AttributeCategory.BOX, 12);
      assertEquals(1, films.size());
      assertEquals(film3.getId(), films.get(0).getId());

    } finally {
      if (film1 != null) { service.deleteFilm(film1.getId()); }
      if (film2 != null) { service.deleteFilm(film2.getId()); }
      if (film3 != null) { service.deleteFilm(film3.getId()); }
    }
  }

  @Test
  public void testGetFilmsPage() {
    List<Film> newFilms = Lists.newArrayList();

    try {
      List<Film> films = service.getFilms(0, 2);
      assertEquals(0, films.size());

      newFilms.add(service.addFilm(ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2000, 10, 0)));
      newFilms.add(service.addFilm(ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2004, 10, 0)));
      newFilms.add(service.addFilm(ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2000, 12, 0)));

      films = service.getFilms(0, 2);
      assertEquals(2, films.size());
      assertEquals(newFilms.get(0).getId(), films.get(0).getId());
      assertEquals(newFilms.get(1).getId(), films.get(1).getId());

      films = service.getFilms(1, 2);
      assertEquals(2, films.size());
      assertEquals(newFilms.get(1).getId(), films.get(0).getId());
      assertEquals(newFilms.get(2).getId(), films.get(1).getId());

      films = service.getFilms(1, 1);
      assertEquals(1, films.size());
      assertEquals(newFilms.get(1).getId(), films.get(0).getId());

      films = service.getFilms(0, 10);
      assertEquals(3, films.size());
      assertEquals(newFilms.get(0).getId(), films.get(0).getId());
      assertEquals(newFilms.get(1).getId(), films.get(1).getId());
      assertEquals(newFilms.get(2).getId(), films.get(2).getId());

    } finally {
      for (Film film : newFilms) {
        if (film != null) {
          service.deleteFilm(film.getId());
        }
      }
    }
  }

  @Test
  public void testSearch() {
    List<Film> films = Lists.newArrayList();
    films.add(service.addFilm(
        ServiceTestUtil.createFilm(FILM_NAME, FILM_NAME, FILM_NAME, 0, 2000,10, 0)));
    films.add(service.addFilm(
        ServiceTestUtil.createFilm(FILM_NAME + 2, FILM_NAME, FILM_NAME, 0, 2000,10, 0)));
    films.add(service.addFilm(
        ServiceTestUtil.createFilm(FILM_NAME + 3, FILM_NAME, FILM_NAME, 0, 2000,10, 0)));
    films.add(service.addFilm(
        ServiceTestUtil.createFilm("Another", FILM_NAME, FILM_NAME, 0, 2000,10, 0)));

    try {
      List<Film> found = service.search(FILM_NAME);
      assertEquals(4, found.size());
      found = service.search("Another");
      assertEquals(1, found.size());
      found = service.search("NONE");
      assertEquals(0, found.size());
    } finally {
      for (Film film : films) {
        if (film != null) {
          service.deleteFilm(film.getId());
        }
      }
    }

  }

  
  private Film insertFilm(String name, String originalName, String desc, Integer time,
      Integer year, Integer box, Integer disk) {
    return service.addFilm(ServiceTestUtil.createFilm(name, originalName, desc, time, year, box,
        disk));
  }

  private void testGetFilmsByMappedAttribute(AttributeCategory category) {
    Attribute attribute1 = null;
    Attribute attribute2 = null;
    Film film = null;

    try {
      attribute1 = new Attribute(null, "attr_map1");
      attribute1.setParam1("param1");
      attribute2 = new Attribute(null, "attr_map2");
      attribute2.setParam1("param1");

      attrService.addAttribute(category, attribute1);
      attrService.addAttribute(category, attribute2);
      film = ServiceTestUtil.createFilm(FILM_NAME);

      addAttributeToFilm(category, film, attribute1, attribute2);
      service.addFilm(film);

      List<Film> films = service.getFilmsByAttribute(category, attribute1.getId());
      assertEquals(1, films.size());
      List<Attribute> attributes = ServiceTestUtil.getFilmAttributes(films.get(0), category);
      assertEquals(2, attributes.size());
      assertEquals(attribute1.getId(), attributes.get(0).getId());
      assertEquals(attribute2.getId(), attributes.get(1).getId());

    } finally {
      if (film != null) {
        service.deleteFilm(film.getId());
      }
      if (attribute1 != null) {
        attrService.deleteAttribute(category, attribute1.getId());
      }
      if (attribute2 != null) {
        attrService.deleteAttribute(category, attribute2.getId());
      }
    }
  }

  private void addAttributeToFilm(AttributeCategory category, Film film, Attribute... attributes) {
    List<Attribute> filmAttrs = ServiceTestUtil.getFilmAttributes(film, category);
    for (Attribute attribute : attributes) {
      filmAttrs.add(attribute);
    }
  }
  
}

