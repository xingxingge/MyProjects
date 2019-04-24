package com.hx.scheduler;

import org.junit.Test;
import org.quartz.Calendar;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.calendar.DailyCalendar;

import java.util.Date;

//import org.quartz.helpers.TriggerUtils;

public class QuartzTest implements Job {

  @Override
  //该方法实现需要执行的任务
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    System.out.println("Generating report - "
            + arg0.getJobDetail().getFullName() + ", type ="
            + arg0.getJobDetail().getJobDataMap().get("type"));
    System.out.println(new Date().toString());
  }

  public static void main(String[] args) {
    try {
      // 创建一个Scheduler
      SchedulerFactory schedFact =
              new org.quartz.impl.StdSchedulerFactory();
      Scheduler sched = schedFact.getScheduler();
      sched.start();
      // 创建一个JobDetail，指明name，groupname，以及具体的Job类名，
      //该Job负责定义需要执行任务
      JobDetail jobDetail = new JobDetail("myJob", "myJobGroup",
              QuartzTest.class);
      jobDetail.getJobDataMap().put("type", "FULL");
      // 创建一个每周触发的Trigger，指明星期几几点几分执行
      Trigger trigger = TriggerUtils.makeWeeklyTrigger(3, 16, 38);
      trigger.setGroup("myTriggerGroup");
      // 从当前时间的下一秒开始执行
      trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
      // 指明trigger的name
      trigger.setName("myTrigger");
      // 用scheduler将JobDetail与Trigger关联在一起，开始调度任务
      sched.scheduleJob(jobDetail, trigger);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testSimpleTrigger() {
    Date sdt = new Date();

    Date edt = new Date(sdt.getTime() + 550000L);

    SimpleTrigger st = new SimpleTrigger("t", "g", "j", "g", sdt, edt, -1,
            10000l);
    //计算第一次触发时间
    st.computeFirstFireTime(null);
    System.err.println("now=" + sdt);
    System.err.println("end=" + edt);

    //最后的触发时间
    System.err.println("lastTime=" + st.getFinalFireTime());

    java.util.List times = TriggerUtils.computeFireTimes(st, null, 5);

    for (int i = 0; i < times.size(); i++) {
      System.err.println("firetime = " + times.get(i));
    }

    times = TriggerUtils.computeFireTimes(st, null, 5);

    for (int i = 0; i < times.size(); i++) {
      System.err.println("firetime = " + times.get(i));
    }
  }

  @Test
  public void testSimpleTrigger2() {
    Date sdt = new Date();

    Date edt = new Date(sdt.getTime() + 5500000L);

    SimpleTrigger st = new SimpleTrigger("t",
            "g",
            "j",
            "g",
            sdt,
            edt,
            -1,
            10000L);

    System.err.println("now=" + sdt);
    System.err.println("end=" + edt);

    //最后的触发时间
    System.err.println("lastTime=" + st.getFinalFireTime());

    Calendar calendar = new DailyCalendar("test", sdt.getTime() - 1, edt.getTime());
    calendar = null;
    //计算第一次触发时间
    st.computeFirstFireTime(calendar);
    System.out.println(st.getNextFireTime());
    st.triggered(calendar);
    System.out.println(st.getNextFireTime());
    st.triggered(calendar);
    System.out.println(st.getNextFireTime());
  }
}
