package com.hx.designpatterns.structure.flyweight;

/**
 * Created by hx on 16-9-2.
 */
public class Client {
  public static void main(String[] args) {
    FlyWeightFactory factory = new FlyWeightFactory();
    FlyWeight fly = factory.factory(new Character('a'));//内蕴状态享元对象
    //传递外蕴对象
    fly.operation("first");
    //另外一个内蕴状态对象
    fly=factory.factory(new Character('b'));
    fly.operation("second");
    fly = factory.factory(new Character('a'));
    factory.checkFlyweight();



  }
}
