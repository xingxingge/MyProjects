package dizoo.std.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by hx on 16-1-23.
 */
public class ReaderAndWriter {

  @Test
  public void testReader() throws Exception {
    System.out.println(System.getProperty("user.dir"));
    FileReader fileReader = new FileReader("./src/main/java/com/hx/io/IoStream.txt");
    FileWriter fileWriter = new FileWriter("./src/main/java/com/hx/io/result.txt");
    char[] chars = new char[64];
    int len;
    while ((len = fileReader.read(chars)) != -1) {
      fileWriter.write(chars, 0, len);
      System.out.println(new String(chars,0,len));
      fileWriter.flush();
    }
    fileReader.close();
    fileWriter.close();
  }

  @Test
  public void testStreamReader() throws Exception {
    BufferedInputStream bufferedInputStream =
            new BufferedInputStream(new FileInputStream("./src/main/java/com/hx/io/IoStream.txt"));
    BufferedOutputStream bufferedOutputStream =
            new BufferedOutputStream(new FileOutputStream("./src/main/java/com/hx/io/result.txt"));
    byte[] bytes = new byte[32];
    int len;
    while ((len = bufferedInputStream.read(bytes)) != -1) {
      bufferedOutputStream.write(bytes, 0, len);
    }
    bufferedInputStream.close();
    bufferedOutputStream.close();
  }


  @Test
  public void testWriter() throws  Exception{

    BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("./src/main/java/com/hx/io/IoStream.txt"),"UTF-8"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./src/main/java/com/hx/io/result.txt",false),"UTF-8"));
    String str;
    while((str=br.readLine())!=null){
      bw.write(str);
      bw.newLine();
      bw.flush();
    }
    br.close();
    bw.close();
  }
}
