package dizoo.std.designpatterns.behavor.mediator;

/**
 * Created by hx on 16-9-28.
 */
public class Colleague2 extends Colleague {
  public Colleague2(Mediator mediator) {
    super(mediator);
  }

  @Override
  public void action() {
    System.out.println("colleague 2...");
  }
}
