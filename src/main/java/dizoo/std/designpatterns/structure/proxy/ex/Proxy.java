package dizoo.std.designpatterns.structure.proxy.ex;

/**
 * Created by hx on 16-8-26.
 */
public class Proxy  implements  Search{
  private Search search=new RealSearch();
  private  UsageLogger usageLogger=new UsageLogger();
  private AccessValidator accessValidator=new AccessValidator();
  public String  doSearch(String string){
    accessValidator.validate();
    String result=search.doSearch(string);
    usageLogger.save();
    return result;

  }

}
