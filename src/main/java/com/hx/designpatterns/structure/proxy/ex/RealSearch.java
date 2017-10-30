package com.hx.designpatterns.structure.proxy.ex;

/**
 * Created by hx on 16-8-26.
 */
public class RealSearch implements Search {
  @Override
  public String doSearch(String string) {
    return string+"123";
  }
}
