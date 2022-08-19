package dizoo.std.designpatterns.behavor.observer;

import java.util.Observable;

/**
 * Created by hx on 16-4-14.
 */
public class Doll extends Observable {
  private float price;

  public Doll(float price) {
    this.price = price;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
    this.setChanged();// 通知，数据已改变
    this.notifyObservers();
  }

}
