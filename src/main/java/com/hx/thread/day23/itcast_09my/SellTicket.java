package com.hx.thread.day23.itcast_09my;

/**
 * Created by hx on 15-10-24.
 */
public class SellTicket implements  Runnable {
  //票数
  private int tickets=50;
  //锁对象
  private Object lock = new Object();

  @Override
  public void run() {
    while (true){
      synchronized(lock){
        if (tickets>0){
          try {
            Thread.currentThread().sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName()+"正在出售第"+(tickets--)+"张票");
        }else
          return;
      }
    }
  }
}
