package com.hx.myenum;

import org.apache.commons.lang.enums.EnumUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 枚举类的应用
 * 
 */
public class EnumDemo {
	public static void main(String[] args) {
		// 使用enum类
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE) ");
		String input = in.next().toUpperCase();
		Size size = Enum.valueOf(Size.class, input);// 返回枚举变量值，相当于把s设置成s.input
//		System.out.println(Size.class);
		System.out.println("size=" + size);
		System.out.println("abbreviation=" + size.getAbbreviation());
		Size o = Enum.valueOf(Size.class, "LARGE");
		// 使用compareTo方法返回两个枚举变量位置之差
		int a = size.compareTo(o);
		System.out.println(a);
		// 使用values（）方法打印枚举类型常量
		System.out.println(Arrays.toString(Size.values()));
		// 使用ordinal方法打印枚举常量的索引号
		System.out.println(Size.LARGE.ordinal());
		if (size == Size.EXTRA_LARGE)
			System.out.println("Good job--you paid attention to the _.");
		in.close();
		String[] s = new String[]{"1","2"};
		System.out.println(Arrays.toString(s));
	}

	enum Size {
		// 实际是定义了4个实例，所以不要再构造对象
		SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

		private String abbreviation;

		private Size(String abbreviation) {
			this.abbreviation = abbreviation;
			
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}

	@Test
	public void test(){
		System.out.println(EnumUtils.getEnum(Size.class,"SMALL"));

	}
}
