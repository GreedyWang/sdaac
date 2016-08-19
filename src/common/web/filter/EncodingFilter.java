package common.web.filter;

import java.io.IOException;

import javax.servlet.*;

public class EncodingFilter implements Filter {

    

    private String encoding = "UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        if(encoding!=null && !"".equals(encoding.trim()))
            this.encoding = encoding.trim();
       
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        System.out.println("==================="+"EncodingFilter!!!!!"+"====================");
        chain.doFilter(request, response);
    }

    public void destroy() {
        
    }

}
