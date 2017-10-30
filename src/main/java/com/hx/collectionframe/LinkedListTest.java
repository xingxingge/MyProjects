package com.hx.collectionframe;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by hx on 16-10-31.
 */
public class LinkedListTest {

  @Test
  public void test(){
    LinkedList<Integer> list = new LinkedList<>();
//    System.out.println(list.getFirst());
    list.addFirst(1);
    list.addFirst(0);
    list.addFirst(-1);
    list.addFirst(-2);
    list.addFirst(-3);
    list.addFirst(-4);
    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.getFirst());
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    Integer peek = queue.peek();
    queue.poll();
    System.out.println(queue.size());

    List<Integer> l =list.subList(0,3);
    l.add(10);
    l.add(20);
    System.out.println(l);
    System.out.println(list);

  }
}
