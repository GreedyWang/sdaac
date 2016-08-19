package app.hr.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.entity.TcompanyIndex;
import app.entity.TindexTarget;
import common.dao.impl.CommonSpringDAOImpl;

public class IndexDao extends CommonSpringDAOImpl<TindexTarget>{



	public IndexDao(String className) throws InstantiationException,
			IllegalAccessException {
		super(className);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询最新的指标版本
	 * @return MaxVersion
	 */
	public String selectMaxVersionIndex()
	{
		String version=null;
		String hhql="select max(version) from TindexTarget";
		List list=this.getHibernateTemplate().find(hhql, null);//zui da version
		if(list!=null&&!list.isEmpty()&&list.get(0)!=null)
		{
			version=list.get(0).toString();
		}
		return version;
	}
	
	public void createVersionBlukFlushInsert( List<TindexTarget> list)
	{
		//add the property version
		SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM");
		String version= sdf.format(new Date());
		for(TindexTarget elem: list)
		{
			elem.setVersion(version);	
		}
		super.blukFlushInsert(list);
	}
	
	public void hasVersionBlukFlushInsert( List<TindexTarget> list,String version)
	{
		
		for(TindexTarget elem: list)
		{
			elem.setVersion(version);	
		}
		super.blukFlushInsert(list);
	}
}
