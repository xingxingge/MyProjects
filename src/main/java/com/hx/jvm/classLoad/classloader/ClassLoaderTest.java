package com.hx.jvm.classLoad.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.test.Test;

/**
 * Created by hx on 16-10-9.
 */
public class ClassLoaderTest {
  public static void main(String[] args) throws Exception {

    ClassLoader myLoader =new CustomClassLoader();

    Object obj = myLoader.loadClass("com.hx.jvm.classLoad.classloader.ClassLoaderTest").newInstance();

    System.out.println("obj classloader:"+obj.getClass().getClassLoader());
    System.out.println("myLoader  classloader:"+myLoader.getClass().getClassLoader());
    System.out.println(obj.getClass());
    System.out.println(obj instanceof ClassLoaderTest);
    Object obj2=myLoader.loadClass("com.hx.jvm.classLoad.classloader.Package").newInstance();
    Object obj3=myLoader.loadClass("java.lang.Package").newInstance();
    Test t = new Test();
  }
}
class CustomClassLoader extends ClassLoader{
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    try {
      String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
      InputStream is = getClass().getResourceAsStream(fileName);
      if (is == null) {
        return super.loadClass(name);
      }
      byte[] b = new byte[is.available()];
      is.read(b);
      return defineClass(name, b, 0, b.length);
    } catch (IOException e) {
      throw new ClassNotFoundException(name);
    }
  }

}



