package com.hx.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo2 {
	public static void main(String[] args) {

		String info = "小白是个:高端大气上档次,低调奢华有内函的淫";
		Pattern p = Pattern.compile("\\p{Punct}"); //匹配标点符号
		String[] str = p.split(info);
		for (String s : str) {
			System.out.println(s);
		}

		String email = "xiaobai1_@qq.cc";
		Pattern p2 = Pattern.compile("\\w+@\\w+\\.[a-zA-Z]+");
		Matcher m = p2.matcher(email);
		if (m.matches()) {
			System.out.println("匹配");
		} else {
			System.out.println("不匹配");
		}

		String date = "2013/11/05";
		Pattern p3 = Pattern.compile("/");
		Matcher m2 = p3.matcher(date);
		String s2 = m2.replaceAll("-");
		System.out.println(s2);

	}

}
