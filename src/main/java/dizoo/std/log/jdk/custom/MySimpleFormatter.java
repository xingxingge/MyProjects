package dizoo.std.log.jdk.custom;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class MySimpleFormatter extends SimpleFormatter {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  public synchronized String format(LogRecord record) {
    StringBuilder sb = new StringBuilder();
    Level level = record.getLevel();
    String time = sdf.format(new Date(record.getMillis()));
    String loggerName = record.getLoggerName();
    String message = record.getMessage();

    sb.append("[").append(level).append("]");
    sb.append(" ").append(time);
    sb.append(" ").append(loggerName);
    sb.append(" :").append(message);
    sb.append("\n");
    return sb.toString();
  }
}
