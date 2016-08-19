package common.util;

public class StringsToInterger {
	public static Integer[] toTransForm(String[] values)
	{
		Integer[] results=new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			if(values[i].equals(""))
			{
				results[i]=0;
			}
			else
			{
				results[i]=Integer.parseInt(values[i]);
			}
		}
		return results;
	}
}
