package XMLParser;

public class DataParser {
	static void DataParse(String[] sStrArray, Integer[] iValues)
	{
		for (int i = 0; i < sStrArray.length; ++i)
		{
			iValues[i] = Integer.parseInt(sStrArray[i]);
		}
	}
	
	static void DataParse(String[] sStrArray, Float[] fValues)
	{
		for (int i = 0; i < sStrArray.length; ++i)
		{
			fValues[i] = Float.parseFloat(sStrArray[i]);
		}
	}
	
	static void DataParse(String[] sStrArray, String[] sValues)
	{
		for (int i = 0; i < sStrArray.length; ++i)
		{
			sValues[i] = sStrArray[i];
		}
	}
}
