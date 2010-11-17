package com.ast.kinoshka.backend.data;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.model.FilmAttributeLink;
import com.ast.kinoshka.backend.model.PageConfig;

import java.util.List;

/**
 * Common attribute mapper
 * @author Aleh_Stsiapanau
 */
public interface AttributeMapper {

  List<Film> getFilms(Integer attributeId);

  List<Film> getFilmsPage(PageConfig edges);

  List<Attribute> getListWithCount();

  List<Attribute> getList();

  List<Attribute> getPage(PageConfig edges);

  void add(Attribute attribute);

  void delete(Integer id);

  void mapFilm(FilmAttributeLink link);

  void unmapFilm(Integer filmId);

}
