package com.hx.designpatterns.structure.adapter.classadapter;

import com.hx.designpatterns.structure.adapter.Adaptee;
import com.hx.designpatterns.structure.adapter.Target;

/**
 * Created by hx on 15-11-30.
 */
public class Adapter extends Adaptee implements Target {

  @Override
  public void request() {
    super.specificRequest();
  }



}
