package sdaac.wym.app.Service.audit;

import java.util.List;
import java.util.Vector;

import common.dao.CommonDAO;

import sdaac.wym.app.entity.audit.WinAudi2;
import sdaac.wym.app.entity.audit.Winaudit;

public class PAuditBizImpl {

	public CommonDAO auditdao = null;
	private String tableName;

	public PAuditBizImpl(String tableName) {

		this.tableName = tableName;
	}

	public PAuditBizImpl() {

	}

	/**
	 * 查询
	 */
	public List<Winaudit> select(WinAudi2 param) {
		String hql = "from " + tableName + " as ad where 1=1 ";
		Vector<Object> params = new Vector<Object>();
		if (param.getCategory() != null && !param.getCategory().equals("")
				&& !param.getCategory().equals("all")) {
			hql += "and category=? ";
			params.add(param.getCategory());
		}
		if (param.getComputer() != null && !param.getComputer().equals("")&& !param.getComputer().equals("All")) {
			hql += "and computer=? ";
			params.add(param.getComputer());
		}
		if (param.getStart() != null && !param.getStart().equals("")) {
			hql += "and dateTimeDb > '" + param.getStart() + "'";

		}
		if (param.getEnd() != null && !param.getEnd().equals("")) {
			hql += "and dateTimeDb < '" + param.getEnd() + "'";
		}
		if (param.getItemName() != null && !param.getItemName().equals("")&& !param.getItemName().equals("All")) {
			hql += "and itemName = ? ";
			params.add(param.getItemName());
			
			if (param.getItemValue1() != null
					&& !param.getItemValue1().equals("")) {  //如果itemValue有值
				if(param.getOperator()!=null&&!param.equals("lager")&&!param.equals("small"))
				{
					hql += "and itemValue1 " + param.getOperator();
					if (param.getOperator().equals("like")) {    //如果是like操作
						hql += " '%" + param.getItemValue1() + "%'";
					} else {
						hql += "'" + param.getItemValue1() + "'";
					}
				}
			} 
		}

		if (params.isEmpty()) {
			return auditdao.select(hql, null);
		} else {
			return auditdao.select(hql, params.toArray());
		}
	}

	/**
	 * 查询所有员工
	 * 
	 * @return
	 */
	public String[] selectAllUser(String computerName) {
		String sql = "select  computer from " + tableName
				+ " where computer like '%" + computerName
				+ "%' group by computer";
		List<Object[]> rs = auditdao.selectBySql(sql);
		Vector<String> params = new Vector<String>();
		for (Object item : rs) {
			params.add(item.toString());
		}
		return params.toArray(new String[0]);
	}

	/**
	 * 查询种类
	 * 
	 * @param computerName
	 * @param category
	 * @return
	 */
	// select distinct(itemname) from winaudi2 where computer='8dgs32x' and
	// Category = 'Groups and Users'
	public String[] selectItem(String computerName, String category) {
		String sql = "select distinct(itemname) from " + tableName
				+ " where 1=1 ";
		if (computerName != null && !computerName.equals("")&& !computerName.equals("All")) {
			sql += " and computer='" + computerName + "'";
		}
		if (category != null && !category.equals("")) {
			sql += " and Category = '" + category + "'";
		}
		List<Object[]> rs = auditdao.selectBySql(sql);
		Vector<Object> params = new Vector<Object>();
		for (Object item : rs) {
			params.add(item);
		}
		return params.toArray(new String[0]);
	}

	/**
	 * 查询结果
	 * 
	 * @param computerName
	 * @param category
	 * @param itemName
	 * @return
	 */
	// select itemValue1+','+itemValue2 from winaudi2 where computer='8dgs32x'
	// and Category = 'Groups and Users' and itemname='Account Expires'
	public String[] selectResult(String computerName, String category,
			String itemName) {
		String sql = "select itemValue1+','+itemValue2 from "
				+ tableName
				+ " where computer='"
				+ computerName
				+ "' and Category = '"
				+ category
				+ "' and itemname='"
				+ itemName
				+ "'"
				+ " and recordId = (select max(recordId) from winaudi2 where computer='"
				+ computerName + "' and Category = '" + category
				+ "' and itemname='" + itemName + "')";
		List<Object[]> rs = auditdao.selectBySql(sql);
		Vector<Object> params = new Vector<Object>();
		for (Object item : rs) {
			params.add(item);
		}
		return params.toArray(new String[0]);
	}
	/**
	 * 模糊查找详细条目
	 * @param computerName
	 * @param category
	 * @param entry
	 * @return
	 */
	public String[] selectEntry(String computerName, String category,String entry) {
		String sql = "select distinct(itemname) from " + tableName
				+ " where 1=1 ";
		if (computerName != null && !computerName.equals("")&& !computerName.equals("All")) {
			sql += " and computer='" + computerName + "'";
		}
		if (category != null && !category.equals("")) {
			sql += " and Category = '" + category + "'";
		}
		if (entry != null && !entry.equals("")) {
			sql += " and itemName like '%" + entry + "%'";
		}
		List<Object[]> rs = auditdao.selectBySql(sql);
		Vector<Object> params = new Vector<Object>();
		for (Object item : rs) {
			params.add(item);
		}
		return params.toArray(new String[0]);
	}
	
	
	public void setAuditdao(CommonDAO auditdao) {
		this.auditdao = auditdao;
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public CommonDAO getAuditdao() {
		return auditdao;
	}

}
