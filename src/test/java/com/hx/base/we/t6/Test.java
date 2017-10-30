package com.hx.base.we.t6;

/**
 * Created by hx on 15-11-16.
 */
public class Test {
  public static void main(String[] args) {
    try {
      A a = new A();
      Thread t = new Thread(a);
      t.start();
//      a.wait();
//      t.wait();
//      t.join();
//      t.yield();
//      t.notify();
      t.interrupt();
      int j = a.I;
      System.out.println(j);
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("error");
    }


  }
}

class A implements Runnable {
  int I;
  @Override
  public void run() {
    try {
      Thread.sleep(2000);
      I = 10;
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println("error 1");

    }
  }
}
