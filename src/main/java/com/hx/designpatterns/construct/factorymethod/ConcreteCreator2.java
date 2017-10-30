package com.hx.designpatterns.construct.factorymethod;

/**
 * Class ConcreteCreator2
 */
public class ConcreteCreator2 implements Creator {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public ConcreteCreator2 () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  //
  // Other methods
  //

  /**
   * @return       Product
   */
  public Product factory()
  {
    return new ConcreteProduct2();
  }


}
