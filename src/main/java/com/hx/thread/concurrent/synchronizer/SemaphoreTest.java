package com.hx.thread.concurrent.synchronizer;

/**
 * Created by hx on 16-12-20.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable {
  final Semaphore semaphore = new Semaphore(5);

  @Override
  public void run() {
    try {
      semaphore.acquire();
      Thread.sleep(2000);
      System.out.println(Thread.currentThread().getId() + " done");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(20);
    final SemaphoreTest t = new SemaphoreTest();
    for (int i = 0; i < 20; i++) {
      executorService.submit(t);
    }
  }

}
