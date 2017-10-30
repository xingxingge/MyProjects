package com.hx.designpatterns.structure.adapter;

/**
 * Created by hx on 15-11-30.
 */
// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
public class Adaptee {
  public void specificRequest() {
    System.out.println("被适配类具有 特殊功能...");
  }
}
