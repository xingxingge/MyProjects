package dizoo.std.thread.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * Created by hx on 16-12-24.
 */
public class ScheduledThreadPoolTest {
  public static void main(String[] args) throws InterruptedException {
//    ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
    ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
    //如果前面的任务还未完成，则调度不会启动。
    System.out.println(System.currentTimeMillis()/1000);


    ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()){

      @Override
      protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("准备执行");
      }

      @Override
      protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("执行完成");
      }

      @Override
      protected void terminated() {
        System.out.println("线程池退出");
      }

    };

//    es.execute(new Runnable() {
//      @Override
//      public void run() {
//        System.out.println("test");
//      }
//    });
//    es.shutdown();

    Runnable runnable1 = new Runnable() {
      @Override
      public void run() {
        try {
//          Thread.sleep(1000);
          System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()/1000);
        } catch (Exception e) {
        }

      }
    };

    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        try {
//          Thread.sleep(1000);
          System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()/1000);
        } catch (Exception e) {
        }

      }
    };

    ses.scheduleWithFixedDelay(runnable1, 1, 1, TimeUnit.SECONDS);//启动0秒后执行，然后周期2秒执行一次
    ses.scheduleWithFixedDelay(runnable2, 1, 1, TimeUnit.SECONDS);//启动0秒后执行，然后周期2秒执行一次
    Thread.sleep(10000);
    ses.shutdown();
  }

}
