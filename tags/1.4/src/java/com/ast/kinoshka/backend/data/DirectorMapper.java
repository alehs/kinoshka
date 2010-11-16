package com.ast.kinoshka.backend.data;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.model.FilmAttributeLink;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DirectorMapper extends AttributeMapper {

  @Override
  @Select("SELECT * FROM DIRECTORS ORDER BY NAME ASC")
  List<Attribute> getList();

  @Override
  List<Attribute> getAdvancedList();

  @Override
  List<Film> getFilms(Integer directorId);

  @Override
  @Delete("DELETE FROM DIRECTORS WHERE id = #{id}")
  void delete(Integer id);

  @Override
  void add(Attribute attribute);

  @Override
  @Insert("INSERT INTO FILM_DIRECTOR(FILM_ID, DIRECTOR_ID) VALUES(#{filmId}, #{attributeId})")
  void mapFilm(FilmAttributeLink link);

  @Override
  @Delete("DELETE FROM FILM_DIRECTOR WHERE FILM_ID = #{filmId}")
  void unmapFilm(Integer filmId);

}
