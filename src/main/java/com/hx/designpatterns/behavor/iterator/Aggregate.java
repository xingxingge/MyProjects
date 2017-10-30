package com.hx.designpatterns.behavor.iterator;

/**
 * Created by hx on 16-9-12.
 */
public abstract class Aggregate<E> {
  public abstract  Iterator<E> createIterator();
  public abstract int  getSize();

  public abstract  E get(int i);
}
