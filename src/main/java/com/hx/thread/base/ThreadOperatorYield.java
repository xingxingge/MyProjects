package com.hx.thread.base;

/**
 * Yield,当前线程让出当次cpu执行时间，然后立即进入就绪状态，继续争抢cpu
 * 
 * @author HuangXing
 * 
 */
public class ThreadOperatorYield {

	public static void main(String[] args) {
		MyThreadYield myThreadYield = new MyThreadYield();

		// 新建线程，指定名称
		Thread t1 = new Thread(myThreadYield, "hx");
		t1.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(i + "当前线程名称：" + Thread.currentThread().getName());
			if (i == 3) {
				Thread.yield();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}

class MyThreadYield implements Runnable {

	@Override
	public void run() {
		///一但中断就不执行了，所以需要用while循环
		for (int j = 0; j < 10; j++) {
			System.out.println(j + "当前线程名称：" + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
