package com.hx.io.net.nio;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by hx on 18-5-1.
 */
public class HttpServerClient {
  @Test
  public void testClient1() throws IOException {
    SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 1330));
    socketChannel.configureBlocking(true);//阻塞模式
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    buffer.put((byte) 100);
    if (buffer.hasRemaining()) {
      socketChannel.write(buffer);//写数据，发送请求
    }
    while (true) {
      buffer.clear();
      int read = socketChannel.read(buffer);
      if (read <= 0) break;
      if (read < 1024) {
        System.out.println("xxx");
      }
      buffer.flip();
      byteArrayOutputStream.write(buffer.array(), 0, buffer.limit());
    }
    System.out.println(new String(byteArrayOutputStream.toByteArray()));
  }

  @Test
  public void testClient2() throws IOException {
    SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 1330));
    socketChannel.configureBlocking(false);
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
    buffer.put((byte) 100);
    if (buffer.hasRemaining()) {
      socketChannel.write(buffer);//写数据，发送请求
    }
    while (true) {
      buffer.clear();
      int read = socketChannel.read(buffer);
      if (read <= 0) break;
      if (read < 1024) {
        System.out.println("xxx");
      }
      buffer.flip();
      writableByteChannel.write(buffer);
    }
  }
}
