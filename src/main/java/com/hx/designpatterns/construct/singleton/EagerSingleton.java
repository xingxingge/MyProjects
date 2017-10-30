package com.hx.designpatterns.construct.singleton;

/**
 * Created by hx on 16-8-15.
 */
public class EagerSingleton {
  private static EagerSingleton instance=new EagerSingleton();
  private EagerSingleton(){}
  public static EagerSingleton getInstance(){
    return instance;
  }
}
