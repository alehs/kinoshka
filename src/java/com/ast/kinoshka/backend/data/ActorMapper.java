package com.ast.kinoshka.backend.data;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.model.FilmAttributeLink;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActorMapper extends AttributeMapper {

  @Override
  public void add(Attribute attribute);

  @Override
  List<Film> getFilms(Integer actorId);

  @Override
  @Delete("DELETE FROM ACTORS WHERE id = #{id}")
  void delete(Integer id);

  @Override
  List<Attribute> getListWithCount();

  @Override
  @Select("SELECT * FROM ACTORS ORDER BY NAME ASC")
  List<Attribute> getList();

  @Override
  @Insert("INSERT INTO FILM_ACTOR(FILM_ID, ACTOR_ID) VALUES(${filmId}, ${attributeId})")
  void mapFilm(FilmAttributeLink link);

  @Override
  @Delete("DELETE FROM FILM_ACTOR WHERE FILM_ID = #{filmId}")
  public void unmapFilm(Integer filmId);
}
