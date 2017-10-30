package com.hx.designpatterns.structure.adapter.xmlAdapter;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hx on 16-8-19.
 */
public class MySAXApp extends DefaultHandler {
  public MySAXApp() {
    super();
  }

  public void startDocument(){
    System.out.println("start document");

  }
  public void endDocument(){
    System.out.println("end document");

  }

  public void character(char ch[],int start,int length){
    System.out.println("charater()");
    for (int i = start; i <  start+length; i++) {
      System.out.println(ch[i]);
      
    }
  }



  public static void main(String[] args) throws SAXException, IOException {
//    XMLReader xr = XMLReaderFactory.createXMLReader();
//    System.getProperties().put("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
    String inputfile="/f/JavaHome/code/MyProjects/MyProjects/src/main/resources/hdfs-site.xml";
    XMLReader xr = new SAXParser();
    MySAXApp handler=new MySAXApp();
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    FileReader fr = new FileReader(inputfile);
    xr.parse(new InputSource(fr));


  }
}
