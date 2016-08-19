package sdaac.wym.app.Service.audit;
/**
 * no use
 */
import java.util.Date;
import java.util.List;
import java.util.Vector;

import common.dao.CommonDAO;

import sdaac.wym.app.entity.audit.WinAudi2;

public class AuditManager {
	
	private CommonDAO<WinAudi2> auditdao=null;
	
	/**
	 * 查询
	 */
	public List<WinAudi2> select(WinAudi2 param)
	{
		String hql="from WinAudi2 as ad where 1=1 ";
		Vector<Object> params=new Vector<Object>();
		if(param.getCategory()!=null&&!param.getCategory().equals("")&&!param.getCategory().equals("all"))
		{
			hql+="and category=? ";
			params.add(param.getCategory());
		} if(param.getComputer()!=null&&!param.getComputer().equals(""))
		{
			hql+="and computer=? ";
			params.add(param.getComputer());
		} if(param.getStart()!=null&&!param.getStart().equals(""))
		{
			hql+="and dateTimeDb > '"+param.getStart()+"'";
		
		} if(param.getEnd()!=null&&!param.getEnd().equals(""))
		{
			hql+="and dateTimeDb < '"+param.getEnd()+"'";
		}
		if(params.isEmpty())
		{
			return auditdao.select(hql, null);
		}else{
			return auditdao.select(hql, params.toArray());
		}
	}
	/**
	 * 查询所有员工
	 * @return
	 */
	public String[] selectAllUser(String computerName)
	{
		String sql="select  computer from winaudi2 where computer like '%"+computerName+"%' group by computer";
		List<Object[]> rs=auditdao.selectBySql(sql);
		Vector<String> params=new Vector<String>();
		for(Object item:rs)
		{			
			params.add(item.toString());
		}		
		return params.toArray(new String[0]);
	}
	/**
	 * 查询种类
	 * @param computerName
	 * @param category
	 * @return
	 */
	//select distinct(itemname) from winaudi2 where computer='8dgs32x' and Category = 'Groups and Users'
	public String[] selectItem(String computerName,String category)
	{
		String sql="select distinct(itemname) from winaudi2 where computer='"+computerName+"' and Category = '"+category+"'";
		List<Object[]> rs=auditdao.selectBySql(sql);
		Vector<Object> params=new Vector<Object>();
		for(Object item:rs)
		{
			params.add(item);
		}
		return params.toArray(new String[0]);
	}
	/**
	 * 查询结果
	 * @param computerName
	 * @param category
	 * @param itemName
	 * @return
	 */
	//select itemValue1+','+itemValue2 from winaudi2 where computer='8dgs32x' and Category = 'Groups and Users' and itemname='Account Expires'
	public String[] selectResult(String computerName,String category,String itemName)
	{
		String sql="select itemValue1+','+itemValue2 from winaudi2 where computer='"+computerName+"' and Category = '"+category+"' and itemname='"+itemName+"'"
				+" and recordId = (select max(recordId) from winaudi2 where computer='"+computerName+"' and Category = '"+category+"' and itemname='"+itemName+"')";
		List<Object[]> rs=auditdao.selectBySql(sql);
		Vector<Object> params=new Vector<Object>();
		for(Object item:rs)
		{
			params.add(item);
		}
		return params.toArray(new String[0]);
	}
	
	public CommonDAO<WinAudi2> getAuditdao() {
		return auditdao;
	}
	public void setAuditdao(CommonDAO<WinAudi2> auditdao) {
		this.auditdao = auditdao;
	}
}
