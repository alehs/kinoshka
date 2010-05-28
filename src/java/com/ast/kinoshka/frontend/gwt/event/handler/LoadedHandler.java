package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.LoadedEvent;
import com.google.gwt.event.shared.EventHandler;

public interface LoadedHandler extends EventHandler {
  void onLoaded(LoadedEvent event);
}
