package com.ast.kinoshka.frontend.gwt.client;

import com.ast.kinoshka.frontend.gwt.event.SearchEvent;
import com.ast.kinoshka.frontend.gwt.utils.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Search widget
 * @author Aleh_Stsiapanau
 */
public class Searcher extends Composite {

  interface SearcherUiBinder extends UiBinder<Widget, Searcher> {}
  private static SearcherUiBinder uiBinder = GWT.create(SearcherUiBinder.class);
  private HandlerManager eventBus;

  @UiField
  TextBox searchInput;

  @UiField
  Button searchBtn;

  public Searcher() {
    initWidget(uiBinder.createAndBindUi(this));
    searchBtn.addStyleName("disabled");
  }

  public Searcher(HandlerManager eventBus) {
    this();
    this.eventBus = eventBus;
  }

  @UiHandler("searchInput")
  public void onKeyPress(KeyPressEvent event) {
    enableSearch(!StringUtil.isEmpty(searchInput.getText())
        && searchInput.getText().length() >= 3);
    if (event.getCharCode() == KeyCodes.KEY_ENTER) {
      doSearch();
    }
  }

  @UiHandler("searchInput")
  public void onChange(ChangeEvent event) {
    enableSearch(!StringUtil.isEmpty(searchInput.getText())
        && searchInput.getText().length() >= 3);
  }

  @UiHandler("searchBtn")
  void onClick(ClickEvent e) {
    doSearch();
  }

  private void doSearch() {
    if (!StringUtil.isEmpty(searchInput.getText()) && eventBus != null) {
      History.newItem("SEARCH", false);
      eventBus.fireEvent(new SearchEvent(searchInput.getText()));
      searchInput.setText(null);
    }
  }

  public void setEventBus(HandlerManager eventBus) {
    this.eventBus = eventBus;
  }

  private void enableSearch(boolean enable) {
    if (enable) {
      searchBtn.removeStyleName("disabled");
    } else {
      searchBtn.addStyleName("disabled");
    }
    searchBtn.setEnabled(enable);
  }

}
