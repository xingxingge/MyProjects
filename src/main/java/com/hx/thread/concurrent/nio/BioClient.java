package com.hx.thread.concurrent.nio;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class BioClient {
  private static ExecutorService tp = Executors.newCachedThreadPool();
  private static final int sleep_time = 1000 * 1000 * 1000;

  public static void main(String[] args) {
    EchoClient echoClient = new EchoClient();
    echoClient.run();
  }

  public static class EchoClient implements Runnable {
    Socket client;
    PrintWriter writer;

    public void run() {
      try {
        client = new Socket();
        client.connect(new InetSocketAddress("localhost", 8000));
        writer = new PrintWriter(client.getOutputStream(), true);
        writer.print("H");
        LockSupport.parkNanos(sleep_time);
        writer.print("e");
        LockSupport.parkNanos(sleep_time);
        writer.print("l");
        LockSupport.parkNanos(sleep_time);
        writer.print("l");
        LockSupport.parkNanos(sleep_time);
        writer.print("o");
        LockSupport.parkNanos(sleep_time);
        writer.print("!");
        LockSupport.parkNanos(sleep_time);
        writer.println();
        writer.flush();
        writer.close();
//        Thread.sleep(5000);
      } catch (Exception e) {
        writer.close();
      }
    }
  }
}
