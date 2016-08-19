package app.biz.vave.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.Vector;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import common.dao.CommonDAO;
import app.biz.EmpBiz;
import app.biz.vave.ProposalBiz;
import app.entity.Tempolyee;
import app.entity.Tproposal;
import app.entity.vave.Figure;
import app.entity.vave.PersonalCount;
import app.entity.vave.VaveProjectSchedule;
import app.entity.vave.VaveProposalMore;
import app.entity.vave.VaveProposalState;

public class ProposalBizImpl implements ProposalBiz {

	private CommonDAO<Tproposal> proporsaldao = null;

	public boolean doInsert(Tproposal item) {
		// TODO 自动生成方法存根
		boolean flag = true;
		if (item.getProposalPerson().getUid() == null
				|| item.getProposalPerson().getUid().equals("")) {
			item.setProposalPerson(null);
		}
		try {
			proporsaldao.insert(item);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean doFlushInsert(List<Tproposal> items) {
		boolean flag = true;
		try {
			this.proporsaldao.blukFlushInsert(items);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Tproposal doSelectByPK(String proposalID) {
		return this.proporsaldao.selectByPk(proposalID);
	}

	/**
	 * 查询条件比对
	 * 
	 * @param hql
	 * @param item
	 * @param uid
	 * @return
	 */
	private List<Tproposal> details(String hql, Tproposal item, String uid) {
		hql += " where state>=0 ";
		Vector<Object> params = new Vector<Object>();
		// 查询提案人的部门
		if (item.getProposalPerson().getTdepartment().getId() != null
				&& item.getProposalPerson().getTdepartment().getId() != 0) {
			hql += "and tp.proposalPerson.tdepartment.id=? ";
			params.add(item.getProposalPerson().getTdepartment().getId());
		}
		if (item.getSource() != null && !item.getSource().equals("")
				&& !item.getSource().equals("-1")) {
			hql += "and tp.source=? ";
			params.add(item.getSource());
		}
		if (item.getProposalPerson().getUid() != null
				&& !item.getProposalPerson().getUid().equals("")) {
			hql += "and tp.proposalPerson.uid=? ";
			params.add(item.getProposalPerson().getUid());
		}
		if (item.getProposalPerson().getName() != null
				&& !item.getProposalPerson().getName().equals("")) {
			hql += "and tp.proposalPerson.name=? ";
			params.add(item.getProposalPerson().getName());
		}
		if (item.getState() != null && item.getState() != -3) {
			hql += "and tp.state=? ";
			params.add(item.getState());
		}
		if (item.getTitle() != null && !item.getTitle().equals("")) {
			hql += "and tp.title like ?";
			String likeParmas = "%" + item.getTitle() + "%";
			params.add(likeParmas);
		}
		if (item.getLastModifyTime() != null
				&& !item.getLastModifyTime().equals("")) {
			if (item.get_selectBeginTime() != null
					&& !item.get_selectBeginTime().equals("")) {
				hql += "and tp.lastModifyTime between ? and ?";
				params.add(item.getLastModifyTime());
				params.add(item.get_selectBeginTime());
				
			} else {
				hql += "and DATEDIFF(day, tp.lastModifyTime, ?) <1 and DATEDIFF(day, tp.lastModifyTime, ?) >-1";
				params.add(item.getLastModifyTime());
				params.add(item.getLastModifyTime());
			}
		}
		if (item.getId() != null && !item.getId().equals("")) {
			hql += "and tp.id=?";
			params.add(item.getId());
		}

		List list = proporsaldao.select(hql, params.toArray());
		return Tproposal.setMoreInfo(list, uid);
	}

	/**
	 * 公司提案查询
	 */

	public List<Tproposal> doSelectCompany(Tproposal item, String uid) {
		// TODO 自动生成方法存根
		String hql = "from Tproposal as tp "
				+ "inner join fetch tp.proposalPerson "
				+ "inner join fetch tp.proposalPerson.tdepartment ";
		return details(hql, item, uid);

	}

	/**
	 * 本部门提案查询
	 */
	public List<Tproposal> doSelectDepartment(Tproposal item, String uid) {
		// TODO 自动生成方法存根
		String hql = "from Tproposal as tp inner join fetch tp.proposalPerson "
				+ "inner join fetch tp.supply";
		return details(hql, item, uid);

	}

	/**
	 * 外部门提案查询
	 */
	public List<Tproposal> doSelectOthers(Tproposal item, String uid) {
		// TODO 自动生成方法存根
		String hql = "from Tproposal as tp inner join fetch tp.proposalPerson "
				+ "inner join fetch tp.supply";
		return details(hql, item, uid);

	}

	public List<Tproposal> doSelect(Tproposal item) {
		// TODO 自动生成方法存根
		String hql = "from Tproposal as tp inner join fetch tp.proposalPerson "
				+ "inner join fetch tp.proposalPerson.tdepartment inner join fetch tp.collectionPersion inner join fetch tp.supply";
		return details(hql, item, item.getProposalPerson().getUid());
	}

	/**
	 * 统计建立项目
	 * 
	 * @param item
	 * @return
	 */
	public List<Tproposal> count1(Tproposal item) {
		String hql = "from Tproposal as tp "
				+ "inner join fetch tp.vaveTeamWorks "
				+ "inner join fetch tp.vaveTeamWorks.projectManager ";
		return details(hql, item, "");
	}

	/**
	 * 更新提案
	 */
	public void doUpdateProposal(Tproposal item) {
		// TODO 自动生成方法存根
		String hql = "update Tproposal as tp set tp.lastModifyTime=?";
		Vector<Object> params = new Vector<Object>();
		params.add(new Date());
		if (item.getCurProgram() != null && !item.getCurProgram().equals("")) {
			params.add(item.getCurProgram());
			hql += ",tp.curProgram=? ";
		}
		if (item.getIns_Program() != null && !item.getIns_Program().equals("")) {
			params.add(item.getIns_Program());
			hql += ",tp.ins_Program=? ";
		}
		if (item.getTitle() != null && !item.getTitle().equals("")) {
			params.add(item.getTitle());
			hql += ",tp.title=? ";
		}
		if (item.getSuggestionType() != null
				&& !item.getSuggestionType().equals("")) {
			params.add(item.getSuggestionType());
			hql += ",tp.suggestionType=? ";
		}if (item.getImvalue() != null
				&& !item.getImvalue().equals("")) {
			params.add(item.getImvalue());
			hql += ",tp.imvalue=? ";
		}
		if (item.getState() != null) {
			params.add(item.getState());
			hql += ",tp.state=? ";
		}if (item.getNvtype() != null && !item.getNvtype().equals("")) {
			params.add(item.getNvtype());
			hql += ",tp.nvtype=? ";
		}
		hql += " where tp.id=?";
		params.add(item.getId());
		proporsaldao.bulkUpdate(hql, params.toArray(new Object[1]));
	}

	public CommonDAO<Tproposal> getProporsaldao() {
		return proporsaldao;
	}

	public void setProporsaldao(CommonDAO<Tproposal> proporsaldao) {
		this.proporsaldao = proporsaldao;
	}

	public List<Tproposal> doSelectByManager(Tempolyee item) {
		String hql = "from Tproposal as tp inner join fetch tp.proposalPerson inner join fetch tp.collectionPersion where tp.proposalPerson.tdepartment.id=? and tp.state>0";

		return proporsaldao.select(hql, item.getTdepartment().getId());
	}

	/**
	 * 更新提案状态
	 */
	public void doUpdateState(Tproposal item) {
		String hql = "update Tproposal as tp set tp.state=? where tp.id=?";
		Object[] params = new Object[2];
		params[0] = item.getState();
		params[1] = item.getId();
		proporsaldao.bulkUpdate(hql, params);
	}

	/**
	 * 更新提案详细信息 单位节约金额，数量，sdaac分享比
	 */
	public void updateMoreInfo(Tproposal item) {
		String hql = "update Tproposal as tp set tp.pp=?,tp.annualConsumption=?,tp.shareValue=?,tp.imvalue=? where tp.id=?";
		proporsaldao.bulkUpdate(hql,
				new Object[] { item.getPp(), item.getAnnualConsumption(),
						item.getShareValue(), item.getId(),item.getImvalue() });
	}

	/**
	 * 统计个人提案
	 * 
	 * @param args
	 */
	public List<PersonalCount> doSelectCount(Integer year) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpBiz empBiz = (EmpBiz) context.getBean("empBiz");

		String hql = "select p.proposalPerson.id ,count(*) from Tproposal as p where p.state>3 and year(p.lastModifyTime)=? group by p.proposalPerson.id";
		List list = this.proporsaldao.select(hql, year);
		List<PersonalCount> reslutList = new ArrayList<PersonalCount>();
		for (int i = 0; i < list.size(); i++) {
			Object[] reslut = (Object[]) list.get(0);
			PersonalCount o = new PersonalCount();
			o.setEmp(empBiz.doSelectGetEmpDetails((String) reslut[0]));
			o.setApprovalCount((Integer) reslut[1]);
			// set savingCost-unfinished
			reslutList.add(o);
		}
		return reslutList;
	}

	/**
	 * 统计部门提案
	 */
	public Integer[][] doSelectCountDepartment(Integer year) {
		// TODO Auto-generated method stub
		/**
		 * SQL: select count(*) as sum_,emp.departmentid from dbo.tProposal as
		 * p,dbo.tEmpolyee as emp where p.proposal_person=emp.uid group by
		 * emp.departmentid
		 */

		/**
		 * SQL动态统计 select emp.departmentid as departmentid, sum(case when
		 * state>0 then 1 else 0 end) as [all], sum(case when state>3 then 1
		 * else 0 end) as [approval], sum(case when state>5 then 1 else 0 end)
		 * as [finish] from dbo.tProposal as p inner join dbo.tEmpolyee as emp
		 * on p.proposal_person=emp.uid group by emp.departmentid
		 */

		String baseHql = "select count(*),emp.tdepartment.id from Tproposal as p inner join fetch p.proposalPerson as emp where year(p.lastModifyTime)=?";
		// 查看部门年度提案数
		String hql_allcount = baseHql + " group by emp.tdepartment.id";
		List departProposalCount = this.proporsaldao.select(hql_allcount, year);

		String hql_approval = baseHql
				+ " and p.state>3 group by emp.tdepartment.id";
		List departApprovalCount = this.proporsaldao.select(hql_approval, year);

		String hql_finish = baseHql
				+ " and p.state>5 group by emp.tdepartment.id";
		List departFinishCount = this.proporsaldao.select(hql_finish, year);
		List<Object[]> result = new ArrayList<Object[]>();

		for (int i = 0; i < departProposalCount.size(); i++) {
			Object[] tmp = new Object[5];
			Object[] proposalCount = (Object[]) departProposalCount.get(i);
			Object[] approvalCount = (Object[]) departApprovalCount.get(i);
			Object[] finishCount = (Object[]) departFinishCount.get(i);

			tmp[0] = proposalCount[0];
			tmp[1] = proposalCount[1];

		}

		return null;
	}

	/**
	 * 删除提案：把状态定为-1
	 */
	public void doDelete(String proposalID) {
		// TODO Auto-generated method stub
		String hql = "update Tproposal set state=-1 where id=?";
		this.proporsaldao.bulkUpdate(hql, proposalID);
	}

	public List<Figure> countFigure() {
		String sql = "select applicable_models ,sum(dyjycb) as a ,count(*) as c from  dbo.tProposal as p"
				+ " inner join dbo.tTheme as t on t.proposalID=p.id"
				+ " group by  applicable_models";

		List<Object[]> rs = proporsaldao.selectBySql(sql);
		List<Figure> result = new ArrayList<Figure>();
		for (Object[] item : rs) {
			Figure temp = new Figure();
			if (item[0] == null) {
				temp.setFigureNo("NULL");
			} else {
				temp.setFigureNo(item[0].toString());
			}
			if (item[1] != null) {
				temp.setSaving(Float.parseFloat(item[1].toString()));
			} else {
				temp.setSaving(0f);
			}
			if (item[2] != null) {
				temp.setCount_(Float.parseFloat(item[2].toString()));
			} else {
				temp.setSaving(0f);
			}
			result.add(temp);
		}
		return result;
	}

	// 更新proposal的单位节约成本
	public void test(ProjectScheduleBizImpl s) {
		String hql = "from Tproposal  ";
		String hql2 = "update Tproposal set pp=? where id=?";
		List<Tproposal> rs = this.proporsaldao.select(hql, null);
		for (Tproposal p : rs) {
			List<VaveProjectSchedule> dd = s.doSelectByProposalIDMaxVersion(p
					.getId());
			// System.out.println(dd.size());
			Float k = 0.00f;
			if (dd != null) {
				for (VaveProjectSchedule asd : dd) {
					if (asd.getProject().equals("1.现在方案成本")
							|| asd.getProject().equals("1.单位节约成本")) {
						// System.out.println(asd.getProject()+"==>"+asd.getQuantity());
						if (asd.getQuantity().equals("")) {
							k += 0;
						} else {
							k = Float.parseFloat(asd.getQuantity());
						}
					}
					if (asd.getProject().equals("2.代用方案成本")) {
						// System.out.println(asd.getProject()+"==>"+asd.getQuantity());
						if (asd.getQuantity().equals("")) {

						} else {
							k = k - Float.parseFloat(asd.getQuantity());
						}

					}
				}
			}

			System.out.println("K=>" + k);
			proporsaldao.bulkUpdate(hql2, new Object[] { k, p.getId() });

		}
	}

	/**
	 * 参与率
	 * 
	 * @return
	 */
	public List<Object[]> doParticipationRate(String year) {

		String sql1 = "select cc,tal,did, d.name,did,did,did as dname from "
				+ // 提案人数
				"(select count(distinct e.uid) cc, e.departmentid did from dbo.tProposal p "
				+ "inner join dbo.tEmpolyee e on e.uid=p.collection_persion "
				+ "where state>0 and source='公司' ";
		if (year != null && !year.equals("")) {
			sql1 += "and year(lastModifyTime)=" + year;
		}
		sql1 += "group by e.departmentid) as v2 "
				+ "inner join (select count(*) tal,departmentid from tEmpolyee "
				+ "where type = 'salary' group by departmentid ) as v1 "
				+ "on v2.did=v1.departmentid "
				+ "inner join tdepartment d on v2.did=d.id ";

		return proporsaldao.selectBySql(sql1);
	}

	/**
	 * 人均批准提案
	 * 
	 * @return
	 */
	public List<Object[]> doApprovedPreRate(String year) {

		// select b,a, v2.did from (
		// select count(*) a,e.departmentid did from dbo.tProposal p
		// inner join dbo.tEmpolyee e on e.uid=p.collection_persion
		// where year(lastModifyTime)=2010
		// group by e.departmentid) v1 inner join
		// (select count(*) b,e.departmentid did from dbo.tProposal p
		// inner join dbo.tEmpolyee e on e.uid=p.collection_persion
		// where state>=4 and year(lastModifyTime)=2010
		// group by e.departmentid) v2 on v1.did=v2.did

		String sql2 = "select b,a, v2.did from (select count(*) a,e.departmentid did from dbo.tProposal p inner join dbo.tEmpolyee e on e.uid=p.collection_persion where state>=4 ";// 批准数
		if (year != null && !year.equals("")) {
			sql2 += "and year(lastModifyTime)=" + year;
		}
		sql2 += " group by e.departmentid) v1 inner join (select count(*) b,e.departmentid did from dbo.tProposal p inner join dbo.tEmpolyee e on e.uid=p.collection_persion ";
		if (year != null && !year.equals("")) {
			sql2 += "where year(lastModifyTime)=" + year;
		}
		sql2 += "group by e.departmentid) v2 on v1.did=v2.did";
		// inner join dbo.tEmpolyee e on e.uid=p.collection_persion
		// where state>=4 and";
		return proporsaldao.selectBySql(sql2);
	}

	/**
	 * 提案批准率
	 * 
	 * @return
	 */
	public List<Object[]> doProposalRate(String year) {
		String sql3 = "select count(*) from dbo.tProposal ";// 总数
		if (year != null && !year.equals("")) {
			sql3 += "where year(lastModifyTime)=" + year;
		}
		sql3 += " group by e.departmentid";
		return proporsaldao.selectBySql(sql3);
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ProposalBizImpl p = (ProposalBizImpl) context.getBean("proposalBiz");
		ProjectScheduleBizImpl s = (ProjectScheduleBizImpl) context
				.getBean("projectScheduleBiz");
		p.test(s);
	}

}
