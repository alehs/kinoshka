package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.LoadingEvent;
import com.google.gwt.event.shared.EventHandler;

public interface LoadingHandler extends EventHandler {
  void onLoading(LoadingEvent loadingEvent);
}

