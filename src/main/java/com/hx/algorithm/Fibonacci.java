package com.hx.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hx on 16-8-19.
 * 1 1 2 3 5 8 13
 */
public class Fibonacci {
  public int times = 0;
  public int n2 = 1, n1 = 1;
  public Map<Integer, Integer> map = new HashMap<>();
  public int maxTimes = 7;

  public Fibonacci() {
    map.put(1, 1);
    map.put(2, 1);
  }

  public synchronized int getI(int index) {//从1开始
    if (index <= 0) throw new RuntimeException("索引号应该从1开始");
    Integer result = map.get(index);
    if (result != null) return result;
    int n1 = map.get(1), n2 = map.get(2);
    for (int i = 3; i <= index; i++) {
      n2 = n1 + n2;
      n1 = n2 - n1;
      map.put(index, n2);
    }
    return n2;
  }

  public static void main(String[] args) {
    Fibonacci f = new Fibonacci();
    for (int i = 1; i <= 70; i++) {
      System.out.println(f.getI(i));
    }
    System.out.println(f.getI(6));
    System.out.println(f.getI(9));

    Fibonacci fibonacci = new Fibonacci();
    Iterator<Integer> iterator = fibonacci.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }

  }

  public Iterator<Integer> iterator() {
    return new FibonacciItetator(this);
  }

}
