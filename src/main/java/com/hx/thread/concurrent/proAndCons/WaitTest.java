package com.hx.thread.concurrent.proAndCons;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unit test for simple App.
 */
public class WaitTest
        extends TestCase {
  private static List<Integer> list = new ArrayList<Integer>();
  private static Object event = new Object();

  static AtomicInteger atomicInteger = new AtomicInteger(200);

  public static void main(String[] args) {
    Thread t1 = new Thread(new Producer());
    Thread t2 = new Thread(new Consumer());
    t1.start();
    t2.start();
  }

  static class Producer implements Runnable {

    public void add() {
      synchronized (event) {
        while (list.size() >= 20) {
          try {
            event.wait();
            System.out.println("add continue");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        list.add(atomicInteger.incrementAndGet());
        event.notify();
        System.out.println("add notify");
      }
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        add();
      }
    }
  }

  static class Consumer implements Runnable {

    public int  remove() {
      int value;
      synchronized (event) {
        while (list.size() == 0) {
          try {
            event.wait();
            System.out.println("remove continue");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println(list.size());
         value  = list.remove(0);
        event.notify();
        System.out.println("remove notify...");
        return value;
      }
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(remove());
      }
    }
  }
}
