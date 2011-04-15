package com.ast.kinoshka.frontend.gwt.client;

import com.ast.kinoshka.frontend.gwt.client.widget.AbstractContentView;
import com.ast.kinoshka.frontend.gwt.client.widget.CategoryListView;
import com.ast.kinoshka.frontend.gwt.client.widget.FilmEditView;
import com.ast.kinoshka.frontend.gwt.client.widget.FilmListView;
import com.ast.kinoshka.frontend.gwt.client.widget.SearchResultsView;
import com.ast.kinoshka.frontend.gwt.event.CategoryChangeEvent;
import com.ast.kinoshka.frontend.gwt.event.FilmEditEvent;
import com.ast.kinoshka.frontend.gwt.event.GetByCategoryEvent;
import com.ast.kinoshka.frontend.gwt.event.LoadedEvent;
import com.ast.kinoshka.frontend.gwt.event.LoadingEvent;
import com.ast.kinoshka.frontend.gwt.event.MessageEvent;
import com.ast.kinoshka.frontend.gwt.event.SearchEvent;
import com.ast.kinoshka.frontend.gwt.event.ViewModeChangeEvent;
import com.ast.kinoshka.frontend.gwt.event.handler.CategoryChangeHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.FilmEditEventHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.GetByCategoryHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.LoadedHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.LoadingHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.SearchEventHandler;
import com.ast.kinoshka.frontend.gwt.event.handler.ViewModeChangeEventHandler;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.utils.Messages;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.ast.kinoshka.frontend.gwt.utils.ViewMode;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Contains film and category views.
 * @author Aleh_Stsiapanau
 */
public class Content extends Composite {

  private static ContentUiBinder uiBinder = GWT.create(ContentUiBinder.class);
  interface ContentUiBinder extends UiBinder<Widget, Content> {
  }

  /**
   * Interface for content composites.
   * @author Aleh_Stsiapanau
   */
  public interface IContentWidget {
    /** Asks widget to load data. */
    void load(Category category, String attributeId);
    /** Sets widget caption. */
    void setWidgetTitle(String title);
    /** Force view reloading next time it will be asked. */
    void makeDirty();
  }

  private ViewMode viewMode = ViewMode.FULL;
  private GwtEvent<?> lastEvent = new CategoryChangeEvent(Category.ALL);

  AbstractContentView categoryList, filmsList, searchResults;
  FilmEditView filmEdit;

  @UiField
  SimplePanel holder;

  @UiField
  HTMLPanel loading;

  @UiField
  HTMLPanel empty;

  public Content() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public Content(final HandlerManager eventBus) {
    this();

    filmsList = new FilmListView(eventBus);
    categoryList = new CategoryListView(eventBus);
    searchResults = new SearchResultsView(eventBus);

    setBody(filmsList); // set default body content
    setLoading(true);

    eventBus.addHandler(CategoryChangeEvent.TYPE, new CategoryChangeHandler() {
      @Override
      public void onCategoryChange(CategoryChangeEvent event) {
        Content.this.lastEvent = event;

        if (event.getCategory() == Category.ALL) {
          filmsList.load(Category.ALL, null);
          setBody(filmsList);
        } else {
          categoryList.load(event.getCategory(), null);
          setBody(categoryList);
        }
      }
    });

    eventBus.addHandler(GetByCategoryEvent.TYPE, new GetByCategoryHandler() {
      @Override
      public void onGetByCategory(GetByCategoryEvent event) {
        Content.this.lastEvent = event;

        filmsList.load(event.getCategory(), event.getCategoryItem());
        filmsList.setWidgetTitle(ResourcesUtil.contentTitle(event.getCategory(), event
            .getCategoryItemName()));
        setBody(filmsList);
      }
    });

    eventBus.addHandler(FilmEditEvent.TYPE, new FilmEditEventHandler() {
      @Override
      public void doEdit(final FilmEditEvent event) {

        // Fetch film edit view.
        GWT.runAsync(new RunAsyncCallback() {
          @Override
          public void onSuccess() {
            eventBus.fireEvent(new LoadingEvent());
            if (filmEdit == null) {
              filmEdit = new FilmEditView(eventBus);
            }
            filmEdit.setBackAction(lastEvent);
            filmEdit.startEditing(event.getFilmToEdit());
            setBody(filmEdit);
          }
          @Override
          public void onFailure(Throwable reason) {
            eventBus.fireEvent(MessageEvent.error(Messages.get().failGeneral()));
          }
        });
      }
    });

    eventBus.addHandler(LoadedEvent.TYPE, new LoadedHandler() {
      @Override
      public void onLoaded(LoadedEvent event) {
        setLoading(false);
        setEmpty(event.isEmptyResult());
      }
    });

    eventBus.addHandler(LoadingEvent.TYPE, new LoadingHandler() {
      @Override
      public void onLoading(LoadingEvent loadingEvent) {
        setLoading(true);
      }
    });

    eventBus.addHandler(ViewModeChangeEvent.TYPE, new ViewModeChangeEventHandler() {
      @Override
      public void onModeChanged(ViewModeChangeEvent event) {
        viewMode = event.getViewMode();

        // Fetching CategoryList view if call it first time.
        if (viewMode == ViewMode.FULL && categoryList == null) {
          GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onSuccess() {
              categoryList = new CategoryListView(eventBus);
              History.fireCurrentHistoryState();
            }
            @Override
            public void onFailure(Throwable reason) {
              eventBus.fireEvent(MessageEvent.error(Messages.get().failGeneral()));
            }
          });
        } else {
          History.fireCurrentHistoryState();
        }
      }
    });

    eventBus.addHandler(SearchEvent.TYPE, new SearchEventHandler() {
      @Override
      public void onSearch(SearchEvent event) {
        searchResults.load(null, event.getKeyWord());
        setBody(searchResults);
      }
    });
  }

  private void setLoading(boolean value) {
    loading.setVisible(value);
  }

  private void setEmpty(boolean value) {
    empty.setVisible(value);
  }

  /**
   * Inserts specified widget into content area.
   * @param widget widget to insert.
   */
  private void setBody(Widget widget) {
    if (holder.getWidget() == null || !holder.getWidget().equals(widget)) {
      holder.setVisible(false);
      holder.setWidget(widget);
      holder.setVisible(true);
    }
  }

}
