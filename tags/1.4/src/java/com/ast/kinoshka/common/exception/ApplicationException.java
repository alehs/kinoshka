package com.ast.kinoshka.common.exception;

/**
 * High level exception that indicates general application failure.
 * @author Aleh_Stsiapanau
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
