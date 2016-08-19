package common.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent session ) {
		// TODO 自动生成方法存根
		System.out.println("Session New!");
		session.getSession().setMaxInactiveInterval(3600);
	
	}

	public void sessionDestroyed(HttpSessionEvent session) {
		// TODO 自动生成方法存根
			//session.getSession().
		//session.getSession().
	
		System.out.println("Session Close!");
	//	session.getSession().getServletContext().setAttribute("sessionCounter", this);
		
	}

}
