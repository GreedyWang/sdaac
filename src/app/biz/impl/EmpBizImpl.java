package app.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import app.biz.EmpBiz;
import app.entity.TeamWork;
import app.entity.Tempolyee;
import app.entity.Test;
import app.entity.WorkerTeam;

import com.microsoft.jdbc.base.BaseBatchUpdateException;
import common.dao.CommonDAO;


public class EmpBizImpl implements EmpBiz {
	private CommonDAO<Tempolyee> empdao;
	private CommonDAO<TeamWork> teamWorkDao;
	private CommonDAO<WorkerTeam> workerTeamDao;

	public CommonDAO<WorkerTeam> getWorkerTeamDao() {
		return workerTeamDao;
	}

	public void setWorkerTeamDao(CommonDAO<WorkerTeam> workerTeamDao) {
		this.workerTeamDao = workerTeamDao;
	}

	public CommonDAO<TeamWork> getTeamWorkDao() {
		return teamWorkDao;
	}

	public void setTeamWorkDao(CommonDAO<TeamWork> teamWorkDao) {
		this.teamWorkDao = teamWorkDao;
	}

	public CommonDAO<Tempolyee> getEmpdao() {
		return empdao;
	}

	public void setEmpdao(CommonDAO<Tempolyee> empdao) {
		this.empdao = empdao;
	}

	public EmpBizImpl() {

	}

	public EmpBizImpl(CommonDAO<Tempolyee> empdao) {
		this.empdao = empdao;
	}

	public void doUpdate(Tempolyee item) {

		empdao.update(item);
	}

