package dizoo.std.reference;

import junit.framework.TestCase;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by hx on 17-2-6.
 */
public class TestRef  extends TestCase{

  /** 强引用，JVM的默认实现 */
  public  void test1() throws InterruptedException {
    Object obj = new Object();
    Object strong = obj;
    obj = null;
    System.gc();
    Thread.sleep(1000);
    System.out.println("strong="+strong);
  }

  /**
   * WeakReference 弱引用( 当所引用的对象在 JVM 内不再有强引用时, GC 后weak reference 将会被自动回收)
   * */
  public  void test2() throws InterruptedException {
    Object obj = new Object();
    ReferenceQueue r = new ReferenceQueue();
    WeakReference<Object> wr = new WeakReference<Object>(obj,r);
    obj = null;
    System.gc();
    Thread.sleep(1000);
    System.out.println("wr.get()="+wr.get());
    System.out.println("wr="+wr);
//    wr.clear();
//    System.gc();
    System.out.println("w1111r="+wr.get());
  }

  /**
   * SoftReference SoftReference 于 WeakReference 的特性基本一致， 最大的区别在于
   * SoftReference 会尽可能长的保留引用直到 JVM 内存不足时才会被回收(虚拟机保证)
   * */
  public  void test3() throws InterruptedException {
    Object obj = new Object();
    SoftReference<Object> sr = new SoftReference<Object>(obj);
    obj = null;
    System.gc();
    Thread.sleep(1000);
    System.out.println("sr.get()="+sr.get());
  }

  /**
   * PhantomReference Phantom Reference(幽灵引用) 与 WeakReference 和 SoftReference
   * 有很大的不同, 因为它的 get() 方法永远返回 null
   * */
  public  void test4() throws InterruptedException {
    Object obj = new Object();
    ReferenceQueue<Object> rq = new ReferenceQueue<Object>();
    PhantomReference<Object> pr = new PhantomReference<Object>(obj, rq);
    obj=null;
    System.gc();
    System.out.println("pr.get()="+pr.get());
    System.out.println("123");
  }

  /**
   * ReferenceQueue：
   * @throws InterruptedException
   */
  public  void test5() throws InterruptedException {
    Object obj = new Object();
    ReferenceQueue<Object> rq = new ReferenceQueue<>();
    WeakReference<Object> pr = new WeakReference<>(obj, rq);
    System.out.println("**pr.enqueue()="+pr.enqueue());
    System.out.println("**pr.isEnqueued()="+pr.isEnqueued());
    System.out.println("**pr="+pr);
    System.out.println("**rq.poll()="+rq.poll());
    obj = null;
    System.gc();
//        System.out.println("pr.enqueue()="+pr.enqueue());
//        System.out.println("**pr.isEnqueued()="+pr.isEnqueued());
//        System.out.println("pr="+pr);
//        System.out.println("rq.poll()="+rq.poll());
//        System.out.println("obj5="+obj);
  }

  /**
   * 使用 WeakReference 作为 key， 一旦没有指向 key 的强引用,
   * WeakHashMap 在 GC 后将自动删除相关的
   * entry
   */
  public  void test6() throws InterruptedException {
    Map<Object, Object> map = new WeakHashMap<Object, Object>();
    Object key = new Object();
    Object value = new Object();
    map.put(key, value);

    key = null;

//        System.out.println("value="+value);
//        System.out.println("key="+key);
//        System.out.println("map.containsValue(value)="+map.containsValue(value));
//        System.out.println("map="+map);

    System.gc();
    Thread.sleep(1000);

    System.out.println("value="+value);
    System.out.println("key="+key);
    System.out.println("map.containsValue(value)="+map.containsValue(value));
    System.out.println("map="+map);
  }
}
