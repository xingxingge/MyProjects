package dizoo.std.exception;

import org.junit.Test;

/**
 * Created by hx on 18-5-2.
 */
public class RuntimeExceptionTest {
  String a;
  @Test
  public void test1(){
    try {
      System.out.println(a.toCharArray());
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("err");
    }
    System.out.println("continue");

  }
}
