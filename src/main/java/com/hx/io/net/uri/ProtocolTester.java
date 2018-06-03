package com.hx.io.net.uri;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by hx on 18-4-25.
 */
public class ProtocolTester {
  public static void main(String[] args) throws URISyntaxException {
    testProtocol("http://www.baidu.com/test/jd.jsp");
  }
  private static void  testProtocol(String url) throws URISyntaxException {
    try {
      URL u = new URL(url);
      System.out.println(u.getProtocol()+" is ok ");
      System.out.println(u.toExternalForm());
      URI uri = u.toURI();
      System.out.println(uri.toString());

    } catch (MalformedURLException e) {
      String p = url.substring(0,url.indexOf(':'));
      System.out.println(p+" is not ok");
    }
  }
}
