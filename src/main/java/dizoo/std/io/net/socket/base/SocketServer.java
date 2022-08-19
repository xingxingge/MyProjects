package dizoo.std.io.net.socket.base;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hx on 18-4-22.
 */
public class SocketServer {

  @Test
  public void test3() {//单线程
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(1330);
      while (true) {
        Socket socket = null;
        try {
          socket = serverSocket.accept();
          onAccept(socket);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try {
            socket.close();
          } catch (IOException e) {
            e.printStackTrace();
          }

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

  @Test
  public void test33() {//多线程
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(1330);

      while (true) {
        Socket socket = serverSocket.accept();
        new Thread(new SocketTask(socket)).start();
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

  @Test
  public void test333() {//线程池
    ExecutorService executorService = Executors.newFixedThreadPool(30);
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(1330);
      while (true) {
        try {
          Socket socket = serverSocket.accept();
          executorService.submit(new SocketTask(socket));
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


  class SocketTask implements Runnable {
    private Socket socket;

    public SocketTask(Socket socket) {
      this.socket = socket;
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

  private void onAccept(Socket socket) throws IOException {
    System.out.println("接受请求：" + socket.getRemoteSocketAddress());
    OutputStream outputStream = socket.getOutputStream();
    Writer writer = new OutputStreamWriter(outputStream);
    writer.write(new Date().toString() + "\n");
    writer.flush();
  }

  public static void main(String[] args) throws Exception {
    // 监听指定的端口
    int port = 55533;
    ServerSocket server = new ServerSocket(port);

    // server将一直等待连接的到来
    System.out.println("server将一直等待连接的到来");
    Socket socket = server.accept();
    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
    InputStream inputStream = socket.getInputStream();
    byte[] bytes = new byte[1024];
    int len;
    StringBuilder sb = new StringBuilder();
    while ((len = inputStream.read(bytes)) != -1) {
      //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
      sb.append(new String(bytes, 0, len, "UTF-8"));
    }
    System.out.println("get message from client: " + sb);
    inputStream.close();
    socket.close();
    server.close();
    System.out.println("close");
  }
}
