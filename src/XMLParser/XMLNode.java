package XMLParser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import XMLParser.XMLNodeList;

public class XMLNode {
	public XMLNode() {}
	public XMLNode(XMLNode cNode)
	{
		cElement_ = (Element)cNode;
	}
	public XMLNode(Node cNode)
	{
		cElement_ = (Element)cNode;
	}
	
	public String GetName()
	{
		return cElement_.getNodeName();
	}
	
	public String GetAttribute(String sAttrName)
	{
		Attr cAttr = cElement_.getAttributeNode(sAttrName);
		return cElement_.getAttribute(sAttrName);		
	}
	
	public String GetStringValue()
	{
		return cElement_.getFirstChild().getNodeValue();
	}
	
	public int GetIntValue()
	{
		String sValue = cElement_.getFirstChild().getNodeValue();
		return Integer.parseInt(sValue);
	}
	
	public float GetFloatValue()
	{
		String sValue = cElement_.getFirstChild().getNodeValue();
		return Float.parseFloat(sValue);
	}
	
	public XMLNode GetChild(String sChildName)
	{
		NodeList cNodeList = cElement_.getElementsByTagName(sChildName);
		if (cNodeList.item(0) == null)
			return null;
		
		XMLNode cRetXML = new XMLNode(cNodeList.item(0));
		
		return cRetXML;
	}
	
	public XMLNodeList GetChildList(String sListName)
	{
		NodeList cNodeList = cElement_.getElementsByTagName(sListName);
		if (cNodeList.item(0) == null)
			return null;
		
		XMLNodeList cRetList = new XMLNodeList(cNodeList);
		
		return cRetList;
	}
	
	public void Load(String sFileName)
	{
		try
		{
			File cFile = new File(sFileName);
			DocumentBuilderFactory cFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder cBuilder = cFactory.newDocumentBuilder();
			Document cDoc = cBuilder.parse(cFile);
			cDoc.getDocumentElement().normalize();
			
			cElement_ = (Element)cDoc.getFirstChild();
			
			System.out.println("Root Element : " + cElement_.getNodeName());
		}
		catch(ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch(SAXException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private Element cElement_;
}
