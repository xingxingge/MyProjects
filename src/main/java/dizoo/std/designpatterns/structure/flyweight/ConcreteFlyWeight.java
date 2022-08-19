package dizoo.std.designpatterns.structure.flyweight;

/**
 * Created by hx on 16-8-31.
 */
public class ConcreteFlyWeight extends FlyWeight {
  private Character intrisicState=null;//内蕴状态的
  @Override
  public void operation(String state) {
    System.out.println("intrisicState:"+intrisicState);
    System.out.println("extrisicState:"+state);

  }

  public ConcreteFlyWeight(Character intrisicState) {
    this.intrisicState = intrisicState;
  }
}
