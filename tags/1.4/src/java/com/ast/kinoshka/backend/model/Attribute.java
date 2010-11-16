package com.ast.kinoshka.backend.model;

/**
 * Film attribute (like genre, actor, director and so on); 
 * @author Aleh_Stsiapanau
 */
public class Attribute {
  Integer id;
  String name;
  Integer itemsCount;
  String param1;

  public Attribute() {/* default constructor to ensure serialization */}

  public Attribute(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getItemsCount() {
    return itemsCount;
  }
  public void setItemsCount(Integer param) {
    this.itemsCount = param;
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
    if (!(obj instanceof Attribute)) {
      return false;
    }
    return id.equals(((Attribute)obj).id);
  }

  @Override
  public String toString() {
    return id + ";" + name;
  }
}
