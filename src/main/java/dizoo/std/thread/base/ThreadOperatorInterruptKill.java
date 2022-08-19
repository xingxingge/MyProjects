package dizoo.std.thread.base;

/**
 * 线程基本操作 interrupt方法：线程要中断，必须是自己中断，其他线程只能通知它自己终端。
 * 当调用interrupt()方法时，线程标记为interrupt，如果调用sleep，wait，join等方法，线程将会抛出中断异常，然后再度标记
 * 为true,这样线程可以继续执行，如果要继续中断线程，需要让自己来中断。实际开发中可以自定义标记中断
 * 
 * @author HuangXing
 * 
 */
public class ThreadOperatorInterruptKill {

	public static void main(String[] args) {
		MyThreadInterruptSelf myThreadInterruptSelf = new MyThreadInterruptSelf();

		// 新建线程，指定名称
		Thread t1 = new Thread(myThreadInterruptSelf, "hx");
		t1.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(i + "当前线程名称：" + Thread.currentThread().getName());
			if (i == 3) {
				t1.interrupt();//把线程标记为中断状态（中断状态为true），线程是独立的，不能被其他线程中断
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}

class MyThreadInterruptSelf implements Runnable {

	@Override
	public void run() {
		///一但中断就不执行了，所以需要用while循环
		int i=0;
		while (!Thread.interrupted()) {
			System.out.println(i + "当前线程名称：" + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				//抛出异常后,需要自己中断自己
				Thread.currentThread().interrupt();
			}
			i++;
		}
	}
}
