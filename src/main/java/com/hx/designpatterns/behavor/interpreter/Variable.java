package com.hx.designpatterns.behavor.interpreter;

/**
 * Created by hx on 16-9-21.
 */
public class Variable extends Expression{
  private String  name;

  public Variable(String name) {
    this.name = name;
  }


  @Override
  public boolean interpret(Context ctx) {
    return ctx.lookup(this);
  }

  @Override
  public boolean equals(Object o) {
    return this.name.equals(((Variable) o).name);
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public String toString() {
    return name;
  }
}
