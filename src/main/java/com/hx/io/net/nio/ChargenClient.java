package com.hx.io.net.nio;

/**
 * Created by hx on 18-4-30.
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
  public static final int DEFAULT_PORT = 1330;
  public static final String HOST= "localhost";
  public static void main(String[] args) {
    try {
      SocketAddress address = new InetSocketAddress(HOST, DEFAULT_PORT);

      SocketChannel client = SocketChannel.open(address);
      // 注意client的通道是阻塞模式，修改为非阻塞模式，则通过
      //client.configureBlocking(false);

      ByteBuffer buffer = ByteBuffer.allocate(74);
      // 使用通道的方式将缓冲区的数据写入标准输出
      // Channels.newChannel是构建一个写入目标流的通道
      WritableByteChannel out = Channels.newChannel(System.out);

      while (client.read(buffer) != -1) {
        // 回绕缓冲区，该方法将缓冲区准备为数据传出状态，输出通道从数据开头而不是末尾开始
        // 回绕缓冲区保持缓冲区数据不变，只是准备写入而不是读取
        buffer.flip();
        out.write(buffer);
        // 要重用现有缓冲区，再次读取之前需要清空缓冲区；该方法不会改变缓冲区数据
        // 只是简单重置索引
        buffer.clear();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
