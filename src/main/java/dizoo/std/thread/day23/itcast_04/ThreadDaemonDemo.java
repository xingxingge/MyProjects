package dizoo.std.thread.day23.itcast_04;

/*
 * public final void setDaemon(boolean on):将该线程标记为守护线程或用户线程。
 * 当正在运行的线程都是守护线程时，Java 虚拟机退出。 该方法必须在启动线程前调用。 
 * 
 * 游戏：坦克大战。
 */
public class ThreadDaemonDemo {
	public static void main(String[] args) {
		ThreadDaemon td1 = new ThreadDaemon();
		ThreadDaemon td2 = new ThreadDaemon();

		td1.setName("关羽");
		td2.setName("张飞");

		// 设置守护线程:非守护线程存在时才有意义,类似象棋,主帅不在时,其他没存在意义
		td1.setDaemon(true);
		td2.setDaemon(true);

		td1.start();
		td2.start();

		Thread.currentThread().setName("刘备");//当前线程
		for (int x = 0; x < 5; x++) {
			System.out.println(Thread.currentThread().getName() + ":" + x);
		}
	}
}
