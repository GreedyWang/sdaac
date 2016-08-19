package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class MyUtil {
	public static Date formatDate(String time)
	{
		Date date=null;
		if(null!=time&&!time.equals(""))
		{
			 SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM-dd");   
				try {
					date=sdf.parse(time);
				} catch (ParseException e) {
					// TODO �Զ���ￄ1�7 catch ��
					e.printStackTrace();
				} 
		}
	//	else
		//{
			//date=new Date();
//			Calendar calendar=Calendar.getInstance();
//			calendar.setTime(date);
//			date=calendar.getTime();
		//}
		return date;
	}
	public static Date formatDateNoDay(String time)
	{
		Date date=null;
		if(null!=time&&!time.equals(""))
		{
			 SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM");   
				try {
					date=sdf.parse(time);
				} catch (ParseException e) {
					// TODO �Զ���ￄ1�7 catch ��
					e.printStackTrace();
				} 
		}

		return date;
	}
	/**
	 * 得到当前月份
	 * @return
	 */
	public static String getMonth()
	{
		/**
		Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(1));
        String month = String.valueOf(calendar.get(2) + 1);
        if(month.length() == 1)
            month = "0" + month;
        String date = String.valueOf(calendar.get(5));
        if(date.length() == 1)
            date = "0" + date;
        String hour = String.valueOf(calendar.get(11));
        if(hour.length() == 1)
            hour = "0" + hour;
        String minute = String.valueOf(calendar.get(12));
        if(minute.length() == 1)
            minute = "0" + minute;
        String second = String.valueOf(calendar.get(13));
        if(second.length() == 1)
            second = "0" + second;
          System.out.println(year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second);
		*/	
		Date date=new Date();
		SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM");   			
		return sdf.format(date);
	}

	
	public static Float[] toTransFormFloat(String[] values)
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

	public static float[] toTransFormfloat(String[] values)
	{
		if(values==null)
		{
			return null;
		}
		else
		{
			float[] results=new float[values.length];
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
	/**
	 * 将带%符号的字符串转化为float
	 * @param values
	 * @return
	 */
	public static Float[] toTransFormFloatWithPencentage(String[] values)
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
				}else if(values[i].endsWith("%")){
					results[i]=Float.parseFloat(values[i].substring(0,values[i].length()-1))/100;
				}else {
					results[i]=Float.parseFloat(values[i]);
				}
				
			}
			return results;
		}

	}
	
	public static Integer[] toTransFormInteger(String[] values)
	{
		if(values!=null)
		{
			Integer[] results=new Integer[values.length];
			for (int i = 0; i < values.length; i++) {
				if(values[i].equals(""))
				{
					results[i]=0;
				}
				else
				{
					
						results[i] = Integer.parseInt(values[i]);
					
				}
			}
			return results;
		}
		else
		{
			return null;
		}
	}
	
	public static int[] toTransFormInt(String[] values)
	{
		if(values!=null)
		{
			int[] results=new int[values.length];
			for (int i = 0; i < values.length; i++) {
				if(values[i].equals(""))
				{
					results[i]=0;
				}
				else
				{
					
						results[i] = Integer.parseInt(values[i]);
					
				}
			}
			return results;
		}
		else
		{
			return null;
		}
	}
}
