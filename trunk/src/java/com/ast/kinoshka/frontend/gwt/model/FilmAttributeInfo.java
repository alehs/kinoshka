package com.ast.kinoshka.frontend.gwt.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Holds abstract film's variable number value attribute like genre, actor, director and so on.
 * @author Aleh_Stsiapanau
 */
public class FilmAttributeInfo implements IsSerializable {

  String id;
  String name;
  Integer itemsCount;
  String param1;

  public FilmAttributeInfo() {/* default constructor to ensure serialization */}

  public FilmAttributeInfo(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public Integer getItemsCount() {
    return itemsCount;
  }
  public void setItemsCount(Integer param) {
    this.itemsCount = param;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getParam1() {
    return param1;
  }
  public void setParam1(String param1) {
    this.param1 = param1;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof FilmAttributeInfo)) {
      return false;
    }
    return id.equals(((FilmAttributeInfo)obj).id);
  }
}
