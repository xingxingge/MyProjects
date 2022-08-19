package dizoo.std.dom;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class JdkDomTest {
  @Test
  public void test1()
      throws IOException, SAXException, ParserConfigurationException {
    File file = new File("./data/dom/Paper.xml");
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(file);

    NodeList nPaper = doc.getElementsByTagName("Paper");//两个paper
    for (int i = 0; i < nPaper.getLength(); i++) {
      Element element = (Element) nPaper.item(i);
      NodeList layer = element.getElementsByTagName("Layer");
      for (int j=0;j<layer.getLength();j++){
        Element e = (Element) layer.item(j);
        NodeList polygon = e.getElementsByTagName("Polygon");
        for (int k=0;k<polygon.getLength();k++){
          Element item =(Element) polygon.item(k);
          NodeList point = item.getElementsByTagName("point");
          for (int l = 0; l <  point.getLength(); l++) {
            Element le = (Element) point.item(l);
            System.out.println(le.getElementsByTagName("x").item(0).getFirstChild().getNodeValue());
            System.out.println(le.getElementsByTagName("y").item(0).getFirstChild().getNodeValue());

          }
        }
      }
    }

  }
}
