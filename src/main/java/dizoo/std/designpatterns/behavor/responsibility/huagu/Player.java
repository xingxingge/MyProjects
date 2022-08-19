package dizoo.std.designpatterns.behavor.responsibility.huagu;

/**
 * Created by hx on 16-9-13.
 */
public  abstract  class Player {
  public abstract  void handle();
  private Player successor;
  private String name;

  public Player(String name) {
    this.name = name;
  }

  public Player() {
  }

  public void next(){
    if (successor!=null){
      successor.handle();
    }else{
      System.out.println("pragram terminating");
      System.exit(0);
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Player getSuccessor() {
    return successor;
  }

  public void setSuccessor(Player successor) {
    this.successor = successor;
  }
}
