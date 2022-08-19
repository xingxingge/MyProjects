package dizoo.std.log.jdk.custom;

import java.io.OutputStream;
import java.util.logging.StreamHandler;
public class MyStreamHandler extends StreamHandler {
  @Override
  public synchronized void setOutputStream(OutputStream out)
          throws SecurityException {
    super.setOutputStream(out);
  }
}
