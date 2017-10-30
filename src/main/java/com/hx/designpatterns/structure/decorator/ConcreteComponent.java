package com.hx.designpatterns.structure.decorator;

/**
 * Created by hx on 16-8-24.
 */
public class ConcreteComponent implements Component {
  @Override
  public void operation() {
    System.out.println("ConcreteComponent");

  }
}
