package com.ast.kinoshka.testcommon;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.service.AttributeDataService;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;

import java.util.List;

/**
 * Helper for service tests
 * @author Aleh_Stsiapanau
 */
public abstract class ServiceTestUtil {

  public static Attribute addAttribute(AttributeDataService service, AttributeCategory category,
      Attribute attr) {
    return service.addAttribute(category, attr);
  }

  public static List<Attribute> getAttributes(AttributeDataService service, AttributeCategory category) {
    return service.getAttributeList(category);
  }

  public static List<Attribute> getAttributesFull(AttributeDataService service,
      AttributeCategory category) {
    return service.getAttributes(category);
  }

  public static void delete(AttributeDataService service, AttributeCategory category, Integer id) {
    service.deleteAttribute(category, id);
  }

  public static Film createFilm(String name) {
    return createFilm(name, null, null, null, null, null, null);
  }

  public static Film createFilm(String name, String originalName, String desc, Integer time,
      Integer year, Integer box, Integer disk) {
    Film f = new Film();
    f.setName(name);
    f.setImageName(name);
    f.setOriginalName(originalName);
    f.setDescription(desc);
    f.setBox(box);
    f.setDisk(disk);
    f.setDisk(disk);
    f.setTime(time);
    f.setYear(year);
    return f;
  }

  public static List<Attribute> getFilmAttributes(Film film, AttributeCategory category) {
    List<Attribute> attributes = null;
    switch (category) {
    case ACTOR:
      attributes = film.getActors();
      break;
    case COUNTRY:
      attributes = film.getCountries();
      break;
    case DIRECTOR:
      attributes = film.getDirectors();
      break;
    case GENRE:
      attributes = film.getGenres();
      break;
    }
    return attributes;
  }

  public static Attribute createAttribute(int id, String name, Integer itemCount, String param1) {
    Attribute attr = new Attribute(id, name);
    attr.setItemsCount(itemCount);
    attr.setParam1(param1);
    return attr;
  }

  public static FilmAttributeInfo createAttributeModel(String id, String name, int itemCount, String param1) {
    FilmAttributeInfo attr = new FilmAttributeInfo(id, name);
    attr.setItemsCount(itemCount);
    attr.setParam1(param1);
    return attr;
  }


}
