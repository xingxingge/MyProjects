package com.hx.designpatterns.structure.bridge;

import java.sql.Connection;

/**
 * Created by hx on 16-9-6.
 */
public interface Driver {
  Connection getConnection();
}

