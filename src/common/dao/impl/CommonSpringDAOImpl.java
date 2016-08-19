package common.dao.impl;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.dao.CommonDAO;
import common.util.Page;

public class  CommonSpringDAOImpl<T> extends HibernateDaoSupport implements
		CommonDAO<T>
{
	private Class<T>	classz	= null;

	public CommonSpringDAOImpl(String className) throws InstantiationException, IllegalAccessException
	{

		try {
		
			classz = (Class<T>) Class.forName(className).newInstance().getClass();
		}
		catch (ClassNotFoundException e) {
			// TODO �iԶ���� catch ��
			e.printStackTrace();
		}
	}

	public CommonSpringDAOImpl(Class<T> classz)
	{

		this.classz = classz;
	}

//	public int myBulkUpdate(String hql, Object[] params)
//	{
//		// TODO �Զ���ɷ������
//		HibernateCallback callback=new HibernateCallback(){
//
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				// TODO Auto-generated method stub
//				ScrollableResults items= session.getNamedQuery("item").setCacheMode(CacheMode.IGNORE).scroll(ScrollMode.FORWARD_ONLY	);
//				int count=0;
//				while(items.next())
//				{
//				   Entity entity = (Entity); entities.get(0);;  
//				   entity.updateOperate(....);;  
//					if ( ++count % batchSize == 0);  
//					     {  
//					         session.flush();;  
//					         session.clear();;  
//					     } 
//				}
//				return null;
//			}
//			};
		//return this.getHibernateTemplate().bulkUpdate(hql, params);
	//}

	public int bulkUpdate(String hql, Object[] params)
	{
		// TODO �Զ���ɷ������
		
		return this.getHibernateTemplate().bulkUpdate(hql, params);
	}

	public int bulkUpdate(String hql, Object param)
	{
		// TODO �Զ���ɷ������
		return this.getHibernateTemplate().bulkUpdate(hql, param);
	}

	public void delete(T item)
	{
		// TODO �Զ���ɷ������
		this.getHibernateTemplate().delete(item);
		
	}

	public void deleteByPK(Serializable id)
	{
		// TODO �Զ���ɷ������
	T item	=(T) this.getHibernateTemplate().get(classz, id);
	this.getHibernateTemplate().delete(item);
		//this.getHibernateTemplate().
	}

	public Serializable insert(T item)
	{
		// TODO �Զ���ɷ������
		return this.getHibernateTemplate().save(item);
	}

	public List<T> select(String hql, Object[] params)
	{
		// TODO �Զ���ɷ������
		return this.getHibernateTemplate().find(hql, params);
	}
	
	public List<T> selectByIterator (final String hql, final Object[] params)
	{
		
		HibernateCallback callback =new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				List<T> rs = new ArrayList<T>();
				// TODO �Զ���ɷ������
				Query query = session.createQuery(hql);
				Iterator it = query.iterate();
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);

					}
				}
				while(it.hasNext()) {
					T emp = (T)it.next();
					rs.add(emp);
					//将对象从一级缓存中删除
					session.evict(emp);
				}
				return rs;

			}
			
		};
		return this.getHibernateTemplate().executeFind(callback);
		

	}
	
	public List<T> select(String hql, Object param)
	{
		// TODO �Զ���ɷ������
		return this.getHibernateTemplate().find(hql, param);
	}
	public T selectOne(final String hql, final Object[] params) {
		// TODO Auto-generated method stub
		HibernateCallback callback =new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);

					}
				}
				T	entity = (T) query.uniqueResult();
				return entity;
			}
			
		};
		return	(T) this.getHibernateTemplate().execute(callback);
	}

	public T selectOne(final String hql, final Object param) {
		HibernateCallback callback =new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				Query query = session.createQuery(hql);
				if (param != null) {
						query.setParameter(0, param);
				}
				T	entity = (T) query.uniqueResult();
				return entity;
			}
			
		};
		return	(T) this.getHibernateTemplate().execute(callback);
	}

	
	public List<T> selectAll()
	{
	
		
		//String queryString="from "+this.classz.getName()+" as t";
		return this.getHibernateTemplate().loadAll(this.classz);
	}

	public T selectByPk(Serializable id)
	{
		// TODO �Զ���ɷ������
		return (T) this.getHibernateTemplate().get(classz, id);
	}

	public int selectCount(final String hql, final Object[] params)
	{
		// TODO �Զ���ɷ������
		HibernateCallback callback =new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				Query query = session.createSQLQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);

					}
				}
				
				Integer	count = (Integer) query.uniqueResult();
				return count;
			}
			
		};
		return	(Integer) this.getHibernateTemplate().execute(callback);
	}

	public void blukFlushInsert(final List list)
	{
		HibernateCallback callback=new HibernateCallback(){
		
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO �Զ���ɷ������
				int i;
		
				
				for( i=1;i<=list.size();i++)
				{
					session.save(list.get(i-1));										
					if(i%20==0)
					{
						session.flush();
						session.clear();											
					}
				}	
				
				return new Integer(i);
				
			}
			
		};
		this.getHibernateTemplate().execute(callback);
	}
	
	public void blukDelete(final String	hql)
	{
		HibernateCallback callback=new HibernateCallback(){
		
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO �Զ���ɷ������
				
				return session.createQuery(hql).executeUpdate();//delete(hql, null);;
				//  null;
			}};
		this.getHibernateTemplate().execute(callback);
	}
	
	
	
	public List<T> selectWithPage(String hqlCount, final String hql, final Object[] params, final Page page)
	{
		// TODO �Զ���ɷ������
		int totalCount = this.selectCount(hqlCount, params);
		page.setTotalCount(totalCount);// ������м�ҳ
		HibernateCallback callback =new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{

				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);

					}
				}
				// ��ǰҳ�ĵ�һ�е��е����
				query.setFirstResult(page.getFirstResult());
				// ҳ�Ĵ�С
				query.setMaxResults(page.getPageSize());
			List<T>	itemlist = query.list();
				return itemlist;
			}
			
		};
		return this.getHibernateTemplate().executeFind(callback);
	}
	/**
	 * hql top
	 * @param hql
	 * @param topNum
	 * @param params
	 * @return
	 */
	public List<T> selectTop(final String hql,final int topNum,final Object[] params )
	{
		HibernateCallback callback =new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);

					}
				}
				query.setFirstResult(0);
				// ҳ�Ĵ�С
				query.setMaxResults(topNum);
				List<T>	itemlist = query.list();
				return itemlist;
			}
			
		};
		return this.getHibernateTemplate().executeFind(callback);
	}
	
	public void update(T item)
	{
		// TODO �Զ���ɷ������
		this.getHibernateTemplate().update(item);
	}

	public List<Object> selectField(String hql, Object[] params)
	{
		return this.getHibernateTemplate().find(hql, params);
	}

	public List<Object[]> selectBySql(final String  sql )
	{
		HibernateCallback callback=new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				
				Query query = session.createSQLQuery(sql);		
				
				List count =  query.list();
				return count;
			}
			
		};
		
		List<Object[]> dd= (List<Object[]>) this.getHibernateTemplate().execute(callback);		
		return dd;
	}
	

}
