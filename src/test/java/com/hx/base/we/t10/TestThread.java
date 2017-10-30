package com.hx.base.we.t10;

/**
 * Created by hx on 15-11-16.
 */
public class TestThread implements  Runnable {
  private int[] vals={1,2,3,4,5};


  public static void main(String[] args) {
    TestThread t = new TestThread();
    (new Thread(t)).start();
    (new Thread(t)).start();

  }
  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println("Value = "+vals[i]+".");
    }
  }
}
