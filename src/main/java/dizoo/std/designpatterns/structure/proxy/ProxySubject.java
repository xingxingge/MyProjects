package dizoo.std.designpatterns.structure.proxy;


/**
 * Created by hx on 16-8-25.
 */
public class ProxySubject  implements Subject{
  private ConcreteSubject concreteSubject;

  public ProxySubject(ConcreteSubject concreteSubject) {
    this.concreteSubject = concreteSubject;
  }

  public ProxySubject() {
  }

  @Override
  public void request() {
    System.out.println("proxy project");
    if (concreteSubject==null)concreteSubject = new ConcreteSubject();
    concreteSubject.request();

  }

  public static void main(String[] args) {
    Subject subject = new ProxySubject();
    subject.request();
  }
}
