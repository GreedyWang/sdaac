package common.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import sdaac.wym.app.entity.News.CatagoryItem;
/**
 * 
 * @author SA1KV5
 * 1:SAXBuilder.build(FileInputStream("*.xml");获取xml文件，返回Document实例（读xml文件)
	2:Element.getChildren();获取该节点的所有字节点，返回List
	3:Element.getChild("child节点名");获取字节点实例
	4:Element.getAttribute("属性名");获取该节点属性的value值（平面式节点)
	5:Element.getText();获取该节点的节点文本
	6:Document(new Element("根节点名"));新建xml文件文档
	7:Document.getRootNote();获取根节点
	8:Element.addContent(Element);添加子节点
	9:Element.setAttribute("属性名","属性值");添加节点属性
	10:Element.setText("文本值");添加该节点的文本值
	11:xmloutPutter(Format.getPrettyFormat())
	12:xmlOutPutter.output(Document,FileOutPutStream);这两句用来输出xml文件，其中Document为填好内容的xml文档对象，FileoutPutStream为文本输出流）
 *
 */
public class XMLManager {

	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/** 将document中的内容写入文件中 */
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			/** 编码 */
			// transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}
	/**
	 * 读取文件
	 * @param filename
	 * @return
	 */
	public static Document load(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 演示修改文件的具体某个节点的值
	 */
	public static void xmlUpdateDemo(String dataUrl,CatagoryItem item) {
		Document document = load(dataUrl);
		Node root = document.getDocumentElement();
		/** 如果root有子元素 */
		if (root.hasChildNodes()) {
			/** ftpnodes */
			NodeList ftpnodes = root.getChildNodes();
			/** 循环取得ftp所有节点 */
			for (int i = 0; i < ftpnodes.getLength(); i++) {
				NodeList ftplist = ftpnodes.item(i).getChildNodes();
				for (int k = 0; k < ftplist.getLength(); k++) {
					Node subnode = ftplist.item(k);
				
				   NamedNodeMap attributes=  subnode.getAttributes();
				 //  NamedNodeMap attributes=fatherNode.getAttributes();
				   if(attributes!=null)
				   {
				        for(int j=0;j<attributes.getLength();j++)
				        {
				            Node attribute=attributes.item(j);
				            //得到属性名
				            String attributeName=attribute.getNodeName();
				            System.out.println("属性名:"+attributeName);
				            //得到属性值
				            String attributeValue=attribute.getNodeValue();
				            System.out.println("属性值:"+attributeValue);
				        }
				   }
				}

			}
		}

		doc2XmlFile(document, dataUrl);
	}

	public static void main(String args[]) throws Exception {
		XMLManager.xmlUpdateDemo("d://IT.xml",null);
	}
}