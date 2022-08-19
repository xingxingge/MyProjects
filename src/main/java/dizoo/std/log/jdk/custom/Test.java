package dizoo.std.log.jdk.custom;

/**
 * Created by hx on 17-1-7.
 */
import junit.framework.TestCase;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Test  extends TestCase{

  public void testDefault(){
    Logger logger=Logger.getLogger(Test.class.getName());//使用默认的配置

    logger.setFilter(new Filter() {
      @Override
      public boolean isLoggable(LogRecord record) {
        return true;
      }
    });
    logger.info(" test!");

  }
  public  void testMy()  throws SecurityException, IOException {
      MyLogManager.resetFromPropertyFile("log/jdk/logging.properties");
    Logger logger=MyLogManager.getLogger(Test.class.getName());
    logger.setFilter(new Filter() {
      @Override
      public boolean isLoggable(LogRecord record) {
        return true;
      }
    });
    logger.info(" test!");



  }
}
