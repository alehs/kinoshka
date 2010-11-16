package com.ast.kinoshka.backend.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import com.ast.kinoshka.backend.data.AttributeMapper;
import com.ast.kinoshka.backend.data.FilmMapper;
import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.AttributeCategory;
import com.ast.kinoshka.backend.model.Film;
import com.ast.kinoshka.backend.model.FilmAttributeLink;
import com.ast.kinoshka.backend.model.PageConfig;
import com.ast.kinoshka.backend.service.FilmDataService;
import com.ast.kinoshka.backend.service.util.AttributeMapperUtil;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * CoreFilmDataService default implementation.
 * @author Aleh_Stsiapanau
 *
 */
public class FilmDataServiceImpl implements FilmDataService {

  private SqlSessionFactory factory;

  @Inject
  public FilmDataServiceImpl(SqlSessionFactory factory) {
    this.factory = factory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Film addFilm(Film film) {
    checkNotNull(film);
    checkState(film.getId() == null);

    SqlSession session = factory.openSession(true);
    try {
      FilmMapper mapper = session.getMapper(FilmMapper.class);
      mapper.add(film);
      mapFilmAttributes(session, film);
    } finally {
      session.close();
    }
    return film;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Film updateFilm(Film film) {
    checkNotNull(film);
    checkNotNull(film.getId());
    checkNotNull(film.getName());

    SqlSession session = factory.openSession(true);
    try {
      FilmMapper mapper = session.getMapper(FilmMapper.class);
      mapper.update(film);
      mapFilmAttributes(session, film);
    } finally {
      session.close();
    }
    return film;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteFilm(Integer filmId) {
    checkNotNull(filmId);
    SqlSession session = factory.openSession(true);
    try {
      unmapFilmAttributes(session, filmId);
      FilmMapper mapper = session.getMapper(FilmMapper.class);
      mapper.delete(filmId);
    } finally {
      session.close();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getFilmsCount() {
    SqlSession session = factory.openSession();
    try {
      FilmMapper mapper = session.getMapper(FilmMapper.class);
      return mapper.getCount();
    } finally {
      session.close();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Film> getFilms(int offset, int limit, int total) {
    List<Film> result = null;
    SqlSession session = factory.openSession();
    try {
      FilmMapper mapper = session.getMapper(FilmMapper.class);
      // This is workaround to get
      // film list in descending order as derby does not support both ORDER BY
      // in subqueries and over() function and LIMIT keyword. 
      result = mapper.getPage(new PageConfig(total - (offset + limit), total - offset + 1));
    } finally {
      session.close();
    }

    if (result == null) {
      result = Lists.newArrayList();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Film> getFilmsByAttribute(AttributeCategory attributeCategory, Integer attributeId) {
    checkNotNull(attributeCategory);
    checkNotNull(attributeId);

    List<Film> result = null;
    SqlSession session = factory.openSession();
    try {
      AttributeMapper mapper = AttributeMapperUtil
          .getMapperByCategory(session, attributeCategory);
      result = mapper.getFilms(attributeId);
    } finally {
      session.close();
    }

    if (result == null) {
      result = Lists.newArrayList();
    }
    return result;
  }

  @Override
  public Film getFilm(Integer filmId) {
    checkNotNull(filmId);

    Film result = null;
    SqlSession session = factory.openSession();
    try {
      FilmMapper mapper = session.getMapper(FilmMapper.class);
      result = mapper.getById(filmId);
    } finally {
      session.close();
    }
    return result;
  }

  @Override
  public List<Film> search(String keyword) {
    checkNotNull(keyword);
    List<Film> result = null;
    SqlSession session = factory.openSession();
    try {
      FilmMapper mapper = session.getMapper(FilmMapper.class);
      result = mapper.search(keyword + "%");
    } finally {
      session.close();
    }

    if (result == null) {
      result = Lists.newArrayList();
    }
    return result;
  }

  private final void mapFilmAttributes(SqlSession session, Film film) {
    mapAttribute(session, AttributeCategory.ACTOR, film.getId(), film.getActors());
    mapAttribute(session, AttributeCategory.DIRECTOR, film.getId(), film.getDirectors());
    mapAttribute(session, AttributeCategory.GENRE, film.getId(), film.getGenres());
    mapAttribute(session, AttributeCategory.COUNTRY, film.getId(), film.getCountries());
  }

  private final void unmapFilmAttributes(SqlSession session, Integer filmId) {
    unmapAttribute(session, AttributeCategory.ACTOR, filmId);
    unmapAttribute(session, AttributeCategory.DIRECTOR, filmId);
    unmapAttribute(session, AttributeCategory.GENRE, filmId);
    unmapAttribute(session, AttributeCategory.COUNTRY, filmId);
  }

  private final void mapAttribute(SqlSession session, AttributeCategory category, Integer id,
      List<Attribute> attributes) {
    if (attributes != null) {
      AttributeMapper mapper = AttributeMapperUtil.getMapperByCategory(session, category);
      mapper.unmapFilm(id);

      // TODO: update all at once?
      for (Attribute attribute : attributes) {
        mapper.mapFilm(new FilmAttributeLink(id, attribute.getId()));
      }
    }
  }

  private final void unmapAttribute(SqlSession session, AttributeCategory category, Integer id) {
    AttributeMapper mapper = AttributeMapperUtil.getMapperByCategory(session, category);
    mapper.unmapFilm(id);
  }

}
