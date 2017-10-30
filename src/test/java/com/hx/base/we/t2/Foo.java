package com.hx.base.we.t2;

public class Foo {
  public static void main(String[] args) {
    StringBuffer a = new StringBuffer("A");
    StringBuffer b = new StringBuffer("B");
    operate(a, b);
    System.out.println(a + "," + b);
  }

  static void operate(StringBuffer x, StringBuffer y) {
    y.append(x);///修改y的内存指向的值，这样b的值变成BA
    y = x;//修改y的内存指向，但是b没有修改,所以a还是A
//    y.append("C");//修改y的内存指向，值变成AC
  }

  // static void operate(StringBuffer x, StringBuffer y) {
  // x.append(y);
  // y = x;
  // }
}
