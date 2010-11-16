package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.CategoryChangeEvent;
import com.google.gwt.event.shared.EventHandler;

/**
 * Handles category change events.
 * @author Aleh_Stsiapanau
 *
 */
public interface CategoryChangeHandler extends EventHandler {
  void onCategoryChange(CategoryChangeEvent event);
}
