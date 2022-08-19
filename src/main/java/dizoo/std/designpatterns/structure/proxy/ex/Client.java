package dizoo.std.designpatterns.structure.proxy.ex;

/**
 * Created by hx on 16-8-26.
 */
public class Client {
  private static  Search search;

  public static void main(String[] args) {
    search = new Proxy();
    String userid="admin";
    String result=search.doSearch(userid);
    System.out.println(result);


  }
}
