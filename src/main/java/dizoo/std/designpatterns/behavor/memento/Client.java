package dizoo.std.designpatterns.behavor.memento;

/**
 * Created by hx on 16-9-18.
 */
public class Client {
  private static  Originator o = new Originator();
  private static  CareTaker c = new CareTaker();
  public static void main(String[] args) {
    o.setState("on");
    c.saveMemento(o.createMenmento());
    o.setState("off");
    o.restoreMemento(c.retriveMememto());
  }
}
