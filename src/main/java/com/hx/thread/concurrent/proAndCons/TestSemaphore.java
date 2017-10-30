package com.hx.thread.concurrent.proAndCons;

import java.util.concurrent.Semaphore;

/**
 * Created by hx on 16-12-24.
 */
public class TestSemaphore {
  int count = 0;
  final Semaphore notFull = new Semaphore(10);
  final Semaphore notEmpty = new Semaphore(0);
  final Semaphore mutex = new Semaphore(1);

  class Producer implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(3000);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          notFull.acquire();//顺序不能颠倒，否则会造成死锁。
          mutex.acquire();
          count++;
          System.out.println(Thread.currentThread().getName()
                  + "生产者生产，目前总共有" + count);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          mutex.release();
          notEmpty.release();
        }

      }
    }
  }

  class Consumer implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
        try {
          notEmpty.acquire();//顺序不能颠倒，否则会造成死锁。
          mutex.acquire();
          count--;
          System.out.println(Thread.currentThread().getName()
                  + "消费者消费，目前总共有" + count);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          mutex.release();
          notFull.release();
        }

      }

    }

  }

  public static void main(String[] args) throws Exception {
    TestSemaphore hosee = new TestSemaphore();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Consumer()).start();

    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Consumer()).start();
  }
}
