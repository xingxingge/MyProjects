package dizoo.std.jvm.classcode.dispatch;

import java.io.Serializable;

/**
 * Created by hx on 16-10-23.
 */
public class Overload {

  public static void sayHello(Object arg) {
    System.out.println("hello Object");
  }

  public static void sayHello(int arg) {
    System.out.println("hello int");
  }

  public static void sayHello(long arg) {
    System.out.println("hello long");
  }

  public static void sayHello(Character arg) {
    System.out.println("hello Character");
  }

//  public static void sayHello(char arg) {
//    System.out.println("hello char");
//  }

  public static void sayHello(char... arg) {
    System.out.println("hello char ...");
  }

  public static void sayHello(Serializable arg) {
    System.out.println("hello Serializable");
  }

  public static void main(String[] args) {
    sayHello('a');
  }
}
