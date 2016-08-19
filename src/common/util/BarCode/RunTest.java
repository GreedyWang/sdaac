package common.util.BarCode;

 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RunTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		
		String   d="2004-01-01";   
		  DateFormat   format=new   SimpleDateFormat("yyyy-MM-dd");   
		  Date dd = new Date();
		try {
			dd = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		  Calendar   calendar=Calendar.getInstance();   
		  calendar.setTime(dd);   
		  calendar.add(Calendar.MONTH,-1);   
		  System.out.println(format.format(calendar.getTime()));
	}

}
