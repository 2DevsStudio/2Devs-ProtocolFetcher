package com.ignitedev.devsprotocolfetcher.util;

import com.ignitedev.devsprotocolfetcher.api.ProtocolFetcherAPI;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DebugUtility {

  private static final Logger logger = Logger.getLogger(DebugUtility.class.getName());

  public void info(String string) {
    if (ProtocolFetcherAPI.isDebugMode()) {
      logger.log(Level.INFO, string);
    }
  }

  public void error(String string) {
    logger.severe(string);
  }

}
