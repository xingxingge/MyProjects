package dizoo.std.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	public static void main(String[] args) {
		//单线程池任务串行执行
//		singleThreadPool();
		//多线程池，可以并行执行
//		newFixedThreadPool();
		//可缓存的线程池
//		newCachedThreadPool();
		//创建大小无线的线程池
		newScheduledThreadPool();
	}

	private static void newScheduledThreadPool() {
		ExecutorService es = Executors.newScheduledThreadPool(2);
		MyThread myThread = new MyThread();
		MyThread myThread2 = new MyThread();
		MyThread myThread3 = new MyThread();
		es.execute(myThread);
		es.execute(myThread2);
		es.execute(myThread3);
		es.shutdown();
	}

	public static void newCachedThreadPool() {
		ExecutorService es = Executors.newCachedThreadPool();
		MyThread myThread = new MyThread();
		MyThread myThread2 = new MyThread();
		MyThread myThread3 = new MyThread();
		es.execute(myThread);
		es.execute(myThread2);
		es.execute(myThread3);
	}

	public static void newFixedThreadPool() {
		ExecutorService es = Executors.newFixedThreadPool(2);
		MyThread myThread = new MyThread();
		MyThread myThread2 = new MyThread();
		MyThread myThread3 = new MyThread();
		es.execute(myThread);
		es.execute(myThread2);
		es.execute(myThread3);
	}

	public static void singleThreadPool() {
		ExecutorService es = Executors.newSingleThreadExecutor();
		MyThread myThread = new MyThread();
		MyThread myThread2 = new MyThread();
		es.execute(myThread);
		es.execute(myThread2);
	}

}

class MyThread implements Runnable{
	@Override
	public void run(){
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()+"-"+i);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException  e){
				e.printStackTrace();
			}
		}
	}
}
