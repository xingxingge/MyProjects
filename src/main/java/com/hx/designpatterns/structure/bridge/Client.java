package com.hx.designpatterns.structure.bridge;

import java.sql.Connection;

/**
 * Created by hx on 16-9-6.
 */
public class Client {
  public static void main(String[] args) {
    OracleDriver oracleDriver = new OracleDriver("oracle.jdbc");
    JdbcApi jdbcApi = new JdbcApi(oracleDriver);
    Connection connection =jdbcApi.getConnection();
  }
}
