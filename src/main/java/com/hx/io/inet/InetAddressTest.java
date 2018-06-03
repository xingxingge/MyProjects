package com.hx.io.inet;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by hx on 18-4-25.
 */
public class InetAddressTest {
  @Test
  public void test1() throws IOException {
    InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
    System.out.println(inetAddress);
    System.out.println(inetAddress.getCanonicalHostName());
    System.out.println("========================");
     inetAddress = InetAddress.getByName("202.113.16.10");
    System.out.println(inetAddress.getHostName());
    System.out.println(inetAddress.getCanonicalHostName());
    System.out.println("========================");
    inetAddress = InetAddress.getByName("1.111.1.1");
    System.out.println(inetAddress.getHostName());
    System.out.println("========================");
    InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
    for (InetAddress inetAddress1:allByName){
      System.out.println(inetAddress1);
    }
    System.out.println("========================");
    InetAddress localHost = InetAddress.getLocalHost();
    System.out.println(localHost);
    System.out.println(localHost.getCanonicalHostName());
    System.out.println("========================");

    byte[] address=new byte[]{(byte)220,(byte)181,57,(byte)216};
    InetAddress byAddress = InetAddress.getByAddress(address);
    System.out.println(byAddress);
    System.out.println("========================");

    InetAddress byAddress1 = InetAddress.getByAddress("baidu.com", address);
    System.out.println(byAddress1);
    System.out.println(byAddress1.isReachable(1000));
  }
}
