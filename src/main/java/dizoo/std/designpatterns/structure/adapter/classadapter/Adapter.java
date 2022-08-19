package dizoo.std.designpatterns.structure.adapter.classadapter;

import dizoo.std.designpatterns.structure.adapter.Adaptee;
import dizoo.std.designpatterns.structure.adapter.Target;

/**
 * Created by hx on 15-11-30.
 */
public class Adapter extends Adaptee implements Target {

  public Adapter(Adaptee adaptee) {

  }

  public Adapter() {

  }

  @Override
  public void request() {
    super.specificRequest();
  }



}
