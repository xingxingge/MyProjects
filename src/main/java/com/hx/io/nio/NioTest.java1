package com.hx.io.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hx on 16-10-24.
 */
public class NioTest {

  @Test
  public void read() throws IOException {
    FileInputStream fin = new FileInputStream("./src/main/java/com/hx/io/nio/NioTest.java");
    FileChannel fc = fin.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    fc.read(buffer);
    System.out.println(buffer.get(0));
    System.out.println(buffer.get(1));

  }

  @Test
  public void write() throws IOException {
    FileOutputStream fous = new FileOutputStream("./src/main/java/com/hx/io/nio/NioTest.java1");
    FileChannel fc = fous.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    String messages = "你好";
    byte[] bytes = messages.getBytes();
    for (int i = 0; i < bytes.length; i++) {
      buffer.put(bytes[i]);
    }
    buffer.flip();
    fc.write(buffer);

  }

  @Test
  public void copyFile() throws IOException {

    FileInputStream fin = new FileInputStream("./src/main/java/com/hx/io/nio/NioTest.java");
    FileChannel fcin = fin.getChannel();

    FileOutputStream fout = new FileOutputStream("./src/main/java/com/hx/io/nio/NioTest.java1");
    FileChannel fcout = fout.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate(1024);

    while (true) {
      buffer.clear();
      int r = fcin.read(buffer);
      if (r == -1) {
        break;
      }
      buffer.flip();
      fcout.write(buffer);
    }


  }
}
