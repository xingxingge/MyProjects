package dizoo.std.thread.base;

/**
 * 线程基本操作
 * @author HuangXing
 *
 */
public class ThreadBaseOperator {

	public static void main(String[] args) {
		//打印main线程的名称和状态
		System.out.println("主线程名称: "+Thread.currentThread().getName());
		System.out.println("主线程id: "+Thread.currentThread().getId());
		//新建线程，指定名称
		Thread t1 = new Thread(new MyThread(), "hx");
		t1.start();
		//修改线程名称
		System.out.println("线程"+t1.getName()+"是否存活："+t1.isAlive());
		t1.setName("liyun");
		//判断线程是否存活

		System.out.println("线程"+t1.getName()+"是否存活："+t1.isAlive());
	}
}

class MyThread implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Thread.currentThread().setName(String.valueOf(i));
			System.out.println("当前线程名称："+Thread.currentThread().getName());
			System.out.println("当前线程id："+Thread.currentThread().getId());
			System.out.print(i+"\t");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 
