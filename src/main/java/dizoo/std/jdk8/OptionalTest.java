package dizoo.std.jdk8;

import java.util.Optional;

public class OptionalTest {
  public static void main(String[] args) {
    Optional<String> op1 = Optional.of("nihao");
    Optional<Object> op2 = Optional.empty();

    //1、get()方法获取值，有值返回值，无值抛出NoSuchElementException 异常
    System.out.println(op1.get());    //nihao
//    System.out.println(op2.get());    //No value present

    //2、isPresent() 判断是否包含值，包含值返回true，不包含返回false
    //   ifPresent(T->{...}) 如果存在值就执行Lambda表达式
    System.out.println(op1.isPresent());  //true
    System.out.println(op2.isPresent());  //false

    //3、 orElse(T t) 如果调用对象包含值，就返回该值，否则返回t
    //   orElseGet(Supplier s) 如果调用对象包含值，就返回该值，否则返回Lambda表达式的返回值
    System.out.println(op1.orElse("hi"));   //nihao
    System.out.println(op2.orElse("123"));  //123
    System.out.println(op2.orElseGet(()->"123"));
  }
}
