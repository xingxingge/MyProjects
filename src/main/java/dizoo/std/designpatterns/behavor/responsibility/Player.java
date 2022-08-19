package dizoo.std.designpatterns.behavor.responsibility;

/**
 * Created by hx on 16-9-13.
 */
public class Player {
  private Handler current;

  public Handler getCurrent() {
    return current;
  }

  public void setCurrent(Handler current) {
    this.current = current;
  }
}
