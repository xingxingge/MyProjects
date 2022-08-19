package com.hx.thread.concurrent.nio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
  public static void main(String[] args) throws Exception {
    ExecutorService tp = Executors.newCachedThreadPool();
    ServerSocket echoServer = null;
    Socket clientSocket = null;
    try {
      echoServer = new ServerSocket(8000);
    } catch (IOException e) {
      System.out.println(e);
    }
    while (true) {
      try {
        clientSocket = echoServer.accept();
        System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
        tp.execute(new HandleMsg(clientSocket));
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }

  static class HandleMsg implements Runnable {
    private Socket clientSocket;

    BufferedReader is;
    PrintWriter os;
    public HandleMsg(Socket clientSocket) {
      this.clientSocket = clientSocket;

    }

    public void run() {
      try {
        is = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        os = new PrintWriter(clientSocket.getOutputStream(), true);
        // 从InputStream当中读取客户端所发送的数据
        String inputLine = null;
        long b = System.currentTimeMillis();
        while ((inputLine = is.readLine()) != null) {
          os.println(inputLine);
        }
        long e = System.currentTimeMillis();
        System.out.println("spend:" + (e - b) + " ms ");
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        //关闭资源
      }
    }
  }
}
