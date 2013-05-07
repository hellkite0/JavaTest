package XMLParser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import XMLParser.XMLNodeList;
import XMLParser.DataParser;

public class XMLNode {
	
	private Element cElement_;
	
	public XMLNode() {}
	public XMLNode(XMLNode cNode)
	{
		cElement_ = (Element)cNode;
	}
	public XMLNode(Node cNode)
	{
		cElement_ = (Element)cNode;
	}
	
	public String GetName() { return cElement_.getNodeName(); }
	public XMLValue GetAttribute(String sAttrName) { return new XMLValue(cElement_.getAttribute(sAttrName)); }
	public XMLValue GetValue() { return new XMLValue(cElement_.getFirstChild().getNodeValue()); }

	
	@SuppressWarnings("unused")
	private String[] Tokenize()
	{
		String sValue = cElement_.getFirstChild().getNodeValue();
		String[] sStrArray;
		
		sStrArray = sValue.replaceAll("[\\n\\t ]", "").split(",");
		
		return sStrArray;
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
	
	public void Parse(String sXML)
	{
		try 
		{
			InputStream cInputStream = new ByteArrayInputStream(sXML.getBytes("UTF-8"));
			DocumentBuilderFactory cFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder cBuilder = cFactory.newDocumentBuilder();
			Document cDoc = cBuilder.parse(cInputStream);
			cDoc.getDocumentElement().normalize();
			
			cElement_ = (Element)cDoc.getFirstChild();
			
			System.out.println("Root Element : " + cElement_.getNodeName());
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void ParseFromURI(String sURI)
	{
		try 
		{
			DocumentBuilderFactory cFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder cBuilder = cFactory.newDocumentBuilder();
			Document cDoc = cBuilder.parse(sURI);
			cDoc.getDocumentElement().normalize();
			
			cElement_ = (Element)cDoc.getFirstChild();
			
			System.out.println("Root Element : " + cElement_.getNodeName());
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public class XMLValue
	 {
	 	private Object[] cObjects_;
	 	public XMLValue(Object cObj)
	 	{
	 		cObjects_ = (((String)cObj).replaceAll("[\\n\\t ]", "")).split(",");
	 	}
	 	
	 	public String	toString() { return (String)cObjects_[0]; }
	 	public Integer	toInteger() { return Integer.parseInt((String)cObjects_[0]); }
	 	public Float	toFloat() { return Float.parseFloat((String)cObjects_[0]); }
	 	
	 	public String[] toStringList()
	 	{
	 		String[] cList = new String[cObjects_.length];
	 		DataParser.DataParse((String[])cObjects_, cList);
	 		
	 		return cList;
	 	}
	 	
	 	public Integer[] toIntegerList()
	 	{
	 		Integer[] cList = new Integer[cObjects_.length];
	 		DataParser.DataParse((String[])cObjects_, cList);
	 		
	 		return cList;
	 	}
	 	
	 	public Float[] toFloatList()
	 	{
	 		Float[] cList = new Float[cObjects_.length];
	 		DataParser.DataParse((String[])cObjects_, cList);
	 		
	 		return cList;
	 	}
	 }
}

