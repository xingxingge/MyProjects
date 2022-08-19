package dizoo.std.base.we.t4;

public class EquivelantTest {
  public static void main(String[] args) {
    System.out.println(16/2^2);
    //1 0 0 0
    //0 0 1 0
    //逻辑亦或,相同为0,不同为1 1 0 1 0 =10
    System.out.println(16>>2);
    //1 0 0 0 0  -- 1 0 0 =4
    System.out.println(16>>>2);
    // >>> 运算符用0填充高位，>>用符号位填充高位，没有<<<运算符
    System.out.println(16/2); //8 
    System.out.println(16*4);  //64
  }

}
