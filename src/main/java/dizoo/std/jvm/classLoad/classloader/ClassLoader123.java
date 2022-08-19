package dizoo.std.jvm.classLoad.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by hx on 16-10-8.
 */
public class ClassLoader123 {
  public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    File file =new File("/f/JavaHome/code/MyProjects/DataStructure/target/datastructure-1.1-SNAPSHOT.jar");
//    URL url = file.toURL();
//    URL[] urls=new URL[]{url};
//    URLClassLoader classLoader = new URLClassLoader(urls);
//    Class c=classLoader.loadClass("org.apache.lucene.util.Version");
////    Class class1=ClassLoaderUtils.getDefaultClassLoader().loadClass("org.apache.lucene.util.MathUtil");
//    c.newInstance();
    URLClassLoader cl = (URLClassLoader) Thread.currentThread().getContextClassLoader();

    System.out.println(Arrays.toString(cl.getURLs()));
    Class sysclass = URLClassLoader.class;
    Method method = null;
    method=sysclass.getDeclaredMethod("addURL",new Class[]{URL.class});
    method.setAccessible(true);
    method.invoke(cl,file.toURI().toURL());
    Class c=cl.loadClass("com.topsec.tsm.datastructure.list.ListArray");
   Object obj= c.newInstance();
    System.out.println(obj.getClass());
    System.out.println(obj);
    System.out.println(Arrays.toString(cl.getURLs()));

  }
}
