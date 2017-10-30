package com.hx.designpatterns.construct.builder;

/**
 * Created by hx on 16-8-15.
 */
public class Director {
  public void contruct(){
    Product product = new ConcreteBuilder().buildPart1().buildPart2().retriveResult();
    System.out.println(product);
  }

  public static void main(String[] args) {
    new Director().contruct();
  }
}
