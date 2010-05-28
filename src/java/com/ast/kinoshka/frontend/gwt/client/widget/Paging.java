package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.model.PagingConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Pagination control.
 * @author Aleh_Stsiapanau
 */
public class Paging extends Composite {

  public static final int PAGE_SIZE = PagingConfig.DEFAULT_PAGE_SIZE;

  public interface PagingUiBinder extends UiBinder<Widget, Paging> { }
  public interface PageChangeHandler {
    void pageChanged(PagingConfig config);
  }

  private PageChangeHandler handler;
  private static PagingUiBinder uiBinder = GWT.create(PagingUiBinder.class);
  private int currentOffset;

  @UiField
  Element countLabel;
  @UiField
  Element totalLabel;
  @UiField
  Anchor newerButton;
  @UiField
  Anchor olderButton;

  public Paging() {
    initWidget(uiBinder.createAndBindUi(this));
    countLabel.setInnerText("0");
    totalLabel.setInnerText("0");

    newerButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        if (handler != null && newerButton.isEnabled()) {
          handler.pageChanged(new PagingConfig(currentOffset - PAGE_SIZE, PAGE_SIZE));
        }
      }
    });

    olderButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        if (handler != null && olderButton.isEnabled()) {
          handler.pageChanged(new PagingConfig(currentOffset + PAGE_SIZE, PAGE_SIZE));
        }
      }
    });
  }

  public void setPageConfig(int offset, int total) {
    this.currentOffset = offset; 
    setCount(offset);
    setTotal(total);
    olderButton.setEnabled((total - offset) > PAGE_SIZE);
  }

  private void setCount(int count) {
    int pageCount = 1 + ((count)/PAGE_SIZE);
    newerButton.setEnabled(pageCount > 1);
    countLabel.setInnerText(String.valueOf(pageCount));
  }

  private void setTotal(int total) {
    int totalCount = 1 + ((total - 1)/PAGE_SIZE);
    olderButton.setEnabled(totalCount > 0);
    totalLabel.setInnerText(String.valueOf(totalCount));
  }

  public void addOnPageChangeHandler(PageChangeHandler pageChangeHandler) {
    handler = pageChangeHandler;
  }

}
