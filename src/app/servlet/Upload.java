package app.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.biz.PersonalPBiz;
import app.entity.TemployeeProduct;

import common.util.CopyOfExcelReader;

public class Upload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Upload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	 //                 fileName = fileName.substring(fileName.lastIndexOf("\\"));
	                  String conPath = getServletContext().getRealPath("");
	                  String filePath=conPath + "//uploadFolder//" +fileName;
	                  fi.write(new File(filePath));
	//              	ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	                 // String fileNamePath=request.getParameter("fileNamePath");
	//              	PersonalPBiz ppbiz= (PersonalPBiz) context.getBean("ppBiz");
//	          		try {
//	          			CopyOfExcelReader excelReader=new CopyOfExcelReader(filePath);
//	          			List<TemployeeProduct> list=excelReader.getList();
//	          			ppbiz.FlushInsert(list);
//	          		} catch (IOException e) {
//	          			// TODO 自动生成 catch 块
//	          			e.printStackTrace();
//	          		} catch (Exception e) {
//	          			// TODO 自动生成 catch 块
//	          			e.printStackTrace();
//	          		}
	                  
	              }
	              else { //如果是请求参数，则保存到HashMap中
	                  String name = fi.getFieldName();
	                  String value = fi.getString();
	                  parameters.put(name, value);
	                  System.out.println(parameters);
	              }
	          }

	     }
	     catch(Exception ex) {
	         System.out.println("上传文件时出错!!!");
	         ex.printStackTrace();
	     }

	    }

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
