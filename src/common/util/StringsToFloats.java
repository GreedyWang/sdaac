package common.util;

public class StringsToFloats {
public static Float[] toTransForm(String[] values)
{
	if(values==null)
	{
		return null;
	}
	else
	{
		Float[] results=new Float[values.length];
		for (int i = 0; i < values.length; i++) {
			if(values[i].equals(""))
			{
				results[i]=0f;
			}
			else
			{
				results[i]=Float.parseFloat(values[i]);
			}
			
		}
		return results;
	}

}
}
