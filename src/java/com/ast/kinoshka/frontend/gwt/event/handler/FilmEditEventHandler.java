package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.FilmEditEvent;
import com.google.gwt.event.shared.EventHandler;

public interface FilmEditEventHandler extends EventHandler {
  void doEdit(FilmEditEvent event);
}
