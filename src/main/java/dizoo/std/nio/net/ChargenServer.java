package dizoo.std.nio.net;

/**
 * Created by hx on 18-4-30.
 */

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

public class ChargenServer {
  public static final int DEFAULT_PORT = 1330;

  // 服务器端单线程处理多客户端连接
  public static void main(String[] args) {
    int port = DEFAULT_PORT;
    System.out.println("listening for connections on port: " + port);

    // 可打印ASCII共95个，由于每打印一行需要左移一个字符，这里生成92*2个只是为了打印方便
    byte[] rotation = new byte[95 * 2];
    for (byte i = ' '; i <= '~'; i++) {
      rotation[i - ' '] = i;
      rotation[i + 95 - ' '] = i;
    }

    ServerSocketChannel serverChannel;
    // Selector只接受非阻塞通道
    Selector selector;
    try {
      serverChannel = ServerSocketChannel.open();
      // 要绑定端口，需要先用socket方法获取ServerSocket的对等端peer
      // 然后使用bind绑定端口
      ServerSocket ss = serverChannel.socket();
      InetSocketAddress address = new InetSocketAddress(DEFAULT_PORT);
      ss.bind(address);
      // 配置serverChannel为非阻塞模式，如果没有连接则accpet方法立即返回null， 如果不设置，accept方法将一直阻塞直到有连接进入
      serverChannel.configureBlocking(false);
      // 使用选择器迭代处理准备好的连接
      selector = Selector.open();
      // 向选择器注册对此通道的监视，对于chargen，只关心服务器Socket是否准备好接收一个新连接
      serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    while (true) {
      try {
        // 检查是否有可操作的通道准备好接受IO操作
        selector.select();
      } catch (IOException e) {
        e.printStackTrace();
        break;
      }

      // 获取就绪通道的的SelectionKey的集合
      Set<SelectionKey> readyKey = selector.selectedKeys();
      // 迭代处理所有的key
      Iterator<SelectionKey> iterator = readyKey.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        // 已经处理的key需要从集合中删除，标记该键已经处理
        // 若下次该key还再次准备好，还将继续出现在Set中
        iterator.remove();

        try {
          // 测试该key是否已经准备好接收一个新的socket连接，即此时就绪的是服务器通道，接收一个新的Socket通道，将其添加到选择器
          if (key.isAcceptable()) {
            // 获取该key创建的channel
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            // 接受一个到此server通道的连接
            SocketChannel client = server.accept();
            System.out.println("accepted from " + client);
            // 配置client的通道为非阻塞模式
            client.configureBlocking(false);
            SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);

            ByteBuffer buffer = ByteBuffer.allocate(74);
            buffer.put(rotation, 0, 72);
            buffer.put((byte) '\r');
            buffer.put((byte) '\n');
            // 回绕缓冲区
            buffer.flip();
            key2.attach(buffer);
          } else if (key.isWritable()) {// 测试该key是否准备好写操作，即此时就绪的是Socket通道，向缓冲区写入数据
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            if (!buffer.hasRemaining()) {
              // 用下一行重新填充缓冲区
              buffer.rewind();
              // 得到上一次首字符
              int first = buffer.get();
              // 准备改变缓冲区数据
              buffer.rewind();
              // 寻找rotation中新的首字符位置
              int position = first - ' ' + 1;
              // 将数据从rotation复制到缓冲区
              buffer.put(rotation, position, 72);
              // 在缓冲区末尾存放一个行分隔符
              buffer.put((byte) '\r');
              buffer.put((byte) '\n');
              // 回绕缓冲区，准备写入
              buffer.flip();
            }
            client.write(buffer);
          }
          // 没有就绪通道，选择器等待就绪通道
        } catch (IOException e) {
          key.cancel();
          try {
            key.channel().close();
          } catch (IOException ex) {

          }
        }
      }
    }
  }
}
