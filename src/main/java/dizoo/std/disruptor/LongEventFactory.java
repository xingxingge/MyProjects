package dizoo.std.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by hx on 16-12-18.
 */
public class LongEventFactory implements EventFactory<LongEvent> {

  @Override
  public LongEvent newInstance() {
    return new LongEvent();
  }
}
