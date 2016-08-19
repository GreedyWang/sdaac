package common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OrdinaryTxtReader  {

	private File file;
	
	public OrdinaryTxtReader(String fileName) {
		file = new File(fileName);
		// TODO Auto-generated constructor stub
	}


	
	public String getFirstRow()
	{

		 String firstRow="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			firstRow=br.readLine();//跳过第一行
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return firstRow;
	}
}
