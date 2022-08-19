package dizoo.std.designpatterns.behavor.memento;

/**
 * Created by hx on 16-9-18.
 */
public class Memento {
  private String state;

  public Memento(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
