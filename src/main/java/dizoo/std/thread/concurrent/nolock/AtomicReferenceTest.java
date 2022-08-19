package dizoo.std.thread.concurrent.nolock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hx on 16-12-24.
 */
public class AtomicReferenceTest {

  public final static AtomicReference<String> atomicString = new AtomicReference<>("hosee");

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      final int num = i;
      new Thread() {
        public void run() {
          try {
            Thread.sleep(Math.abs((int) Math.random() * 100));
          } catch (Exception e) {
            e.printStackTrace();
          }
          if (atomicString.compareAndSet("hosee", "ztk")) {
            System.out.println(Thread.currentThread().getId() + "Change value");
          } else {
            System.out.println(Thread.currentThread().getId() + "Failed");
          }
        }
      }.start();
    }
  }
}
