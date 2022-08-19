package dizoo.std.thread.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hx on 16-12-24.
 */
public class ThreadPoolDemo {
  public static class MyTask implements Runnable {
    @Override
    public void run() {
      System.out.println(System.currentTimeMillis() + "Thread ID:"
              + Thread.currentThread().getId());
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    MyTask myTask = new MyTask();
    ExecutorService es = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 10; i++) {
      es.submit(myTask);
    }
    es.shutdown();
  }
}
