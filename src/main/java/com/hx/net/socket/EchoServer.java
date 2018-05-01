package com.hx.net.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hx on 18-4-30.
 */
public class EchoServer {
  public static void main(String[] args) throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    ServerSocket serverSocket = serverSocketChannel.socket();
    InetSocketAddress address = new InetSocketAddress(5000);
    serverSocket.bind(address);
    serverSocketChannel.configureBlocking(false);
    Selector selector = Selector.open();
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    while (true) {
      int num = selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey selectionKey = iterator.next();
        iterator.remove();
        if (selectionKey.isAcceptable()) {
          ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
          SocketChannel accept = server.accept();
          accept.configureBlocking(false);
          SelectionKey register = accept.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
          register.attach(ByteBuffer.allocate(1024));
        }
        if (selectionKey.isReadable()) {
          SocketChannel client = (SocketChannel) selectionKey.channel();
          ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
//          byteBuffer.put("input>".getBytes());
          client.read(byteBuffer);
        }
        if (selectionKey.isWritable()) {
          SocketChannel client = (SocketChannel) selectionKey.channel();
          ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
          byteBuffer.flip();
          client.write(byteBuffer);
          byteBuffer.compact();
        }
      }
    }
  }
}
