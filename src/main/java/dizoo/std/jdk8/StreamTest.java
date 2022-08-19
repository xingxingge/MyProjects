package dizoo.std.jdk8;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
  //获取流
  @Test
  public void test1() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    Stream<Integer> stream = list.stream();
    System.out.println(stream.count());
    Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    System.out.println(integerStream.collect(Collectors.toList()));
  }

  @Test
  public void testsForEach() {
    List<String> list = new ArrayList<>();
    list.add("张三");
    list.add("张三四");
    list.add("王五");
    list.add("赵六");
    list.add("张三三");
    list.add("周八");
    //正常遍历
    list.forEach(System.out::println);
    // 函数模型：获取流 --> 逐一消费流中的元素
    // 函数模型：获取流 --> 逐一消费流中的元素
    System.out.println("==========================");
    list.stream().forEach(System.out::println);
    /*
     * 虽然方法名称叫做【forEach】，但是与for循环中的【for-each】昵称不同，该方法并不保证元素的逐一消费动作在流中是被有序执行的
     * */
    System.out.println("==========================");
    // parallelStream()方法可以得到并行流:可能无序
    list.parallelStream().forEach((String name) -> {
      System.out.println(name);
    });
  }

  @Test
  public void  testFilter() {
    // 获取stream流
    Stream<String> stream = Stream.of("张三", "张三四", "王五", "赵六", "张三三", "周八");
    // 需求：过滤处
    stream.filter(name -> name.startsWith("张")).forEach(System.out::println);
  }

  @Test
  public void testLimit() {
    // 获取stream流
    Stream<String> stream = Stream.of("张三", "张三四", "王五", "赵六", "张三三", "周八");
    // 需求：保留前3个元素
    stream.limit(3).forEach(System.out::println);
    System.out.println("===========================");
    // 如果流的获取长度 大于 流当前的长度，则不操作

//    stream.limit(10).forEach(System.out::println); // 异常： stream has already
    // been operated upon or closed 流已被操作或关闭
    System.out.println("===========================");
    Stream<String> stream1 = Stream.of("张三", "张三四", "王五", "赵六", "张三三", "周八");
    stream1.limit(10).forEach(System.out::println);
  }

  @Test
  public void testSkip() {
    // 获取stream流
    Stream<String> stream = Stream.of("张三", "张三四", "王五", "赵六", "张三三", "周八");
    // 需求：跳过前3个
    stream.skip(3).forEach(System.out::println);
  }

  @Test
  public void  testMap() {
    //获取所有学生的姓名，并形成一个新的集合  //结果：张三 李四 王五 赵六
    ArrayList<Pair<String,String>> students = new ArrayList<>();
    students.add(new Pair("张三","M"));
    students.add(new Pair("李四","M"));
    students.add(new Pair("王五","F"));
    students.add(new Pair("赵六","F"));

    List<String> collect = students.stream().map(Pair::getKey).collect(Collectors.toList());
    collect.forEach(s -> System.out.print(s + " "));
  }

  @Test
  public void testSort(){
    List<Integer> sortLists = new ArrayList<>();
    sortLists.add(1);
    sortLists.add(4);
    sortLists.add(6);
    sortLists.add(3);
    sortLists.add(2);
    sortLists.stream().sorted((In1,In2)->
        In1-In2).collect(Collectors.toList()).forEach(System.out::println);
  }

  @Test
  public  void testConcat() {
    Stream<String> original1 = Stream.of("Java", "C", "Python");
    Stream<String> original2 = Stream.of("Hadoop", "Spark");
    Stream<String> result = Stream.concat(original1, original2);
    result.forEach(System.out::println);
  }

  @Test
  public  void testDistinct() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 4, 5, 5, 6, 7, 8, 2, 2, 2, 2);
    stream.distinct().collect(Collectors.toList()).forEach(System.out::println);
  }
  @Test
  public  void testReduce() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 4, 5, 5, 6, 7, 8, 2, 2, 2, 2);
    Integer reduce = stream.reduce(0, Integer::sum);
    System.out.println(reduce);

    String s = Stream.of("1", "2", "3", "4", "5", "6")
        // identity 是默认值
        .reduce("0",(x, y) -> {
          System.out.println("x= "+x+" y= "+y);
          return x +y;
        });
    System.out.println(s);
  }

  @Test
  public  void testMaxMin(){
    List<String> maxLists = new ArrayList<>();
    maxLists.add("a");
    maxLists.add("b");
    maxLists.add("c");
    maxLists.add("d");
    maxLists.add("e");
    maxLists.add("f");
    maxLists.add("hahaha");
    String s1 = maxLists.stream().max(Comparator.comparingInt(String::length))
        .get();
    System.out.println(s1);
    int maxLength = maxLists.stream().mapToInt(s->s.length()).max().getAsInt();

    System.out.println("字符串长度最长的长度为"+maxLength);
  }

  @Test
  public void testMatch(){
    //  判断集合中有无c的元素：
    Stream<String> stream = Stream.of("a", "b", "c", "d");
    System.out.println(stream.anyMatch(s -> s.equals("c")));

    //判断集合中是否全不为空
    List<String> matchList = new ArrayList<>();
    matchList.add("a");
    matchList.add("");
    matchList.add("a");
    matchList.add("c");
    matchList.add("d");
    boolean isNotEmpty = matchList.stream().noneMatch(s -> s.isEmpty());
    System.out.println(isNotEmpty);
  }

  @Test
  public void testCollector() {
    List<String> list = new ArrayList<>();
    list.add("张三");
    list.add("张三四");
    list.add("王五");
    list.add("赵六");
    list.add("张三三");
    list.add("周八");
    // 需求：过滤出姓张的并且长度为3的元素
    Stream<String> stream1 = list.stream().filter(name -> name.startsWith("张")).filter(name -> name.length() == 3);

    // stream 收集到单列集合中
    List<String> list1 = stream1.collect(Collectors.toList());
    System.out.println(list1);

    // stream 收集到单列集合中
//    Set<String> set1 = stream1.collect(Collectors.toSet());
//    System.out.println(set1);
//
//    // 将stream流中的元素转成Array数组对象
//    Object[] arr = stream1.toArray();
//    System.out.println(Arrays.toString(arr));
  }



}
