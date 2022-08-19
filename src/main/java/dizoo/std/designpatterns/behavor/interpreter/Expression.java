package dizoo.std.designpatterns.behavor.interpreter;

/**
 * Created by hx on 16-9-21.
 */
public abstract class Expression {
  public abstract  boolean interpret(Context ctx);
  public abstract  boolean equals(Object o);
  public abstract  int hashCode();
  public abstract  String toString();
}
