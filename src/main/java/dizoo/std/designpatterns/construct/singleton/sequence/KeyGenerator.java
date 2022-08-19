package dizoo.std.designpatterns.construct.singleton.sequence;


/**
 * Created by hx on 16-8-15.
 */
public class KeyGenerator {
  private static KeyGenerator keygen=new KeyGenerator();
  private KeyGenerator(){
  }
  private  int key=100;
  public static KeyGenerator getInstance(){
    return keygen;
  }
  public synchronized int  getNextKey(){
    return key++;
  }

  public static void main(String[] args) {
    KeyGenerator keyGenerator = KeyGenerator.getInstance();
    System.out.println(keyGenerator.getNextKey());
    System.out.println(keyGenerator.getNextKey());
    System.out.println(keyGenerator.getNextKey());
  }
}
