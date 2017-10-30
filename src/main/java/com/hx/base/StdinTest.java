package com.hx.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Scanner;


public class StdinTest {

	public static void main(String[] var0) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What\'s your name?");
		String var2 = scanner.nextLine();
		System.out.println("My name is " + var2);
		Scanner scanner1 = new Scanner(Paths.get("/f/JavaHome/code/MyProjects/MyProjects/src/main/java/com/hx/base/TestConsole.txt", new String[0]));

		while(scanner1.hasNext()) {
			String str = scanner1.nextLine();
			str = new String(str.getBytes("UTF-8"));
			System.out.println(str);
		}

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ifconfig").getInputStream()));
		String line;

		while((line = br.readLine()) != null) {
			line = new String(line.getBytes("UTF-8"), "UTF-8");
			System.out.println(line);
		}

		br.close();
		scanner.close();
		scanner1.close();
	}
}
