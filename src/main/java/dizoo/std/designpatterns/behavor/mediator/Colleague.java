package dizoo.std.designpatterns.behavor.mediator;

/**
 * Created by hx on 16-9-28.
 */
public abstract  class Colleague {
  private Mediator mediator;

  public Colleague(Mediator mediator) {
    this.mediator = mediator;
  }
  public abstract void action();

  public Mediator getMediator() {
    return mediator;
  }

  public void setMediator(Mediator mediator) {
    this.mediator = mediator;
  }

  public void change(){
    mediator.colleagueChanged(this);

  }

}
