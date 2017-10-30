package com.hx.object;

import junit.framework.TestCase;

/**
 * Created by hx on 16-12-18.
 */
public class HashCodeTest  extends TestCase{
  public void test(){
    Object obj = new Object();
    System.out.println(Integer.toBinaryString(obj.hashCode()));

  }
}
