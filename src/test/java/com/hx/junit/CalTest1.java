package com.hx.junit;

import junit.framework.TestCase;

/**
 * Created by hx on 16-3-21.
 */
public class CalTest1 extends TestCase {
  private Calculator calculator = new Calculator();

  public void testadd(){
    assertEquals(calculator.add(1,2),3);
  }
}
