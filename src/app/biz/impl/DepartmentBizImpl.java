package app.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;
import app.biz.DepartmentBiz;
import app.biz.vave.ProposalStateBiz;
import app.entity.Tdepartment;
import app.entity.Tempolyee;
import app.entity.Tproposal;
import app.entity.vave.Count;
import app.entity.vave.VaveProposalState;
import app.entity.vave.VaveProposalStateId;

public class DepartmentBizImpl implements DepartmentBiz {

	private CommonDAO<Tdepartment> departdao = null;

	public CommonDAO<Tdepartment> getDepartdao() {
		return departdao;
	}

	public void setDepartdao(CommonDAO<Tdepartment> departdao) {
		this.departdao = departdao;
	}

	public DepartmentBizImpl(CommonDAO<Tdepartment> dao) {
		this.departdao = dao;
	}

	public DepartmentBizImpl() {

	}

	/**
	 * 判断是否为部门经理
	 */
	public boolean isManager(String uid) {
		boolean flag = true;
		String hql = "from Tdepartment as pd where type='salary' and pmangerid=?";
		List reslut = departdao.select(hql, uid);
		if (reslut == null || reslut.isEmpty()) {
			flag = false;
		}
		return flag;
	}

	/**
	 * get All departmentName
	 */
	public Object[] getDepartmentName() {
		// TODO
		String hql = "select depart.name from Tdepartment as depart";
		return departdao.select(hql, null).toArray();
	}

	/**
	 * get the department's info
	 * 
	 * @param integer
	 *            departmentID
	 */
	public List<Tdepartment> getDepart(Integer id) {
		// TODO 自动生成方法存根
		if (id != null) {
			String hql = "from Tdepartment as depart where depart.id=?";
			return departdao.select(hql, id);
		} else {
			String hql = "from Tdepartment ";
			return departdao.select(hql, null);
		}
	}

	/**
	 * 得到所有管理部门
	 */
	public List<Tdepartment> getAll() {
		String hql="from Tdepartment where type='salary'";	
		return departdao.select(hql, null);
	}

	/**
	 * get departmentManager
	 * 
	 * @param departmentID
	 */
	public Tdepartment doSelectManagerByDepartID(Integer departmentID) {
		String hql = "from Tdepartment as dp where dp.id=?";
		List<Tdepartment> list = this.departdao.select(hql, departmentID);
		if (list != null && !list.isEmpty())
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * 查询部门vave管理者
	 */
	public String[] doSelectVaves(Integer departmentID) {
		String hql = "from Tdepartment as dp where dp.id=?";
		List<Tdepartment> list = this.departdao.select(hql, departmentID);
		List<String> targets = new ArrayList<String>();
		if (list != null && !list.isEmpty()) {

			if (list.get(0).getVaveUid() != null
					&& !list.get(0).getVaveUid().equals("")) {
				String[] vaves = list.get(0).getVaveUid().split(",");
				for (int i = 0; i < vaves.length; i++) {
					targets.add(vaves[i]);
				}
			}
//			if (list.get(0).getPmangerid() != null
//					&& !list.get(0).getPmangerid().equals("")) {
//				targets.add(list.get(0).getPmangerid());
//			}
		}
		return targets.toArray(new String[0]);
	}
	
	public List<Tempolyee> SelectAllPM() {
		String sql = " select distinct(uid),e.name,e.departmentid,dd.name as dname from tDepartment dd inner join tEmpolyee e on e.uid=dd.pmangerid ";
		sql += " where kpi_type = 'DT02'";
		List<Object[]> dd = this.departdao.selectBySql(sql);
		List<Tempolyee> empList = new ArrayList<Tempolyee>();
		for (Object[] element : dd) {
			if(element[0]!=null && !element[0].toString().equals("1")) {//除去VAVE团队
				Tempolyee item = new Tempolyee(element[0].toString());
				item.setName(element[1].toString());
				item.setTdepartment(new Tdepartment((Integer)element[2],element[3]+""));
				empList.add(item);
			}
		}	
		return empList;
	}	
	
	/**
	 * 按部门经理uid查询部门
	 * @param uid
	 */
	public List<Tdepartment> selectByManagerId(String uid)
	{
		String hql="from Tdepartment where pmangerid=?";
		return this.departdao.select(hql, uid);
	}
	/**
	 * 得到所有部门
	 * @return List<Tdepartment>
	 */
	public List<Tdepartment> getAll2() {
		String hql="from Tdepartment where type='salary'";	
		return departdao.select(hql, null);
		//return departdao.selectAll();
	}
	
}
