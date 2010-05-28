package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.CategoryChangeHandler;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that notifies about grouping category change.
 * @author Aleh_Stsiapanau
 */
public class CategoryChangeEvent extends GwtEvent<CategoryChangeHandler> {

  public static final Type<CategoryChangeHandler> TYPE = new Type<CategoryChangeHandler>();
  private Category category;

  public CategoryChangeEvent(Category category) {
    this.category = category;
  }

  @Override
  protected void dispatch(CategoryChangeHandler handler) {
    handler.onCategoryChange(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<CategoryChangeHandler> getAssociatedType() {
    return TYPE;
  }

  public Category getCategory() {
    return category;
  }

}
