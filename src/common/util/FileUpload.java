package common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;

public class FileUpload {
	
	public static String upLoad(FormFile file,String filePath)
	{
		String fileDestiny=null;
		try {
			InputStream stream = file.getInputStream();//把文件读入
			//String filePath = request.getRealPath("/uploadFolder");//上传到指定的upload包中
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    fileDestiny=filePath + "/" +file.getFileName();
			OutputStream bos = new FileOutputStream(fileDestiny);//建立一个上传文件的输出流
			//System.out.println(filePath+"/"+file.getFileName());
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ( (bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);//将文件写入服务器
			}
			bos.close();
			stream.close();
			}catch(Exception e){
				System.err.println(e);
			}
		return fileDestiny;
	}
	
	public static String upLoad(InputStream in,String filePath)
	{
		//String fileDestiny=null;
		
		try {
			
			//InputStream stream = file.getInputStream();//把文件读入
			//String filePath = request.getRealPath("/uploadFolder");//上传到指定的upload包中
			//ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 //   fileDestiny=filePath ;//+ "/" +in.getFileName();
			OutputStream bos = new FileOutputStream(filePath);//建立一个上传文件的输出流
			//System.out.println(filePath+"/"+file.getFileName());
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ( (bytesRead = in.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);//将文件写入服务器
			}
			bos.close();
			in.close();
			}catch(Exception e){
				System.err.println(e);
			}
		return filePath;
	}
	
	
	public static String upLoad(FormFile file,String filePath,String newUrl) throws FileNotFoundException, IOException
	{
		String fileDestiny=null;

			InputStream stream = file.getInputStream();//把文件读入
			//String filePath = request.getRealPath("/uploadFolder");//上传到指定的upload包中
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    fileDestiny=filePath + "/" +newUrl+file.getFileName();
			OutputStream bos = new FileOutputStream(fileDestiny);//建立一个上传文件的输出流
			//System.out.println(filePath+"/"+file.getFileName());
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ( (bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);//将文件写入服务器
			}
			bos.close();
			stream.close();

		return fileDestiny;
	}
	
}
