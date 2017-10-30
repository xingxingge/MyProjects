package com.topsec.tsm.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by hx on 16-3-19.
 */
public class DnsResolver {
  public static void main(String[] args) throws UnknownHostException {
    InetAddress address = InetAddress.getByName("6600");
    System.out.println(address);
    System.out.println("-----");
    InetAddress address1 = InetAddress.getLocalHost();
    System.out.println(address1);

    System.out.println("-----");
    InetAddress[] addresses = InetAddress
            .getAllByName("wap.ydjfc10086.com");
    System.out.println(addresses.length);
    for (InetAddress addr : addresses) {
      System.out.println(addr);
    }

  }
}
