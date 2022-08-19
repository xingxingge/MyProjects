package dizoo.std.designpatterns.structure.adapter.objectadapter;

import dizoo.std.designpatterns.structure.adapter.Adaptee;
import dizoo.std.designpatterns.structure.adapter.Target;

/**
 * Created by hx on 15-11-30.
 */
public class Adapter implements Target {
  private Adaptee adaptee;

  public Adapter(Adaptee adaptee) {
    this.adaptee = adaptee;
  }

  @Override
  public void request() {
    this.adaptee.specificRequest();
  }
}
