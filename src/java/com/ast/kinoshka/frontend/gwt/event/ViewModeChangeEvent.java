package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.ViewModeChangeEventHandler;
import com.ast.kinoshka.frontend.gwt.utils.ViewMode;
import com.google.gwt.event.shared.GwtEvent;

public class ViewModeChangeEvent extends GwtEvent<ViewModeChangeEventHandler> {

  public static final Type<ViewModeChangeEventHandler> TYPE = new Type<ViewModeChangeEventHandler>();
  private ViewMode mode;

  public ViewModeChangeEvent(ViewMode mode) {
    this.mode = mode;
  }

  @Override
  protected void dispatch(ViewModeChangeEventHandler handler) {
    handler.onModeChanged(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ViewModeChangeEventHandler> getAssociatedType() {
    return TYPE;
  }

  public ViewMode getViewMode() {
    return mode;
  }

}
