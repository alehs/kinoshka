package com.ast.kinoshka.frontend.gwt.utils;

import com.ast.kinoshka.frontend.gwt.event.LoadedEvent;
import com.google.gwt.event.shared.HandlerManager;

import java.util.ArrayList;

/**
 * AsyncCallback for list data receiving service.
 * @author Aleh_Stsiapanau
 */
public abstract class ListReceivingCallback<T> extends DataReceivingCallback<ArrayList<T>> {

  public ListReceivingCallback(HandlerManager eventBus) {
    super(eventBus);
  }

  @Override
  public void done(ArrayList<T> result) {
    eventBus.fireEvent(new LoadedEvent(result.isEmpty()));
  }

}
