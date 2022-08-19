package dizoo.std.xml;

import com.hx.base.io.DefaultResourceLoader;
import com.hx.base.io.Resource;
import com.hx.base.io.ResourceLoader;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

public class XMLParser {
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	@SuppressWarnings("unchecked")
	public static void parseXmlForSim(String xmlFile) throws Exception {
		SAXReader reader = new SAXReader();
		Resource resource = resourceLoader.getResource(xmlFile);
		InputStream input =resource.getInputStream();
		Document doc = reader.read(input);
		Element root = doc.getRootElement();

		Iterator<Element> iters = root.elementIterator();

		while (iters.hasNext()) {
			Element element = iters.next();
			// System.out.println(element.getName());

			// 根节点枚举属性
			for (Iterator<Attribute> ia = element.attributeIterator(); ia
					.hasNext();) {
				Attribute attribute = ia.next();
				System.out.println(attribute.getName() + "="
						+ attribute.getData());
			}
			// 枚举根节点的所有子节点：

			for (Iterator<Element> ieson = element.elementIterator(); ieson
					.hasNext();) {
				Element elementSon = ieson.next();
				// System.out.println(elementSon.getName() + ":" //节点名称
				// + elementSon.getText());
				// 在elementSon下继续枚举
				StringBuffer sb = new StringBuffer();
				for (Iterator<Element> iesonson = elementSon.elementIterator(); iesonson
						.hasNext();) {
					Element elementsonson = iesonson.next();

					String st = !(elementsonson.getText().equals(null) || elementsonson
							.getText().equals("")) ? elementsonson.getText()
							: "null";
					if (elementsonson.getName().equals("时间")) {
						st = "to_timestamp('" + st
								+ "','yyyy-mm-dd hh24:mi:ss.ff')";
					} else if (!st.equals("null")) {
						st = "'" + st + "'";
					}
					sb.append(st);
					sb.append(",");

				}
				String str = sb.toString();
				str = str.substring(0, str.lastIndexOf(",") - 1);
				if (!str.endsWith("null")) {
					str = str + "'";

				}

				System.out
						.println("insert into test_jcfx_sim_event(start_time,src_address,dest_address,app_protocol_name,protocol_name,request_method,file_name,referer,request_domain) values("
								+ str + ");");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void parseXmlForFlow(String xmlFile) throws Exception {
		SAXReader reader = new SAXReader();
		Resource resource = resourceLoader.getResource(xmlFile);
		InputStream input = resource.getInputStream();
		Document doc = reader.read(input);
		Element root = doc.getRootElement();

		Iterator<Element> iters = root.elementIterator();

		while (iters.hasNext()) {
			Element element = iters.next();

			for (Iterator<Element> ieson = element.elementIterator(); ieson
					.hasNext();) {
				Element elementSon = ieson.next();
				// System.out.println(elementSon.getName() + ":" //节点名称
				// + elementSon.getText());
				// 在elementSon下继续枚举
				StringBuffer sb = new StringBuffer();
				for (Iterator<Element> iesonson = elementSon.elementIterator(); iesonson
						.hasNext();) {
					Element elementsonson = iesonson.next();

					String st = !(elementsonson.getText().equals(null) || elementsonson
							.getText().equals("")) ? elementsonson.getText()
							: "null";
					if (elementsonson.getName().equals("会话开始时间")
							|| elementsonson.getName().equals("会话结束时间")) {
						st = "to_timestamp('" + st
								+ "','yyyy-mm-dd hh24:mi:ss.ff')";
					} else if (!st.equals("null") && !(elementsonson.getName().equals("源端口")
							|| elementsonson.getName().equals("目的端口")
							|| elementsonson.getName().equals("包数")
							|| elementsonson.getName().equals("字节数"))) {
						st = "'" + st + "'";
					} 
						
					sb.append(st);
					sb.append(",");

				}
				String str = sb.toString();
				str = str.substring(0, str.lastIndexOf(",") - 1);
				System.out
						.println("insert into test_jcfx_flow(start_time,end_time,app_protocol_name,src_address,dest_address,src_port,dest_port,packets,bytes) values("
								+ str + ");");
			}
		}

	}

	public static void main(String[] args) {
		try {
			// parseXmlForSim("./resultSetForSim.xml");
			parseXmlForFlow("./resultSetForFlow.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
