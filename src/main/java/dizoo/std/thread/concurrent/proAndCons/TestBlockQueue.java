package dizoo.std.thread.concurrent.proAndCons;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hx on 16-12-24.
 */
public class TestBlockQueue {
  private static Integer count = 0;
  final BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(10);

  class Producer implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          bq.put(1);
          count++;
          System.out.println(Thread.currentThread().getName()
                  + "生产者生产，目前总共有" + count);
        } catch (InterruptedException e) {
          e.printStackTrace();
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
        try {
          bq.take();
          count--;
          System.out.println(Thread.currentThread().getName()
                  + "消费者消费，目前总共有" + count);
        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }
      }
    }

  }

  public static void main(String[] args) throws Exception {
    TestBlockQueue hosee = new TestBlockQueue();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Producer()).start();
    new Thread(hosee.new Consumer()).start();
//    new Thread(hosee.new Consumer()).start();

//    new Thread(hosee.new Consumer()).start();
  }
}
