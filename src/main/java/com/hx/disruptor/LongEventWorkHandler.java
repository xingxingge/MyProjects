package com.hx.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by hx on 16-12-18.
 */
public class LongEventWorkHandler implements WorkHandler<LongEvent> {
  private int index;

  public LongEventWorkHandler(int index) {
    this.index = index;
  }

  @Override
  public void onEvent(LongEvent longEvent) throws Exception {
    if (index == 2) {
      Thread.sleep(15000);
    }
    System.out.println(Thread.currentThread().getName()+"--"+longEvent.getValue());
  }
}
