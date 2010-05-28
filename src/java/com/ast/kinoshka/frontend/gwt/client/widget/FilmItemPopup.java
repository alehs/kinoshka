package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.event.CategoryChangeEvent;
import com.ast.kinoshka.frontend.gwt.event.GetByCategoryEvent;
import com.ast.kinoshka.frontend.gwt.event.LoadedEvent;
import com.ast.kinoshka.frontend.gwt.event.handler.CategoryChangeHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.GetByCategoryHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.LoadedHandler;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.PopupPanel;

public class FilmItemPopup extends PopupPanel {

  FilmItem filmItem;

  public FilmItemPopup(HandlerManager eventBus) {
    super(true);
    filmItem = new FilmItem(eventBus);
    filmItem.addStyleName("popup");
    add(filmItem);

    eventBus.addHandler(GetByCategoryEvent.TYPE, new GetByCategoryHandler() {
      @Override
      public void onGetByCategory(GetByCategoryEvent event) {
        FilmItemPopup.this.hide();
      }
    });

    eventBus.addHandler(CategoryChangeEvent.TYPE, new CategoryChangeHandler() {
      @Override
      public void onCategoryChange(CategoryChangeEvent event) {
        FilmItemPopup.this.hide();
      }
    });

    eventBus.addHandler(LoadedEvent.TYPE, new LoadedHandler() {
      @Override
      public void onLoaded(LoadedEvent event) {
        FilmItemPopup.this.hide();
      }
    });
  }

  public void setFilmInfoToDysplay(FilmInfo info) {
    filmItem.setFilmInfoToDisplay(info);
  }

}
