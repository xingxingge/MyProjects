package dizoo.std.designpatterns.behavor.mediator;

/**
 * Created by hx on 16-9-28.
 */
public  abstract  class Mediator {
  public abstract void colleagueChanged(Colleague c);

  public static void main(String[] args) {
    ConcreteMediator mediator = new ConcreteMediator();
    mediator.createConcreteMediator();
    Colleague colleague1 = new Colleague1(mediator);
    Colleague colleague2 = new Colleague2(mediator);
    mediator.colleagueChanged(colleague1);

  }
}
