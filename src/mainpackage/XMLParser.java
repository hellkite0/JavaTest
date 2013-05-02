package mainpackage;

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

public class XMLParser {
	public XMLParser()
	{
		
	}
	
	public void Load(String sFileName)
	{
		try
		{
			File cFile = new File(sFileName);
			DocumentBuilderFactory cFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder cBuilder = cFactory.newDocumentBuilder();
			
			cDoc_ = cBuilder.parse(cFile);
			
			cDoc_.getDocumentElement().normalize();
			
			System.out.println("Root Element : " + cDoc_.getDocumentElement().getNodeName());
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
	
	public Node GetNode(String sNodeName)
	{
		NodeList cNodeList = cDoc_.getElementsByTagName(sNodeName);
		Node cNode = cNodeList.item(0);
		
		return cNode;
	}
	
	private Document cDoc_;
}
