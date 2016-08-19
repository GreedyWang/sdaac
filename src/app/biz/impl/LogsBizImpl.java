package app.biz.impl;

import app.entity.Logs;
import common.dao.CommonDAO;
import java.util.List;

public class LogsBizImpl {

	private CommonDAO<Logs> logdao=null;
	public List<Logs> doSelect(String beginTime,String endTime,String uid)
	{
		String hql=" from Logs where 1=1 ";
		if(beginTime!=null&&!beginTime.equals(""))
		{
			if(endTime!=null&&!endTime.equals(""))
			{
				hql+="datetime between '"+beginTime+"' and '"+endTime+"'" ;
			}
			else
			{
				hql+="datetime= '"+beginTime+"'";
			}
		}
		if(uid!=null&&!uid.equals(""))
		{
			hql+="uid=?";
		}
		String param=uid;
		return  logdao.select(hql, param);
	}
	public void doInsert(Logs item)
	{
		logdao.insert(item);
	}
//	public void doInsert(Logs item)
//	{
//		logdao.insert(item);
//	}
	public CommonDAO<Logs> getLogdao() {
		return logdao;
	}
	public void setLogdao(CommonDAO<Logs> logdao) {
		this.logdao = logdao;
	}
}
