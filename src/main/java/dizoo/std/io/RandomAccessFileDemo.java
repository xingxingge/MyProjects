package dizoo.std.io;

import org.junit.Test;

import java.io.RandomAccessFile;

/**
 * Created by hx on 16-1-24.
 */
public class RandomAccessFileDemo {
  @Test
  public void write() throws Exception{
    RandomAccessFile raf = new RandomAccessFile(
            "./src/main/java/com/hx/io/random.txt","rw");
    raf.writeChar('a');
    raf.writeInt(100);
    raf.writeByte(20);
    raf.writeUTF("中");
    raf.close();
  }

  //read
  @Test
  public void read() throws  Exception{
    RandomAccessFile raf = new RandomAccessFile(
            "./src/main/java/com/hx/io/random.txt","rw");
    System.out.println(raf.readChar());
    System.out.println(raf.getFilePointer());
    System.out.println(raf.readInt());
    System.out.println(raf.getFilePointer());
    System.out.println(raf.readByte());
    System.out.println(raf.getFilePointer());
    System.out.println(raf.readUTF());
    System.out.println(raf.getFilePointer());
    raf.seek(2);
    System.out.println(raf.getFilePointer());
    System.out.println(raf.readInt());
  }
}
