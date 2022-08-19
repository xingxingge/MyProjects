package dizoo.std.collectionframe;

import org.junit.Test;

import java.util.*;    

/**
 * Created by hx on 16-11-4.
 */
public class MapTest {

  @Test
  public void hashMapTest(){
    HashMap<Integer,Integer> h = new HashMap<>();
    for (int i = 0; i < 75 ; i++) {
      h.put(i,i);
    }
    HashMap<Integer,Integer> h2 = new HashMap<>(h);
    System.out.println(Integer.highestOneBit(100));
    System.out.println(Integer.highestOneBit(0));
    System.out.println(Integer.highestOneBit(-2147483648));



  }
  @Test
  public void hashMap2(){
    HashMap<Integer,Integer> h = new HashMap<>(3);
//    for (int i = 0; i < 75 ; i++) {
//      h.put(i,i);
//    }
//    h.get(10);
//    h.put(0b100,0);
//    h.put(0b1100,4);
//    h.put(0b11100,8);
//    h.put(0b111100,12);
    h.put(0,0);
    h.put(4,4);
    h.put(8,8);
    h.put(12,12);
    Set<Map.Entry<Integer, Integer>> entries = h.entrySet();
    Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
    while(iterator.hasNext()){
      Map.Entry<Integer, Integer> next = iterator.next();
      System.out.println(next);
    }

    Set<Integer> iis = h.keySet();
    Iterator<Integer> iterator1 = iis.iterator();
    while(iterator1.hasNext()){
      System.out.println(iterator1.next());
    }

    LinkedHashMap<Integer,Integer> l = new LinkedHashMap<>();
    l.put(1,1);
    l.put(2,2);
    l.put(2,33);
    l.put(3,3);
    l.put(4,4);
    l.put(5,5);

    Set<Map.Entry<Integer, Integer>> e = l.entrySet();
    Iterator<Map.Entry<Integer, Integer>> i = e.iterator();
    while(i.hasNext()){
      Map.Entry<Integer, Integer> next = i.next();
      System.out.println(next);
    }
  }

  @Test
  public void cloneTest(){
    HashMap<Integer,Integer>map = new HashMap<>();
    map.put(1,100000);
    map.put(50000,50000);
    HashMap<Integer,Integer> m1 =  (HashMap<Integer,Integer>)  map.clone();
    System.out.println(m1);

  }

  @Test
  public void removeTest() {
    Map<String, String> map = new HashMap<>();
    map.put("1", "1");
    map.put("2", "2");
    map.put("3", "3");
    for (Iterator<String> it = map.values().iterator(); it.hasNext(); ) {
      String str = it.next();
      map.remove("1");
    }
  }

  @Test
  public void remove2Test(){
    Map<String,String> map = new HashMap<>();
    map.put("1","1");
    map.put("2","2");
    map.put("3","3");
    for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
      String str = it.next();
      map.remove(str);
    }
  }

  @Test
  public void remove3Test(){
    Map<String,String> map = new HashMap<>();
    map.put("1","1");
    map.put("2","2");
    map.put("3","3");
    for (String str:map.keySet().toArray(new String[map.size()]) ) {
      map.remove(str);
    }
  }
}


