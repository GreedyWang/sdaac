package app.hr.DAO;
import java.util.ArrayList;
import java.util.List;

import app.entity.TcompanyIndex;
import app.entity.TindexTarget;
import common.dao.impl.CommonSpringDAOImpl;

public class DepartIndexDao extends CommonSpringDAOImpl<TcompanyIndex> implements IDepartIndexDao{

	public DepartIndexDao(String className) throws InstantiationException,
			IllegalAccessException {
		super(className);
		// TODO Auto-generated constructor stub
	}
	public void updateDpIndexsByindexId(TindexTarget item)
	{
		String hql="from TcompanyIndex as d inner join fetch d.tindexTarget as i where i.id=?";
		List<TcompanyIndex> rs= this.getHibernateTemplate().find(hql, item.getId());
		Float score=this.cal(item,rs.get(0).getAct_output());
		
		//Integer[] dpIndexIds=new Integer[rs.size()];
		String cc="";
		for(int i=0;i<rs.size();i++)
		{
			
			cc+=rs.get(i).getId()+",";
		}
		cc=cc.substring(0, cc.length()-1);
		String hql2="update TcompanyIndex set score="+score+" where id in ("+cc+") ";
		this.getHibernateTemplate().bulkUpdate(hql2,null);
	}
	public void changeUpdate(Integer dpIndexId,Float output, TindexTarget item) {
		// TODO Auto-generated method stub
		Float score=this.cal(item,output);
		String hql="update TcompanyIndex set score=? where id=? ";
		this.getHibernateTemplate().bulkUpdate(hql, new Object[]{score,dpIndexId});
	}
	private Float cal(TindexTarget item,Float output)
	{
		Float score=0f;
		if(!item.getType().equals("非量化指标"))
		{
			Float a=Float.parseFloat(item.getA());
			Float b=Float.parseFloat(item.getB());
			Float c=Float.parseFloat(item.getC());
			if(a>output)
			{
				return score;
			}
			if(output>=a&&output<b)
			{
				score=(30/(b-a))*output+(70-(30*a/(b-a)));
				return score;
			}
			if(output>=b&&output<c)
			{
				
				score=(50/(c-b))*output+(100-(50*b/(c-b)));
				return score;
			}
			if(output>=c)
			{
				score=150f;
				return score;
			}
		}
		return score;
	}
}
