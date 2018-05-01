package com.hx.net.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by hx on 18-4-30.
 */
public class NonblockingSingleFileHTTPServer {
  public NonblockingSingleFileHTTPServer() {
  }

  private ByteBuffer contentBuffer;
  private int port = 80;

  public NonblockingSingleFileHTTPServer(
          ByteBuffer data, String encoding, String MIMEType, int port)
          throws UnsupportedEncodingException {
    this.port = port;
    String header = "HTTP/1.0 200 OK\r\n"
            + "Server: OneFile 2.0\r\n"
            + "Content-length: " + data.limit() + "\r\n"
            + "Content-type: " + MIMEType +";charset=utf-8"+ "\r\n\r\n";
    byte[] headerData = header.getBytes("UTF-8");
    ByteBuffer buffer = ByteBuffer.allocate(
            data.limit() + headerData.length);
    buffer.put(headerData);
    buffer.put(data);
    buffer.flip();
    this.contentBuffer = buffer;

  }


  public void run() throws IOException {
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    ServerSocket serverSocket = serverChannel.socket();
    Selector selector = Selector.open();
    InetSocketAddress localPort = new InetSocketAddress(port);
    serverSocket.bind(localPort);
    serverChannel.configureBlocking(false);
    //服务端通道只注册accept事件
    serverChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      selector.select();
      Iterator keys = selector.selectedKeys().iterator();
      while (keys.hasNext()) {
        SelectionKey key = (SelectionKey) keys.next();
        //一定要删除已处理过的事件，否则下一次select仍然会提交给你
        keys.remove();
        try {
          if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            //给客户端通道注册可读事件
            SelectionKey newKey = channel.register(selector,
                    SelectionKey.OP_READ);
          } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            synchronized (buffer) {
              if (buffer.hasRemaining()) {
                //可写则写
                channel.write(buffer);
              } else {  // we're done
                //写完关闭客户端连接
                channel.close();

              }
            }
          } else if (key.isReadable()) {
            // Don't bother trying to parse the HTTP header.
            // Just read something.
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            channel.read(buffer);
            //buffer.flip();
            //System.out.println(new String(buffer.array()));

            // switch channel to write-only mode
            //更改为只注册可写事件
            key.interestOps(SelectionKey.OP_WRITE);
            key.attach(contentBuffer.duplicate());
          }
        } catch (IOException ex) {
          ex.printStackTrace();
          //取消key所对应的事件
          key.cancel();
          try {
            key.channel().close();
          } catch (IOException cex) {
            cex.printStackTrace();
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    try {
      String contentType = "text/html";
//      if (args[0].endsWith(".html") || args[0].endsWith(".htm")) {
//        contentType = "text/html";
//      }
      String file = "/f/JavaHome/Resources/NIO 入门.html";
      FileInputStream fin = new FileInputStream(file);
      FileChannel in = fin.getChannel();
      ByteBuffer input = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
      int port=1330;
//      try {
//        port = Integer.parseInt(args[1]);
//        if (port < 1 || port > 65535) port = 80;
//      } catch (Exception ex) {
//        port = 80;
//      }
      String encoding = "UTF-8";
//      if (args.length > 2) encoding = args[2];

      NonblockingSingleFileHTTPServer server

              = new NonblockingSingleFileHTTPServer(
              input, encoding, contentType, port);
      server.run();
    } catch (Exception ex) {
      ex.printStackTrace();
      System.err.println(ex);
    }
  }
}
