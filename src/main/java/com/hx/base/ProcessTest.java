package com.hx.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Process类的使用
 * 
 * @author HuangXing
 * 
 */

/*
 * ProcessBuilder.start() 和 Runtime.exec 方法创建一个本机进程，并返回 Process
 * 子类的一个实例，该实例可用来控制进程并获得相关信息。Process
 * 类提供了执行从进程输入、执行输出到进程、等待进程完成、检查进程的退出状态以及销毁（杀掉）进程的方法。
 * 
 * 创建进程的方法可能无法针对某些本机平台上的特定进程很好地工作，比如，本机窗口进程，守护进程，Microsoft Windows 上的 Win16/DOS
 * 进程，或者 shell 脚本。创建的子进程没有自己的终端或控制台。它的所有标准 io（即 stdin、stdout 和 stderr）操作都将通过三个流
 * (getOutputStream()、getInputStream() 和 getErrorStream())
 * 重定向到父进程。父进程使用这些流来提供到子进程的输入和获得从子进程的输出
 * 。因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小，如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。
 * 
 * 当没有 Process 对象的更多引用时，不是删掉子进程，而是继续异步执行子进程。
 * 
 * 对于带有 Process 对象的 Java 进程，没有必要异步或并发执行由 Process 对象表示的进程。
 */

public class ProcessTest {

	/**
	 * 获取window当前时间
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static String readSystemStartTime() throws IOException,
			InterruptedException {
		Process process = Runtime.getRuntime().exec(
				"cmd /c net statistics workstation");
		String startUpTime = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		int i = 0;
		String str = "";
		while ((str = br.readLine()) != null) {
			if (i == 3) {
				startUpTime = str;
				break;
			}
			i++;
		}
		process.waitFor();
		br.close();
		String[] Array = startUpTime.trim().split(" ");
		return Array[2].replace("/", "-") + " " + Array[3];
	}

	/**
	 * 首先打开一个notepad窗口，再打开浏览器窗口,测试两种生成process的方法
	 * 
	 * @param args
	 */

	public static void ProcessApp() throws IOException, InterruptedException {
		// 本地进程的两种建立方式，Runtime.exec()方法实际也是调用ProcessBuilder的方法，实最后返回Precess的子类：ProcessImpl
		Process proc1 = new ProcessBuilder("notepad").start();
		proc1.waitFor();// 导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。然后才能调用exitvalue方法
		proc1.destroy();
		System.out.println(proc1.exitValue());
		String exeFullPathName = "C:/Program Files/Internet Explorer/IEXPLORE.EXE";
		String message = "www.baidu.com";
		String[] cmd = { exeFullPathName, message,"2345" };
		Process proc2 = Runtime.getRuntime().exec(cmd);
		proc2.waitFor();
		System.out.println(proc2.exitValue());
	}

	public static void main(String[] args) {
		try {
			System.out.println(readSystemStartTime());
			ProcessApp();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
