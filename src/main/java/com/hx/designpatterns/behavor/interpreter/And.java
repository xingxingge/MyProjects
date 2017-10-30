package com.hx.designpatterns.behavor.interpreter;

/**
 * Created by hx on 16-9-21.
 */
public class And  extends Expression{
  private Expression left,right;

  public And(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean interpret(Context ctx) {
    return left.interpret(ctx) && right.interpret(ctx);
  }

  @Override
  public boolean equals(Object o) {
    return left.equals(((And)o).left) && right.equals(((And) o).right);
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public String toString() {
    return  "("+left.toString()+" AND " +right.toString()+")";
  }
}
