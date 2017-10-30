package com.hx.designpatterns.construct.abstractfactory;

public class creator2 implements Creator {
  public creator2 () { };
  
  //
  // Methods
  //


  //
  // Other methods
  //

  /**
   * @return       ProductA
   */
  public ProductA factoryA()
  {
    return new ProductA2();
  }


  /**
   * @return       ProductB
   */
  public ProductB factoryB()
  {
    return new ProductB2();
  }


}
