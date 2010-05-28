package com.ast.kinoshka.common.exception;

/**
 * Indicates error in application configuration.
 * 
 * @author Aleh_Stsiapanau
 * 
 */
public class ApplicationConfigurationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApplicationConfigurationException(String message) {
        super(message);
    }

    public ApplicationConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
