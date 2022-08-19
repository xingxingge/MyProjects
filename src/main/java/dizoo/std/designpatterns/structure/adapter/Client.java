package dizoo.std.designpatterns.structure.adapter;

import dizoo.std.designpatterns.structure.adapter.classadapter.Adapter;
import org.junit.Test;

/**
 * Created by hx on 15-11-30.
 */
public class Client {
  @Test
  public  void classAdaperTest() {
    // 使用普通功能类
    Target concreteTarget = new ConcreteTarget();
    concreteTarget.request();

    // 使用特殊功能类，即适配类
    Target adapter = new Adapter();
    adapter.request();
  }

  @Test
  public  void objectAdaperTest() {
    // 使用普通功能类
    Target concreteTarget = new ConcreteTarget();
    concreteTarget.request();

    // 使用特殊功能类，即适配类
    Target adapter = new Adapter(new Adaptee());
    adapter.request();
  }
}
