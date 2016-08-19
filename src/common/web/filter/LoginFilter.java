package common.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.entity.Tuser;

public class LoginFilter extends BaseFilter implements Filter {

	@Override
	public void init(FilterConfig conf) throws ServletException {
		// TODO Auto-generated method stub
		super.init(conf);
		noLimit = conf.getInitParameter("noLimit");
	}

	private String noLimit;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO �Զ���ɷ������
		// super.doFilter(request, response, filterChain);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding("utf-8");
		String path = httpRequest.getServletPath();
		String exactPath=path;
		if(httpRequest.getParameter("actionType")!=null){
			 exactPath=path+"?actionType="+httpRequest.getParameter("actionType");
		}
		
		boolean flag = false;
//		boolean flag = true;
		for (String url : noLimit.split(";")) {
			if (exactPath.equalsIgnoreCase(url)){
//				exactPath.matches(url)
				flag = true;
			}
		}
		if (path.equalsIgnoreCase("/login.do") || flag) {
			System.out.println("=============needn't safe=================");
			filterChain.doFilter(request, response);
		} else {
			if (httpRequest.getSession().getAttribute("logineduser") != null) {
				filterChain.doFilter(request, response);
			} else {
				System.out.println("=============need safe=================");
				httpRequest.getSession().invalidate();
				PrintWriter out = response.getWriter();
				out.println("<script language=javascript>");
				out.println("alert('time out! please login again!')");
				out.println("</script>");
				out.println("<html><head></head>");
				out.println("<Script LANGUAGE='javascript'> window.location.href='/bpp/welcome/Login.jsp';</script>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>please login first</h1>");
				out.println("</body></html>");
			}
		}
	}
}
