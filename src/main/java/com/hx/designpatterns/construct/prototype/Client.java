package com.hx.designpatterns.construct.prototype;

/**
 * Created by hx on 16-8-18.
 */
public class Client {
  private Prototype prototype;
  public void operation(Prototype ex){
    prototype=(Prototype)ex.copy();

  }
}
