package com.hx.thread.concurrent.synchronizer;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by hx on 16-12-24.
 */
public class CyclicBarrierTest implements Runnable {
  private String soldier;
  private final CyclicBarrier cyclic;

  public CyclicBarrierTest(String soldier, CyclicBarrier cyclic) {
    this.soldier = soldier;
    this.cyclic = cyclic;
  }

  @Override
  public void run() {
    try {
      //等待所有士兵到齐
      cyclic.await();
      dowork();
      //等待所有士兵完成工作
      cyclic.await();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  private void dowork() {
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
    }
    System.out.println(soldier + ": done");
  }

  public static class BarrierRun implements Runnable {

    boolean flag;
    int n;

    public BarrierRun(boolean flag, int n) {
      super();
      this.flag = flag;
      this.n = n;
    }

    @Override
    public void run() {
      if (flag) {
        System.out.println(n + "个任务完成");
      } else {
        System.out.println(n + "个集合完成");
        flag = true;
      }

    }

  }

  public static void main(String[] args) {
    final int n = 10;
    Thread[] threads = new Thread[n];
    boolean flag = false;
    CyclicBarrier barrier = new CyclicBarrier(n, new BarrierRun(flag, n));
    System.out.println("集合");
    for (int i = 0; i < n; i++) {
      System.out.println(i + "报道");
      threads[i] = new Thread(new CyclicBarrierTest("士兵" + i, barrier));
      threads[i].start();
    }
  }
}
