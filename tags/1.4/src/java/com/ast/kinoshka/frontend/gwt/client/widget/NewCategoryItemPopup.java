package com.ast.kinoshka.frontend.gwt.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public abstract class NewCategoryItemPopup extends PopupPanel {

  FlowPanel panel = new FlowPanel();
  TextBox value = new TextBox();
  Button save = new Button("Save");
  Button cancel = new Button("Cancel");

  public NewCategoryItemPopup(final int x, final int y) {
    setModal(true);
    setGlassEnabled(true);
    setAnimationEnabled(true);
    setPopupPosition(x, y);
    add(panel);

    save.setTabIndex(0);
    cancel.setTabIndex(1);

    //panel.setStyleName("");
    panel.add(value);
    panel.add(save);
    panel.add(cancel);

    cancel.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        NewCategoryItemPopup.this.hide();
      }
    });

    save.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        NewCategoryItemPopup.this.hide();
        doSave();
      }
    });

    value.addKeyPressHandler(new KeyPressHandler() {
      @Override
      public void onKeyPress(KeyPressEvent event) {
        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
          NewCategoryItemPopup.this.hide();
          doSave();
        }
      }
    });

    show();
    value.setFocus(true);
  }

  public abstract void doSave();

}
