package dizoo.std.designpatterns.behavor.iterator;

/**
 * Created by hx on 16-9-12.
 */
public class IteratorImpl<E> implements Iterator<E> {
  private int index;
  private Aggregate aggregate;
  @Override
  public boolean hasNext() {
    return index<aggregate.getSize();
  }

  @Override
  public E next() {
    return (E) aggregate.get(index++);
  }

  public IteratorImpl(Aggregate aggregate) {
    this.aggregate = aggregate;
    this.index = 0;
  }
}
