package common.util;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MailListTxtWrite {

	private String FILENAME="/conf/MailList.txt";
	private String fileName;
	public MailListTxtWrite() {
		fileName=getClass().getResource(FILENAME).getFile();
		System.out.println("fileName="+fileName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * write
	 * @param pContext
	 */
	public void write(String pContext) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append(pContext+";");
		try {
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(fileName));
			String context=sb.toString();
			byte[] b=(context).getBytes();
			bos.write(b, 0, b.length);
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MailListTxtWrite aa = new MailListTxtWrite();
		aa.write("xxxxx");
		
	}
}
