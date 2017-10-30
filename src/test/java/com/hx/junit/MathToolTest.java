package com.hx.junit;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MathToolTest extends TestCase {
  private Calculator cal = new Calculator();

  public MathToolTest(String testMethod) {
    super(testMethod);
  }
  public void testGcd() throws Exception {
    assertEquals(5,cal.divide(10,2) );
  }
  public void gcd() throws Exception {
    assertEquals(6,cal.divide(10,2) );
  }
  public static void main(String[] args) {
//    junit.textui.TestRunner.run(MathToolTest.suite2());
    junit.textui.TestRunner.run(MathToolTest.suite2());
  }


  public static Test suite() {
    return new TestSuite(MathToolTest.class);
  }

  public static Test suite2() {
    TestSuite suite = new TestSuite(MathToolTest.class);
    suite.addTest(new MathToolTest("gcd"));
    return suite;
  }


  public static Test suite3() {
    TestSuite suite= new TestSuite();
    suite.addTestSuite(CalculatorTest.class);
    suite.addTestSuite(Calculator2Test.class);
    return suite;
  }
}
