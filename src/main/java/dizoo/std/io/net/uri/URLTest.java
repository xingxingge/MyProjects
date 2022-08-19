package dizoo.std.io.net.uri;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hx on 18-4-26.
 */
public class URLTest {
  @Test
  public void test1() throws IOException {
    URL url = new URL("http://www.baidu.com");
    URLConnection urlConnection = url.openConnection();
    InputStream inputStream = urlConnection.getInputStream();
    int available = inputStream.available();
    byte[] bytes = new byte[available];
    inputStream.read(bytes);
    System.out.println(urlConnection.getContentType());
    System.out.println(urlConnection.getContentLength());
    System.out.println(urlConnection.getContentEncoding());
    System.out.println(urlConnection.getDate());
    System.out.println(urlConnection.getExpiration());
    System.out.println(urlConnection.getLastModified());
    System.out.println(urlConnection.getHeaderFields());
    System.out.println(new String(bytes));


  }
}
