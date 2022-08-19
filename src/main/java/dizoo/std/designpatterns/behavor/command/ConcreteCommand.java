package dizoo.std.designpatterns.behavor.command;

/**
 * Created by hx on 16-9-14.
 */
public class ConcreteCommand implements Command {
  private Receiver receiver;

  public ConcreteCommand(Receiver receiver) {
    this.receiver = receiver;
  }

  @Override
  public void execute() {
    receiver.action();

  }
}
