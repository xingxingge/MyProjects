package dizoo.std.jvm;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 16-2-16.
 * vm:-XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOMIntern {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    int i =0;
    while(true){
      list.add(String.valueOf(i++).intern());
    }
  }

  @Test
  public void testIntern(){
    String str1= new StringBuilder("计算机").append("软件").toString();
    System.out.println(str1.intern()==str1);
    String str2= new StringBuilder("ja").append("va").toString();
    System.out.println(str2.intern()==str2);
  }
  @Test
  public void testIntern1(){
    String str1= new String("java123");
    System.out.println(str1.intern()==str1);
    String str2= "java123";
    System.out.println(str2.intern()==str2);
  }
}
