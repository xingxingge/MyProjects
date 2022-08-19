package dizoo.std.designpatterns.behavor.memento;

/**
 * Created by hx on 16-9-18.
 */
public class Originator {
  private String state;

  public Memento createMenmento(){
    return new Memento(state);
  }

  public void restoreMemento(Memento memento){
    this.state=memento.getState();
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
