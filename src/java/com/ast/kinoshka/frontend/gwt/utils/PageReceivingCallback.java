package com.ast.kinoshka.frontend.gwt.utils;

import com.ast.kinoshka.frontend.gwt.event.LoadedEvent;
import com.ast.kinoshka.frontend.gwt.model.PagingResult;
import com.google.gwt.event.shared.HandlerManager;

/**
 * AsyncCallback for PagingResult data.
 */
public abstract class PageReceivingCallback extends DataReceivingCallback<PagingResult> {

  public PageReceivingCallback(HandlerManager eventBus) {
    super(eventBus);
  }

  @Override
  public void done(PagingResult result) {
    eventBus.fireEvent(new LoadedEvent(result.getData().isEmpty()));
  }

}
