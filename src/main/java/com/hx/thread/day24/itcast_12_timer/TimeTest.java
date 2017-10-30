package com.hx.thread.day24.itcast_12_timer;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hx on 15-10-24.
 */

class DeleteFolder extends TimerTask {
  private Timer timer;

  public DeleteFolder() {
  }

  public DeleteFolder(Timer timer) {
    this.timer = timer;
  }

  public void run() {
    File file = new File("/f/JavaHome/code/MyProjects/MyProjects/src/main/java/com/hx/thread/day24/itcast_12_timer/test");
//    deleteFolder(file);
    System.out.println("begin run...");
    System.out.println("ttt");
    timer.cancel();


  }

  private void deleteFolder(File file) {
    File[] files = file.listFiles();
    if (files != null) {
      for (File f : files) {
        if (f.isDirectory()) {
          deleteFolder(f);
        } else {
          f.delete();
        }
      }
    }
    file.delete();
  }
}

//在指定时间删除指定的目录
public class TimeTest {
  public static void main(String[] args) throws ParseException {
    Timer t = new Timer();
    String s = "2016-09-13 11:15:10";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sdf.parse(s);
    t.schedule(new DeleteFolder(t), date);
    System.out.println("gggg");
  }

}
