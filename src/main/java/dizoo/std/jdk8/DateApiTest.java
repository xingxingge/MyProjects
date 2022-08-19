package dizoo.std.jdk8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class DateApiTest {
  @Test
  public  void getDate(){
    LocalDate currDate=LocalDate.now();
    //指定日期
    LocalDate noeDay=LocalDate.of(2020, 1, 11);
    //通过字符串指定日期
    LocalDate towDay=LocalDate.parse("2020-01-11");

    // 年
    int year = currDate.getYear();
    // 月
    int month = currDate.getMonthValue();
    // 一月的第几天
    int day = currDate.getDayOfMonth();
    // 一周的第几天
    int week = currDate.getDayOfWeek().getValue();
    System.out.println(currDate);
  }
  @Test
  public void testTime(){
    //   时间比较（LocalDate重写了equals方法，让日期的比较也变得简单了。）

    LocalDate date1 = LocalDate.parse("2022-12-12");
    LocalDate date2 = LocalDate.parse("2022-12-12");
    System.out.println("比较两个日期是否相同："+date1.equals(date2));  //true
    //isBefore在之前
    boolean isBefore=LocalDate.parse("2020-01-11").isBefore(LocalDate.parse("2020-01-10"));
    //isAfter在之后
    boolean isAfter=LocalDate.parse("2020-01-11").isAfter(LocalDate.parse("2020-01-10"));

    // 日期加减
    System.out.println("当前时间"+LocalDate.now());
    System.out.println("当前时间加1天"+LocalDate.now().plusDays(1));
    System.out.println("当前时间加1月"+LocalDate.now().plusMonths(1));
    System.out.println("当前时间加1年"+LocalDate.now().plusYears(1));

    //LocalDate的atTime()方法或LocalTime的atDate()方法将LocalDate或LocalTime合并成一个LocalDateTime。
    //LocalDateTime与LocalDate和LocalTime之间可以相互转化
    LocalDateTime datetime4=LocalDate.now().atTime(LocalTime.now());
    LocalDateTime datetime5=LocalTime.now().atDate(LocalDate.now());

    //ZonedDateTime-创建时区时间。用于处理带时区的日期和时间。ZoneId表示不同的时区。大约有40不同的时区。

    Set<String> allZoneIds= ZoneId.getAvailableZoneIds();
    //创建时区：
    ZoneId zoneId=ZoneId.of("Asia/Shanghai");
    //把LocalDateTime转换成特定的时区：
    ZonedDateTime zonedDateTime=ZonedDateTime.of(LocalDateTime.now(), zoneId);
    //获取当前时区
    ZoneId z=ZoneId.systemDefault();
    //获取日期时间：
    ZonedDateTime dd = ZonedDateTime.now();
    ZonedDateTime time2 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
    System.out.println(time2);

  }

  @Test
  public  void dateFormat(){
    LocalDateTime dateTime=LocalDateTime.now();
    String str=dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    System.out.println(str);
    str = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    System.out.println(str);
  }
  @Test
  public void calDiff(){
    //日期时间差
    LocalDate today = LocalDate.now();
    LocalDate birthDate = LocalDate.of(1993, Month.OCTOBER, 19);
    Period p = Period.between(birthDate, today);   //  birthDate - today
    System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());

    //时分秒时间差
    Instant inst1 = Instant.now();
    Instant inst2 = inst1.plus(Duration.ofSeconds(10));
    System.out.println("Difference in milliseconds : " + Duration.between(inst1, inst2).toMillis());
    System.out.println("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());

    //ChronoUnit类可用于在单个时间单位内测量一段时间，例如天数或秒
    LocalDate startDate = LocalDate.of(1993, Month.OCTOBER, 19);
    System.out.println("开始时间  : " + startDate);
    LocalDate endDate = LocalDate.of(2017, Month.JUNE, 16);
    System.out.println("结束时间 : " + endDate);
    long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
    System.out.println("两天之间的差在天数   : " + daysDiff);//两天之间的差在天数   : 8641

  }
}
