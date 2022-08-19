package dizoo.std.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by hx on 16-2-16.
 */
public class DirectMemoryOOM {
  private static final int mb = 1024*1024;

  public static void main(String[] args) throws IllegalAccessException {
    Field unsafeField = Unsafe.class.getDeclaredFields()[0];
    unsafeField.setAccessible(true);
    Unsafe unsafe = (Unsafe) unsafeField.get(null);
    while (true) {
      unsafe.allocateMemory(mb);

    }
  }
}
