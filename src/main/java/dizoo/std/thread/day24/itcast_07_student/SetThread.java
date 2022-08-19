package dizoo.std.thread.day24.itcast_07_student;

public class SetThread implements Runnable {

	private Student s;
	private int x = 0;

	public SetThread(Student s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (true) {
			if (x % 2 == 0) {
				s.set("林青霞", 27);
			} else {
				s.set("刘意", 30);
			}
			x++;
		}
	}
}
