package com.hx.string;

public class StringBase {
	public static void stringBase() {
		String myIntroduction = "My name is Huangxingxx";
		String subStringName = myIntroduction.substring(11,20);
		System.out.println("(myIntroduction.substring(11,20)):My name is "
				+ subStringName);
		int age = 25;
		String myAge = "My age is " + age;
		System.out.println("my age:" + myAge);
		if ("My age is 25".equals(myAge)) {
			System.out.println("equals(myAge) That's ok");
		}
		if ("my age is 25".equalsIgnoreCase(myAge)) {
			System.out.println("忽略大小写:That's ok");
		}
		if (myAge != "My age is 25") {
			System.out.println("That's ok");
		}

		String nullString = "";
		if (nullString.length() == 0 || nullString == null) {
			System.out.println("null String:it is a null string");
		}
		String str = "明天早一点起床，haha，逗你玩呢！";
		int n = str.length();
		System.out.println("字符串长度：" + n);//需要的代码单元个数

		n = str.codePointCount(0, n);//实际的长度
		System.out.println("代码单元:" + n);
		
		System.out.print("按照字符输出：");
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
		}
		
		System.out.print("\n");
		
		int index = str.offsetByCodePoints(0, 5);//打印代码的字符编码
		int cp = str.codePointAt(index);//获取代码点的代码单元(编码，十进制)
		System.out.println(cp+"表示:"+"\u8D77");
	}

	public static void main(String[] args) {
		stringBase();
	}
}
