package com.hx.thread;

import java.io.IOException;

/**
 * Created by hx on 16-5-13.
 */
public class TestSyString {

  public void test(String str) throws InterruptedException {
    synchronized (str) {
      Thread.sleep(3000);
      System.out.println(Thread.currentThread());

    }
  }

  public static void main(String[] args) throws InterruptedException, IOException {
    final TestSyString ts = new TestSyString();

    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          ts.test("name");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          ts.test("name");
//          ts.test(new String("name"));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });


    Thread thread3 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {

          String str = "name";
          synchronized (str) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread());
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    thread1.setName("t1");
    thread2.setName("t2");
    thread3.setName("t3");
    thread1.start();
    thread2.start();
    thread3.start();

  }
}
