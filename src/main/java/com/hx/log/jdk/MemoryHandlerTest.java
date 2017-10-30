package com.hx.log.jdk;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;

public class MemoryHandlerTest {
  public static void main(String[] args) {
    Logger logger = Logger.getLogger("com.hx.log.jdk.MemoryHandlerTest");
    ConsoleHandler handler = new ConsoleHandler();
    MemoryHandler mHandler = new MemoryHandler(handler, 10, Level.ALL);
    logger.addHandler(mHandler);
    logger.setUseParentHandlers(false);
    LogRecord record1 = new LogRecord(Level.SEVERE, "This is SEVERE level message");
    LogRecord record2 = new LogRecord(Level.WARNING, "This is WARNING level message");
    logger.log(record1);
    logger.log(record2);

  }

}
