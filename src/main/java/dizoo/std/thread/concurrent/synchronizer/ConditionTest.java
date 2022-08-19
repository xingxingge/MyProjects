package dizoo.std.thread.concurrent.synchronizer;

/**
 * Created by hx on 16-12-20.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest implements Runnable {
  public static ReentrantLock lock = new ReentrantLock();
  public static Condition condition = lock.newCondition();

  @Override
  public void run() {
    try {
      lock.lock();
      condition.await();//释放锁
      System.out.println("Thread is going on");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ConditionTest t = new ConditionTest();
    Thread thread = new Thread(t);
    thread.start();
    Thread.sleep(2000);

    lock.lock();
    condition.signal();
    lock.unlock();
  }

}
