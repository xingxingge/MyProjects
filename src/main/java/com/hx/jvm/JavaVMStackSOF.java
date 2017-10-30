package com.hx.jvm;

/**
 * Created by hx on 16-2-16.
 */
public class JavaVMStackSOF {
  private int stackLength=1;
  public void stackLeak(){
    stackLength++;
    stackLeak();
  }

  public static void main(String[] args) throws Throwable {
    JavaVMStackSOF oom = new JavaVMStackSOF();
    try {
      oom.stackLeak();
    }catch (Throwable e){
      System.out.println(oom.stackLength);
      throw e;
    }
  }
}
