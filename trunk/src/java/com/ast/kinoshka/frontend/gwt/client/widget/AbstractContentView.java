package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.client.Content.IContentWidget;
import com.ast.kinoshka.frontend.gwt.event.LoadingEvent;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.shared.DataService;
import com.ast.kinoshka.frontend.gwt.shared.DataServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;

/**
 * Base class for widget in content area.
 * @author Aleh_Stsiapanau
 */
public abstract class AbstractContentView extends Composite implements IContentWidget {

  protected HandlerManager eventBus;
  protected Category currentCategory;
  protected String currentParam;
  //private boolean isDirty = false;

  //TODO: inject
  protected DataServiceAsync dataService = (DataServiceAsync) GWT.create(DataService.class);

  public AbstractContentView(HandlerManager eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public void load(Category category, String categoryItem) {
    this.eventBus.fireEvent(new LoadingEvent());
    doLoad(category, categoryItem);
    currentCategory = category;
    currentParam = categoryItem;
  }

  @Override
  public void makeDirty() {
    //this.isDirty = true;
  }

  @Override
  public void setWidgetTitle(String title) {
    //do nothing
  }

  /**
   * Loads data for the given attributes.
   * @param category film attribute category
   * @param categoryItem category item
   */
  abstract void doLoad(Category category, String categoryItem);

}
