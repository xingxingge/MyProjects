package com.hx.designpatterns.structure.decorator.grep;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by hx on 16-8-24.
 */
public class Test {
  public static void main(String[] args) throws IOException {
    GrepView grepView = new GrepView();
    Reader reader = new FileReader("/f/JavaHome/code/MyProjects/MyProjects/src/main/java/com/hx/designpatterns/decorator/grep/GrepReader.java");
    GrepReader grepReader = new GrepReader(reader,"Grep");
    String line;
    while((line=grepReader.readLine())!=null){
      grepView.println(line);
      System.out.println(grepReader.getLineNumber());

    }



  }
}
