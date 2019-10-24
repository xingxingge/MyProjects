package com.hx.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

public class EntityTranslator
    implements EventTranslatorOneArg<LongEvent, Long> {

  @Override
  public void translateTo(LongEvent entityWrapper, long l, Long o) {
    entityWrapper.setValue(o);
  }
}
