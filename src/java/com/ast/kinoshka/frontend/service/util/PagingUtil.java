package com.ast.kinoshka.frontend.service.util;

import com.ast.kinoshka.backend.model.PageConfig;
import com.ast.kinoshka.frontend.gwt.model.PagingConfig;

/**
 * Helper methods to evaluate paging configuration.
 * @author Aleh_Stsiapanau
 */
public class PagingUtil {

  /**
   * Evaluate configuration to get paging by descending content.
   * @param config paging configuration
   * @param total total number of items to get paging for
   * @return paging configuration
   */
  public static PageConfig evalDesc(PagingConfig config, int total) {
    final int from = total - (config.getOffset() + config.getLimit()) + 1;
    final int to = total - config.getOffset();
    return new PageConfig(from, to);
  }

}
