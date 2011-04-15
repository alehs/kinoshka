package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.model.SearchResult;
import com.ast.kinoshka.frontend.gwt.utils.DataReceivingCallback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * Search results view.
 * @author Aleh_Stsiapanau
 */
public class SearchResultsView extends AbstractContentView {

  private static SearchResultsUiBinder uiBinder = GWT.create(SearchResultsUiBinder.class);
  interface SearchResultsUiBinder extends UiBinder<Widget, SearchResultsView> {}
  private FilmItemPopup popup;

  @UiField
  InlineLabel keyword;
  @UiField
  FlowPanel listHolder;
  @UiField
  HTMLPanel empty;

  public SearchResultsView(HandlerManager eventBus) {
    super(eventBus);
    initWidget(uiBinder.createAndBindUi(this));
    popup = new FilmItemPopup(eventBus);
  }

  @Override
  void doLoad(Category category, String categoryItem) {
    keyword.setText(categoryItem);
    dataService.search(categoryItem, new DataReceivingCallback<SearchResult>(eventBus) {
      @Override
      public void processResult(SearchResult result) {
        listHolder.clear();
        populateList(result.getFilmList());
        empty.setVisible(result.getFilmList().isEmpty());
      }
    });
  }

  /**
   * Populates control with the given data.
   * @param data to populate with
   */
  private void populateList(List<FilmInfo> data) {
    for (FilmInfo filmInfo : data) {
      final SearchResultItem film = new SearchResultItem(filmInfo);
      listHolder.add(film);
      film.getLabel().addMouseOverHandler(new MouseOverHandler() {
        @Override
        public void onMouseOver(MouseOverEvent event) {
          int left = film.getLabel().getAbsoluteLeft() + 50;
          int top = film.getLabel().getAbsoluteTop() + 14;

          popup.hide();
          popup.setFilmInfoToDysplay(film.getInfo());
          popup.setPopupPosition(left, top);
          popup.show();
        }
      });
    }
  }

}
