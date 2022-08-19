package dizoo.std.thread;

/**
 * Created by hx on 16-3-27.
 */
public class SaketKhandelwal {
  public static void main(String[] args) {
    try {
      Thread a = new Thread(new Thread1("A","B","C"));
      a.start();
    } catch (Exception e) {
      System.out.println("Error");
    }
  }
}

class Thread1 implements Runnable {
  String character;
  private Thread1 thread2;
  private Thread1 thread3;


  public Thread1(String a,String b,String c) {
    this.character = a;
    thread2 = new Thread1(b);
    thread3 = new  Thread1(c);
    new Thread(thread2).start();
    new Thread(thread3).start();
  }

  public Thread1(String c) {
    this.character=c;
  }

  public void run() {
    for (int i = 1; i < 21; i++) {
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("No." + i + " Thread: " + character);
    }
  }
}
