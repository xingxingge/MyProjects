package dizoo.std.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Demo001 {
  public static void main(String[] args) throws Exception {
    // 1、获取工厂实例
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    // 2、获取dom解析器
    DocumentBuilder db = dbf.newDocumentBuilder();
    // 3、解析xml文档，获取document对象(根节点)
    Document document = db.parse(new File("./data/dom/demo001.xml"));

    NodeList list = document.getElementsByTagName("student");

    for (int i = 0; i < list.getLength(); i++) {
      // 注意：使用的都是org.w3c.dom包下的
      Element element = (Element) list.item(i);
      // element.getElementsByTagName("name").item(0) 是 <name>小明</name>
      // getFirstChild().getNodeValue() 是获取 <name>小明</name>里面的小明
      String context = element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
      System.out.println(context);
      context = element.getElementsByTagName("age").item(0).getFirstChild().getNodeValue();
      System.out.println(context);
      context = element.getElementsByTagName("grade").item(0).getFirstChild().getNodeValue();
      System.out.println(context);
    }
  }
}