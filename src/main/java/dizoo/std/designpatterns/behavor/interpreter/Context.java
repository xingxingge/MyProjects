package dizoo.std.designpatterns.behavor.interpreter;

import java.util.HashMap;

/**
 * Created by hx on 16-9-21.
 */
public class Context {
  private HashMap map = new HashMap();

  public void assign(Variable var,boolean value){
    map.put(var,new Boolean(value));
  }
  public boolean lookup(Variable var){
    Boolean value= (Boolean) map.get(var);
    return value.booleanValue();
  }



}
