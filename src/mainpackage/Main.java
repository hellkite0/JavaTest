package mainpackage;

import XMLParser.*;

import mainpackage.Test;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
	
		String sPathName = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(sPathName);
		
		XMLNode cXML = new XMLNode();
		cXML.Load("./bin/03509.xml");
		
		XMLNode cCostNode = cXML.GetChild("cost");
		if (cCostNode == null)
			System.out.println("null");
		else
			System.out.println(cCostNode.GetName());
		
		XMLNode cGoldNode = cCostNode.GetChild("gold");
		int[] iValues = new int[2];
		//Integer[] iValues = new Integer[2];
		System.out.println(iValues.length);
		cGoldNode.GetValues(iValues);
		System.out.println(iValues[0] + " " + iValues[1]);
		System.out.println(cGoldNode.GetAttribute("test"));
		
		XMLNodeList cItemsNodeList = cCostNode.GetChild("items").GetChildList("item");
		
		for (int i = 0; i < cItemsNodeList.GetSize(); ++i)
		{
			XMLNode cNode = cItemsNodeList.Get(i);
			
			System.out.println(cNode.GetAttribute("minor"));
		}
	}
}
