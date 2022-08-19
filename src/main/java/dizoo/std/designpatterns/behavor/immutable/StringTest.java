package dizoo.std.designpatterns.behavor.immutable;

/**
 * Created by hx on 16-9-7.
 */
public class StringTest {
  public static void main(String[] args) {
    String a="yesmyhome";
    String b="yesmyhome";
    String c=new String("yesmyhome");
    System.out.println(a==b);
    System.out.println(a==c);
    System.out.println(a.hashCode());
    System.out.println(b.hashCode());
    System.out.println(c.hashCode());
    System.out.println(a.equals(c));
    System.out.println(a.intern()==c.intern());
    System.out.println(c=="yesmyhome");
    String d=a.replace("ccc","aa");
    System.out.println(a==d);
  }
}
