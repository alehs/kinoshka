package com.ast.kinoshka.frontend.gwt.client;

import com.ast.kinoshka.frontend.gwt.event.CategoryChangeEvent;
import com.ast.kinoshka.frontend.gwt.event.FilmEditEvent;
import com.ast.kinoshka.frontend.gwt.event.GetByCategoryEvent;
import com.ast.kinoshka.frontend.gwt.event.ViewModeChangeEvent;
import com.ast.kinoshka.frontend.gwt.event.handler.CategoryChangeHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.GetByCategoryHandler;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.utils.ViewMode;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

/**
 * UI Form header.
 * @author Aleh_Stsiapanau
 */
public class Header extends Composite {

  interface HeaderUiBinder extends UiBinder<Widget, Header> { }
  private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

  private HandlerManager eventBus;
  private Hyperlink activeLink;

  @UiField
  Hyperlink allFilms;
  @UiField
  Hyperlink byGenre;
  @UiField
  Hyperlink byDirector;
  @UiField
  Hyperlink byActor;
  @UiField
  Hyperlink byCountry;
  @UiField
  Hyperlink byYear;
  @UiField
  Hyperlink byBox;

  @UiField
  Searcher searcher;

  @UiField
  Anchor add;
  @UiField
  Anchor mode1;
  @UiField
  Anchor mode2;

  public Header() {
    initWidget(uiBinder.createAndBindUi(this));

    allFilms.setTargetHistoryToken(Category.ALL.name());
    byGenre.setTargetHistoryToken(Category.GENRE.name());
    byDirector.setTargetHistoryToken(Category.DIRECTOR.name());
    byActor.setTargetHistoryToken(Category.ACTOR.name());
    byCountry.setTargetHistoryToken(Category.COUNTRY.name());
    byYear.setTargetHistoryToken(Category.YEAR.name());
    byBox.setTargetHistoryToken(Category.BOX.name());

    setActiveLink(allFilms);
  }

  public Header(HandlerManager eventBus) {
    this();
    this.eventBus = eventBus;
    searcher.setEventBus(eventBus);

    this.eventBus.addHandler(CategoryChangeEvent.TYPE, new CategoryChangeHandler() {
      @Override
      public void onCategoryChange(CategoryChangeEvent event) {
        GWT.log("Navigation: " + event.getCategory().name(), null);
        setActiveLink(getCategoryLink(event.getCategory()));
      }
    });

    this.eventBus.addHandler(GetByCategoryEvent.TYPE, new GetByCategoryHandler() {
      @Override
      public void onGetByCategory(GetByCategoryEvent event) {
        setActiveLink(getCategoryLink(event.getCategory()));
      }
    });

    add.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        History.newItem("NEW", false);
        Header.this.eventBus.fireEvent(new FilmEditEvent(null));
      }
    });

    mode1.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Header.this.eventBus.fireEvent(new ViewModeChangeEvent(ViewMode.FULL));
        mode1.removeStyleName("inactive");
        mode2.addStyleName("inactive");
      }
    });

    mode2.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        
        Header.this.eventBus.fireEvent(new ViewModeChangeEvent(ViewMode.SIMPLE));
        mode2.removeStyleName("inactive");
        mode1.addStyleName("inactive");
      }
    });
  }

  private Hyperlink getCategoryLink(Category category) {
    Hyperlink result;
    switch (category) {
    case ALL:
      result = allFilms;
      break;
    case ACTOR:
      result = byActor;
      break;
    case DIRECTOR:
      result = byDirector;
      break;
    case GENRE:
      result = byGenre;
      break;
    case YEAR:
      result = byYear;
      break;
    case COUNTRY:
      result = byCountry;
      break;
    case BOX:
      result = byBox;
      break;

    default:
      result = allFilms;
      break;
    }

    return result;
  }

  private final void setActiveLink(Hyperlink link) {
    if (activeLink != null) {
      activeLink.removeStyleName("act");
    }
    activeLink = link;
    activeLink.addStyleName("act");
  }

}
