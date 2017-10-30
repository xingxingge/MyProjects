package com.hx.designpatterns.behavor.memento;

/**
 * Created by hx on 16-9-18.
 */
public class CareTaker {
  private Memento memento;
  public Memento retriveMememto(){
    return this.memento;
  }
  public void saveMemento(Memento memento){
    this.memento =memento;
  }
}
