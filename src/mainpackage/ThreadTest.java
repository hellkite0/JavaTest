package mainpackage;

public class ThreadTest extends Thread
{
	public ThreadTest()
	{
		
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			System.out.println("thread test");
		}
	}
}
