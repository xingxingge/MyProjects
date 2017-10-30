package com.hx.designpatterns.structure.adapter.xmlAdapter;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hx on 16-8-19.
 */
public class XMLProperties  extends Properties{
  XMLParser p= null;

  public XMLProperties(XMLParser p) {
    this.p = p;
  }
  public XMLProperties() {
    super();
  }

  public XMLProperties(Properties p) {
   super(p);
  }

  public synchronized void load(InputStream in){
    p=new XMLParser(in,this);
  }
  public synchronized void load(File file) throws FileNotFoundException {
    InputStream in = new FileInputStream(file);
    p=new XMLParser(in,this);
  }

}
