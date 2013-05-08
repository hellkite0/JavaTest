package mainpackage;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import XMLParser.*;
import network_package.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		SetShutdownHooker();
		
		//FrameTest();
		SocketTest();
		//XMLTest();
	}
	
	public static void SetShutdownHooker()
	{
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			public void run()
			{
				System.out.println("System shutdown");
				System.gc();
			}
		}));
	}
	
	public static void FrameTest()
	{
		JFrame cFrame = new JFrame("Test Frame");
		cFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cFrame.setSize(800, 600);
		cFrame.setVisible(true);
		
		JPanel cPanel = new JPanel();
		cPanel.setLayout(new BorderLayout());
		
		JLabel cLabel = new JLabel("Test Text");
		cPanel.add(cLabel, BorderLayout.NORTH);
		
		cFrame.add(cPanel);
	}
	
	public static void SocketTest()
	{
		JSocket cSocket = new JSocket();
		if (!cSocket.Connect("localhost", 3001))
		{
			System.out.println("Connection failed.");
			System.exit(1);
		}
		
		String sInput;
		Scanner cScanner = new Scanner(System.in);
		do
		{
			sInput = cScanner.nextLine();
			cSocket.Write(sInput);
			cSocket.Send();
		} while(sInput.compareTo("exit") != 0);
		
		//cSocket.Write("test message");
		//cSocket.Write(10);
		//cSocket.Write('c');
		//cSocket.Send();
		
		cSocket.Close();
	}
	
	public static void XMLTest()
	{
		System.out.println("test");
		
		String sPathName = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(sPathName);
		
		XMLNode cXML = new XMLNode();
		//cXML.Load("./bin/03509.xml");
		cXML.ParseFromURI("ftp://192.168.1.86/03509.xml");
		
		XMLNode cCostNode = cXML.GetChild("cost");
		if (cCostNode == null)
			System.out.println("null");
		else
			System.out.println(cCostNode.GetName());
		
		XMLNode cGoldNode = cCostNode.GetChild("gold");
	
		Integer iValue = cGoldNode.GetValue().toInteger();
		System.out.println(iValue);
		
		String[] sStrings = cGoldNode.GetAttribute("test").toStringList();
		System.out.println(sStrings.length + sStrings[0] + sStrings[1] + sStrings[2]);
		
		XMLNodeList cItemsNodeList = cCostNode.GetChild("items").GetChildList("item");
		
		for (int i = 0; i < cItemsNodeList.GetSize(); ++i)
		{
			XMLNode cNode = cItemsNodeList.Get(i);			
			Integer iValue2 = cNode.GetAttribute("minor").toInteger();
			System.out.println(iValue2);
		}
	}
}
