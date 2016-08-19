package common.web.filter;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class MyOSIV extends OpenSessionInViewFilter {

	@Override
	protected void closeSession(Session session, SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		session.flush();
		super.closeSession(session, sessionFactory);
	}

	@Override
	protected Session getSession(SessionFactory sessionFactory)
			throws DataAccessResourceFailureException {
		// TODO Auto-generated method stub
	     Session session = SessionFactoryUtils.getSession(sessionFactory, true);            
	            session.setFlushMode(FlushMode.AUTO);             
	        return session;    
		
		
		
	}

}
