package com.hx.thread.day24.itcast_10;

import java.util.concurrent.Callable;

/**
 * Created by hx on 15-10-24.
 */
//线程求和案例

public class MyCallable implements Callable<Integer> {
  private int number;

  public MyCallable(int number) {
    this.number = number;

  }

  @Override
  public Integer call() throws Exception {
    int sum = 0;
    for (int i = 1; i < number; i++) {
      sum += number;
    }
    return sum;
  }

}
