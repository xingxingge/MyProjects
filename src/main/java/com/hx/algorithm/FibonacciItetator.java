package com.hx.algorithm;

import java.util.Iterator;

/**
 * Created by hx on 16-8-19.
 */
public class FibonacciItetator implements Iterator<Integer> {

  private Fibonacci fibonacci;

  public FibonacciItetator(Fibonacci fibonacci) {
    this.fibonacci = fibonacci;
  }

  @Override
  public boolean hasNext() {
    return fibonacci.times<=fibonacci.maxTimes;
  }

  @Override
  public synchronized  Integer next() {
    if (++fibonacci.times <= 2) return  fibonacci.map.get(1);
    fibonacci.n2 = fibonacci.n1 + fibonacci.n2;
    fibonacci.n1 = fibonacci.n2 - fibonacci.n1;
    return fibonacci.n2;
  }


  @Override
  public void remove() {

  }
}
