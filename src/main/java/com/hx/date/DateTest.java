package com.hx.date;

import junit.framework.TestCase;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTest  extends TestCase{

  public static void main(String[] args) {
    java.util.Date aa = new java.util.Date();
    System.out.println(1+"\t\t"+aa);
    java.sql.Date bb = new java.sql.Date(aa.getTime());
    System.out.println(2+"\t\t"+bb);
    java.sql.Time cc = new java.sql.Time(aa.getTime());
    System.out.println(3+"\t\t"+cc);
    java.sql.Timestamp dd = new java.sql.Timestamp(aa.getTime());
    System.out.println(4+"\t\t"+dd);

    String ee= String.valueOf(dd.toString()).split("\\.")[0];
		System.out.println(5+"\t\t"+ee);

    Timestamp ff = Timestamp.valueOf(dd.toString());
    System.out.println((6+"\t\t"+ff.getTime()));

    java.sql.Date gg = java.sql.Date.valueOf(bb.toString());
    System.out.println(7+"\t\t"+gg);

    java.util.Date hh = new java.util.Date(gg.getTime());
    System.out.println(8+"\t\t"+hh);
  }

  public void test1() throws ParseException {
    String d="2016-12-26 21:03:01";
    Date date = new SimpleDateFormat("yyyy-MM-dd HH").parse(d);
    System.out.println(date.getTime());
    System.out.println(System.currentTimeMillis());
    System.out.println(System.nanoTime());
  }
  public void test2() throws ParseException {
    System.out.println(new Date());
    String d="Sat Aug 30 19:57:29 CST 2014";

    Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH).parse(d);
    System.out.println(date);
    System.out.println(System.currentTimeMillis());
    System.out.println(System.nanoTime());
  }



  public void test3() throws ParseException {

    System.out.println(new Date());
    String d="Sat Aug 30 19:57:29 CST 2014";

    Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH).parse(d);
    System.out.println(date);
    System.out.println(System.currentTimeMillis());
    System.out.println(System.nanoTime());
  }

}
