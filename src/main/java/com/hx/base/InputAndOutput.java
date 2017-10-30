package com.hx.base;

/**
 * 
 * 基本输入输出
 * 
 * 注意，可以使用基本输入输出捆绑文件。例如下面的程序可以这样运行
 * java  InputAndOutput < TestConsole.java > TestConsole.text
 */

import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class InputAndOutput {

	public static void main(String[] args) throws Exception {
		InputStream ins = new FileInputStream(
				"F:\\JavaHome\\WorkSpace\\MyJavaEE\\src\\com\\hx\\base\\TestConsole.java");
		Scanner sc = new Scanner(ins);
		String instr = null;
		while (sc.hasNextLine()) {
			instr = sc.nextLine();
			System.out.println(instr);
		}
		sc.close();
		ins.close();

		Scanner in = new Scanner(System.in);// 构造一个Scanner对象，并与System.in（标准输入流）关联
		// 下面调用对象的各种方法
		System.out.println("What's your name?");
		String username = in.nextLine();// nextLine()方法用于获取一行
		System.out.println("My name is " + username);
		// 如果要输入一个单词(不是一行，而是自动空格分开)
		String singleword = in.next();
		System.out.println("My first name is " + singleword);

		// 格式化输出,使用与c相同的函数，不错.

		double x = 100 / 3;
		System.out.printf("%5.2f\n", x);
		// 打印当前日期，以完整格式打印
		System.out.printf("%tc\n", new Date());
		// 以yyyy-mm-dd hh24:mi:ss输出，需要用到索引符号：
		System.out.printf("%1$tF %1$tT\n", new Date());

		// 文件输入与输出
		// 读取文件的多种方法

		// 获取系统的换行符
		
		PrintWriter out = new PrintWriter(
				"F:\\JavaHome\\WorkSpace\\MyJavaEE\\src\\com\\hx\\base\\TestConsole.txt",
				"UTF-8");
		String crlf = System.getProperty("line.separator");

		Scanner scInputFile = new Scanner(
				Paths.get("F:\\JavaHome\\WorkSpace\\MyJavaEE\\src\\com\\hx\\base\\TestConsole.java"));
		while (scInputFile.hasNext()) {
			String str = scInputFile.nextLine();
			out.write(str+crlf);
		}

		// 执行命令后的输出
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		BufferedReader br = new BufferedReader(new InputStreamReader(Runtime
				.getRuntime().exec("ipconfig").getInputStream(), "GBK"));
		String str = null;
		// ISO-8859-1 ,"UTF-8"
		while ((str = br.readLine()) != null) {
			str = new String(str.getBytes("GBK"), "GBK");
			out.write(str + crlf);
		}
		br.close();
		out.close();
		in.close();
		scInputFile.close();

	}
}
