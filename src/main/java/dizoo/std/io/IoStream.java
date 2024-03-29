package dizoo.std.io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * Created by hx on 16-1-17.
 */
public class IoStream {
  @Test
  public void readTest() throws IOException {
    // 把读取的字节序列写入字节数组,数组的长度一般是1024或者1024的整数倍
    byte[] bys = new byte[64];
    InputStream ins = new FileInputStream("/f/JavaHome/code/MyProjects/MyProjects/src/main/java/com/hx/io/IoStream.java");
    OutputStream ous = new FileOutputStream("/f/JavaHome/code/MyProjects/MyProjects/src/main/java/com/hx/io/IoStream.txt");
    int len;
    while ((len = ins.read(bys)) != -1) {
//      System.out.println(len);
      //这样最后一次输出时容易出错,因为可能最后的几个字符没能替换掉
//      System.out.println(new String(bys));
      ous.write(bys);
      System.out.print(new String(bys, 0, len));//标准写法是用这个
    }
    ins.close();
    ous.close();
  }

  @Test
  public void chineseTest() {
    // String s = "abcde";
    // [97, 98, 99, 100, 101]
    String s = "我爱你中国";
    //utf-8编码:2-4个字节一个中文字符
    // [-26, -120, -111,
    // -25, -120, -79,
    // -28, -67, -96,
    // -28, -72, -83,
    // -27, -101, -67]
    //gbk编码: 2个字节一个中文字符
    // [-50, -46, -80, -82, -60, -29, -42, -48, -71, -6]
    byte[] bys = s.getBytes();
    System.out.println(Arrays.toString(bys));
  }
}
