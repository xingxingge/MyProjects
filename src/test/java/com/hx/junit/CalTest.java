package com.hx.junit;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by hx on 16-3-21.
 */
public class CalTest {

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite3());
  }

  public static Test suite3() {
    TestSuite suite= new TestSuite();
    suite.addTestSuite(CalTest1.class);
    suite.addTestSuite(CalTest2.class);
    return suite;
  }

}
