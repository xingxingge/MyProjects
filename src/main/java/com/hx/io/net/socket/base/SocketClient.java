package com.hx.io.net.socket.base;

/**
 * Created by hx on 18-4-22.
 */

import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class SocketClient {
  @Test
  public void test1() {
    Socket socket = null;
    try {
      socket = new Socket("time.nist.gov", 13);
//      socket.setSoTimeout(15000);
      InputStream inputStream = socket.getInputStream();
      StringBuilder sb = new StringBuilder();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
      char[] chars=new char[10];
      int length;
      while ((length=inputStreamReader.read(chars))>=0){
        sb.append(new String(chars,0,length));
      }
      System.out.println(sb.toString());
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
  @Test
  public void test2() {
    Socket socket = null;
    try {
      socket = new Socket("dict.org", 2628);
      socket.setSoTimeout(15000);
      OutputStream outputStream = socket.getOutputStream();
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
      outputStreamWriter.write("SHOW DB\r\n");
      outputStreamWriter.flush();

      StringBuilder sb = new StringBuilder();
      String str;
      while ((str=bufferedReader.readLine())!=null){
        sb.append(str).append("\n");
        if (str.startsWith("250 ")){
          break;
        }
      }
      System.out.println(sb.toString());
      outputStreamWriter.write("quit\r\n");
      outputStreamWriter.flush();
      bufferedReader.close();
      outputStream.close();
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


  @Test
  public void test3() {
    Socket socket = null;
    try {
      socket = new Socket("localhost", 1330);
//      socket.setSoTimeout(15000);
      InputStream inputStream = socket.getInputStream();
      StringBuilder sb = new StringBuilder();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
      char[] chars=new char[10];
      int length;
      while ((length=inputStreamReader.read(chars))>=0){//server ，flush后可以读，但是server socket没关闭，就会阻塞,所以client必须有读取结束标志
        sb.append(new String(chars,0,length));
      }
      System.out.println(sb.toString());
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


  public static void main(String args[]) throws Exception {
    // 要连接的服务端IP地址和端口
    String host = "127.0.0.1";
    int port = 55533;
    // 与服务端建立连接
    Socket socket = new Socket(host, port);
    // 建立连接后获得输出流
    OutputStream outputStream = socket.getOutputStream();
    String message = "你好  yiwangzhibujian";
    socket.getOutputStream().write(message.getBytes("UTF-8"));
    outputStream.close();
    socket.close();
    System.out.println("close");
  }
}
