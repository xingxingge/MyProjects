package dizoo.std.thread.concurrent.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by hx on 16-12-24.
 */
public class CyclicBarrierTest {

  //主线程程,栅栏线程达到一定数量后触发
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
        System.out.println("稍等一会");
        try {
          Thread.sleep(4000);
          System.out.println("士兵们开始工作...");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        flag = true;
      }
    }

  }

  //士兵线程
  public static class Soldier implements Runnable {
    private String name;
    private CyclicBarrier cyclic;

    public Soldier(String name, CyclicBarrier cyclic) {
      this.name = name;
      this.cyclic = cyclic;
    }

    private void dowork() {
      try {
        Thread.sleep(3000);
      } catch (Exception e) {
      }
      System.out.println(name + ": done");
    }

    @Override
    public void run() {
      System.out.println(name + " 报到！");
      //等待
      try {
        cyclic.await();
        //被唤醒，开始工作
        dowork();
        //工作完成等待
        cyclic.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
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
      threads[i] = new Thread(new Soldier("士兵" + i, barrier));
      threads[i].start();
      //      if(i==5){
      //        threads[i].interrupt();
      //      }
    }
  }
}
