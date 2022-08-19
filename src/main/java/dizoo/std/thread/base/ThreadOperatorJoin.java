package dizoo.std.thread.base;

/**
 * 线程基本操作
 * Join方法：当前进程执行暂停，让另外一个进程执行一段时间后再执行当前进程，或者可以指定等待时间
 * @author HuangXing
 *
 */
public class ThreadOperatorJoin {

	public static void main(String[] args) {
		MyThreadJoin myThreadJoin = new MyThreadJoin();

		//新建线程，指定名称
		Thread t1 = new Thread(myThreadJoin, "hx");
		t1.start();
		for (int i = 0; i < 10; i++) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (i==3) {
				try {
					t1.join();
//					t1.join(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			System.out.println(i+"当前线程名称："+Thread.currentThread().getName());
			
		}
	}
}

class MyThreadJoin implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i+"当前线程名称："+Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} 
