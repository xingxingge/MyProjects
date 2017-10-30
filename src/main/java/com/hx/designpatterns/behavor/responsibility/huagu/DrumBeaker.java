package com.hx.designpatterns.behavor.responsibility.huagu;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hx on 16-9-13.
 */
public class DrumBeaker {
  public static boolean stopped=false;
  Timer timer;

  public static void main(String[] args) {
    Player p1 = new ConcretePlayer("p1");
    Player p2 = new ConcretePlayer("p2");
    Player p3 = new ConcretePlayer("p3");
    p1.setSuccessor(p2);
    p2.setSuccessor(p3);
    p3.setSuccessor(p1);
    DrumBeaker db = new DrumBeaker();
    db.start(3);
    p1.handle();


  }

  private void start(int seconds) {
    System.out.println("begin...");
    timer = new Timer();
    timer.schedule(new Stop(timer,Thread.currentThread()),seconds*1000);
    System.out.println("end");

  }

  private  class Stop extends TimerTask{
    private Thread thread;
    private Timer timer;

    public Stop(Timer timer,Thread thread) {
      this.timer=timer;
      this.thread = thread;
    }

    @Override
    public void run() {
//      DrumBeaker.stopped=true;
      if (thread.isAlive()) thread.stop(new RuntimeException("fff"));
      timer.cancel();
      while(true){
        try {
          Thread.sleep(1000);
          System.out.println(thread.isInterrupted());
          System.out.println(thread.isAlive());
          System.out.println("ok");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }


    }
  }


}
