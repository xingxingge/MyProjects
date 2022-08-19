package dizoo.std.jdk8;

import org.junit.Test;

public class InterfaceEnhance {
  //默认方法
  @Test
  public void test1() {
    A b = new B();
    b.test02();
    A c = new C();
  }

  //静态方法
  @Test
  public void test2(){
    A a = new B();
//    a.test03();
    A.test03();
  }
}


interface A {
  void test01();

  default void test02() {
    System.out.println("A接口中的默认方法test02执行了。。。");
  }
  static void test03(){
    System.out.println("A接口中的静态方法test03执行了。。。");
  }
}

class B implements A {
  @Override
  public void test01() {
  }
}

class C implements A {
  @Override
  public void test01() {
  }
}

