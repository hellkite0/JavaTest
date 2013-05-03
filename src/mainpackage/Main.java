package mainpackage;

//import org.w3c.dom.Node;

import mainpackage.Test;
import mainpackage.ThreadTest;

import XMLParser.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
	
		String sPathName = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(sPathName);
		
//		XMLParser cXml = new XMLParser();
//		cXml.Load("./bin/03509.xml");
//		
//		Node cNode = cXml.GetNode("id");
//		System.out.println(cNode.getNodeValue());
		
		XMLNode cXML = new XMLNode();
		cXML.Load("./bin/03509.xml");
		
		XMLNode cCostNode = cXML.GetChild("cost");
		if (cCostNode == null)
			System.out.println("null");
		else
			System.out.println(cCostNode.GetName());
		
		XMLNode cGoldNode = cCostNode.GetChild("gold");
		int aValue = cGoldNode.GetIntValue();
		System.out.println(aValue);
		System.out.println(cGoldNode.GetAttribute("test"));
		
		XMLNodeList cItemsNodeList = cCostNode.GetChild("items").GetChildList("item");
		
		for (int i = 0; i < cItemsNodeList.GetSize(); ++i)
		{
			XMLNode cNode = cItemsNodeList.Get(i);
			
			System.out.println(cNode.GetAttribute("minor"));
		}
	}
}
