package dizoo.std.designpatterns.behavor.responsibility;

/**
 * Created by hx on 16-9-13.
 */
public abstract class Handler {
  private String name;
  private Handler successor;
  public abstract void operation();

  public Handler getSuccessor() {
    return successor;
  }

  public Handler(String name) {
    this.name = name;
  }

  public void setSuccessor(Handler successor) {
    this.successor = successor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
