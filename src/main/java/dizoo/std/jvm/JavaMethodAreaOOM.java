package dizoo.std.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hx on 16-2-16.
 */
public class JavaMethodAreaOOM {
  static class OOMObject{}
  public static void main(String[] args) {
    while (true){
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(OOMObject.class);
      enhancer.setUseCache(false);
      enhancer.setCallback(new MethodInterceptor() {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
          return methodProxy.invokeSuper(o,objects);
        }
      });
      enhancer.create();
    }
  }
}
