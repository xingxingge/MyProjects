package com.hx.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by hx on 16-1-24.
 */
public class DataStreamTest {
  @Test
  public void write() throws IOException {
    DataOutputStream ds = new DataOutputStream(
            new FileOutputStream("./src/main/java/com/hx/io/result.txt"));
    ds.writeByte(127);
    ds.writeChar('a');
    ds.writeShort(32767);
    ds.writeInt(2147483647);
    ds.writeLong(9223372036854775807l);
    ds.writeDouble(10.2);
    ds.writeBoolean(true);
    ds.close();
  }

  @Test
  public void read() throws  Exception{
    DataInputStream dis = new DataInputStream(
            new FileInputStream("./src/main/java/com/hx/io/result.txt"));
    // 读数据
    byte b = dis.readByte();
    char c = dis.readChar();
    short s = dis.readShort();
    int i = dis.readInt();
    long l = dis.readLong();
    double d = dis.readDouble();
    boolean bb = dis.readBoolean();

    // 释放资源
    dis.close();
    System.out.println(b);
    System.out.println(s);
    System.out.println(i);
    System.out.println(l);
    System.out.println(d);
    System.out.println(c);
    System.out.println(bb);

  }
}
