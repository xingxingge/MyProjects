package dizoo.std.thread.pool;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hx on 18-6-18.
 */
public class ThreadPoolExecutorTest {
//  @Test
  public static void main(String[] args) {
    ThreadPoolExecutor executor =
            new ThreadPoolExecutor(1, 4, 300, TimeUnit.SECONDS,
                    new PriorityBlockingQueue<Runnable>());
//    ThreadPoolExecutor executor =
//            new ThreadPoolExecutor(2, 4, 300, TimeUnit.SECONDS,
//                    new LinkedBlockingDeque<Runnable>());
//    ThreadPoolExecutor executor =
//            new ThreadPoolExecutor(2, 4, 300, TimeUnit.SECONDS,
//                    new ArrayBlockingQueue<Runnable>(5));
//    ThreadPoolExecutor executor =
//            new ThreadPoolExecutor(2, 3, 300, TimeUnit.SECONDS,
//                    new SynchronousQueue<Runnable>());

    /**
     * newFixedThreadPool
     */
//    ThreadPoolExecutor executor=new ThreadPoolExecutor(2, 4,
//            0L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>());

    /**
     *newCachedThreadPool
     */
//    ThreadPoolExecutor executor=new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//            60L, TimeUnit.SECONDS,
//            new SynchronousQueue<Runnable>());
    for (int i = 0; i < 4; i++) {
      executor.execute(new MyRunnableTest(i));
    }
  }
}

class MyRunnableTest implements Runnable, Comparable<MyRunnableTest> {
  private int index;

  public MyRunnableTest(int index) {
    this.index = index;
  }

  @Override
  public int compareTo(MyRunnableTest o) {
    return 0;
  }

  @Override
  public void run() {
    System.out.println("start:"+index);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("end:"+index);
  }
}
