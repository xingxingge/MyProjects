package dizoo.std.base.wrapperclass;

import org.omg.CORBA.IntHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 一切皆是对象，但是基本数据类型不是引用类型，包装类可以让基本数据类型也可以用类表示 jdk1.5新特性：包装类
 *  一、包装类的赋值方式
 * （1）使用new关键字创建对象 
 * （2）直接给包装类赋值 包装类使用注意事项：
 * （1）Integer，Long,Float,Double,Short,Byte
 * ,Character这六个类派生于超类Number，剩余的派生于object类
 * （2）包装类中的值是不允许改变的，而且包装类本身就是final类，不允许被继承
 * （3）由于Integer中的值是不可变的，所以没法通过保证类修改数据的值，可以使用IntHolder类完成
 * （4）比较包装类是否相等时候，要保证包装类中数值是一个字节范围。
 * 
 */
public class WrapperClass {
	public static void main(String... args) {
		// 使用泛型数组列表：
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		// 上面的语句实际是调用实际是调用下面语句，实现自动装箱
		// 考虑到效率，大数据量的list应该换用int[]更好一些
		list.add(Integer.valueOf(1));
		list.add(2);
		list.add(3);
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());

		}
		//arrayList与array转化
		Object[] intx = list.toArray();
		List<Object> list123 = Arrays.asList(intx);
		System.out.println(list123);
		// 由于Integer中的值是不可变的，所以没法通过保证类修改数据的值，可以使用IntHolder类完成
		IntHolder x = new IntHolder(10);
		trip(x);
		System.out.println("x * 3 = " + x.value);
		Long x1 = new Long(100);// 创建包装类对象

		long x2 = 100L;
		Long x3 = 100L;// 直接给包装类赋值（自动装箱）

		long x4 = x3;// （自动拆箱）
		long x5 = x3.longValue();// 手动拆箱

		// 字符串转基本数据类型必须为数字型的字符串
		String s1 = "12345";
		int x6 = Integer.parseInt(s1);// 把一个字符串转换为int基本数据类型

		// System.out.println(x6);
		System.out.printf("x6=%d\n", x6);
		// 等价于
		System.out.printf("x6=%d\n", new Object[] { new Integer(x6) });

		//把字符串赋给Integer对象
		Integer x7 = Integer.valueOf(s1);
		System.out.println("x7=" + x7);

		System.out.println("------------------------------");
		
		//享元设计模式
		Integer x8 = new Integer(10);
		Integer x9 = new Integer(10);

		// 享元设计模式：Integer直接赋值时（自动装箱），当值为1个字节内的数时，将使用同一个对象
		// Byte Short Long Integer 享元设计模式
		Integer x10 = 127;
		Integer x11 = 127;
		System.out.println(x8 == x9);
		System.out.println(x8 == x10);
		System.out.println(x10 == x11);
		double m = max(1.1, 1.2, 1.3);
		// max函数参数是可变的，上面的写法相当于把new double[]{1.1,1.2,1.3}传递给max方法
		System.out.println(m);
	}

	/**
	 * 使用InHolder类修改实参的值（实现引用传递，而不是普通的值传递）
	 * **/
	public static void trip(IntHolder x) {
		x.value = 3 * x.value;
	}

	/**
	 * 参数变量数量可变方法
	 * **/
	public static double max(double... values) {
		double largest = Double.MIN_VALUE;
		for (double d : values) {
			if (d > largest) {
				largest = d;
			}
		}
		return largest;

	}

}
