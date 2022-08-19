package dizoo.std.thread.base;

/**
 *自定义中断线程，实质是用自定义标记停止run方法。项目中常用的
 * 好处：1.不用报安全异常，2.不用手动去中断异常
 * @author HuangXing
 * 
 */
public class ThreadOperatorInterruptKillSelf {

	public static void main(String[] args) {
		MyThreadInterruptKillSelf myThreadInterruptKillSelf = new MyThreadInterruptKillSelf();
		MyThreadDaemon  myThreadInterruptKillSelfAux = new MyThreadDaemon();
		
		// 新建线程，指定名称
		Thread t1 = new Thread(myThreadInterruptKillSelf, "t1");
		Thread t2 = new Thread(myThreadInterruptKillSelfAux, "t2");
		t1.start();
		t2.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(i + "当前线程名称：" + Thread.currentThread().getName());
			if (i == 3) {
				t1.interrupt();//把线程标记为中断状态（中断状态为true），线程是独立的，不能被其他线程中断
				myThreadInterruptKillSelfAux.setFlag(false);//中断线程，实际就是run方法执行结束
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}



class MyThreadInterruptKillSelfAux implements Runnable{
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

class MyThreadInterruptKillSelf implements Runnable {

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
