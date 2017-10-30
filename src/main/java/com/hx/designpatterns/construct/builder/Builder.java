package com.hx.designpatterns.construct.builder;

/**
 * Created by hx on 16-8-15.
 */
public  abstract  class Builder {
  public abstract Builder buildPart1();
  public abstract Builder buildPart2();
  public abstract Product retriveResult();

}
