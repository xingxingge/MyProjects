package dizoo.std.thread.day23.itcast_09my;

/**
 * Created by hx on 15-10-24.
 */
public class SellTicketDemo {
  public static void main(String[] args) throws InterruptedException {
    SellTicket sellTicket = new SellTicket();
    Thread t1 = new Thread(sellTicket, "窗口1");
    Thread t2 = new Thread(sellTicket, "窗口2");
    Thread t3 = new Thread(sellTicket, "窗口3");
    t1.start();
    t2.start();
    t2.join();
    t3.start();
  }
}
