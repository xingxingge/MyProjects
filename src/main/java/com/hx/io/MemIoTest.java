package com.hx.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by hx on 16-1-24.
 */
public class MemIoTest {
  @Test
  public void ByteArrayTest() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write("hello1".getBytes());
    baos.write("hello2".getBytes());
    // 释放资源
    // 通过查看源码我们知道这里什么都没做，所以根本需要close()
//    baos.close();
    byte[] bys = baos.toByteArray();
//    System.out.println(new String(bys));

    //读取数据
    ByteArrayInputStream bais = new ByteArrayInputStream(bys);
    int i;
    while ((i = bais.read()) != -1) {
      System.out.print((char) i);
    }

  }

  @Test
  public void CharArrayTest() throws Exception {
    CharArrayWriter caw = new CharArrayWriter();
    caw.write("中国");
    char[] chars = caw.toCharArray();
    CharArrayReader car = new CharArrayReader(chars);
    int i;
    while ((i = car.read()) != -1) {
      System.out.print((char) i);
    }
  }

  @Test
  public void StringTest() throws Exception {
    StringWriter sw = new StringWriter();
    sw.write("中国");
    sw.write("北京");
    StringBuffer sb = sw.getBuffer();
    StringReader sr = new StringReader(sb.toString());
    int i;
    while ((i = sr.read()) != -1) {
      System.out.print((char) i);
    }
  }
  @Test
  public void ByteArrayAndObjectTest() throws IOException, ClassNotFoundException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    Person o = new Person();
    o.setId(100);
    System.out.println(o);
    oos.writeObject(o);
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bais);
    Person i =(Person)ois.readObject();
    System.out.println(i);
    System.out.println(i.getId());
  }

}
