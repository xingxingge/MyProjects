package com.hx.log.jdk;

/**
 * Created by hx on 17-1-6.
 */

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

public class SocketHandlerTest {

  private SocketHandler handler = null;

  private static Logger logger = Logger
          .getLogger("com.hx.log.jdk.SocketHandlerTest");

  public SocketHandlerTest(String host, int port) {
    try {
      handler = new SocketHandler(host, port);
      logger.addHandler(handler);
      logger.info("SocketHandler运行成功......");
    } catch (IOException e) {
      logger.severe("请检查地址和端口是否正确......");

      StringBuilder sb = new StringBuilder();
      sb.append(e.toString()).append("\n");
      for (StackTraceElement elem : e.getStackTrace()) {
        sb.append("\tat ").append(elem).append("\n");
      }
      logger.severe(sb.toString());
    }
  }

  public static void main(String args[]) {
    new SocketHandlerTest("localhost", 8080);
  }
}
