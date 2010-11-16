package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;

/**
 * Item with context remove button.
 * @author Aleh_Stsiapanau
 */
public class RemovableItem extends CategoryItem {

  private PushButton contextAction = new PushButton();
  private boolean enabled = true;

  /**
   * Creates instance from FilmAttributeInfo.
   * @param name
   * @param value
   */
  public RemovableItem(FilmAttributeInfo item, boolean disabled) {
    this(ResourcesUtil.filmName(item.getName(), item.getItemsCount().toString()), item.getId(),
        disabled);
  }

  /**
   * Creates instance with default remove button available
   * @param name
   * @param value
   * @param disabled hide context action
   */
  public RemovableItem(final String name, final String value) {
    this(name, value, true);
  }

  /**
   * Creates instance.
   * @param name
   * @param value
   */
  public RemovableItem(final String name, final String value, boolean enabled) {
    super(name, value);
    this.enabled = enabled;
    setStyleName("rem-item");

    panel.add(contextAction);

    HTML cl = new HTML();
    cl.setStyleName("cl");
    panel.add(cl);

    contextAction.setStyleName("rem-btn");
    contextAction.setVisible(false);

    addDomHandler(new MouseOutHandler() {
      @Override
      public void onMouseOut(MouseOutEvent event) {
        contextAction.setVisible(false);
      }
    }, MouseOutEvent.getType());

    addDomHandler(new MouseOverHandler() {
      @Override
      public void onMouseOver(MouseOverEvent event) {
        contextAction.setVisible(true && RemovableItem.this.enabled);
      }
    }, MouseOverEvent.getType());
  }

  public void setEnabled(boolean value) {
    this.enabled = value;
  }

  public void addClickHandler(ClickHandler handler) {
    contextAction.addClickHandler(handler);
  }

}
