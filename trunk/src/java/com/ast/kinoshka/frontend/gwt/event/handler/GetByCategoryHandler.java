package com.ast.kinoshka.frontend.gwt.event.handler;

import com.ast.kinoshka.frontend.gwt.event.GetByCategoryEvent;
import com.google.gwt.event.shared.EventHandler;

/**
 * Handles event when user clicks on specific category value item.
 * @author Aleh_Stsiapanau
 *
 */
public interface GetByCategoryHandler extends EventHandler {
  void onGetByCategory(GetByCategoryEvent event);
}
