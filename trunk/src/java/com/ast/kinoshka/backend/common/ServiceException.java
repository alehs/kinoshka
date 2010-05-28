package com.ast.kinoshka.backend.common;

/**
 * Backend service exception.
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

  /**
   * Creates new exception.
   *
   * @param message exception detail
   */
  public ServiceException(String message) {
    super(message);
  }

  /**
   * Creates new exception.
   *
   * @param message exception detail
   * @param cause the cause of exception
   */
  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

}
