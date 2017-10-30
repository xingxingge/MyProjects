package com.hx.designpatterns.structure.decorator.grep;

import java.io.PrintStream;

/**
 * Created by hx on 16-8-24.
 */
public class GrepView {
  PrintStream out;

  public GrepView() {
    this.out = System.out;
  }

  public void println(String string){
    out.println(string);


  }
}
