package dizoo.std.designpatterns.behavor.command;

/**
 * Created by hx on 16-9-14.
 */
public class Invoker {
  private Command command;

  public Invoker(Command command) {
    this.command = command;
  }
  public void execute(){
    command.execute();
  }
}
