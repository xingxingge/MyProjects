package com.hx.nio.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class EchoClient_NIO {
  private Selector selector;

  private ByteBuffer sendBuf = ByteBuffer.allocate(1024);

  private ByteBuffer revBuf = ByteBuffer.allocate(1024);

  public EchoClient_NIO() {
    try {
      System.out.println("正在连接服务器...");
      SocketChannel socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
      selector = Selector.open();
      socketChannel.register(selector, SelectionKey.OP_CONNECT);
      socketChannel.connect(new InetSocketAddress("localhost", 8777));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void service() throws IOException {
    while (true) {
      selector.select();
      Set<SelectionKey> keys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = keys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove();
        if (key.isConnectable()) {
          connect(key);
        } else if (key.isReadable()) {
          read(key);
        } else if (key.isWritable()) {
          write(key);
        }
        keys.clear();
      }
    }
  }

  private void connect(SelectionKey key) {
    try {
      SocketChannel sc = (SocketChannel) key.channel();
      if (sc.isConnectionPending()) {
        sc.finishConnect();
        System.out.println("服务器连接成功");
        sc.register(selector, SelectionKey.OP_WRITE);
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("服务器连接失败");
    }
  }

  private void read(SelectionKey key) {
    try {
      revBuf.clear();
      SocketChannel sc = (SocketChannel) key.channel();
      int read = sc.read(revBuf);
      if (read > 0) {
        revBuf.flip();
        String recText = new String(revBuf.array(), 0, read);
        System.out.println(recText);
        if (recText.equals("quit")) {
          key.channel().close();
          key.cancel();
          return;
        }
        sc.register(selector, SelectionKey.OP_WRITE);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void write(SelectionKey key) {
    try {
      SocketChannel sc = (SocketChannel) key.channel();
      Scanner scanner = new Scanner(System.in);
      String message = scanner.nextLine();
      byte[] bytes = message.getBytes();
      int start = 0;
      int length = 1024;
      while (start < bytes.length) {
        sendBuf.clear();
        int len=length;
        if(start+len>bytes.length){
          len=bytes.length-start;
        }
        sendBuf.put(bytes, start, len);
        start+=len;
        sendBuf.flip();
        sc.write(sendBuf);
      }

//      sendBuf.put(message.getBytes());
//      sendBuf.flip();
//      sc.write(sendBuf);
      if (message.equals("quit")) {
        key.channel().close();
        key.cancel();
        selector.close();
        return;
      }
      sc.register(selector, SelectionKey.OP_READ);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
//  private void write(SelectionKey key) {
//    try {
//      sendBuf.clear();
//      SocketChannel sc = (SocketChannel) key.channel();
//      Scanner scanner = new Scanner(System.in);
//      String message = scanner.nextLine();
//      sendBuf.put(message.getBytes());
//      sendBuf.flip();
//      sc.write(sendBuf);
//      if (message.equals("quit")){
//        key.channel().close();
//        key.cancel();
//        selector.close();
//        return;
//      }
//      sc.register(selector,SelectionKey.OP_READ);
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//  }

  public static void main(String[] args) throws IOException {
    new EchoClient_NIO().service();
  }
}
