package com.hx.designpatterns.construct.singleton;

/**
 * Created by hx on 16-8-15.
 */
public class LazySingleton {
  private static LazySingleton instance=null;
  private LazySingleton(){}
  public static LazySingleton getInstance(){
    if (instance==null){
      synchronized (LazySingleton.class){
        if (instance==null)
          instance=new LazySingleton();
      }
    }
    return instance;
  }
}
