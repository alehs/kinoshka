package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.client.widget.Paging.PageChangeHandler;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.model.PagingConfig;
import com.ast.kinoshka.frontend.gwt.model.PagingResult;
import com.ast.kinoshka.frontend.gwt.utils.ListReceivingCallback;
import com.ast.kinoshka.frontend.gwt.utils.PageReceivingCallback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Film list widget.
 * @author Aleh_Stsiapanau
 */
public class FilmListView extends AbstractContentView {

  private static FilmListWidgetUiBinder uiBinder = GWT.create(FilmListWidgetUiBinder.class);
  interface FilmListWidgetUiBinder extends UiBinder<Widget, FilmListView> {
  }

  private Category currentCategory;
  private String currentCategoryItem;

  @UiField
  Paging paging;
  @UiField
  FlowPanel listHolder;
  @UiField
  InlineHyperlink back;
  @UiField
  Label title;

  public FilmListView(HandlerManager eventBus) {
    super(eventBus);
    initWidget(uiBinder.createAndBindUi(this));

    paging.setOnPageChangeHandler(new PageChangeHandler() {
      @Override
      public void pageChanged(PagingConfig config) {
        doLoad(config);
      }
    });
  }

  @Override
  public void doLoad(Category category, String categoryItem) {
    currentCategory = category;
    currentCategoryItem = categoryItem;

    title.setText(null);
    title.setVisible(false);

    back.setTargetHistoryToken(category.name());

    doLoad(paging.getPagingConfig());
  }

  /**
   * Loads paginated film list.
   * @param config pagination configuration
   * @param category film attribute category
   * @param categoryItem category item
   */
  public void doLoad(PagingConfig config) {
    listHolder.clear();

    if (Category.ALL == currentCategory) {
      back.setVisible(false);
      dataService.getFilms(config, new PageReceivingCallback(eventBus) {
        @Override
        public void processResult(PagingResult result) {
          paging.setPageConfig(result.getOffset(), result.getTotalLength());
          populateList(result.getData());
        };
      });
    } else {
      back.setVisible(true);
      dataService.getFilmsByCategory(currentCategory, currentCategoryItem,
          new ListReceivingCallback<FilmInfo>(eventBus) {
          @Override
          public void processResult(ArrayList<FilmInfo> result) {
            paging.setPageConfig(0, 0);
            populateList(result);
          }
        });
    }
  }

  /**
   * Populates control with the given data.
   * @param data to populate with
   */
  private void populateList(List<FilmInfo> data) {
    for (FilmInfo filmInfo : data) {
      FilmItem film = new FilmItem(eventBus);
      film.setFilmInfoToDisplay(filmInfo);
      listHolder.add(film);
    }
  }

  @Override
  public void setWidgetTitle(String titleText) {
    title.setText(titleText);
    title.setVisible(true);
  }
}
