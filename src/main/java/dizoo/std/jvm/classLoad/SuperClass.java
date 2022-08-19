package dizoo.std.jvm.classLoad;

/**
 * Created by hx on 16-5-9.
 * -XX:+TraceClassLoading
 * 被动使用字段:
 * 通过子类引用父类的静态字段,不会导致子类的初始化
 */
public class SuperClass {
  static{
    System.out.println("SuperClass init...");
  }
  public static int value=123;
}

class SubClass extends  SuperClass{
  static{
    System.out.println("SubClass init...");
  }
}

class NotInititlization{//调用父类static属性不初始化子类
  public static void main(String[] args) {
    System.out.println(SubClass.value);
  }
}

class NotInitilization1{//数组引用,不会初始化
  public static void main(String[] args) {
    SuperClass[] ca  = new SuperClass[10];
  }
}
