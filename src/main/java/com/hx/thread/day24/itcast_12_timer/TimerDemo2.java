package com.hx.thread.day24.itcast_12_timer;

/**
 * Created by hx on 15-10-24.
 */

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时,或者重复做事情:
 * 依赖Timer:定时器
 * Timertask:任务
 */
public class TimerDemo2 {
  public static void main(String[] args) {
    //定时器对象
    Timer timer = new Timer();
    //3秒以后执行,如果不成功,每隔1秒再执行
//    timer.schedule(new MyTask(), 3000);
    //结束任务
    timer.schedule(new MyTask2(timer), 3000,1000);
  }

}

/*
定义一个任务,在内部关闭任务
 */
class MyTask2 extends TimerTask {
  private Timer timer;

  public MyTask2() {
  }

  public MyTask2(Timer timer){
    this.timer=timer;
  }
  @Override
  public void run() {
    System.out.println("beng....");
//    timer.cancel();
  }
}


