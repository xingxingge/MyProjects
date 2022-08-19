package dizoo.std.scheduler.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {

  public void execute(JobExecutionContext context) throws JobExecutionException {
    //打印当前的时间
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date date = new Date();
    System.out.println("current exec time is :"+sf.format(date));
  }


}
