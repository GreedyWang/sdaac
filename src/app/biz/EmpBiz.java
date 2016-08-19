package app.biz;

import java.util.List;

import com.microsoft.jdbc.base.BaseBatchUpdateException;

import app.biz.impl.KpiAccount;
import app.entity.TeamWork;
import app.entity.Tempolyee;
import app.entity.Test;
public interface EmpBiz {
	public Tempolyee doSelect(Tempolyee item);//
	public Tempolyee doSelectByUid(String uid);
	public Tempolyee doSelectByNetid(String netid);
	//public Tempolyee doSelectManagerByUid(String uid);Ա��
	public String doSelectById(String uid);//界面检验用户id
	public void addInformToUser(List<Test> item);
	public void doUpdate(Tempolyee item);
	public void doInsert(Tempolyee item)throws BaseBatchUpdateException ;
	public List<Tempolyee> doSelectByDepartID(int id); 
	public Tempolyee doSelectGetEmpDetails(String uid);
	public void updateEmpInfo(Float[] levels);
	public void updateEmpMail(Tempolyee item);
	public List<String> doSelectAllUid();
	public void doUpdateUid(String old,String newUID);
	public List<Tempolyee> selectEmpsExpectManager(Integer departId);
	public List<Tempolyee> selectAllSalarys(Tempolyee item);
	public void updateEmpBaseInfo(Tempolyee item);
	public List<Tempolyee> selectFuzzy(Tempolyee item);
	public List<Tempolyee> selectByTeamLeadId(String[] teamID);
	public List<Tempolyee> selectAllwork();
	public List<TeamWork> selectTeamByLead(String leaderUid);
//	public List<Tempolyee> selectAllwork(Tempolyee worker);
	public void createTeamWork(TeamWork	teamWork,String[] menebersUid);
	public boolean createTeamWork(TeamWork	teamWork);
	public void insertWorkerTeam(int teamId,String[] workersId);
	public void deleteWorkerTeam(int teamId,String workersId);
	public List<Tempolyee> doSelectSalaryByDepartID(int id);
	public List<KpiAccount> doAccount(Tempolyee Pemployee,String version);
	public List<Object[]> doSelectEachCount();
	public Tempolyee doSelectManagerBydepartId(Integer departId);
	public String doSelectManagerBydepartId2(Integer departId);
	public Tempolyee doSelectManagerByUid(String uid);
	public List<Tempolyee> doSelectAllBackersByUid(String uid);
	public void doAddBacker(String uid,String target);
	public void doADelBacker(String uid,String target);
	public Tempolyee doQueryByNetID(String netid);
}
