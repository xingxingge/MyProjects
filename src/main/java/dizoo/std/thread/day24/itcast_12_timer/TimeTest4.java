package dizoo.std.thread.day24.itcast_12_timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hx on 16-12-15.
 */
public class TimeTest4 {
  private final Timer timer = new Timer();
  private final int minutes;

  public TimeTest4(int minutes) {
    this.minutes = minutes;
  }

  public void start() {
    timer.schedule(new TimerTask() {
      public void run() {
        playSound();
        timer.cancel();
        TimeTest4 eggTimer = new TimeTest4(2);
        eggTimer.start();
      }

      private void playSound() {

      }
    }, minutes * 1 * 100);
  }

  public static void main(String[] args) {
    TimeTest4 eggTimer = new TimeTest4(1);
    eggTimer.start();
  }


}

