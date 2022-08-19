package dizoo.std.jvm;

import org.junit.Test;

import java.util.List;

/**
 * Created by hx on 16-2-16.
 */
public class HeapOOM {
  static class OOMObject{}

  @Test
  public void oomTest(){
    List<OOMObject> list = new java.util.ArrayList<>();
    while(true){
      list.add(new OOMObject());
    }

  }
}
