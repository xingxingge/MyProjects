package dizoo.std.thread.day24.itcast_02_dielock;

/**
 * Created by hx on 15-10-29.
 */
public class LockedExample implements Runnable {
  private boolean flag;
  private Object obj1;
  private Object obj2;

  public LockedExample(boolean flag, Object obj1, Object obj2) {
    this.flag = flag;
    this.obj1 = obj1;
    this.obj2 = obj2;
  }

  @Override
  public void run() {
    if (flag) {
      synchronized (obj1) {
        System.out.println("if obj1");
        synchronized (obj2) {
          System.out.println("if obj2");
        }
      }
    } else {
      synchronized (obj2) {
        System.out.println("else obj2");
        synchronized (obj1) {
        }
        System.out.println("else obj1");
      }
    }
  }

  public static void main(String[] args) {
    Object obj1 = new Object();
    Object obj2 = new Object();
    new Thread(new LockedExample(true, obj1, obj2)).start();
    new Thread(new LockedExample(false, obj1, obj2)).start();
  }
}
