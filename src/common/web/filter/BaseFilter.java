package common.web.filter;

import java.io.IOException;

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

public class BaseFilter implements Filter {

	public void destroy() {
		// TODO 自动生成方法存根

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO 自动生成方法存根
		System.out.println("============run Safe Filter===========");
		
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		String contextpath=httpRequest.getContextPath();
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		HttpSession session=httpRequest.getSession(false);
		if(session==null)
		{
			httpResponse.sendRedirect(contextpath+"/accessError.jsp");
			return;
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成方法存根

	}

}
