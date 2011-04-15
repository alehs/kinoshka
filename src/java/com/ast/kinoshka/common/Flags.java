package com.ast.kinoshka.common;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Static methods to process command line flags, register completion hooks
 * (which are run after flag processing).
 */
public class Flags {

  private final static Logger LOG = Logger.getLogger(Flags.class.getName());

  private final Vector<String> params = new Vector<String>();
  private final Hashtable<String, String> options = new Hashtable<String, String>();
  private int paramIndex = 0;

  private Flags() {
  } // uninstantiable

  /**
   * Processes the flags on the specified command line arguments. Returns an
   * array containing all non-flag arguments. If "--help" appears on the command
   * line, then we print a help message to stdout and exit.
   *
   * @param args
   *          the command line arguments
   * @return the remainder of the command line arguments after flags have been
   *         removed
   * @throws InvalidFlagsException
   *           if any flags are invalid
   */
  public static Flags parse(String[] args) {
    LOG.fine("Parsing command line arguments...");

    Flags flags = new Flags();

    for (int i = 0; i < args.length; i++) {
      if (args[i].charAt(0) == '-' || args[i].charAt(0) == '/') {
        int loc = args[i].indexOf("=");
        String key = (loc > 0) ? args[i].substring(1, loc) : args[i].substring(1);
        String value = (loc > 0) ? args[i].substring(loc + 1) : "";
        flags.options.put(key.toLowerCase(), value);

        LOG.fine("param: " + key + "=" + value);
      } else {
        flags.params.addElement(args[i]);
      }
    }

    return flags;
  }

  public boolean hasOption(String opt) {
    return options.containsKey(opt.toLowerCase());
  }

  public String getOption(String opt) {
    String result = null;
    if (hasOption(opt)) {
      result = options.get(opt.toLowerCase());
    }
    return result;
  }

  public String nextParam() {
    if (paramIndex < params.size()) {
      return params.elementAt(paramIndex++);
    }
    return null;
  }

  public Properties getOptions() {
    Properties props = new Properties();
    for (Entry<String, String> option: options.entrySet()) {
      props.put(option.getKey(), option.getValue());
    }
    return props;
  }

}
