package dizoo.std.jvm.classLoad;

/**
 * Created by hx on 16-5-10.
 */
public class ConsClass {
  static {
    System.out.println("ConsClass init...");
  }
  public final static String string="hello world";
}

class NotInitialization2{
  public static void main(String[] args) {
    System.out.println(ConsClass.string);
  }
}
