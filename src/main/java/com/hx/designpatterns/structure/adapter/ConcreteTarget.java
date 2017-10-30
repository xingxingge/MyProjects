package com.hx.designpatterns.structure.adapter;

/**
 * Created by hx on 15-11-30.
 */
public class ConcreteTarget implements Target {
  @Override
  public void request() {
    System.out.println("不是适配器类,只有普通功能..");

  }
}
