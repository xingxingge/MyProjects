package dizoo.std.thread;

import org.junit.Test;

/**
 * Created by hx on 16-11-11.
 */
public class DeadLock {

  @Test
  public void test1() {

    final B b = new B();
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        b.testA();

      }
    });
    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        b.testB();

      }
    });

    thread1.start();
    thread2.start();
  }

  public static void main(String[] args) {
//    new DeadLock().test1();
    new DeadLock().test3();

  }


  @Test
  public void test3(){
    C c = new C();
    D d  = new D();
    c.d=d;
    d.c=c;

    Thread t1 = new Thread(new ThreadTest(c));
    Thread t2 = new Thread(new ThreadTest(d));
    t1.start();
    t2.start();
  }
}


class B {
  private static Object obj1 = new Object();
  private static Object obj2 = new Object();


  public void testA() {
    synchronized (obj1) {
      try {
        System.out.println("abc");
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      synchronized (obj2) {
        System.out.println("abc");

      }
    }
  }

  public void testB() {
    synchronized (obj2) {
      try {
        System.out.println("def");
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      synchronized (obj1) {
        System.out.println("def");

      }
    }
  }

}




class ThreadTest implements Runnable {
  private C c;

  public ThreadTest(C c) {
    this.c = c;
  }

  @Override
  public void run() {
    c.method1();
  }

}


  class C {
    public D d;

    public synchronized void method1() {

      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("call d");
      d.method2();
    }
    public synchronized void method2() {
    }

  }

  class D  extends C{
    public C c;

    public synchronized void method1() {
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("call c");
      c.method2();
    }
    public synchronized void method2() {
    }


  }



