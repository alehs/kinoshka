package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.SearchEventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * User search event
 * @author Aleh_Stsiapanau
 */
public class SearchEvent extends GwtEvent<SearchEventHandler> {
  public static final Type<SearchEventHandler> TYPE = new Type<SearchEventHandler>();
  private String keyWord;

  public SearchEvent(String  keyword) {
    this.keyWord = keyword;
  }

  @Override
  protected void dispatch(SearchEventHandler handler) {
    handler.onSearch(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<SearchEventHandler> getAssociatedType() {
    return TYPE;
  }

  public String getKeyWord() {
    return keyWord;
  }

}
