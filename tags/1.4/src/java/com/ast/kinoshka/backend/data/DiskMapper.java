package com.ast.kinoshka.backend.data;

import com.ast.kinoshka.backend.model.Attribute;
import com.ast.kinoshka.backend.model.Film;

import java.util.List;

public interface DiskMapper extends AttributeMapper {

  @Override
  public List<Attribute> getList();

  @Override
  public List<Attribute> getAdvancedList();

  @Override
  List<Film> getFilms(Integer attributeId);
}
