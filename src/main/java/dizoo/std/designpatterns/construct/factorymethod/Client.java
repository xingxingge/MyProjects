package dizoo.std.designpatterns.construct.factorymethod;

/**
 * Class Client
 */
public class Client {

  //
  // Fields
  //

  private static Creator creator1;
  private static Creator creator2;
  private static Product prod1;
  private static Product prod2;


  public static void main(String[] args) {
    creator1 = new ConcreteCreator1();
    prod1 = creator1.factory();
    creator2=new ConcreteCreator2();
    prod2=creator2.factory();


  }


}
