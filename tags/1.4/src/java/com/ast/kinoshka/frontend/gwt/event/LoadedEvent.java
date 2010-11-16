package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.LoadedHandler;
import com.google.gwt.event.shared.GwtEvent;

public class LoadedEvent extends GwtEvent<LoadedHandler> {

  public static final Type<LoadedHandler> TYPE = new Type<LoadedHandler>();
  private boolean isEmptyResult;

  public LoadedEvent() {
    this.isEmptyResult = false;
  }

  public LoadedEvent(boolean isEmpty) {
    this.isEmptyResult = isEmpty;
  }

  @Override
  protected void dispatch(LoadedHandler handler) {
    handler.onLoaded(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<LoadedHandler> getAssociatedType() {
    return TYPE;
  }

  public boolean isEmptyResult() {
    return isEmptyResult;
  }
}
