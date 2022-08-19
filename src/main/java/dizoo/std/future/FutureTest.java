package dizoo.std.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    Future<String> future = executor.submit(new Callable<String>() {
      public String call() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "result:";
      }
    });

    try {
      System.out.println("获取future结果：");
      System.out.println(future.get()); // use future
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    executor.shutdown();
  }
}
