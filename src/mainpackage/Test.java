package mainpackage;

public class Test<T> {
	public Test(T xValue)
	{
		xValue_ = xValue;
	}
		
	public T Get()
	{
		System.out.println(xValue_.getClass().getName());
		return xValue_;
	}
	
	private T xValue_;
}
