package dizoo.std.designpatterns.construct.prototype;

/**
 * Created by hx on 16-8-18.
 */
public class ConcretePrototype implements Prototype {
  @Override
  public Object copy() {
    try {
      return clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }
}
