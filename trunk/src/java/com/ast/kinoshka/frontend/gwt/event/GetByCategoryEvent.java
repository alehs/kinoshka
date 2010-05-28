package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.GetByCategoryHandler;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Fetching values for specified category
 * @author Aleh_Stsiapanau
 *
 */
public class GetByCategoryEvent extends GwtEvent<GetByCategoryHandler> {

  public static final Type<GetByCategoryHandler> TYPE = new Type<GetByCategoryHandler>();
  private Category category;
  private String value;
  private String name;

  public GetByCategoryEvent(Category category, String value) {
    this.category = category;
    this.value = value;
  }

  public GetByCategoryEvent(Category category, String value, String name) {
    this.category = category;
    this.value = value;
    this.name = name;
  }

  @Override
  protected void dispatch(GetByCategoryHandler handler) {
    handler.onGetByCategory(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<GetByCategoryHandler> getAssociatedType() {
    return TYPE;
  }

  public Category getCategory() {
    return category;
  }

  public String getCategoryItem() {
    return value;
  }

  public String getCategoryItemName() {
    return name;
  }
}
