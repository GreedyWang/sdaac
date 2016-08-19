package common.dao;

import java.io.Serializable;
import java.util.List;

import common.util.Page;

public interface CommonDAO<T> {
	public List<T> selectByIterator (final String hql, final Object[] params);
	
	public Serializable insert(T item);

	public void update(T item);

	public void delete(T item);

	public void deleteByPK(Serializable id);

	public List<T> selectAll();

	public T selectByPk(Serializable id);

	public List<T> select(String hql, Object[] params);

	public List<T> select(String hql, Object param);
	public T selectOne(String hql, Object[] params);

	public T selectOne(String hql, Object param);
	public List<Object> selectField(String hql, Object[] params); 
	public int bulkUpdate(String hql, Object[] params);

	public int bulkUpdate(String hql, Object param);

	public int selectCount(String hql, Object[] params);

	public List<T> selectWithPage(String hqlCount, String hql, Object[] params,
			Page page);
	public void blukFlushInsert(final List list);
	
	public List<Object[]> selectBySql(final String  sql );
	
	public void blukDelete(final String	hql);
	public List<T> selectTop(final String hql,final int topNum,final Object[] params );
}