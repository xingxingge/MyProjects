package com.hx.designpatterns.behavor.responsibility;

/**
 * Created by hx on 16-9-13.
 */
public class ConcreteHandler extends Handler {
  @Override
  public void operation() {
    if (getSuccessor()!=null)getSuccessor().operation();
    else {
      System.out.println(getName()+"   my handler...");
    }


  }

  public ConcreteHandler(String name) {
    super(name);
  }
}
