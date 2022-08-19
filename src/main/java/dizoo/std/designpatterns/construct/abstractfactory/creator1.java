package dizoo.std.designpatterns.construct.abstractfactory;

public class creator1 implements Creator {
  public creator1 () { };
  
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
    return new ProductA1();
  }


  /**
   * @return       ProductB
   */
  public ProductB factoryB()
  {
    return new  ProductB1();
  }


}
