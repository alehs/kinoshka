package com.ast.kinoshka.backend.inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Log all Service's method invocation through AOP.
 */
public class CoreServiceLogger implements MethodInterceptor {

  private static final String LOG_CALL_PATTERN = "Call method ''{0}'' with arguments: {1}.";
  private static final String LOG_RETURN_PATTERN = "Method ''{0}'' returns {1}.";
  //private static final String LOG_EXCEPTION_PATTERN = "Method ''{0}'' throws an exception {1}.";

  private static final Logger LOG = Logger.getLogger(CoreServiceLogger.class.getName());

  @Override
  public Object invoke(MethodInvocation target) throws Throwable {
    LOG.fine(generateString(LOG_CALL_PATTERN, target.getMethod().getDeclaringClass().getName()
        + "." + target.getMethod().getName(), Arrays.toString(target
        .getArguments())));
    Object result = null;
    try {
      result = target.proceed();
    } catch (Exception e) {
      LOG.throwing(target.getMethod().getDeclaringClass().getName(), target.getMethod().getName(), e);
      //LOG.severe(generateString(LOG_EXCEPTION_PATTERN, target.getMethod().getName(), e.getMessage()));
      throw e;
    }
    LOG.fine(generateString(LOG_RETURN_PATTERN, target.getMethod().getName(), result));
    return result;
  }

  private String generateString(String pattern, String method, Object params) {
    MessageFormat messageFormat = new MessageFormat(pattern);
    return messageFormat.format(new Object[] { method, params });
  }

}
