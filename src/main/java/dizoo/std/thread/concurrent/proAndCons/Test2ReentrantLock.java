package dizoo.std.thread.concurrent.proAndCons;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hx on 16-12-24.
 */
public class Test2ReentrantLock {
  private static Integer count = 0;
  private final Integer max = 10;
  final Lock lock = new ReentrantLock();
  final Condition notFull = lock.newCondition();
  final Condition notEmpty = lock.newCondition();

  class Producer implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          e.printStackTrace();
        }
        lock.lock();
        try {
          while (count == max) {
            try {
              notFull.await();//full,释放锁,需要等待,等待唤醒
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          count++;
          System.out.println("produce-"+Thread.currentThread().getName()
                  + "生产者生产，目前总共有" + count);
          notEmpty.signal();//唤醒消费
        } finally {
          lock.unlock();
        }

      }
    }
  }

  class Consumer implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
        lock.lock();
        try {
          while (count == 0) {
            try {
              notEmpty.await();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          count--;
          System.out.println("consume-"+Thread.currentThread().getName()
                  + "消费者消费，目前总共有" + count);
          notFull.signal();
        } finally {
          lock.unlock();
        }

      }

    }

  }

  public static void main(String[] args) throws Exception {
//    test2();
//    test1();
    test3();
  }

  private static void test1() {
    Test2ReentrantLock hosee = new Test2ReentrantLock();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Consumer()).start();
  }
  private static void test2() {
    Test2ReentrantLock hosee = new Test2ReentrantLock();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Consumer()).start();
  }
  private static void test3() {
    Test2ReentrantLock hosee = new Test2ReentrantLock();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Consumer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
  }
}
