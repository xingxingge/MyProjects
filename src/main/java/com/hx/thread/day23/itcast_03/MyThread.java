package com.hx.thread.day23.itcast_03;

public class MyThread extends Thread {

	public MyThread() {
	}
	
	public MyThread(String name){
		super(name);
	}

	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			//本线程可以使用getName方式
			System.out.println(getName() + ":" + x);
//			System.out.println(Thread.currentThread().getName());
		}
	}
}
