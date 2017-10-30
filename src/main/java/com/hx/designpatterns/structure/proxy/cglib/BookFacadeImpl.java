package com.hx.designpatterns.structure.proxy.cglib;

/**
 * Created by hx on 17-1-2.
 */
public class BookFacadeImpl  implements BookFacade{
  public void addBook() {
    System.out.println("增加图书的普通方法...");
  }
}
