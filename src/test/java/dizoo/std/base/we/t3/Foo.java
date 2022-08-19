package dizoo.std.base.we.t3;

public class Foo {
  public static void main(String[] args) {
    String a = "A";
    String b = "B";
    operate(a, b);
    System.out.println(a + "," + b);
  }

  static void operate(String x, String y) {
    y+=x;//y=BA
    y=x;//Y=A
  }
}
