package dizoo.std.thread.concurrent.synchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hx on 16-12-24.
 */
public class ReentrantLockLimitTimeTest implements Runnable {

  public static ReentrantLock lock = new ReentrantLock();

  @Override
  public void run() {
    try {
      if (lock.tryLock(5, TimeUnit.SECONDS)) {
        Thread.sleep(6000);
      } else {
        System.out.println("get lock failed");
      }
    } catch (Exception e) {
    } finally {
      if (lock.isHeldByCurrentThread()) {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    ReentrantLockLimitTimeTest t = new ReentrantLockLimitTimeTest();
    Thread t1 = new Thread(t);
    Thread t2 = new Thread(t);
    t1.start();
    t2.start();
  }

}
