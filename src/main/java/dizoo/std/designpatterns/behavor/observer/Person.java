package dizoo.std.designpatterns.behavor.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by hx on 16-4-14.
 */
public class Person implements Observer {
  private String name;

  public Person(String name) {
    this.name = name;
  }

  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof Doll) {
      Doll doll = (Doll) o;
      System.out.println(name + "关注的娃娃价格已更新为：" + doll.getPrice());
    }
  }

}
