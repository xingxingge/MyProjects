package com.hx.designpatterns.construct.builder;

/**
 * Created by hx on 16-8-15.
 */
public class ConcreteBuilder extends Builder {
  String part1;
  String part2;
  @Override
  public Builder buildPart1() {
    System.out.println("part1");
    part1="part1";
    return this;

  }

  @Override
  public Builder buildPart2() {
    System.out.println("part2");
    part2="part2";
    return this;

  }

  @Override
  public Product retriveResult() {
    return new Product(part1,part2);

  }
}