	public Tempolyee doSelect(Tempolyee item) {
		// TODO �Զ���ɷ������
		List<String> params = new ArrayList<String>();
		String hql = "from Tempolyee as emp inner join fetch emp.tdepartment where 1=1";
		if (null != item.getUid() && !item.getUid().equals("")) {
			params.add(item.getUid());
			hql += " and emp.uid=?";
		}
		String[] pps = (String[]) params.toArray(new String[0]);

		List<Tempolyee> list = empdao.select(hql, pps);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	/**
	 * 查询员工的所有备用人员
	 * @param uid
	 * @return
	 */
	public List<Tempolyee> doSelectAllBackersByUid(String uid){
		String hql = "from Tempolyee where backer like ?";
		return empdao.select(hql, "%" + uid + "%");
	}
	
	public Tempolyee doSelectByUid(String uid){
		String hql = "from Tempolyee where uid = ?";
		List<Tempolyee> list = empdao.select(hql, uid);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public Tempolyee doSelectByNetid(String netid){
		String hql = "from Tempolyee where netid = ?";
		List<Tempolyee> list = empdao.select(hql, netid);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public void doAddBacker(String uid,String target){
		Tempolyee emp = empdao.selectByPk(uid);
		if(emp.getBacker()==null){
			emp.setBacker(target+",");
		}else{
			emp.setBacker(emp.getBacker()+target+",");
		}	
	}
	
	public void doADelBacker(String uid,String target){
		Tempolyee emp = empdao.selectByPk(uid);
		String backer = emp.getBacker().replace(target+",", "");
		emp.setBacker(backer);
	}
	
//	public static void main(String[] args) {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		EmpBiz ma=(EmpBiz)ac.getBean("empBiz");
//		System.out.println(ma.doSelectAllBackersByUid("5004"));;
//		
//	}
	
	/**
	 * 模糊查询查询
	 */
	public List<Tempolyee> selectFuzzy(Tempolyee item) {
		// TODO �Զ���ɷ������
		List<String> params = new ArrayList<String>();
		String hql = "from Tempolyee as emp inner join fetch emp.tdepartment where 1=1";
		if (null != item.getName() && !item.getName().equals("")) {
			params.add("%" + item.getName() + "%");
			hql += " and emp.name like ?";
		}

		if (null != item.getUid() && !item.getUid().equals("")) {
			params.add(item.getUid());
			hql += " and emp.uid=?";
		}
		String[] pps = (String[]) params.toArray(new String[0]);

		return empdao.select(hql, pps);

	}

	/**
	 * 添加员工详细信息-》test(excel输出辅助类)
	 * 
	 * @param
	 */
	public void addInformToUser(List<Test> item) {
		// TODO �Զ���ɷ������
		for (Test test : item) {
			String hql = "from Tempolyee as u inner join fetch u.tdepartment where u.uid=?";
			Tempolyee emp = empdao.select(hql, test.getUid()).get(0);
			test.setName(emp.getName());
			test.setBaseSalary(emp.getBaseSalary());
			test.setSalaryType(emp.getSalaryType());
			test.setDepartmentName(emp.getTdepartment().getName());
		}
	}

	public void doInsert(Tempolyee item) throws BaseBatchUpdateException {

				empdao.insert(item);
	}

	/**
	 * 查讯部门员工
	 * 
	 * @param departmentID
	 */
	public List<Tempolyee> doSelectByDepartID(int id) {
		// TODO �Զ���ɷ������
		String hql = "from Tempolyee as u inner join fetch u.tdepartment "
				+ "where u.isSeparation =1 and u.tdepartment.id=?";
		return empdao.select(hql, id);

	}

	/**
	 * 查部门员工,salary
	 * 
	 * @param departmentID
	 */
	public List<Tempolyee> doSelectSalaryByDepartID(int id) {
		// TODO �Զ���ɷ������
		String hql = "from Tempolyee as u inner join fetch u.tdepartment "
				+ "where u.isSeparation =1 and u.type='salary' and u.tdepartment.id=?";
		return empdao.select(hql, id);

	}

	/**
	 * 查人名 在职的
	 * 
	 * @param uid
	 */
	public String doSelectById(String uid) {
		// TODO 自动生成方法存根
		String hql = "select u.name from Tempolyee as u where u.uid=? and u.isSeparation =1";
		return empdao.select(hql, uid).toString();

	}

	/**
	 * 查个人详细信息
	 * 
	 * @param uid
	 */
	public Tempolyee doSelectGetEmpDetails(String uid) {
		// TODO 自动生成方法存根
		String hql = "from Tempolyee as emp inner join fetch emp.tdepartment where emp.uid=? ";
		List<Tempolyee> emp_List = empdao.select(hql, uid);
		if (emp_List.isEmpty()) {
			return null;
		} else {
			return emp_List.get(0);
		}
	}

	/**
	 * 查个人详细信息
	 * @param netid
	 */
	public Tempolyee doQueryByNetID(String netid) {
		// TODO 自动生成方法存根
		String hql = "from Tempolyee as emp inner join fetch emp.tdepartment where emp.netid=? ";
		List<Tempolyee> emp_List = empdao.select(hql, netid);
		if (emp_List.isEmpty()) {
			return null;
		} else {
			return emp_List.get(0);
		}
	}
	
	/*
	 * 计算员工abc档--test
	 */
	public void updateEmpInfo(Float[] levels) {
		List<Tempolyee> list = empdao.selectAll();
		for (Tempolyee tempolyee : list) {
			if (tempolyee.getBaseSalary() != null) {
				Float baseSalary = tempolyee.getBaseSalary();
				if (baseSalary > levels[0]) {
					tempolyee.setSalaryType("A");

				}
				if (baseSalary > levels[1] && baseSalary <= levels[0]) {
					tempolyee.setSalaryType("B");

				}
				if (baseSalary > levels[2] && baseSalary <= levels[1]) {
					tempolyee.setSalaryType("C");

				}
				if (baseSalary > levels[3] && baseSalary <= levels[2]) {
					tempolyee.setSalaryType("D");

				}
				empdao.update(tempolyee);
			}
		}
	}

	/**
	 * 查询所有员工id
	 */
	public List<String> doSelectAllUid() {
		// TODO Auto-generated method stub
		String hql = "select uid from Tempolyee";
		List list = this.empdao.select(hql, null);
		return list;
	}

	/**
	 * 更新员工邮箱地址
	 * 
	 * @param 员工编号，email地址
	 */
	public void updateEmpMail(Tempolyee item) {
		// TODO Auto-generated method stub
		String hql = "update Tempolyee as emp set emp.mail=? where emp.uid=?";
		this.empdao.bulkUpdate(hql, new String[] { item.getMail(),
				item.getUid() });
	}

	public void doUpdateUid(String newUID, String old) {
		String hql = "update Tempolyee set uid=? where uid=?";
		this.empdao.bulkUpdate(hql, new String[] { newUID, old });
	}

	/**
	 * 查询除部门经理外员工
	 */
	public List<Tempolyee> selectEmpsExpectManager(Integer departId) {
		// TODO Auto-generated method stub
		String hql = "from Tempolyee as u inner join fetch u.tdepartment as d where u.isSeparation =1 and "
				+ "u.tdepartment.id=? and u.uid<> d.pmangerid";
		List<Tempolyee> emps = empdao.select(hql, departId);
		return emps;
	}
	
	/**
	 * 查询部门经理
	 * @param departId
	 * @return
	 */
	public Tempolyee doSelectManagerBydepartId(Integer departId) {
		// TODO Auto-generated method stub
		String hql = "from Tempolyee as u inner join fetch u.tdepartment as d where u.isSeparation =1 and "
				+ "u.tdepartment.id=? and u.uid = d.pmangerid";
		List<Tempolyee> emps = empdao.select(hql, departId);
		if(emps.isEmpty()){
			Tempolyee aa = new Tempolyee();
			aa.setName(departId+"");
			return aa;
		}else{
			return emps.get(0);
		}
	}
	
	public String doSelectManagerBydepartId2(Integer departId) {
		// TODO Auto-generated method stub
//		String hql = "from Tempolyee as u inner join fetch u.tdepartment as d where u.isSeparation =1 and "
//				+ "u.tdepartment.id=? and u.uid = d.pmangerid";
		String sql = "select e.netid from tDepartment d inner join tEmpolyee e on uid = pmangerid where isSeparation=1 and d.id="+departId;
		List<Object[]> rs = empdao.selectBySql(sql);
		if(rs!=null && !rs.isEmpty()){
			return rs.get(0)+"";
		}else{
			return "";
		}
	}
	
	/**
	 * 按工号查询部门经理
	 * @param uid
	 * @return
	 */
	public Tempolyee doSelectManagerByUid(String uid){
		Tempolyee item = doSelectGetEmpDetails(uid);
		return doSelectManagerBydepartId(item.getTdepartment().getId());
	}
	
	/**
	 * 查询所有Salary员工
	 */
	public List<Tempolyee> selectAllSalarys(Tempolyee item) {
		// TODO Auto-generated method stub
		String hql = "from Tempolyee as u inner join fetch u.tdepartment as d where u.isSeparation =1 and u.type='"
				+ "salary" + "'";
		if (item.getName() != null && !item.getName().equals("")) {
			hql += " and u.name='" + item.getName() + "'";
		}
		if (item.getTdepartment().getId() != null) {
			hql += " and d.id='" + item.getTdepartment().getId() + "'";
		}
		return this.empdao.select(hql, null);
	}

	/**
	 * 更新员工基础信息
	 * @param item
	 */
	public void updateEmpBaseInfo(Tempolyee item) {
		String hql = "update Tempolyee set ";
		Vector<Object> params = new Vector<Object>();

		// 姓名
		if (item.getName() != null && !item.getName().equals("")) {
			hql += "name=?,";
			params.add(item.getName());
		}
		if (item.getManagedms()!= null && !item.getManagedms().equals("")) {
			hql += "managedms=?,";
			params.add(item.getManagedms());
		}
		if (item.getEprownerarea() != null && !item.getEprownerarea().equals("")) {
			hql += "eprownerarea=?,";
			params.add(item.getEprownerarea());
		}
		if (item.getArea() != null && !item.getArea().equals("")) {
			hql += "area=?,";
			params.add(item.getArea());
		}
		if (item.getNetid() != null && !item.getNetid().equals("")) {
			hql += "netid=?,";
			params.add(item.getNetid());
		}
		// DMS奖金系数
		if (item.getDmsratio()!=null && item.getDmsratio()!= 0 ) {
			hql += "dmsratio=?,";
			params.add(item.getDmsratio());
		}
		// 部门
		if (item.getTdepartment().getId() != null) {
			hql += "tdepartment.id=?,";
			params.add(item.getTdepartment().getId());
		}
		// 档案工资
		if (item.getBaseSalary() != null) {
			hql += "baseSalary=?,salaryType=?,";
			params.add(item.getBaseSalary());
			params.add(item.getSalaryType());
		}
		if (item.getIsSeparation() != null) {
			hql += "isSeparation=?,";
			params.add(item.getIsSeparation());
		}
		if (item.getLeaderID() != null && !item.getLeaderID().equals("")) {
			hql += "leaderID=?,";
			params.add(item.getLeaderID());
		}
		if (item.getDistrict() != null && !item.getDistrict().equals("")) {
			hql += "district=?,";
			params.add(item.getDistrict());
		}
		if (item.getGroup() != null && !item.getGroup().equals("")) {
			hql += "workgroup=?,";
			params.add(item.getGroup());
		}
		if (item.getBacker() != null && !item.getBacker().equals("")) {
			hql += "backer=?,";
			params.add(item.getBacker());
		}
		hql = hql.substring(0, hql.length() - 1);
		hql += " where uid=?";
		params.add(item.getUid());
		this.empdao.bulkUpdate(hql, params.toArray());
	}

	/**
	 * 查询组长下面的成员
	 * 
	 * @param String
	 *            uid
	 * @return List<Tempolyee>
	 */
	public List<Tempolyee> selectByTeamLeadId(String[] teamIds) {
		// TODO Auto-generated method stub

		String param = "-1";
		for (String teamId : teamIds) {
			param += "," + teamId;
		}
		String hql = "from WorkerTeam as wt inner join fetch wt.worker where wt.team.id in ("
				+ param + ")";
		List<WorkerTeam> rs = workerTeamDao.select(hql, null);
		List<Tempolyee> temployees = new ArrayList<Tempolyee>();
		for (WorkerTeam workerTeam : rs) {
			temployees.add(workerTeam.getWorker());
		}
		return temployees;
	}

	/**
	 * 查询员工编号下的组
	 */
	public List<TeamWork> selectTeamByLead(String leaderUid) {
		String hql = "from TeamWork where leader.uid=?";
		return teamWorkDao.select(hql, leaderUid);

	}

	/**
	 * 查询工人
	 * 
	 * @return
	 */
	public List<Tempolyee> selectAllwork() {
		String hql = "from Tempolyee where type='worker'";
		return empdao.select(hql, null);
	}

	public List<Tempolyee> selectAllwork(Tempolyee worker) {
		String hql = "from Tempolyee where type='worker'";
		if (worker.getUid() != null) {
			hql += " and uid=?";
		}
		return empdao.select(hql, worker.getUid());
	}

	/**
	 * 创建车间工作组
	 * 
	 * @param teamWork
	 * @param menebersUid
	 */
	public void createTeamWork(TeamWork teamWork, String[] menebersUid) {
		String selectHql = "from TeamWork where id=?";
		int pk;
		if (teamWorkDao.select(selectHql, teamWork.getId()) != null) { // 如果没有这个组
			pk = (Integer) teamWorkDao.insert(teamWork);
			String hql = "update Tempolyee set teamWork.id=" + pk
					+ " where uid in (?)";
			empdao.bulkUpdate(hql, menebersUid);
		}
	}

	/**
	 * 新建组
	 * 
	 * @param team
	 */
	public boolean createTeamWork(TeamWork team) {
		if (teamWorkDao.insert(team) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除组
	 * 
	 * @param team
	 */
	public void deleteTeam(TeamWork team) {
		teamWorkDao.delete(team);
	}

	/**
	 * 修改组信息
	 * 
	 * @param team
	 */
	public void updateTeam(TeamWork team) {
		String hql = "update TeamWork set teamName=? and leader.uid=? where id=?";
		teamWorkDao.bulkUpdate(hql, new Object[] { team.getTeamName(),
				team.getLeader().getUid(), team.getId() });
	}

	/**
	 * 添加组下面的成员
	 * 
	 * @param teamId
	 * @param workersId
	 */
	public void insertWorkerTeam(int teamId, String[] workersId) {
		List<WorkerTeam> workerTeams = new ArrayList<WorkerTeam>();
		for (String workerId : workersId) {
			WorkerTeam temp = new WorkerTeam(teamId, workerId);
			workerTeams.add(temp);
		}
		workerTeamDao.blukFlushInsert(workerTeams);
	}

	/**
	 * 删除组下面的成员
	 * 
	 * @param teamId
	 * @param workersId
	 */
	public void deleteWorkerTeam(int teamId, String workersId) {
		String hql = "delete from WorkerTeam where worker.uid=" + workersId
				+ " and team.id=" + teamId;
		workerTeamDao.blukDelete(hql);
	}
	
	/**
	 * 统计各个员工的KPI总得分
	 * @param Pemployee
	 * @return
	 */
	public List<KpiAccount> doAccount(Tempolyee Pemployee,String version ) {
		StringBuffer sql = new StringBuffer( "select e.uid,e.name as uname,d.name as dname,al from tEmpolyee as e inner join ");
				sql.append("(select uid,sum(score*percentage/10000) as al from dbo.tEmpIndex ei ") ;
				sql.append(" inner join dbo.tindex_target i on ei.indexid = i.id");
				sql.append(" where version = '"+version+"'");
				sql.append(" group by uid ");
				sql.append(" having sum(score*percentage/10000) > 0) as a ");
				sql.append(" on a.uid = e.uid ");
				sql.append(" inner join tDepartment as d on d.id=e.departmentid ");
		if(Pemployee.getTdepartment().getId()!=null){
			sql.append("where d.id="+Pemployee.getTdepartment().getId());
		}
			//	+ "order by d.name";
		List<Object[]> rs=empdao.selectBySql(sql.toString());
		List<KpiAccount> kpiAccountList=new ArrayList<KpiAccount>();
		for(Object[] item:rs){
			KpiAccount kpiAccount = new KpiAccount(item[0].toString(),item[1].toString(),item[2].toString(),(Float)item[3]);
			kpiAccountList.add(kpiAccount);
		}
		return kpiAccountList;
	}
	
	/**
	 * 统计每个部门人数
	 * @return
	 */
	public List<Object[]> doSelectEachCount() {
		String sql = "select count(*),departmentid from dbo.tEmpolyee group by departmentid";
		return empdao.selectBySql(sql);
	}
}
