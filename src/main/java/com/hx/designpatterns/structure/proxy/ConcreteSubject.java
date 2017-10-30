package com.hx.designpatterns.structure.proxy;

/**
 * Created by hx on 16-8-25.
 */
public class ConcreteSubject implements Subject {
  @Override
  public void request() {
    System.out.println("real subject");
  }
}
