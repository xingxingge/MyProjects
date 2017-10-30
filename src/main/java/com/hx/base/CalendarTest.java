package com.hx.base;

import org.junit.Test;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @version 1.4 2007-04-07
 * @author Cay Horstmann hx
 */

public class CalendarTest {
  public static void main(String[] args) {
    GregorianCalendarAndDate();
    printCalenderNew();
    printCalendar();
  }

  public static void printCalendar() {
    GregorianCalendar d = new GregorianCalendar();

    int today = d.get(Calendar.DAY_OF_MONTH);
    int month = d.get(Calendar.MONTH);

    // set d to start date of the month
    d.set(Calendar.DAY_OF_MONTH, 1);

    int weekday = d.get(Calendar.DAY_OF_WEEK);

    // get first day of week (Sunday in the U.S.)
    int firstDayOfWeek = d.getFirstDayOfWeek();

    // determine the required indentation for the first line
    int indent = 0;
    while (weekday != firstDayOfWeek) {
      indent++;
      d.add(Calendar.DAY_OF_MONTH, -1);
      weekday = d.get(Calendar.DAY_OF_WEEK);
    }

    // print weekday names
    String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
    do {
      System.out.printf("%4s", weekdayNames[weekday]);
      d.add(Calendar.DAY_OF_MONTH, 1);
      weekday = d.get(Calendar.DAY_OF_WEEK);
    } while (weekday != firstDayOfWeek);
    System.out.println();

    for (int i = 1; i <= indent; i++)
      System.out.print("    ");

    d.set(Calendar.DAY_OF_MONTH, 1);
    do {
      // print day
      int day = d.get(Calendar.DAY_OF_MONTH);
      System.out.printf("%3d", day);

      // mark current day with *
      if (day == today)
        System.out.print("*");
      else
        System.out.print(" ");

      // advance d to the next day
      d.add(Calendar.DAY_OF_MONTH, 1);
      weekday = d.get(Calendar.DAY_OF_WEEK);

      // start a new line at the start of the week
      if (weekday == firstDayOfWeek) System.out.println();
    } while (d.get(Calendar.MONTH) == month);
    // the loop exits when d is day 1 of the next month

    // print final end of line if necessary
    if (weekday != firstDayOfWeek) System.out.println();
  }

  public static void GregorianCalendarAndDate() {
    {
      String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // 注意时间格式大小写，否则错误
          .format(new Date());
      System.out.println(s);
    }

    {
      Date birthday = new Date();
      Date deadline = birthday;

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      Date now = new Date();
      if (now.after(deadline)) {
        System.out.println("now is later  deadline");
      }

      @SuppressWarnings("deprecation")
      int hour = now.getHours();
      GregorianCalendar g = new GregorianCalendar();
      g.setTime(now);
      hour = g.get(Calendar.HOUR_OF_DAY);// 打印小时，注意：写成Calendar.HOUR后会按照0-12小时显示
      System.out.println("hour: " + hour);
      System.out.println("minute:"+g.get(Calendar.MINUTE));
      g.set(Calendar.MINUTE,15);
      System.out.println(g.getTime());
    }

    {
      GregorianCalendar deadline = new GregorianCalendar();
      int year = deadline.get(GregorianCalendar.YEAR);
      Date time = deadline.getTime();
      System.out.println("time:" + time);
      deadline.set(GregorianCalendar.YEAR, 2015);
      year = deadline.get(GregorianCalendar.YEAR);
      System.out.println("year:" + year);

      GregorianCalendar calender = new GregorianCalendar(2014, 05, 20, 11, 4, 20);// 注意月份从0开始，这个实际表示6月，可以设定月份名称
      System.out.println(calender.getTime());
      System.out.println("newTime:"
          + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new GregorianCalendar(2014,
              Calendar.MAY, 20, 11, 4, 20).getTime()));
    }
  }

  public static void printCalenderNew() {
    // 1.打印首部
    // 2.确定今天是星期几
    // 3.确定本月一号是星期几，从而确认前导空格
    // 4.打印结果

    // 打印首部
    Locale.setDefault(Locale.ENGLISH);

    GregorianCalendar g = new GregorianCalendar();
    int today = g.get(Calendar.DAY_OF_MONTH);
    int month = g.get(Calendar.MONTH);
    // g.setFirstDayOfWeek(Calendar.MONDAY);
    int firstDayOfWeek = g.getFirstDayOfWeek();

    System.out.println("today: " + today);
    System.out.println("month: " + month);
    System.out.println("firstDayOfWeek: " + firstDayOfWeek); // 一周中第一天的标记,用来计算缩进

    // 确定本月一号是星期几
    g.set(Calendar.DAY_OF_MONTH, 1);
    // 获取当月一号的星期,是星期6，所以从这里开始计算缩进
    int weekOfFirstDay = g.get(Calendar.DAY_OF_WEEK); // 1-7

    System.out.println("weekOfFirstDay: " + weekOfFirstDay);
    // 计算缩进
    int count = 0;
    while (firstDayOfWeek < weekOfFirstDay) {
      count++;
      firstDayOfWeek++;
    }

    String[] weekday = new DateFormatSymbols().getShortWeekdays();
    for (int i = 1; i < weekday.length; i++) {
      System.out.printf("%4s", weekday[i]);
    }
    System.out.println();

    count = count * 4;
    for (int i = 1; i <= count; i++) {
      System.out.printf(" ");

    }

    String flag = "";

    while (g.get(Calendar.MONTH) == month) {
      flag = g.get(Calendar.DAY_OF_MONTH) == today ? "*" : " ";
      if (g.get(Calendar.DAY_OF_WEEK) % 7 == 0) {
        System.out.printf("%4s\n", g.get(Calendar.DAY_OF_MONTH) + flag);
      } else {
        System.out.printf("%4s", g.get(Calendar.DAY_OF_MONTH) + flag);
      }
      g.add(Calendar.DAY_OF_MONTH, 1);
    }
    System.out.println();
  }

  @Test
  public void test(){
    Date date = new Date();
    Date date2=new Date(date.getTime()+3000);
    date = getDate(date);
    date2=getDate(date2);
    System.out.println(date.equals(date2));

  }

  private Date getDate(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    date = calendar.getTime();
    return date;
  }
}
