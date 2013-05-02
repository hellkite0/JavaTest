package mainpackage;

import java.io.ObjectInputStream.GetField;

import mainpackage.Test;
import mainpackage.ThreadTest;
import mainpackage.XMLParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
	
		String sPathName = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(sPathName);
		
		XMLParser cXml = new XMLParser();
		cXml.Load(sPathName + "00001.xml");
	}
}
