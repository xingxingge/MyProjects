package com.hx.junit;


import junit.framework.TestCase;

/**
 * Created by hx on 16-3-21.
 */
public class CalTest2 extends TestCase {
  private Calculator calculator = new Calculator();

  public void testadd(){
    assertEquals(calculator.add(1,3),4);
  }
}
