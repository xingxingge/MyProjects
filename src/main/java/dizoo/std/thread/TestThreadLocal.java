package dizoo.std.thread;

/**
 * Created by hx on 16-4-8.
 */
public class TestThreadLocal {

  private static final ThreadLocal<Integer> value =
          new ThreadLocal<Integer>() {
    @Override
    protected Integer initialValue() {
      return Integer.valueOf(1);
    }
  };

  public static void main(String[] args) {

    for (int i = 0; i < 5; i++) {
      MyThread t = new MyThread(i);
      Thread t1 = new Thread(t);
      t1.start();
    }
  }

  static class MyThread implements Runnable {
    private int index;

    public MyThread(int index) {
      this.index = index;
    }

    public void run() {
      System.out.println("线程" + index + "的初始value:" + value.get());
      for (int i = 0; i < 10; i++) {
        value.set(value.get() + i);
      }
      System.out.println("线程" + index + "的累加value:" + value.get());
    }
  }
}
