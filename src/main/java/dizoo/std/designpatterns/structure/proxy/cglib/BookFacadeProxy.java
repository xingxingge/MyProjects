package dizoo.std.designpatterns.structure.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hx on 17-1-2.
 */
public class BookFacadeProxy implements MethodInterceptor {
  private Object target;


  @Override
  public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
    System.out.println("事物开始");
    methodProxy.invokeSuper(o, args);
    System.out.println("事物结束");
    return null;
  }

  public Object getInstance(Object target) {
    this.target = target;
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(this.target.getClass());
    // 回调方法
    enhancer.setCallback(this);
    // 创建代理对象
    return enhancer.create();
  }

  public static void main(String[] args) {
    BookFacadeProxy cglib=new BookFacadeProxy();
    BookFacade bookCglib=(BookFacade)cglib.getInstance(new BookFacadeImpl());
    bookCglib.addBook();
  }
}
