package com.ast.kinoshka.frontend.gwt.client;

import com.ast.kinoshka.frontend.gwt.event.CategoryChangeEvent;
import com.ast.kinoshka.frontend.gwt.event.GetByCategoryEvent;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;

public class HistoryChangeController implements ValueChangeHandler<String> {

  private HandlerManager eventBus;

  public HistoryChangeController(HandlerManager eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    Token token = parseToken(event.getValue());

    if (token == null) {
      // default history event - load all category page
      eventBus.fireEvent(new CategoryChangeEvent(Category.ALL));

    } else if (token.hasParams()) {
      eventBus.fireEvent(new GetByCategoryEvent(token.category, token.params[0], token.params[1]));

    } else {
      eventBus.fireEvent(new CategoryChangeEvent(token.category));
    }
  }

  /**
   * Parses token string to extract parameters and category
   * @param tokenString string to parse
   * @return token object corresponded to current string
   */
  private Token parseToken(final String tokenString) {
    if (tokenString == null || tokenString.length() == 0) {
      return null;
    }

    Token token;
    if (tokenString.contains("?")) {
      int paramIndex = tokenString.indexOf("?");
      token = new Token(Category.getByName(tokenString.substring(0, paramIndex)));

      String param = tokenString.substring(paramIndex + 1);
      if (param.contains("&")) {
        token.params = param.split("&"); 
      } else {
        token.params[0] = param;
      }

    } else {
      token = new Token(Category.getByName(tokenString));
    }

    return token;    
  }

  private class Token {
    private Category category;
    private String[] params;

    private Token(Category category) {
      this.category = category;
      this.params = new String[2];
    }

    public boolean hasParams() {
      return params[0] != null;
    }
  }

}
