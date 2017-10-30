package com.hx.designpatterns.behavor.iterator;

/**
 * Created by hx on 16-9-12.
 */
public class Client {
  public static void main(String[] args) {
    Integer[] integers = new Integer[]{1,2,3,4,5,6,7,8,9};
    Aggregate<Integer> aggregate = new AggregateImpl<>(integers);
    Iterator<Integer> iterator=aggregate.createIterator();
    while (iterator.hasNext()){
      System.out.println(iterator.next());
    }

  }
}
