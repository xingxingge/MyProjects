package dizoo.std.designpatterns.behavor.command;

/**
 * Created by hx on 16-9-14.
 */
public class Client {
  public static void main(String[] args) {
    Receiver receiver = new Receiver();
    Command command = new ConcreteCommand(receiver);
    Invoker invoker = new Invoker(command);
    invoker.execute();
  }
}
