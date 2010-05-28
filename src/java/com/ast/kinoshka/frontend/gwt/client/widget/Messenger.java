package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.event.MessageEvent;
import com.ast.kinoshka.frontend.gwt.event.handler.MessageEventHandler;
import com.ast.kinoshka.frontend.gwt.utils.MessageType;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Displays user messages in PopupPanel.
 * @author Aleh_Stsiapanau
 *
 */
public class Messenger extends PopupPanel {

  private final static int DISPLAY_TIME = 5000;

  private InlineLabel label = new InlineLabel();

  private Timer collapseTimer = new Timer() {
    @Override
    public void run() {
      Messenger.this.hide();
      label.setText("");
    }
  };

  public Messenger(HandlerManager eventBus) {
    setAnimationEnabled(true);
    setStyleName("msg");
    add(label);

    eventBus.addHandler(MessageEvent.TYPE, new MessageEventHandler() {
      @Override
      public void onDisplayMessage(MessageEvent message) {
        displayMessage(message.getMessage(), message.getMessageType());
      }
    });
  }

  public void displayMessage(String text, MessageType type) {

    if (isShowing()) {
      hide();
    }

    label.setStyleName(type.getStyle());
    label.setText(text);
    collapseTimer.schedule(DISPLAY_TIME);
    setPopupPositionAndShow(new PositionCallback() {
      @Override
      public void setPosition(int offsetWidth, int offsetHeight) {
        center();
        setPopupPosition((Window.getClientWidth() - offsetWidth) / 2, 10);
      }
    });
  }

  public void displayInfo(String info) {
    displayMessage(info, MessageType.INFO);
  }

  public void displayWarn(String warn) {
    displayMessage(warn, MessageType.WARN);
  }

  public void displayError(String error) {
    displayMessage(error, MessageType.ERROR);
  }

}
