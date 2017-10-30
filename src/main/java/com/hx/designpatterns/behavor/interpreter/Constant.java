package com.hx.designpatterns.behavor.interpreter;

/**
 * Created by hx on 16-9-21.
 */
public class Constant extends Expression{
  private boolean value;

  public Constant(boolean value) {
    this.value = value;
  }


  @Override
  public boolean interpret(Context ctx) {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    return this.value==((Constant) o).value;
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public String toString() {
    return new Boolean(value).toString();
  }
}
