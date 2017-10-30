package com.hx.base.we.t5;

abstract class abstractIt {
  abstract float getFloat();
}
public class AbstractTest extends abstractIt {
  private float f1 = 1.0f;
//  private  float getFloat() {return f1;}//Cannot reduce the visibility of the inherited method from abstractIt
  float getFloat() {return f1;}//Cannot reduce the visibility of the inherited method from abstractIt
  //不可下降方法的访问权限
}
