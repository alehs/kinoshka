package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.MessageEventHandler;
import com.ast.kinoshka.frontend.gwt.utils.MessageType;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Indicates event display message to user.
 * @author Aleh_Stsiapanau
 */
public class MessageEvent extends GwtEvent<MessageEventHandler>  {

  public static final Type<MessageEventHandler> TYPE = new Type<MessageEventHandler>();
  private MessageType messageType;
  private String message;

  public String getMessage() {
    return message;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public MessageEvent(String message, MessageType type) {
    this.message = message;
    this.messageType = type;
  }

  @Override
  protected void dispatch(MessageEventHandler handler) {
    handler.onDisplayMessage(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<MessageEventHandler> getAssociatedType() {
    return TYPE;
  }

  /*###################### Factory methods. #####################*/

  /**
   * Creates error message event.
   */
  public static MessageEvent error(String message) {
    return new MessageEvent(message, MessageType.ERROR);
  }

  /**
   * Creates waring message event.
   */
  public static MessageEvent warning(String message) {
    return new MessageEvent(message, MessageType.WARN);
  }

  /**
   * Creates info message event.
   */
  public static MessageEvent info(String message) {
    return new MessageEvent(message, MessageType.INFO);
  }
  
}
