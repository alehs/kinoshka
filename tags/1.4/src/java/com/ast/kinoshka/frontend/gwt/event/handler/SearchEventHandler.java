package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.SearchEvent;
import com.google.gwt.event.shared.EventHandler;

public interface SearchEventHandler extends EventHandler {
  void onSearch(SearchEvent event);
}
