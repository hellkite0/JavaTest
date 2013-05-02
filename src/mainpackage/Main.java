package mainpackage;

import mainpackage.Test;
import mainpackage.ThreadTest;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		
		Test cTest = new Test();
		
		ThreadTest cThread = new ThreadTest();
		cThread.start();
	}
}
