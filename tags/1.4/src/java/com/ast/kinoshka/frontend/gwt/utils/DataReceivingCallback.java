package com.ast.kinoshka.frontend.gwt.utils;

import com.ast.kinoshka.frontend.gwt.event.LoadedEvent;
import com.ast.kinoshka.frontend.gwt.event.MessageEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Common AsyncCallback for data receiving service.
 * @author Aleh_Stsiapanau
 *
 * @param <T>
 */
public abstract class DataReceivingCallback<T> implements AsyncCallback<T> {
  protected HandlerManager eventBus;

  public DataReceivingCallback(HandlerManager eventBus) {
    this.eventBus = eventBus;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onFailure(Throwable caught) {
    eventBus.fireEvent(new LoadedEvent(true));
    eventBus.fireEvent(MessageEvent.error(Messages.get().failGeneral()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onSuccess(T result) {
    processResult(result);
    done(result);
  }

  /**
   * Default fires the loaded event.
   */
  public void done(T result) {
    eventBus.fireEvent(new LoadedEvent());
  }

  /**
   * Processes result.
   */
  public abstract void processResult(T result);

}
