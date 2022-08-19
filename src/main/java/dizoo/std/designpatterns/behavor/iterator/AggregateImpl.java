package dizoo.std.designpatterns.behavor.iterator;

/**
 * Created by hx on 16-9-12.
 */
public class AggregateImpl<E> extends Aggregate<E> {
  private E[] objects;
  public Iterator<E> createIterator(){
    return new IteratorImpl<>(this);
  }

  public AggregateImpl(E[] objects) {
    this.objects = objects;
  }

  public int getSize(){
    return objects.length;
  }

  @Override
  public E get(int i) {
    return objects[i];
  }

}
