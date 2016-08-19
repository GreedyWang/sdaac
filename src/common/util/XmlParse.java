package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import sdaac.wym.app.entity.News.CatagoryItem;

public class XmlParse {
	private CatagoryItem item;
	private Element result;
	private SAXBuilder builder;
	private InputStream file;
	private Document doc;
	private Element root;
	private String value;

	public XmlParse() {
	}

	public XmlParse(CatagoryItem param) throws JDOMException, IOException {
		item = param;
		builder = new SAXBuilder();
		file = new FileInputStream(item.getFileName());
		doc = builder.build(file);
		root = doc.getRootElement();
	}

	private void XmlParse(Element proot) {
		Element root = proot;
		List<Element> list = root.getChildren();
		search(list);
	}

	private void search(List<Element> list) {
		for (Element e : list) {
			System.out.println(e.getAttributeValue("id"));
			if (e.getAttribute("id") != null
					&& e.getAttributeValue("id").equals(value)) {
				System.out.println("ok");
				result = e;
				continue;
			}
			if (e.getChildren() != null && !e.getChildren().isEmpty()) {
				search(e.getChildren());
			}
		}
	}

	public void add() throws IOException {
		value = item.getPid();
		XmlParse(root);
		Element element = new Element("categroy");
		element.setAttribute("id", item.getId());
		element.setAttribute("name", item.getName());
		element.setAttribute("url", item.getUrl());
		element.setText(item.getIntroduce());

		if (result != null) {
			element.removeAttribute(new Attribute("menu", "y"));
			if (result.getAttributeValue("menu") == null) {
				result.setText("");
			}
			result.addContent(element);

			result.setAttribute(new Attribute("menu", "y"));

		} else {
			root.addContent(element);
		}
		doc.setRootElement(root);

		XMLOutputter out = new XMLOutputter();
		out.output(doc, new FileOutputStream(item.getFileName()));
	}

	public void delete() throws JDOMException, IOException {
		//delete2(root.getChildren());
		value = item.getId();
		XmlParse(root);
		if(result!=null){
			result.getParentElement().removeContent(result);
			XMLOutputter out = new XMLOutputter();
			out.output(doc, new FileOutputStream(item.getFileName()));
		}
	}

//	private void delete2(List<Element> list) throws JDOMException, IOException {
//		for (Element e : list) {
//
//			if (e.getAttribute("id") != null
//					&& e.getAttributeValue("id").equals(item.getId())) {
//				e.getParentElement().removeContent(e);
//				XMLOutputter out = new XMLOutputter();
//				out.output(doc, new FileOutputStream(item.getFileName()));
//				break;
//			}
//			if (e.getChildren() != null && !e.getChildren().isEmpty()) {
//				delete2(e.getChildren());
//			}
//		}
//	}

	public void update() throws JDOMException, IOException {
		value = item.getId();
		List<Element> list = root.getChildren();
		search(list);
		if (result != null) {
			result.setAttribute("name", item.getName());
			if (item.getIsMenu() == null ) {
				result.setAttribute("url", item.getUrl());
				result.setText(item.getIntroduce());
			}
			XMLOutputter out = new XMLOutputter();
			out.output(doc, new FileOutputStream(item.getFileName()));
		}
	}

	public void setItem(CatagoryItem item) {
		this.item = item;
	}

}
