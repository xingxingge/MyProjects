package com.hx.thread.day23.itcast_04;

/*
 * public final void join():等待该线程终止。 
 */
public class ThreadJoinDemo {
	public static void main(String[] args) {
		ThreadJoin tj1 = new ThreadJoin();
		ThreadJoin tj2 = new ThreadJoin();
		ThreadJoin tj3 = new ThreadJoin();

		tj1.setName("李渊");
		tj2.setName("李世民");
		tj3.setName("李元霸");

		tj1.start();


		//tj1走完了下面才能走
		try {
			tj1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		tj2.start();

		tj3.start();
	}
}
