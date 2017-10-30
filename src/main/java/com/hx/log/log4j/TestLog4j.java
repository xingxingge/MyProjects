package com.hx.log.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog4j {
	static {
		PropertyConfigurator.configure("log.property");
	}

	private static Logger _log = Logger.getLogger(Thread.class);

	public void test() {
		_log.debug("debug");
		_log.info("info");
		_log.error("error");
	}

	public static void main(String[] args) {
		TestLog4j tl = new TestLog4j();
		tl.test();
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// tl.test();
	}
}
