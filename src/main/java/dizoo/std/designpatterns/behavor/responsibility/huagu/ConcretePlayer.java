package dizoo.std.designpatterns.behavor.responsibility.huagu;

/**
 * Created by hx on 16-9-13.
 */
public class ConcretePlayer extends Player {
  @Override
  public void handle() {
    System.out.println("my name is "+getName());
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (DrumBeaker.stopped){
      System.out.println(getName()+" goto drink...");
    }else {
      next();
    }

  }

  public ConcretePlayer(String name) {
    super(name);
  }
}
