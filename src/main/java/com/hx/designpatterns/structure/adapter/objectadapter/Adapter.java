package com.hx.designpatterns.structure.adapter.objectadapter;

import com.hx.designpatterns.structure.adapter.Adaptee;
import com.hx.designpatterns.structure.adapter.Target;

/**
 * Created by hx on 15-11-30.
 */
public class Adapter implements Target {
  private Adaptee adaptee;

  public Adapter(Adaptee adaptee) {
    this.adaptee = adaptee;
  }

  @Override
  public void request() {
    this.adaptee.specificRequest();
  }
}
