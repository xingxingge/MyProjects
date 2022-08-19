package dizoo.std.designpatterns.construct.factorymethod;

/**
 * Class ConcreteCreator1
 */
public class ConcreteCreator1 implements Creator {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public ConcreteCreator1 () { };
  
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
    return new ConcreteProduct1();
  }


}
