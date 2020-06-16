package com.hx.disruptor;

public class EntityWrapper<T> {

  protected T entity;

  public T getEntity() {
    return entity;
  }

  public void setEntity(T entity) {
    this.entity = entity;
  }
}
