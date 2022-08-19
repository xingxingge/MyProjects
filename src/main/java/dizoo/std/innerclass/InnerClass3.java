package dizoo.std.innerclass;

/**
 *1.内部类：静态内部类 一个静态内部类相当于外部类
 *2.private和static修饰类的时候只能修饰内部类
 *3.静态内部类只能访问外部类中的静态方法和成员
 *4.静态内部类不属于对象，通过调用静态方法方法实例化
 *5.可以在类的外部实例化内部类的方法
 *6.希望隐藏，内部类不希望被外面的类访问的时候比较适用
 */

public class InnerClass3 {

	public static void main(String[] args) {
		Dog3 d1 = new Dog3("小花");
		d1.say();
		Dog3.setStaticInnerdog();
		Dog3.StaticInnerDog c = new Dog3.StaticInnerDog();
		c.talk();
	}
}

class Dog3 {
	public String name;
	public Dog3(String name) {
		this.name = name;
	}

	public void say() {
		System.out.println("My name is " + name);
	}

	static class StaticInnerDog {
		public void talk() {
			System.out.println("小小花");
		}
	}
	public static void setStaticInnerdog(){
		StaticInnerDog c  = new  StaticInnerDog();
		c.talk();	
	}

}
