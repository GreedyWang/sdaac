package common.util.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.xml.sax.InputSource;

import app.entity.epor.PrPrForm;

import common.util.FileUpload;
import common.util.LoadProperties;

public class UploadFile {
	
	public String uFile(FormFile file,String filePath,String id){
		
		if(file.getFileSize()!=0){
			//创建文件夹
			File f = new File(filePath+"/"+id);
			f.mkdir(); 		
			filePath += "/"+id;
			FileUpload.upLoad(file, filePath);	
			return file.getFileName();
		}	
		return "";
	}
	
	public void uFiles(FormFile[] files,String filePath,PrPrForm form){
		for(FormFile file:files){
			String fileName = uFile(file,filePath,form.getId());
		}
	}
	
	public String uFileIdentify(FormFile file,String filePath,String id){		
		if(file.getFileSize()!=0){
			//创建文件夹
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			String nowTip = df.format(new Date());
			File f = new File(filePath+"/"+id);
			if(f.exists()) {
				File f2 = new File(filePath+"/"+id+"/"+nowTip);
				if(!f2.exists()) {
					f2.mkdir(); 			
				}
			}else {
				f.mkdir(); 
				File f2 = new File(filePath+"/"+id+"/"+nowTip);
				if(!f2.exists()) {
					f2.mkdir(); 			
				}
			}
			FileUpload.upLoad(file, filePath+id+"/"+nowTip);	
			return file.getFileName();
		}	
		return "";
	}
	
	public String uFileNoDir(FormFile file,String filePath){
		
		if(file.getFileSize()!=0){
			FileUpload.upLoad(file, filePath);	
			return file.getFileName();
		}	
		return "";
	}
	
	public String uFile(FormFile file,String filePath,String id,String newUrl){		
		if(file.getFileSize()!=0){
			File f = new File(filePath+"/"+id);
			f.mkdir(); 					
			filePath += "/"+id;
			try {
				FileUpload.upLoad(file, filePath,newUrl);
				return file.getFileName();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}	
		return "";
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param path
	 */
	public void getFile(HttpServletResponse response,String path){
		File file = new File(path);
		OutputStream out = null;
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			//new PATH 
			String newPath = "//10.243.75.22/Department/IT/PR_data/DontRemove/"+file.getParentFile().getName()+"/"+file.getName();
			file = new File(newPath);
		}

		try {
			in = new FileInputStream(file);
			//OutputStream out = new FileOutputStream(file);
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "utf-8"));
			response.addHeader("Content-Length", "" + file.length());					
			response.setContentType("application/octet-stream;charset=utf-8");
			out = response.getOutputStream();
			
			byte[] buffer = new byte[1024];
			while( (in.read(buffer))!=-1){
				out.write(buffer);
			}
			out.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				response.setContentType("application/octet-stream;charset=utf-8");
				response.getWriter().write("文件未找到："+path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				if(in != null) in.close();
				if(out != null) out.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
}
