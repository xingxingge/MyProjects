package com.hx.log.jdk.custom;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public class MyLogManager {

  public static void resetFromPropertyFile(String filePath) {
    LogManager logManager = LogManager.getLogManager();
    logManager.reset();
    InputStream inputStream = MyLogManager.class.getClassLoader().getResourceAsStream(filePath);
    try {
      logManager.readConfiguration(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static Logger getLogger(String name) {
    Logger logger = Logger.getLogger(name);
    logger.setLevel(Level.ALL);
    for (Handler handler : logger.getHandlers()) {
      logger.removeHandler(handler);

    }

    // add console  handler
    ConsoleHandler consoleHandler = new ConsoleHandler();
    consoleHandler.setLevel(Level.INFO);
    consoleHandler.setFormatter(new MySimpleFormatter());

    consoleHandler.setFilter(new Filter() {
      @Override
      public boolean isLoggable(LogRecord record) {
        return true;
      }
    });
    logger.addHandler(consoleHandler);

    // add file handler
    FileHandler fileHandler;
    try {
      fileHandler = new FileHandler("file.log", true);
      fileHandler.setLevel(Level.INFO);
      fileHandler.setFormatter(new MySimpleFormatter());
      logger.addHandler(fileHandler);
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // add default stream handler
    MyStreamHandler defaultStreamHandler = new MyStreamHandler();
    defaultStreamHandler.setLevel(Level.INFO);
    defaultStreamHandler.setFormatter(new MySimpleFormatter());
    defaultStreamHandler.setOutputStream(System.out);
    logger.addHandler(defaultStreamHandler);

    return logger;
  }
}
