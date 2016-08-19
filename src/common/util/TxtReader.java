package common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import sdaac.wym.app.Service.cw.TaxReader;
import sdaac.wym.app.entity.cw.Tax;
import sdaac.wym.app.entity.cw.TaxItem;

public abstract class TxtReader {
	
	private File file;
	private InputStream is;
	private	String firstRow;
	protected List<String[]> texts;
	public TxtReader(String fileName)
	{
		file=new File(fileName);
		
	}
	
	public TxtReader(InputStream pIs)
	{
		is=pIs;
		
	}
	
	abstract protected void dealHandler(String resource);

	
	public List<String[]> getContext()
	{
		 texts=new ArrayList<String[]>();
		 String text;
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			firstRow=br.readLine();//跳过第一行
			while((text=br.readLine())!=null) 
			{
				dealHandler(text);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return texts;
	}	

	public String getFirstRow() {
		return firstRow;
	}


}
