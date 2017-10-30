package com.hx.designpatterns.structure.flyweight.composite;


/**
 * Created by hx on 16-9-2.
 */
public class Client {
  public static void main(String[] args) {
    FlyWeightFactory factory = new FlyWeightFactory();
    FlyWeight fly = factory.factory(new String("aba"));//内蕴状态享元对象
    //传递外蕴对象
    fly.operation("Composite call");
    factory.checkFlyweight();



  }
}
