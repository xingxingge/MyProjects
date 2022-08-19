package dizoo.std.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaceTest {
  //生产数据
  @Test
  public void test1() {
    Supplier<List<Integer>> supplier = () -> {
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
        list.add(i);
      }
      return list;
    };
    List<Integer> integers = supplier.get();
    System.out.println(integers);
  }

  //消费数据
  @Test
  public void test2() {
    Consumer<Integer> consumer = (t) -> {
      System.out.println(t);
    };
    consumer.accept(10);
  }

  //消费数据:and then,c1执行完再执行c2
  @Test
  public void test3() {
    Consumer<Integer> consumer1 = (t) -> {
      System.out.println(t+1);
    };
    Consumer<Integer> consumer2 = (t) -> {
      System.out.println(t-1);
    };
    int  r = 100;
    consumer1.andThen(consumer2).accept(r);
  }

  //Function函数使用:输入类型T，返回类型R
  @Test
  public void test4(){
    Function<String,Integer> function = s -> {
      return Integer.valueOf(s);
    };
    System.out.println(function.apply("100"));
  }

  //Predicate函数使用
  @Test
  public void test5(){
    Predicate<Integer> function = s -> s>=5;
    System.out.println(function.test(100));
  }
}
