package com.hx.net.socket.base;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hx on 18-4-29.
 */
public class EchoTest {
  @Test
  public void server() {//线程池
    ExecutorService executorService = Executors.newFixedThreadPool(30);
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(1330);
      while (true) {
        try {
          Socket socket = serverSocket.accept();
          executorService.submit(new EchoServerTask(socket));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (serverSocket != null) try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }




  class EchoServerTask implements Runnable {
    private Socket socket;

    public EchoServerTask(Socket socket) {
      this.socket = socket;
    }

    private void onAccept(Socket socket) throws IOException {
      System.out.println("接受请求：" + socket.getRemoteSocketAddress());
      while(true){
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[100];
        int length;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((length=inputStream.read(bytes))>0){
          baos.write(bytes,0,length);
          if (bytes[length-1]==10)break;
        }
        String content=new String(baos.toByteArray(),0,baos.size()-1);
        System.out.println("read: "+content);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(content.getBytes());
        outputStream.write(10);
        outputStream.flush();
      }

    }

    @Override
    public void run() {
      try {
        onAccept(socket);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (socket != null) try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    new EchoTest().client();
  }



  @Test
  public void client() {
    Socket socket = null;
    try {
      socket = new Socket("localhost", 1330);
      socket.setSoTimeout(5000);
      while(true){
        System.out.print("input>");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(next.getBytes());
        outputStream.write(10);
        outputStream.flush();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[100];
        int length;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((length=inputStream.read(bytes))>0){
          baos.write(bytes,0,length);
          if (bytes[length-1]==10){
            break;
          }
        }
        System.out.println(new String(baos.toByteArray(),0,baos.size()-1));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
