package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.event.MessageEvent;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.utils.ListReceivingCallback;
import com.ast.kinoshka.frontend.gwt.utils.Messages;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * Category items view widget for the full display mode.
 * @author Aleh_Stsiapanau
 */
public class CategoryListView extends AbstractContentView {

  FlowPanel listHolder = new FlowPanel();
  private Category currentCategory;

  public CategoryListView(HandlerManager eventBus) {
    super(eventBus);
    initWidget(listHolder);
    setStyleName("list");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doLoad(final Category category, final String categoryItem) {
    listHolder.clear();
    currentCategory = category;

    dataService.getAttributeList(category, 
        new ListReceivingCallback<FilmAttributeInfo>(eventBus) {
          @Override
          public void processResult(ArrayList<FilmAttributeInfo> result) {
            populateList(result);
          }
        });
  }

  /**
   * Populates control with the given data.
   * @param data to populate with
   */
  private void populateList(final ArrayList<FilmAttributeInfo> data) {
    boolean sec = false;
    for (FilmAttributeInfo item: data) {
      final Widget listItem;

      if (item.getItemsCount() > 0) {
        listItem = new Hyperlink(ResourcesUtil.filmName(item.getName(), item.getItemsCount()
            .toString()), ResourcesUtil.target(currentCategory, item.getId(), item.getName()));

      } else {
        final RemovableItem remItem = new RemovableItem(item, Category.COUNTRY != currentCategory);
        listItem = remItem;
        remItem.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {

            dataService.deleteAttribute(currentCategory, remItem.getValue(),
                new AsyncCallback<Void>() {
                  @Override
                  public void onSuccess(Void result) {
                    eventBus.fireEvent(MessageEvent.info(Messages.get()
                        .removed(remItem.getName())));
                    listHolder.remove(listItem);
                  }

                  @Override
                  public void onFailure(Throwable caught) {
                    eventBus.fireEvent(MessageEvent.warning(Messages.get().failGeneral()));
                  }
                });

          }
        });
      }

      if (sec) {
        listItem.addStyleName("sec");
      }
      sec = !sec;
      listHolder.add(listItem);
    } // end for
  }
}
