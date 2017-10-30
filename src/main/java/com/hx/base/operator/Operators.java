package com.hx.base.operator;

public class Operators {
	// 位运算符
	public static void main(String[] args) {
		bitwiseOperatorTest();
	}

	private static void bitwiseOperatorTest() {
		// 逻辑与运算符
		System.out.println(2 >> 1);
		System.out.println(3 << 3);
		// 地址转换:192.168.3.2 ---3232236290
		Long number = (long) (2 + 3 * (Math.pow(2, 8)) + 168 * (Math.pow(2, 16)) + 192 * (Math.pow(2, 24)));
		System.out.println(number);
		//十进制转点分十进制 
//		System.out.println(14&15); //00001110 & 00001111
		long first = number & 0xFF000000l;
		long second = number& 0x00FF0000l;
		long third= number &  0x0000FF00l;
		long forth =number &  0x000000FFl;
		int a = (int) (first/(Math.pow(2, 24)));
		System.out.println(a);
		System.out.println(first >> 24);
		System.out.println(second>> 16);
		System.out.println(third>> 8);
		System.out.println(forth>> 0);
		// System.out.println(l/Math.pow(2, 24)); //相同为1，不同
	}
}