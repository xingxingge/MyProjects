package dizoo.std.innerclass;

/*
 * 匿名内部类也就是没有名字的内部类
 * 正因为没有名字，所以匿名内部类只能使用一次，它通常用来简化代码编写
 * 但使用匿名内部类还有个前提条件：必须继承一个父类或实现一个接口
 *
 * */

/**
 * 内部类：匿名内部类
 * 1.不能有构造方法，只能有一个实例
 * 2.不能定义任何静态成员和静态方法
 * 3.匿名内部类不能用public private protect static修饰
 * 4.一定在new后面，用于隐含实现一个接口或者类
 * 5.匿名内部类是局部的，所有局部内部类的限制对它都生效
 */
public class InnerClass4 {

  public static void main(String[] args) {
    //1. 继承式的匿名内部类
    Dog44 d1 = new Dog45() {
      public void say() {
        System.out.println("重写继承式匿名内部类");
      }
    };
    d1.say();
    //2. 匿名内部类的接口实现
    Dog46 d2 = new Dog46() {
      public void say() {
        System.out.println("接口上实现匿名类"+Dog46.NAME);
      }
    };

    d2.say();
    //3.参数匿名内部类，使用到了接口

    talk(new Dog46() {
      public void say() {
        System.out.println(NAME);
        System.out.println("参数式匿名内部类");

      }
    });
  }

  public static void talk(Dog46 c) {
    c.say();
  }

}

abstract class Dog44 {
  public abstract void say();

}

class Dog45 extends Dog44 {
  public void say() {
    System.out.println("抽象继承实现匿名内部类");
  }
}

interface Dog46 {
   String NAME = "参数式";

   void say();
}
