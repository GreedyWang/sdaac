package common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DownLoad {
	 private final Log log=LogFactory.getLog(getClass());
	public void dLoad(HttpServletRequest request,HttpServletResponse response,String fileName )
	{
		response.setContentType("application/x-msdonload");      
		response.setCharacterEncoding("utf-8");
	
		File file=new File(fileName);
		int fileSize=(int)file.length();
		String DecodefileName="";
			try {
				DecodefileName = new String(file.getName().getBytes("GBK"),"ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   //URLEncoder.encode(fileName, "UTF-8"); 
		String str="attachment;filename="+DecodefileName;
		response.setHeader("Content-Disposition",str);       
         response.setContentLength(fileSize);
         //System.out.println(fileSize);
        // System.out.println(file.getName());
         try {    
             OutputStream os = response.getOutputStream();    
             FileInputStream fis = new FileInputStream(file);    
             BufferedInputStream bis = new BufferedInputStream(fis);    
             BufferedOutputStream bos = new BufferedOutputStream(os);    

             int r = 0;    
             byte[] ba = new byte[4096];    
             while ((r = bis.read(ba)) != -1) {    
                 bos.write(ba, 0, r);    
                 bos.flush();    
             }    
             bos.close();    
             bis.close();    
             fis.close();    
         } catch (Exception e) {    
             log.debug("OutputStream exception");    
         }   
	}
}
