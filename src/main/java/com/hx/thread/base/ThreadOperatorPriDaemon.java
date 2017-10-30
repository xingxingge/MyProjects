package com.hx.thread.base;

/**
 *设置线程优先级
 *设置线程为守护线程，只有用户线程存在时，守护线程才存在，否则退出
 * @author HuangXing
 * 
 */
public class ThreadOperatorPriDaemon {

	public static void main(String[] args) {
		MyThreadDaemon myThreadDaemon = new MyThreadDaemon();
		
		// 新建线程，指定名称
		Thread t1 = new Thread(myThreadDaemon, "t1");
		t1.setPriority(Thread.MIN_PRIORITY);
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.setDaemon(true);
		t1.start();
		for (int i = 0; i < 7; i++) {
			System.out.println(i + "当前线程名称：" + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}

class MyThreadDaemon implements Runnable{
	private boolean flag=true;
	

	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		int i=0;
		//以flag作为是否结束的条件
		while (flag) {
			System.out.println(i + "当前线程名称：" + Thread.currentThread().getName());
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
}
