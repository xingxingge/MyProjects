package dizoo.std.innerclass;

/**
 * 内部类：方法内部类
 * 1.方法内部类只能在方法内部实例化
 * 2.方法内部类对象不能使用所在方法的非final变量，内部类的生命周期在外部类中，而方法内部的局部变量在方法调用时候才
 * 能使用，这样内部类的声明周期可能大于局部变量，使用final后，即时内部类所在的方法调用结束，
 * 内部类中仍有final变量的一个拷贝
 */
public class InnerClass2 {

  public static void main(String[] args) {
    Dog2 d1 = new Dog2("小花");
    d1.say();
    d1.innerTalk("小小花");//在方法外部使用内部类的方法
  }
}

class Dog2 {
  public String name;

  public Dog2(String name) {
    this.name = name;
  }

  public void say() {
    System.out.println("My name is " + name);
  }

  public Inner child;

  //因为类的作用域是在方法内部，如果要在外部使用，则应该使用内部类实现接口
  //方法内部类只能在方法内部实例化
  //方法内部类不能使用所在方法的非final局部变量
  //根本原因：child对象是实现接口，声明周期在dog类中，如果局部变量不是final，由于局部变量在方法中，生命周期没有child对象的长，出了方法后成员接口再引用会有空指针错误
  //所以方法需要写成final，这样在整个类声明周期中都可以用。
  public void innerTalk(final String childName) {
    final int x = 10;
    class InnerDog implements Inner {
      public void talk() {
        System.out.println("x=" + x);
        System.out.println("My mother is " + name);
        System.out.println("My name is " + childName);
      }
    }
    child = new InnerDog();
    child.talk();
  }
}

interface Inner {
  void talk();
}
