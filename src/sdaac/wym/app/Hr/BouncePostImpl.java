package sdaac.wym.app.Hr;

import java.util.List;
import java.util.Vector;

import sdaac.wym.app.entity.hr.BouncePost;
import common.dao.CommonDAO;

public class BouncePostImpl {
	private CommonDAO<BouncePost> bpDao;
	
	public CommonDAO<BouncePost> getBpDao() {
		return bpDao;
	}



	public void setBpDao(CommonDAO<BouncePost> bpDao) {
		this.bpDao = bpDao;
	}



	public List<BouncePost> selectAll(String zone)
	{
		String hql="from BouncePost as bp  where remark=? order by bp.postId";
		return this.bpDao.select(hql, zone);
	}

	/**
	 * 查询工序
	 */
	public List<BouncePost> select(BouncePost item)
	{
		String hql="from BouncePost as bp where 1=1 ";
		Vector<Object> param=new Vector<Object>();
		if(item.getPostId()!=null&&!"".equals(item.getPostId()))
		{
			hql+="and bp.postId=? ";
			param.add(item.getPostId());
		}
		if(item.getPostName()!=null&&!"".equals(item.getPostName()))
		{
			hql+="and bp.postName=? ";
			param.add(item.getPostName());
		}
		if(item.getRemark()!=null&&!"".equals(item.getRemark()))
		{
			hql+="and bp.remark=? ";
			param.add(item.getRemark());
		}
		hql+="order by bp.postName,bp.postId";
		return this.bpDao.select(hql, param.toArray());
	}

	/**
	 * 删除工序
	 */
	public void deleteBouncePost(BouncePost item )
	{
		//this.bpDao.deleteByPK(item.getPostId());
		update(item);
	}
	/**
	 * 更改工序
	 */
	public void update(BouncePost bp)
	{		
		String hql="update BouncePost bp set ";
		Vector<Object> param=new Vector<Object>();
		if(bp.getPostName()!=null)
		{
			param.add(bp.getPostName());
			hql+="postName=?,";
		}
		if(bp.getRemark()!=null)
		{
			param.add(bp.getRemark());
			hql+="deviceNum=?,";
		}
		if(bp.getDeviceNum()!=null)
		{
			param.add(bp.getDeviceNum());
			hql+="remark=?,";
		}
		hql=hql.substring(0,hql.length()-1);
		hql+=" where id=?";		
		param.add(bp.getId());
		this.bpDao.bulkUpdate(hql, param.toArray());
		//this.
	}
}
