package com.ast.kinoshka.frontend.gwt.utils;


/**
 * Types of messages.
 * @author Aleh_Stsiapanau
 *
 */
public enum MessageType {

    /** Info message. */
    INFO(0),
    /** Warning message. */
    WARN(1),
    /** Error message. */
    ERROR(2);

    private int styleId;

    private MessageType(final int style) {
        this.styleId = style;
    }

    /**
     * Get css style for given type.
     * @return css style name
     */
    public String getStyle() {
        String result;
        switch (this.styleId) {
            case 0:
                result = "msg-info";
                break;
            case 1:
                result = "msg-warn";
                break;
            case 2:
                result = "msg-error";
                break;
            default:
                result = "msg-info";
        }

        return result;
    }

}
