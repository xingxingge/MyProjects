package com.hx.thread.day23.itcast_11;

public class SellTicket implements Runnable {

  // 定义100张票
  private static int tickets = 100;

  // 定义同一把锁
  private Object obj = new Object();
  private Demo d = new Demo();

  private int x = 0;

  //同步代码块用obj做锁
//	@Override
//	public void run() {
//		while (true) {
//			synchronized (obj) {
//				if (tickets > 0) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()
//							+ "正在出售第" + (tickets--) + "张票 ");
//				}
//			}
//		}
//	}

  //同步代码块用任意对象做锁
//	@Override
//	public void run() {
//		while (true) {
//			synchronized (d) {
//				if (tickets > 0) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()
//							+ "正在出售第" + (tickets--) + "张票 ");
//				}
//			}
//		}
//	}

  @Override
  public   void run() {
    while (true) {
      //t1,t2.t3同时进入,x=0
      if (x % 2 == 0) {
          sellTicket();
      } else {
        sellTicket();
      }
      x++;
    }
  }

//	private void sellTicket() {
//		synchronized (d) {
//			if (tickets > 0) {
//			try {
//					Thread.sleep(100);
//			} catch (InterruptedException e) {
//					e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName()
//						+ "正在出售第" + (tickets--) + "张票 ");
//			}
//		}
//	}

  //如果一个方法一进去就看到了代码被同步了，那么我就再想能不能把这个同步加在方法上呢?
//	 private synchronized void sellTicket() {
//			if (tickets > 0) {
//			try {
//					Thread.sleep(100);
//			} catch (InterruptedException e) {
//					e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName()
//						+ "正在出售第" + (tickets--) + "张票 ");
//			}
//	}

  private static synchronized void sellTicket() {
    if (tickets > 0) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName()
              + "正在出售第" + (tickets--) + "张票 ");
    }
  }
}

class Demo {
}
