package dizoo.std.thread.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * Created by hx on 16-12-25.
 */
public class LinkedBlockingQueueTest {
  public static void main(String[] args) throws InterruptedException {
//    ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
//    ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
    //如果前面的任务还未完成，则调度不会启动。
    ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()) {

      @Override
      protected void beforeExecute(Thread t, Runnable r) {

        System.out.println("准备执行:"+t.getName());
      }

      @Override
      protected void afterExecute(Runnable r, Throwable t) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("执行完成:");
      }

      @Override
      protected void terminated() {
        System.out.println("线程池退出");
      }

    };

    es.execute(new MyRunnable(1));
    es.execute(new MyRunnable(2));
    es.execute(new MyRunnable(3));
    es.execute(new MyRunnable(4));
    es.execute(new MyRunnable(5));
    es.execute(new MyRunnable(6));
//    Thread.sleep(10000);
    es.shutdown();



//    ses.scheduleWithFixedDelay(runnable1, 1, 1, TimeUnit.SECONDS);//启动0秒后执行，然后周期2秒执行一次
//    ses.scheduleWithFixedDelay(runnable2, 1, 1, TimeUnit.SECONDS);//启动0秒后执行，然后周期2秒执行一次
//    ses.shutdown();
  }




}
class MyRunnable implements Runnable {
  private int i;
  public MyRunnable(int i){
    this.i=i;
  }


  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName()+":"+i);

  }
}
