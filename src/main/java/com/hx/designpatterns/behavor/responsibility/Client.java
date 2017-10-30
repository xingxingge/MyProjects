package com.hx.designpatterns.behavor.responsibility;

/**
 * Created by hx on 16-9-13.
 */
public class Client  {
  public static void main(String[] args) {
    Handler h1 = new ConcreteHandler("h1");
    Handler h2=new ConcreteHandler("h2");
    Handler h3=new ConcreteHandler("h3");
    h1.setSuccessor(h2);
    h2.setSuccessor(h3);
    h1.operation();

  }

}
