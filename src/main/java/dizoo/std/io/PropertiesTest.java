package dizoo.std.io;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Set;

/**
 * Created by hx on 16-1-24.
 */
public class PropertiesTest {

  @Test
  public void prosTest() throws Exception {
    Properties properties = new Properties();
    properties.setProperty("id", "100");
    properties.put("name", "abcd");
    Set<String> set = properties.stringPropertyNames();
    for (String str : set) {
      System.out.println(properties.getProperty(str));
    }
    properties.store(new FileWriter("./src/main/java/com/hx/io/props.txt"), "my");

    Properties pros = new Properties();
    pros.load(new FileReader("./src/main/java/com/hx/io/props.txt"));
    System.out.println(pros);

    pros=System.getProperties();
    Set<String> sets=pros.stringPropertyNames();
    for (String str : sets){
      System.out.println(str+"="+pros.getProperty(str));
    }
    System.out.println(pros.get("user.dir"));
    System.out.println(pros.getProperty("user.home"));
  }
}
