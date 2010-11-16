package com.ast.kinoshka.backend.data;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.model.FilmAttributeLink;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper for GENRE table
 * @author Aleh_Stsiapanau
 */
public interface GenreMapper extends AttributeMapper {

  @Override
  @Select("SELECT * FROM GENRES ORDER BY NAME ASC")
  List<Attribute> getList();

  @Override
  List<Attribute> getAdvancedList();

  @Override
  List<Film> getFilms(Integer genreId);

  @Override
  @Delete("DELETE FROM GENRES WHERE id = #{id}")
  void delete(Integer id);

  @Override
  void add(Attribute attribute);

  @Override
  @Insert("INSERT INTO FILM_GENRE(FILM_ID, GENRE_ID) VALUES(#{filmId}, #{attributeId})")
  void mapFilm(FilmAttributeLink link);

  @Override
  @Delete("DELETE FROM FILM_GENRE WHERE FILM_ID = #{filmId}")
  public void unmapFilm(Integer filmId);

}
