package com.hx.base;

import junit.framework.TestCase;

/**
 * Created by hx on 16-12-18.
 */

public class TranspositionTest extends TestCase {

  public void testTransposition(){
    System.out.println("算术右移");
    System.out.println(100>>1>>1);//25
    System.out.println(-100>>1>>1);//-25
    System.out.println("逻辑右移");
    System.out.println(100>>>1>>>1);//25
    System.out.println(-100>>>1>>>1);//1073741799

  }
  public void testApply(){

    System.out.println("变为一半");
    System.out.println(100>>1);//25
    System.out.println(-100>>1);//1073741799
    System.out.println("增加一倍");
    System.out.println(100<<1);//25

  }
}
