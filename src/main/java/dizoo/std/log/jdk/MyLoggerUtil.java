package dizoo.std.log.jdk;

/**
 * Created by hx on 17-1-6.
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLoggerUtil {

  private static final SimpleDateFormat sdf = new SimpleDateFormat(
          "yyyy-MM-dd");

  private static final String LOG_FOLDER_NAME = "MyLoggerFile";

  private static final String LOG_FILE_SUFFIX = ".log";

  private synchronized static String getLogFilePath() {
    StringBuffer logFilePath = new StringBuffer();
    logFilePath.append(System.getProperty("user.home"));
    logFilePath.append(File.separatorChar);
    logFilePath.append(LOG_FOLDER_NAME);

    File file = new File(logFilePath.toString());
    if (!file.exists())
      file.mkdir();

    logFilePath.append(File.separatorChar);
    logFilePath.append(sdf.format(new Date()));
    logFilePath.append(LOG_FILE_SUFFIX);

    return logFilePath.toString();
  }

  public synchronized static Logger setLoggerHanlder(Logger logger) {
    return setLoggerHanlder(logger, Level.ALL);
  }

  public synchronized static Logger setLoggerHanlder(Logger logger,
                                                     Level level) {

    FileHandler fileHandler = null;
    try {
      //文件日志内容标记为可追加
      fileHandler = new FileHandler(getLogFilePath(), true);

      //以文本的形式输出
      fileHandler.setFormatter(new SimpleFormatter());

      logger.addHandler(fileHandler);
      logger.setLevel(level);


    } catch (SecurityException e) {
      logger.severe(populateExceptionStackTrace(e));
    } catch (IOException e) {
      logger.severe(populateExceptionStackTrace(e));
    }
    return logger;
  }

  private synchronized static String populateExceptionStackTrace(Exception e) {
    StringBuilder sb = new StringBuilder();
    sb.append(e.toString()).append("\n");
    for (StackTraceElement elem : e.getStackTrace()) {
      sb.append("\tat ").append(elem).append("\n");
    }
    return sb.toString();
  }
}
