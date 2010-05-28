package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.MessageEvent;
import com.google.gwt.event.shared.EventHandler;

public interface MessageEventHandler extends EventHandler {
  void onDisplayMessage(MessageEvent message);
}
