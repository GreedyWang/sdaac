package common.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.entity.Tuser;

public class SalaryFilter extends BaseFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// TODO �Զ���ɷ������
		super.doFilter(request, response, filterChain);
		HttpServletRequest httprequest=(HttpServletRequest) request;
		HttpServletResponse	httpresponse=(HttpServletResponse) response;
		Tuser user=(Tuser) httprequest.getSession().getAttribute("logineduser");
		System.out.println("================="+"DoSalaryFilter"+"=====================");
		
		if (user==null) 
			{httpresponse.sendRedirect(httprequest.getContextPath()+"/accessError.jsp");
			 return ;
			}
		else
		{
//			if(user.getRights()!=null&&user.getRights()>=2)
//			{
				filterChain.doFilter(request, response);
//			}
//			else
//			{
//		
//				httpresponse.sendRedirect(httprequest.getContextPath()+"/accessError.jsp");
//				return;
//			}
		}
	}
	
}
