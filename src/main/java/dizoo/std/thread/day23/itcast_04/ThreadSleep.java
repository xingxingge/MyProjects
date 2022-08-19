package dizoo.std.thread.day23.itcast_04;

import java.util.Date;

public class ThreadSleep extends Thread {
	@Override
	//由于父类方法没有抛出异常,本类重写的方法也不能抛出
	public void run() {
		for (int x = 0; x < 100; x++) {
			System.out.println(getName() + ":" + x + ",日期：" + new Date());
			// 睡眠
			// 困了，我稍微休息1秒钟
			try {
				//sleep(long millis)
				//（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响。
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
