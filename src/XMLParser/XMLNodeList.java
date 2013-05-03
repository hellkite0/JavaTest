package XMLParser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLNodeList {
	public XMLNodeList() {}
	public XMLNodeList(NodeList cNodeList)
	{
		cNodeList_ = cNodeList;
	}
	
	public int GetSize()
	{
		return cNodeList_.getLength();
	}
	
	public XMLNode Get(int i)
	{
		Node cNode = cNodeList_.item(i);
		
		XMLNode cRetNode = new XMLNode(cNode);
		
		return cRetNode;
	}
	
	private NodeList cNodeList_;
}
