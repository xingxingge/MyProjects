package dizoo.std.share;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by hx on 15-11-28.
 */
public class FlyweightPatternTest {

  @Test
  public void test1() {
    Integer a = 1000, b = 1000;
    System.out.println(a == b);//1
    Integer c = 100, d = 100;
    System.out.println(c == d);//2
  }

  @Test
  public void test2()
          throws NoSuchFieldException, IllegalAccessException {
    Class cache = Integer.class.getDeclaredClasses()[0]; //1
    Field myCache = cache.getDeclaredField("cache"); //2
    myCache.setAccessible(true);//3
    Integer[] newCache = (Integer[]) myCache.get(cache); //4
    newCache[132] = newCache[133]; //5
    int a = 2;
    int b = a + a;
    System.out.printf("%d + %d = %d", a, a, b); //

  }
}
