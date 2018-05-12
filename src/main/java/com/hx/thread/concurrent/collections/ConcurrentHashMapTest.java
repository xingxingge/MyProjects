package com.hx.thread.concurrent.collections;

import junit.framework.TestCase;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hx on 16-12-25.
 */
public class ConcurrentHashMapTest extends TestCase {
  public void test1() {
    ConcurrentHashMap<Test, Test> tests = new ConcurrentHashMap<>();
    Test t = new Test(1);
    Test t1 =tests.put(t,t);
    t1=tests.putIfAbsent(t,t);
    t=new Test(1);
    t1=tests.put(t,t);
    t1=tests.putIfAbsent(t,t);

  }
  public void test2() {
    Map<String, String > tests = new ConcurrentHashMap<>();
    tests.put("111","111");
    tests.put("222","222");
    Set<String> strings = tests.keySet();
    Integer i=0;
    for (String key:strings){
      tests.put((i++).toString(),"dd");
    }

  }


}

class Test {
  private int i;

  public Test(int i) {
    this.i = i;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Test test = (Test) o;
    return i == test.i;
  }

  @Override
  public int hashCode() {
    return Objects.hash(i);
  }
}
