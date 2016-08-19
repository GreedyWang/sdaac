package app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.security.biz.RoleFunctionBiz;

import common.entity.TroleFunction;

import app.biz.PostTypeBiz;
import app.entity.TpostType;

public class LoadApplication extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadApplication() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		PostTypeBiz ptbiz=  (PostTypeBiz) context.getBean("ptBiz");
		List<TpostType> list=ptbiz.doSelect();		
		Map<String , Float[]> map=new HashMap<String, Float[]>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getType(), new Float[]{list.get(i).getMidValue(),list.get(i).getThrity()});
		}		
		this.getServletContext().setAttribute("postType", map);
		//得到权限上下文
		RoleFunctionBiz rolefunBiz=(RoleFunctionBiz) context.getBean("roleFunctionBiz");
		List<TroleFunction> AuthorizationContext=rolefunBiz.doSelectAuthorizationContext();
		this.getServletContext().setAttribute("AuthorizationContext", AuthorizationContext);
		

//		javax.net.ssl.TrustManager[] trustAllCerts =new javax.net.ssl.TrustManager[1];
//				javax.net.ssl.TrustManager tm = new miTM();
//				trustAllCerts[0] = tm;
//				javax.net.ssl.SSLContext sc =javax.net.ssl.SSLContext.getInstance("SSL");
//				sc.init(null,trustAllCerts,null);
//				javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	}

}
