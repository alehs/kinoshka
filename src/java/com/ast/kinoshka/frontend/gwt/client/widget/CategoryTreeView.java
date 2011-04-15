package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.event.MessageEvent;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.utils.ListReceivingCallback;
import com.ast.kinoshka.frontend.gwt.utils.Messages;
import com.ast.kinoshka.frontend.gwt.utils.MessagesBundle;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;

import java.util.ArrayList;

/**
 * Category items view widget for simple display mode.
 * @author Aleh_Stsiapanau
 */
public class CategoryTreeView extends AbstractContentView {

  private MessagesBundle messages = Messages.get();
  private Tree tree = new Tree();

  private Category currentCategory;
  private FilmItemPopup popup;

  public CategoryTreeView(HandlerManager eventBus) {
    super(eventBus);
    initWidget(tree);
    setStyleName("list");

    tree.setAnimationEnabled(true);

    // load children handler
    tree.addOpenHandler(new OpenHandler<TreeItem>() {
      public void onOpen(OpenEvent<TreeItem> event) {
        loadTreeItem(event.getTarget());
      }
    });

    popup = new FilmItemPopup(eventBus);
  }

  @Override
  void doLoad(Category category, String attrinbuteId) {
    tree.clear();
    currentCategory = category;

    dataService.getAttributeList(category,
        new ListReceivingCallback<FilmAttributeInfo>(eventBus) {
          @Override
          public void processResult(ArrayList<FilmAttributeInfo> result) {
            populateTree(result);
          }
        });
  }

  /**
   * Populates tree root node with the given category items.
   * @param data film attribute items
   */
  protected void populateTree(final ArrayList<FilmAttributeInfo> data) {
    boolean sec = false;
    for (FilmAttributeInfo info : data) {
      final CategoryItem attributeLabel;
      final TreeItem item;

      if (info.getItemsCount() > 0) {
        attributeLabel = new CategoryItem(info);
        item = tree.addItem(attributeLabel);

        attributeLabel.getLabel().addMouseDownHandler(new MouseDownHandler() {
          @Override
          public void onMouseDown(MouseDownEvent event) {
            item.setState(!item.getState());
            tree.setFocus(false);
          }
        });

      } else {
        attributeLabel = new RemovableItem(ResourcesUtil.filmName(info.getName(), info
            .getItemsCount().toString()), info.getId(), Category.COUNTRY != currentCategory);
        item = tree.addItem(attributeLabel);

        ((RemovableItem) attributeLabel).addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            dataService.deleteAttribute(currentCategory, attributeLabel.getValue(),
                new AsyncCallback<Void>() {
                  @Override
                  public void onSuccess(Void result) {
                    eventBus.fireEvent(MessageEvent.info(messages.removed(attributeLabel
                        .getName())));
                    tree.removeItem(item);
                  }

                  @Override
                  public void onFailure(Throwable caught) {
                    eventBus.fireEvent(MessageEvent.warning(messages.failGeneral()));
                  }
                });
          }
        });

      }

      item.addItem("");
      if (sec) {
        item.addStyleName("sec");
      }
      sec = !sec;
    } // END FOR
  }

  /**
   * Loads films for specifies category.
   * 
   * @param item
   *          category item
   */
  private void loadTreeItem(final TreeItem item) {

    if (item.getChildCount() == 1) {
      item.addStyleName("wt");

      // Close the item immediately
      item.setState(false, false);
      CategoryItem widget = (CategoryItem) item.getWidget();

      dataService.getFilmsByCategory(currentCategory, widget.getValue(),
          new AsyncCallback<ArrayList<FilmInfo>>() {
            @Override
            public void onSuccess(ArrayList<FilmInfo> result) {
              // Remove the temporary item when we finish loading
              item.getChild(0).remove();
              item.removeStyleName("wt");

              populateTreeNode(item, result);

              // Reopen the item
              item.setState(true, false);

              eventBus.fireEvent(MessageEvent.info(messages.helloWorld()));
            }

            @Override
            public void onFailure(Throwable caught) {
              item.removeStyleName("wt");
              eventBus.fireEvent(MessageEvent.error(messages.failLoadFilms()));
            }
          });
    }
  }

  /**
   * Populates tree node with the given data.
   * @param item tree node item
   * @param data to populate with
   */
  protected void populateTreeNode(final TreeItem item, final ArrayList<FilmInfo> data) {
    for (FilmInfo filmInfo : data) {
      final FilmItemShort film = new FilmItemShort(filmInfo);
      item.addItem(film);
      film.getLabel().addMouseOverHandler(new MouseOverHandler() {
        @Override
        public void onMouseOver(MouseOverEvent event) {
          final int left = film.getLabel().getAbsoluteLeft() + 50;
          final int top = film.getLabel().getAbsoluteTop() + 14;

          popup.hide();
          popup.setFilmInfoToDysplay(film.getInfo());
          popup.setPopupPositionAndShow(new PositionCallback() {
            @Override
            public void setPosition(int offsetWidth, int offsetHeight) {
              int realTop = top;
              if (top + offsetHeight > Window.getClientHeight() + Window.getScrollTop()) {
                realTop -= offsetHeight;
              }
              popup.setPopupPosition(left, realTop);
            }
          });
          popup.show();
        }
      });
    }
  }
}
