package dizoo.std.designpatterns.structure.flyweight;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hx on 16-8-31.
 */
public class FlyWeightFactory {
  private HashMap files=new HashMap();
  private FlyWeight flyWeight;
  public FlyWeight factory(Character state){
    if (files.containsKey(state)){//如果已经包含了state,就不要在new了
      return (FlyWeight) files.get(state);
    }
    else{
      FlyWeight fly=new ConcreteFlyWeight(state);
      files.put(state,fly);
      return fly;
    }
  }
  public void checkFlyweight(){
    FlyWeight flyweight;
    int i=0;
    System.out.println("===");
    for(Iterator it = files.entrySet().iterator(); it.hasNext();){
      Map.Entry e =(Map.Entry) it.next();
      System.out.println("Item "+(++i)+":"+e.getKey());

    }
  }
}
