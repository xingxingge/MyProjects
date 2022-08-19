package dizoo.std.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hx on 16-12-18.
 */
public class LongEventMain {
  protected static ThreadFactory buildThreadFactory() {
    return new ThreadFactory() {
      private volatile AtomicInteger counter = new AtomicInteger(0);

      public Thread newThread(Runnable r) {
        return new Thread(r,
            String.format("pool-%d", counter.getAndIncrement()));
      }
    };
  }

  public static void main(String[] args) throws InterruptedException {
    test2();
  }

  /**
   * 广播模式
   *
   * @throws InterruptedException
   */
  private static void test1() throws InterruptedException {
    // The factory for the event
    LongEventFactory factory = new LongEventFactory();
    // Specify the size of the ring buffer, must be power of 2.
    int bufferSize = 1024;
    // Construct the Disruptor
    Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize,
        buildThreadFactory());
    // Connect the handler
    EventHandler<LongEvent> e1 = new LongEventHandler();
    EventHandler<LongEvent> e2 = new LongEventHandler();
    EventHandler[] eventHandlers = new EventHandler[] { e1, e2 };
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

  /**
   * 单波模式与Translator写法
   *
   * @throws InterruptedException
   */
  private static void test2() throws InterruptedException {
    // The factory for the event
    LongEventFactory factory = new LongEventFactory();
    // Specify the size of the ring buffer, must be power of 2.
    int bufferSize = 1024;
    // Construct the Disruptor
    Disruptor<LongEvent> disruptor = new Disruptor(factory, bufferSize,
        buildThreadFactory(), ProducerType.SINGLE, new BusySpinWaitStrategy());
    // Connect the handler
    WorkHandler<LongEvent> e1 = new LongEventWorkHandler(0);
    WorkHandler<LongEvent> e2 = new LongEventWorkHandler(1);
    WorkHandler<LongEvent> e3 = new LongEventWorkHandler(2);
    WorkHandler[] eventHandlers = new WorkHandler[] { e1, e2, e3 };
    disruptor.handleEventsWithWorkerPool(eventHandlers);
    // Start the Disruptor, starts all threads running
    disruptor.start();
    // Get the ring buffer from the Disruptor to be used for publishing.
    final RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

    final EntityTranslator entityTranslator = new EntityTranslator();

    final AtomicLong l = new AtomicLong(0);
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        while (true) {
          ringBuffer.publishEvent(entityTranslator, l.getAndIncrement());
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
  }
}
