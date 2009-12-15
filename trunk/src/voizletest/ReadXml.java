package voizletest;

import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXml {

	public Hashtable<String, String> getResult(String query) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			Document dom = db.parse("http://api.voizle.com/?type=all&id="
					+ query);
			dom.getDocumentElement().normalize();

			NodeList nodeLst = dom.getElementsByTagName("voizle");
			if (nodeLst == null)
				return null;

			Hashtable<String, String> values = new Hashtable<String, String>();
			Node fstNode = nodeLst.item(0);
			values.put(fstNode.getChildNodes().item(0).getNodeName(), fstNode
					.getChildNodes().item(0).getFirstChild().getNodeValue());
			values.put(fstNode.getChildNodes().item(1).getNodeName(), fstNode
					.getChildNodes().item(1).getFirstChild().getNodeValue());
			values.put(fstNode.getChildNodes().item(2).getNodeName(), fstNode
					.getChildNodes().item(2).getFirstChild().getNodeValue());
			String title = fstNode.getChildNodes().item(3).getFirstChild()
					.getNodeValue();
			title = title.equals("Voizle - New Link") ? "Voizle - No Title"
					: title;

			values.put(fstNode.getChildNodes().item(3).getNodeName(), title);
			try {
				values.put(fstNode.getChildNodes().item(6).getNodeName(),
						fstNode.getChildNodes().item(6).getFirstChild()
								.getNodeValue());
				values.put(fstNode.getChildNodes().item(7).getNodeName(),
						fstNode.getChildNodes().item(7).getFirstChild()
								.getNodeValue());
			} catch (NullPointerException nex) {
				values.put(fstNode.getChildNodes().item(4).getNodeName(),
						fstNode.getChildNodes().item(4).getFirstChild()
								.getNodeValue());
				values.put(fstNode.getChildNodes().item(5).getNodeName(),
						fstNode.getChildNodes().item(5).getFirstChild()
								.getNodeValue());
			}

			return values;

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
}
