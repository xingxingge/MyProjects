package dizoo.std.designpatterns.structure.decorator;

/**
 * Created by hx on 16-8-24.
 */
public class Client {
  public static void main(String[] args) {
    Component component = new ConcreteComponent();
    Decorator decorator= new Decorator(component);
    decorator.operation();
  }
}
