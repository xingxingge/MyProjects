package dizoo.std.designpatterns.construct.abstractfactory;

public interface Creator {

  
  //
  // Methods
  //


  //
  // Other methods
  //

  /**
   * @return       ProductA
   */
  public ProductA factoryA();


  /**
   * @return       ProductB
   */
  public ProductB factoryB();


}
