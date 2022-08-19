package dizoo.std.thread.day24.itcast_10;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hx on 15-10-24.
 */
public class CallableDemo {
  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    Future<Integer>  a = pool.submit(new MyCallable(5));
    Future<Integer>  b =  pool.submit(new MyCallable(10));
    try {
      System.out.println(a.get());
      System.out.println(b.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    pool.shutdown();
  }
}
