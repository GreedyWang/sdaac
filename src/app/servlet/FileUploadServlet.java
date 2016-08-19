package app.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;



public class FileUploadServlet extends HttpServlet
{
    public void destroy()
    {
        super.destroy();
    }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	 
 
      
      
      
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
                  fi.write(new File(conPath + "//uploadFolder//" +fileName));
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
//


  public void init() throws ServletException
  {
      // Put your code here
  }
}
