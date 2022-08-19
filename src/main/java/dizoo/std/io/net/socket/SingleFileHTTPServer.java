package dizoo.std.io.net.socket;

/**
 * Created by hx on 18-4-29.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SingleFileHTTPServer {

  private byte[] content;
  private byte[] header;
  private int port=80;
  private String encoding;

  private SingleFileHTTPServer(String data, String encoding,
                               String MIMEType, int port) throws UnsupportedEncodingException {
    this(data.getBytes(encoding), encoding, MIMEType, port);
  }

  public SingleFileHTTPServer(byte[] data, String encoding, String MIMEType, int port)throws UnsupportedEncodingException {
    this.content=data;
    this.port=port;
    this.encoding=encoding;
    String header="HTTP/1.0 200 OK\r\n"+
            "Server: OneFile 1.0\r\n"+
            "Content-length: "+this.content.length+"\r\n"+
            "Content-type: "+MIMEType+"\r\n\r\n";
    this.header=header.getBytes(encoding);
  }

  class HTTPHandler implements Runnable {

    public HTTPHandler(Socket connection) {
      this.connection = connection;
    }

    private Socket connection;

    @Override
    public void run() {
      try {
        OutputStream out = new BufferedOutputStream(connection.getOutputStream());
        InputStream in = new BufferedInputStream(connection.getInputStream());
        StringBuffer request = new StringBuffer();
        while (true) {
          int c = in.read();
          if (c == '\r' || c == '\n' || c == -1) {
            break;
          }
          request.append((char) c);
        }
        //如果检测到是HTTP/1.0及以后的协议，按照规范，需要发送一个MIME首部
        if (request.toString().indexOf("HTTP/") != -1) {
          out.write(header);
        }
        out.write(content);
        out.flush();
      }catch (IOException e){
        e.printStackTrace();

      }finally {
        try {
          connection.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }


    }
  }

  public void start() {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    try {
      ServerSocket server=new ServerSocket(this.port);
      System.out.println("Accepting connections on port "+server.getLocalPort());
      System.out.println("Data to be sent:");
      System.out.write(this.content);

      while (true) {
        Socket connection;
        try {
          connection=server.accept();
          executorService.submit(new HTTPHandler(connection));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    } catch (IOException e) {
      System.err.println("Could not start server. Port Occupied");
    }
  }

  public static void main(String[] args) {
    try {
      String contentType="text/plain";
//      if (args[0].endsWith(".html")||args[0].endsWith(".htm")) {
//        contentType="text/html";
//      }

      InputStream in=new FileInputStream("/f/JavaHome/code/MyProjects/MyProjects/src/main/java/com/hx/io/net/socket/SingleFileHTTPServer.java");
      ByteArrayOutputStream out=new ByteArrayOutputStream();
      int b;
      while ((b=in.read())!=-1) {
        out.write(b);
      }
      byte[] data=out.toByteArray();
      in.close();
      int port=5000;
      String encoding="UTF-8";
      new SingleFileHTTPServer(data, encoding, contentType, port).start();
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Usage:java SingleFileHTTPServer filename port encoding");
    }catch (Exception e) {
      System.err.println(e);// TODO: handle exception
    }
  }
}
