package com.hx.thread.concurrent.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hx on 16-12-24.
 */
public class CountDownLatchTest implements Runnable {
  static final CountDownLatch countDownLatch = new CountDownLatch(5);
  static final CountDownLatchTest t = new CountDownLatchTest();
  private int i;

  public CountDownLatchTest(int i) {
    this.i = i;
  }
  public CountDownLatchTest() {
  }

  @Override
  public void run() {
    try {
      Thread.sleep(2000);
      System.out.println("complete:"+i);
      countDownLatch.countDown();
      System.out.println("ok:"+i);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      executorService.execute(new CountDownLatchTest(i));
//      executorService.execute(t);
    }
    countDownLatch.await();
    System.out.println("end...");
    executorService.shutdown();
  }
}
