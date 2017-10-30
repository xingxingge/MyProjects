package com.hx.thread.day24.itcast_12_timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hx on 16-12-15.
 */
public class TimeTest3 {
  private final Timer timer = new Timer();
  private final int minutes;

  public TimeTest3(int minutes) {
    this.minutes = minutes;
  }

  public void start() {
    timer.schedule(new Task() ,1l,minutes * 1 * 1000);
  }

  public static void main(String[] args) {
    TimeTest3 eggTimer = new TimeTest3(1);
    eggTimer.start();
  }


}

class Task extends   TimerTask{

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName());
    System.out.println(Thread.activeCount());
    System.out.println("Your egg is ready!");
    // Start a new thread to play a sound...

  }
}
