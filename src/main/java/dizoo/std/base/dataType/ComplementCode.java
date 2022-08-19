package dizoo.std.base.dataType;

import org.junit.Test;

/**
 * Created by hx on 16-1-17.
 */
public class ComplementCode {

  @Test
  public void test2(){
    byte b =  -128;//-2^7
    System.out.printf("%x\n",b);
    short c = -32768;//-2^15
    System.out.printf("%x\n",c);
    int d = -2147483648;//-2^31
    System.out.printf("%x\n",d);
    long e = -9223372036854775808l;//-2^63
    System.out.printf("%x\n",e);
  }

  @Test
  public void test3(){
    short a = 12345;
    short b =  -12345;
    int c = 53191;
    System.out.printf("%x\n",a);
    System.out.printf("%x\n",b);
    System.out.printf("%x\n",c);
  }


  //符号扩展与数字截断
  @Test
  public void test4(){
    byte a = 0x64;
    System.out.printf("%x\n",a);
    short b = a;
    System.out.printf("%x\n",b);
    byte c = (byte) 0xe4;
    System.out.printf("%x\n",c);
    short d = c;
    System.out.printf("%x\n",d);//ffe4
    short e = (short) 0x88e4;
    System.out.printf("%x\n",e);
    byte f = (byte) e;
    System.out.printf("%d\n",f);//ffe4
  }

  @Test
  public void test5(){
    short a = (short) 0b1111_0111_0111_1111;
    System.out.println(a);
    System.out.printf("%x\n", a >> 4);//算术移位,高位补上符号位
    System.out.printf("%x\n",a>>>4);//逻辑移位,高位不管
    short b = (short) (a<<4);
    System.out.printf("%x\n", b);//算术左移
    System.out.println(b);

  }

}
