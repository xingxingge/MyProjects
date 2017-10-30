package com.hx.base.dataType;


/**
 * 
 * @author HuangXing
 * 
 *         8种基本数据类型的使用
 *         java包含4中整型数据(byte,short,int,long),2中浮点类型(float，double),1种对应unicode编码的char类型
 *         ，1种真值类型
 */
public class BaseDataTypes {

	public static final String MYNAME = "黄星"; // 定义常量，后面的方法引用，常量只能赋值一次！

	public static void main(String[] args) {
		baseDataTypes();
	}

	// strictfp表示中间计算也要使用严格的浮点类型
	private static strictfp void baseDataTypes() {
		byte b = 100;// 字节类型,1个字节
		short s = 10; // 短整形，占用2字节，表示-32768~32767
		int age = 28; // 声明一个int类型的变量并赋值,占用4字节，最大数超过20亿
		long g = 1000L;// 长整形，8字节，表示很大
		long hex = 0xA;// 表示十六进制数
		long oct = 010; // 八进制数0用作后缀
		long bin = 0b1001;// Java7开始,前缀表示二进制数
		long bin_sep = 0b1111_0100_0010_0100_0000;// Java7开始支持分割表示，100万

		float f = 3.14159265369f;// 4字节,有效位数是6-7位，没有F后缀会被认为是double
		double d = 10.12345123451234512345d;// 八字节，能表示的数是float的两倍.

		char c = '黄';

		boolean flag; // 声明一个boolean类型的变量
		flag = true; // 给已经声明过的变量赋值
		// 多数情况使用，有效位数是15位
		// System.out.println(Character.isJavaIdentifierPart('f'));
		System.out.println("b=" + b + "\t" + "s=" + s + "\t" + "g=" + g + "\t"
				+ "hex=" + hex + "\t");
		System.out.println("oct=" + oct + "\t" + "bin=" + bin + "\t"
				+ "bin_sep=" + bin_sep + "\t" + "c=" + c + "\t");
		System.out.println("f=" + f + "\t" + "d=" + d + "\t" + "flag=" + flag
				+ "\t" + "age=" + age + "\t");
		/**
		 * 检查一个数是否是数字
		 */
		if (!Double.isNaN(d)) {
			System.out.println("d是一个数字");
		} else {
			System.out.println("d不是一个数字");
		}

		/**
		 * 关于四舍五入,由于浮点数值采用二进制系统表示，二进制无法精确到1/10,应该使用BigDecimal类; BigDecimal
		 */
		System.out.println("2.0-1.1的值: " + (2.0 - 1.1));
//		System.out.println("使用BigDecimal进行四则运算：" + BigDecimalUtils.div(2.0, 1.1));

		/**
		 * 常用转义字符
		 */
		System.out.println("制表符:" + "\t" + "换行:" + "\n" + "回车" + "\r"
				+ "退格(回车不见了)" + "\b" + "\"");
		System.out.print("使用unicode编码打印注册符号:");
		System.out.println('\u2122');
	}
}
