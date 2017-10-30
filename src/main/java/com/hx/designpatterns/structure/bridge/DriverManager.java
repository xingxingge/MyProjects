package com.hx.designpatterns.structure.bridge;

import java.sql.Connection;

/**
 * Created by hx on 16-9-6.
 */
public abstract class DriverManager {
  public  Driver driver;

  public DriverManager(Driver driver) {
    this.driver = driver;
  }

  public  Connection getConnection(){
    return driver.getConnection();

  }


}
