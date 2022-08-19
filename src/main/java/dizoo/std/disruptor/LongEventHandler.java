package dizoo.std.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by hx on 16-12-18.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
  @Override
  public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
    System.out.println(longEvent.getValue());
  }
}
