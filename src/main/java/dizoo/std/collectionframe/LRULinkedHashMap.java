package dizoo.std.collectionframe;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hx on 16-11-5.
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1l;

  /**
   * 最大数据存储容量
   */
  private static final int LRU_MAX_CAPACITY = 1024;

  /**
   * 存储数据容量
   */
  private int capacity;

  /**
   * 默认构造方法
   */
  public LRULinkedHashMap() {
    super();
    capacity = LRU_MAX_CAPACITY;
  }

  /**
   * 带参数构造方法
   *
   * @param initialCapacity 容量
   * @param loadFactor      装载因子
   */
  public LRULinkedHashMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor, true);
    capacity = LRU_MAX_CAPACITY;
  }

  public LRULinkedHashMap(int initialCapacity, float loadFactor, int lruCapacity) {
    super(initialCapacity, loadFactor, true);
    this.capacity = lruCapacity;
  }

  /**
   * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
   */
  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    logger.warn("the eldest: " + eldest);
    if (size() > capacity) {
      logger.warn("remove:" + eldest.toString());
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    LinkedHashMap<Integer, Integer> map = new LRULinkedHashMap<>(16, 0.75f, 5);
    for (int i = 0; i < 10; i++) {
      map.put(i, i);
    }

  }

}
