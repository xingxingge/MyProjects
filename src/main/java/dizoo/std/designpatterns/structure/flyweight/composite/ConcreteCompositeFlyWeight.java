package dizoo.std.designpatterns.structure.flyweight.composite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hx on 16-9-5.
 */
public class ConcreteCompositeFlyWeight  extends  FlyWeight{
  private HashMap flies=new HashMap();
  private FlyWeight flyWeight;
  @Override
  public void operation(String state) {
    FlyWeight flyweight=null;
    System.out.println("===");
    for(Iterator it = flies.entrySet().iterator(); it.hasNext();){
      Map.Entry e =(Map.Entry) it.next();
      flyweight=(FlyWeight)e.getValue();
      flyweight.operation(state);
    }

  }

  public ConcreteCompositeFlyWeight() {
  }

  public void add(Character key,FlyWeight flyWeight){
    flies.put(key,flyWeight);
  }

}
