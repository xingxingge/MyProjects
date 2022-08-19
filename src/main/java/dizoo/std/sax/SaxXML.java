package dizoo.std.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SaxXML {

  public static void main(String[] args) {
    File file = new File("./data/sax/People.xml");
    try {
      SAXParserFactory spf = SAXParserFactory.newInstance();
      SAXParser parser = spf.newSAXParser();
      SaxHandler handler = new SaxHandler("People");
      parser.parse(new FileInputStream(file), handler);

      List<People> peopleList = handler.getPeoples();
      for(People people : peopleList){
        System.out.println(people.getId()+"\t"+people.getName()+"\t"+people.getEnglishName()+"\t"+people.getAge());
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}

class SaxHandler extends DefaultHandler {
  private List<People> peoples = null;
  private People people;
  private String currentTag = null;
  private String currentValue = null;
  private String nodeName = null;

  public List<People> getPeoples() {
    return peoples;
  }

  public SaxHandler(String nodeName) {
    this.nodeName = nodeName;
  }

  @Override
  public void startDocument() throws SAXException {
    // TODO 当读到一个开始标签的时候，会触发这个方法
    super.startDocument();

    peoples = new ArrayList<People>();
  }

  @Override
  public void endDocument() throws SAXException {
    // TODO 自动生成的方法存根
    super.endDocument();
  }

  @Override
  public void startElement(String uri, String localName, String name,
      Attributes attributes) throws SAXException {
    // TODO 当遇到文档的开头的时候，调用这个方法
    super.startElement(uri, localName, name, attributes);

    if (name.equals(nodeName)) {
      people = new People();
    }
    if (attributes != null && people != null) {
      for (int i = 0; i < attributes.getLength(); i++) {
        if(attributes.getQName(i).equals("id")){
          people.setId(attributes.getValue(i));
        }
        else if(attributes.getQName(i).equals("en")){
          people.setEnglishName(attributes.getValue(i));
        }
      }
    }
    currentTag = name;
  }

  @Override
  public void characters(char[] ch, int start, int length)
      throws SAXException {
    // TODO 这个方法用来处理在XML文件中读到的内容
    super.characters(ch, start, length);

    if (currentTag != null && people != null) {
      currentValue = new String(ch, start, length);
      if (currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")) {
        if(currentTag.equals("Name")){
          people.setName(currentValue);
        }
        else if(currentTag.equals("Age")){
          people.setAge(currentValue);
        }
      }
    }
    currentTag = null;
    currentValue = null;
  }

  @Override
  public void endElement(String uri, String localName, String name)
      throws SAXException {
    // TODO 在遇到结束标签的时候，调用这个方法
    super.endElement(uri, localName, name);

    if (name.equals(nodeName)) {
      peoples.add(people);
    }
  }

}