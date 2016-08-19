package app.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpLoad extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Map parameters = new HashMap();
	     try {
	         FileItemFactory factory = new DiskFileItemFactory();
	         ServletFileUpload fu = new ServletFileUpload(factory);
	      
	         fu.setSizeMax(4194304);

	         // 得到所有的文件，以及其它请求参数：
	          List fileItems = fu.parseRequest(request);
	          Iterator i = fileItems.iterator();
	          // 依次处理每一个文件，以及请求参数：
	          while(i.hasNext()) {
	              FileItem fi = (FileItem)i.next();

	              // 如果是文件项，则保存文件到上传目录
	              if (!fi.isFormField()) {
	                  // 获得文件名，这个文件名包括路径：
	                  String fileName = fi.getName();

	                  long filesize = fi.getSize();
	                  System.out.println("上传的文件名为!!!"+fileName + " size : " + filesize);

	                  // 写入文件
	                  fileName = fileName.substring(fileName.lastIndexOf("\\"));
	                  String conPath = getServletContext().getRealPath("");
	                  String filePath=conPath + "//uploadFolder//" +fileName;
	                  fi.write(new File(filePath));
	              	}
	          }
	     }
	      catch(Exception ex) {
	         System.out.println("上传文件时出错!!!");
	         ex.printStackTrace();
	     }
	}
}
