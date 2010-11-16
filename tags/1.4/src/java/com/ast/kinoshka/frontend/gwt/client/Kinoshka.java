package com.ast.kinoshka.frontend.gwt.client;

import com.ast.kinoshka.frontend.gwt.client.widget.Messenger;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kinoshka implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    RootPanel.get().setStyleName(getRandomStyle());

    HandlerManager eventBus = new HandlerManager(null);

    RootPanel.get().add(new Messenger(eventBus));
    RootPanel.get().add(new Header(eventBus));
    RootPanel.get().add(new Content(eventBus));

    History.addValueChangeHandler(new HistoryChangeController(eventBus));
    History.fireCurrentHistoryState();
  }

  private String getRandomStyle() {
    return "bg" + Random.nextInt(10); 
  }

}
