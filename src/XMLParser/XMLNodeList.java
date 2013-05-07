package XMLParser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
