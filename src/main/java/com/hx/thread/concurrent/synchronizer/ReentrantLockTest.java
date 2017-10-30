package com.hx.thread.concurrent.synchronizer;

/**
 * Created by hx on 16-12-20.
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable {
  public static ReentrantLock lock = new ReentrantLock();
  public static int i = 0;

  @Override
  public void run() {
    for (int j = 0; j < 10000000; j++) {
      lock.lock();
      try {
        i++;
      } finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
    Thread t1 = new Thread(reentrantLockTest);
    Thread t2 = new Thread(reentrantLockTest);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(i);
  }

}
