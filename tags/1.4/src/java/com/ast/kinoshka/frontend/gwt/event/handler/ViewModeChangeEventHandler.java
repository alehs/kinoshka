package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.ViewModeChangeEvent;
import com.google.gwt.event.shared.EventHandler;

public interface ViewModeChangeEventHandler extends EventHandler {
  void onModeChanged(ViewModeChangeEvent event);
}
