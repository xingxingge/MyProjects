package com.hx.thread.concurrent.proAndCons;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Unit test for simple App.
 */
public class WaitTest2
        extends TestCase {
  private static List<Integer> list = new ArrayList<Integer>();
  private static final ReentrantLock lock = new ReentrantLock();
  private static final Condition notEmpty = lock.newCondition();
  private static final Condition notFull = lock.newCondition();

  static AtomicInteger atomicInteger = new AtomicInteger(200);

  public static void main(String[] args) {
    Thread t1 = new Thread(new Producer());
    Thread t2 = new Thread(new Consumer());
    t1.start();
    t2.start();
  }

  static class Producer implements Runnable {

    public void add() throws InterruptedException {
      lock.lockInterruptibly();
      try {
        while (list.size() >= 100) {
          notFull.await();
        }
        list.add(atomicInteger.incrementAndGet());
        notEmpty.signal();
      } catch (InterruptedException e) {
        notFull.signal();
        throw e;
      } finally {
        lock.unlock();
      }
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        try {
          add();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class Consumer implements Runnable {

    public void remove() throws InterruptedException {
      lock.lockInterruptibly();
      try {
        while (list.size() ==0) {
          notEmpty.await();
        }
        System.out.println(list.remove(0));
        notFull.signal();
      } catch (InterruptedException e) {
        notEmpty.signal();
        throw e;
      } finally {
        lock.unlock();
      }
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(40);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        try {
          remove();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
