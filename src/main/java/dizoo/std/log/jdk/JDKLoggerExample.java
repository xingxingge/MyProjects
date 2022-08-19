package dizoo.std.log.jdk;

import junit.framework.TestCase;
import sun.reflect.Reflection;

import java.util.logging.Logger;

public class JDKLoggerExample extends TestCase {


//  private static Logger logger = MyLoggerUtil.setLoggerHanlder(Logger.getLogger("com.hx.log.jdk"));

  public static void main(String[] args) {

//    logger.info("JDK Logsger is logging information at INFO Level");
  }

  public void test(){
    Logger log = Logger.getLogger("test");
    log.info("123");
    log.severe("456");
    log.fine("789");
    System.out.println(Reflection.getCallerClass());

  }

}
