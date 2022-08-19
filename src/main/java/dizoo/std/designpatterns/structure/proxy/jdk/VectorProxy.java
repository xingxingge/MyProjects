package dizoo.std.designpatterns.structure.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

/**
 * Created by hx on 16-8-25.
 */
public class VectorProxy implements InvocationHandler {
  private Object proxyObject;

  //执行方法时候调用
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before");
    Object o = method.invoke(proxyObject,args);
    System.out.println("after");
    return o;
  }

  public VectorProxy(Object proxyObject) {
    this.proxyObject = proxyObject;
  }

  public Object bind(){
    return Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(),proxyObject.getClass().getInterfaces(),this);
  }

  public static void main(String[] args) throws Throwable {
    Vector vector = new Vector();
    VectorProxy vectorProxy = new VectorProxy(vector);
    List v= (List) vectorProxy.bind();
    v.add("123");
//    vectorProxy.invoke(null,vector.getClass().getMethod("add",Object.class),new Object[]{2});
    System.out.println(vector.size());
  }
}
