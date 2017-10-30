package com.hx.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hx on 16-12-18.
 */
public class LongEventMain {
  public static void main(String[] args) throws InterruptedException {
    // Executor that will be used to construct new threads for consumers
    Executor executor = Executors.newCachedThreadPool();
    // The factory for the event
    LongEventFactory factory = new LongEventFactory();
    // Specify the size of the ring buffer, must be power of 2.
    int bufferSize = 1024;
    // Construct the Disruptor
    Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
    // Connect the handler
    EventHandler<LongEvent> e1 = new LongEventHandler();
    EventHandler<LongEvent> e2 = new LongEventHandler();
    Map<Long,EventHandler> map = new LinkedHashMap<>();
    map.put(1l,e1);
    map.put(2l,e2);
    EventHandler[] eventHandlers = map.values().toArray(new EventHandler[map.size()]);
    disruptor.handleEventsWith(eventHandlers);
    // Start the Disruptor, starts all threads running
    disruptor.start();
    // Get the ring buffer from the Disruptor to be used for publishing.
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

    LongEventProducer producer = new LongEventProducer(ringBuffer);

    ByteBuffer bb = ByteBuffer.allocate(8);
    for (long l = 0; true; l++) {
      bb.putLong(0, l);
      producer.onData(bb);
      Thread.sleep(1000);
    }
  }
}
