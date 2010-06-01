package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.model.PagingConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
  private int currentOffset = 0;
  private int total = 0;

  @UiField
  Element countLabel;
  @UiField
  Element totalLabel;
  @UiField
  Anchor newerButton;
  @UiField
  Anchor olderButton;
  @UiField
  Anchor firstPage;
  @UiField
  Anchor lastPage;

  @UiHandler("firstPage")
  void onFirstClick(ClickEvent event) {
    if (handler != null && newerButton.isEnabled()) {
      handler.pageChanged(new PagingConfig(0, PAGE_SIZE));
    }
  }

  @UiHandler("lastPage")
  void onLastClick(ClickEvent event) {
    if (handler != null && olderButton.isEnabled()) {
      handler.pageChanged(new PagingConfig((total/PAGE_SIZE)*PAGE_SIZE, PAGE_SIZE));
    }
  }

  @UiHandler("newerButton")
  void onNewerClick(ClickEvent event) {
    if (handler != null && newerButton.isEnabled()) {
      handler.pageChanged(new PagingConfig(currentOffset - PAGE_SIZE, PAGE_SIZE));
    }
  }

  @UiHandler("olderButton")
  public void onOlderClick(ClickEvent event) {
    if (handler != null && olderButton.isEnabled()) {
      handler.pageChanged(new PagingConfig(currentOffset + PAGE_SIZE, PAGE_SIZE));
    }
  }

  public Paging() {
    initWidget(uiBinder.createAndBindUi(this));
    countLabel.setInnerText("0");
    totalLabel.setInnerText("0");
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

  public void setPageConfig(int offset, int total) {
    this.currentOffset = offset;
    this.total = total;
    setCount(offset);
    setTotal(total);
    olderButton.setEnabled((total - offset) > PAGE_SIZE);
  }

  /**
   * Registers page change handler.
   * @param pageChangeHandler handler to register
   */
  public void setOnPageChangeHandler(PageChangeHandler pageChangeHandler) {
    handler = pageChangeHandler;
  }

  /**
   * Returns paging configuration for current pagination state.
   * @return paging configuration
   */
  public PagingConfig getPagingConfig() {
    return new PagingConfig(currentOffset, PAGE_SIZE);
  }
}
