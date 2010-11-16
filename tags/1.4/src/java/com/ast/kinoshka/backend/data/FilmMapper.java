package com.ast.kinoshka.backend.data;

import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.model.PageConfig;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FilmMapper {

  List<Film> getPage(PageConfig edges);

  List<Film> search(String keyword);

  Film getById(Integer filmId);

  void add(Film film);

  void update(Film film);

  @Delete("DELETE FROM FILMS WHERE id = #{filmId}")
  void delete(Integer filmId);

  @Delete("DELETE FROM FILMS")
  void deleteAll();

  @Select("SELECT COUNT(*) FROM FILMS")
  Integer getCount();

}

