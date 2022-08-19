package dizoo.std.base;

import java.text.NumberFormat;

public class StaticFiledAndStaticMethod {
	public static void main(String args[]) {
		/**
		 * 0.使用numberFormat类的工厂方法产生不同风格的格式对象(直接使用类名调用静态方法)
		 * **/
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		NumberFormat percentFormatter = NumberFormat.getPercentInstance();
		double x = 0.1;
		System.out.println(currencyFormatter.format(x));
		System.out.println(percentFormatter.format(x));
		Employee E1 = new Employee();
		Employee E2 = new Employee();//实例化完成后，两个对象的nextId和id都是1
		E1.setId();//由于nextId是静态变量，id不是静态变量，设置完成后，E1对象的id变成1，E2的id是默认的0,两个对象的nextId都是2
		int a = E1.getId();
		int b = Employee.getNextId();
		/**
		 * 1. 多个类共享一个静态方法值，由于是私有静态方法，两个对象的nextId相同
		 **/
		int c = Employee.getNextId();
		// E2获得的id值和E1的没有关系
		int d = E2.getId();
		System.out.println(a);
		System.out.println(b);
		// 可以看到，由于共享了nextId，对象E2得到的值是经过对象E1中修改过的,但是id不是静态变量，所以与E1是无关的。
		System.out.println(c);
		System.out.println(d);
		/***
		 * 2.静态方法很重要的特性：可以通过类名直接访问静态方法，不建议通过对象名来调用静态方法。
		 * */

		int f = Employee.getIdS();
		System.out.println(f);
		
		double e = StaticConstant.getPi();
		System.out.println(e);

	}
}

class Employee {
	private static int nextId = 1;
	private   int id;

	public void setId() {
		id = nextId;
		nextId++;
	}

	public int getId() {
		return id;
	}

	public static int getNextId() {
		return nextId;
	}

	/***
	 * 3 静态方法可以看作是不带隐含参数this的方法。静态方法可以直接访问实例中的静态域，不能直接访问非静态域,原因是由于静态方法不能操作对象。
	 * 但是注意非静态方法是可以访问静态域的。
	 * 
	 * */
	public  static int getIdS() {
		// return nextId;
		// 如果直接访问非静态变量是不行的
		// try {
		// // Integer.parseInt("a");
		// return id;
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println(e.toString());
		// }
		// // finally {
		// // System.out.println("ok");
		// return 12345;
		// }
		return nextId;

	}

	/***
	 * 4. 每个类都可以有一个静态的main方法，main方法不操作任何对象，可以作为单元测试使用，如果要单独测试emplooyee，
	 * 可以使用main方法，例如 java employee;
	 * */
	public static void main(String[] args) {
		System.out.println(Employee.getNextId());
	}
}

/**
 * 5.注意静态常量的应用
 * 
 * **/
class StaticConstant {
	public static final double PI = 3.1415;

	public static double getPi() {
		return PI;
	}
}