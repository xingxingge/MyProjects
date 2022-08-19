package dizoo.std.thread.day23.itcast_06;

public class SellTicket extends Thread {

	// 定义100张票
	// private int tickets = 100;
	// 为了让多个线程对象共享这100张票，我们其实应该用静态修饰
	private static int tickets = 1;

	@Override
	public void run() {
		// 定义100张票
		// 每个线程进来都会走这里，这样的话，每个线程对象相当于买的是自己的那100张票，
		// 这不合理，所以应该定义到外面
		// int tickets = 100;

		// 是为了模拟一直有票
		while (tickets > 0 && tickets<=100) {
				System.out.println(getName() + "正在出售第" + (tickets++) + "张票");
		}
	}
}
