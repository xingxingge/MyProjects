package com.hx.io;

import java.io.Serializable;

/**
 * Created by hx on 16-1-24.
 */
public class Person implements Serializable {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
