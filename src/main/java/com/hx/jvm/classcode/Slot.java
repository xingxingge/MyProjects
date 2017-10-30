package com.hx.jvm.classcode;

/**
 * Created by hx on 16-10-17.
 */
public class Slot {
  public static void main(String[] args) {
    {
      byte[] placeholder = new byte[64 * 1024 * 1024];
      placeholder=null;
    }
    int a=0;
    int c;

    System.gc();
  }
}
