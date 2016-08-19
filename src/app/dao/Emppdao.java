package app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import app.entity.Test;

import common.dao.impl.CommonSpringDAOImpl;
import common.util.Page;

public class Emppdao extends CommonSpringDAOImpl {

	public Emppdao(String className) throws InstantiationException, IllegalAccessException {
		super(className);
	}
	
	public List<Object[]> doSelectByPaging(final String  hql,String CountHql,final Page page,final Object[] params )
	{
		HibernateCallback callback=new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				
				Query query = session.createSQLQuery(hql);		
				query.setMaxResults(page.getPageSize());
				query.setFirstResult(page.getFirstResult());
				List count =  query.list();
				return count;
			}
			
		};
		
		List<Object[]> dd= (List<Object[]>) this.getHibernateTemplate().execute(callback);		
		int totalCount=this.selectCount(CountHql, params);
		page.setTotalCount(totalCount);
		return dd;
	}
	
	public List<Object[]> doSelectByCondtion(final String  hql )
	{
		HibernateCallback callback=new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				
				Query query = session.createSQLQuery(hql);		
				
				List count =  query.list();
				return count;
			}
			
		};
		
		List<Object[]> dd= (List<Object[]>) this.getHibernateTemplate().execute(callback);		
		return dd;
	}
}
