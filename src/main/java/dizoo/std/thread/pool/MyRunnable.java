package dizoo.std.thread.pool;

public class MyRunnable implements Runnable {
	private int i;

	public MyRunnable(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		for (int x = 0; x <= 5; x++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":"+"i"+":"+i+":" + x);
		}
	}

}
