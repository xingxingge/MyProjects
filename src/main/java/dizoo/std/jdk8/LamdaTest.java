package dizoo.std.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

interface ItemCreatorBlankConstruct {
  Item getItem();
}

interface ItemCreatorParamConstruct {
  Item getItem(int id, String name, double price);
}

class Item {

  int id;
  String name;
  double price;

  public Item(int id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public Item() {
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Item{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", price=").append(price);
    sb.append('}');
    return sb.toString();
  }
}

interface ReturnOneParam {
  int method(int a);
}

class Exe1 {

  /**
   * 要求
   * 1.参数数量和类型要与接口中定义的一致
   * 2.返回值类型要与接口中定义的一致
   */
  public static int doubleNum(int a) {
    return a * 2;
  }

  public int addTwo(int a) {
    return a + 2;
  }
}

public class LamdaTest {

  //方法引用
  @ Test
  public void test1() {
    ReturnOneParam lambda1 = a -> Exe1.doubleNum(a);
    System.out.println(lambda1.method(3));
    //lambda2 引用了已经实现的 doubleNum 方法  此种用法在mybatisplus中用来构建sql语句时常用
    ReturnOneParam lambda2 = Exe1::doubleNum;
    System.out.println(lambda2.method(3));
    Exe1 exe = new Exe1();
    //lambda4 引用了已经实现的 addTwo 方法
    ReturnOneParam lambda4 = exe::addTwo;
    System.out.println(lambda4.method(2));
  }

  //构造方法引用
  @Test
  public void test2() {
    //lamda表达式实现接口方法
    ItemCreatorBlankConstruct creator = () -> new Item();
    Item item = creator.getItem();

    //构造方法引用实现接口方法
    ItemCreatorBlankConstruct creator2 = Item::new;
    Item item2 = creator2.getItem();

    ItemCreatorParamConstruct creator3 = Item::new;
    Item item3 = creator3.getItem(112, "鼠标", 135.99);
  }

  //集合遍历
  @Test
  public void test3() {
    List<Integer> list = new ArrayList<>();


    Collections.addAll(list, 1, 2, 3, 4, 5);
    //lambda表达式 方法引用
    list.forEach(System.out::println);

    list.forEach(new Consumer<Integer>() {
      @Override
      public void accept(Integer integer) {
        System.out.println(integer);
      }
    });

    list.forEach(element -> {
      if (element % 2 == 0) {
        System.out.println(element);
      }
    });
  }

  //删除集合中的元素
  @Test
  public void test4() {
    List<Item> items = new ArrayList<>();
    items.add(new Item(11, "小牙刷", 12.05));
    items.add(new Item(5, "日本马桶盖", 999.05));
    items.add(new Item(7, "格力空调", 888.88));
    items.add(new Item(17, "肥皂", 2.00));
    items.add(new Item(9, "冰箱", 4200.00));

    items.removeIf(ele -> ele.getId() == 7);

    //通过 foreach 遍历，查看是否已经删除
    items.forEach(item -> System.out.println(item.getId()));

  }

  //集合元素内排序
  @Test
  public void test5(){
    ArrayList<Item> list = new ArrayList<>();
    list.add(new Item(13, "背心", 7.80));
    list.add(new Item(11, "半袖", 37.80));
    list.add(new Item(14, "风衣", 139.80));
    list.add(new Item(12, "秋裤", 55.33));

    list.sort(((o1, o2) -> o1.getId()-o2.getId()));
    list.sort((Comparator.comparingInt(Item::getId)));

    System.out.println(list);

  }

  //闭包问题,编译时虚拟机自动加上final
  @Test
  public void test6(){
    int num = 10;

    Consumer<String> consumer = ele -> {
      System.out.println(num);
    };

    //num = num + 2;
    consumer.accept("hello");
  }

}
