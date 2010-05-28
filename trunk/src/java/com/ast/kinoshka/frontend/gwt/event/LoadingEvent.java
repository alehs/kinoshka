package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.LoadingHandler;
import com.google.gwt.event.shared.GwtEvent;

public class LoadingEvent extends GwtEvent<LoadingHandler> {

  public static final Type<LoadingHandler> TYPE = new Type<LoadingHandler>();

  @Override
  protected void dispatch(LoadingHandler handler) {
    handler.onLoading(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<LoadingHandler> getAssociatedType() {
    return TYPE;
  }

}
