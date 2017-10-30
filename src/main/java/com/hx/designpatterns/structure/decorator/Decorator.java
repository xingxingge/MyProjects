package com.hx.designpatterns.structure.decorator;

/**
 * Created by hx on 16-8-24.
 */
public class Decorator  implements Component{
  private Component component;

  public Decorator(Component component) {
    this.component = component;
  }

  @Override
  public void operation() {
    System.out.println("start");
    component.operation();
    System.out.println("end");
  }
}
