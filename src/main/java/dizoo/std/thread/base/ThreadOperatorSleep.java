package dizoo.std.thread.base;

/**
 * 线程基本操作
 * sleep方法：让当前线程处于休眠状态，让出当次cpu的执行时间，但是该线程不丢失监视器的所属权
 * 意思是说：当多个线程访问一个对象时候，如果这个对象被
 * sleep了，那么另外一个对象需要等待这个sleep
 * @author HuangXing
 *
 */
public class ThreadOperatorSleep {

	public static void main(String[] args) {
		MyThreadSleep myThreadSleep = new MyThreadSleep();

		//新建线程，指定名称
		Thread t1 = new Thread(myThreadSleep, "hx");
		Thread t2 = new Thread(myThreadSleep, "ly");
		t1.start();
		t2.start();
	}
}

class MyThreadSleep implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i+"当前线程名称："+Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} 
