package dizoo.std.jdk8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {


  @Test
  public  void testComplete() throws InterruptedException {
    final CompletableFuture<Integer> future = new CompletableFuture<>();
    new Thread(new AskThread(future)).start();
    // 模拟长时间的计算过程
    Thread.sleep(1000);
    // 告知完成结果,get返回此值
    future.complete(5000);
  }

  @Test
  public void testAsync() throws ExecutionException, InterruptedException {
    final CompletableFuture<Integer> future = CompletableFuture
        .supplyAsync(() -> calc(50));
    System.out.println(future.get());

  }

  @Test
  public void testStream() throws ExecutionException, InterruptedException {
    CompletableFuture<Void> fu = CompletableFuture
        .supplyAsync(() -> calc(50))
        .thenApply((i) -> Integer.toString(i))
        .thenApply((str) -> "\"" + str + "\"")
        .thenAccept(System.out::println);
    fu.get();

  }

  public static Integer calc2(Integer para) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return para / 2;
  }

  @Test
  public void testCompose() throws ExecutionException, InterruptedException {
    CompletableFuture<Void> fu = CompletableFuture
        .supplyAsync(() -> calc2(50))
        .thenCompose(
            (i) -> CompletableFuture.supplyAsync(() -> calc2(i)))
        .thenApply((str) -> "\"" + str + "\"")
        .thenAccept(System.out::println);
    fu.get();

  }


  public static Integer calc(Integer para) {
    try {
      // 模拟一个长时间的执行
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }
    return para * para;
  }


}

class AskThread implements Runnable {
  CompletableFuture<Integer> re = null;

  public AskThread(CompletableFuture<Integer> re) {
    this.re = re;
  }

  @Override
  public void run() {
    int myRe = 0;
    try {
      myRe = re.get() * re.get();
    } catch (Exception e) {
    }
    System.out.println(myRe);
  }
}
