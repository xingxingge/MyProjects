package dizoo.std.jvm.gc;

/**
 * Created by hx on 16-5-10.
 */
public class ReferenceCountingGC {
  public Object instance=null;
  private static final int _1MB=1024*1024;
  private byte[] bigSize=new byte[2*_1MB];


}

class GcTest{
  public static void main(String[] args) {
    ReferenceCountingGC a = new ReferenceCountingGC();
    ReferenceCountingGC b = new ReferenceCountingGC();
    a.instance=b;
    b.instance=a;
    a=null;
    b=null;
    System.out.println("ttt");
  }
}
