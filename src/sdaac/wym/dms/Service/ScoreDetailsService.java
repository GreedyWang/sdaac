package sdaac.wym.dms.Service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;

import sdaac.wym.app.Hr.EmpSourceManager;
import sdaac.wym.app.Service.dms.calc.Calc;
import sdaac.wym.app.Service.dms.calc.Calc1;
import sdaac.wym.app.Service.dms.calc.Calc2;
import sdaac.wym.app.Service.dms.calc.Calc3;
import sdaac.wym.dms.Service.i.ISocreDetailsService;
import sdaac.wym.dms.entity.ScoreDetails;
import sdaac.wym.dms.entity.Templete;
import app.entity.Tempolyee;

public class ScoreDetailsService implements ISocreDetailsService {

	private CommonDAO<ScoreDetails> scoreDetailsDao;
	
	private Calc calc1 = new Calc1();
	private Calc2 calc2 = new Calc2();
	private Calc3 calc3 = new Calc3();
	
	public CommonDAO<ScoreDetails> getScoreDetailsDao() {
		return scoreDetailsDao;
	}

	public void setScoreDetailsDao(CommonDAO<ScoreDetails> scoreDetailsDao) {
		this.scoreDetailsDao = scoreDetailsDao;
	}

	@Override
	public void doScore(ScoreDetails item) {
		// TODO Auto-generated method stub
		calc1.doScore(item);
	}

	@Override
	public void doScores(List<ScoreDetails> items,String version) {
		// TODO Auto-generated method stub
		//先删除原有的信息
		String uids = "";
		for(ScoreDetails item :items){
			uids += "'"+item.getEmp().getUid()+"',";
		}
		String hql = "delete from ScoreDetails where version ='"+version+"' and emp.uid in ("+uids+"'a')"; 
		scoreDetailsDao. blukDelete(hql);
		scoreDetailsDao.blukFlushInsert(items);
	}

	@Override
	public List<ScoreDetails> doShow(String version,String leaderUid) {
		// TODO Auto-generated method stub
		String hql ="from ScoreDetails s inner join fetch s.indicate inner join fetch s.emp where version =? and leaderUid=? order by s.emp.uid,s.indicate.worder";//
		return scoreDetailsDao.select(hql, new Object[]{version,leaderUid});	
	}

	@Override
	public List<ScoreDetails> doShow(List<Tempolyee> emps, String version,List<Templete> templetes) {
		// TODO Auto-generated method stub
		String uids = "'8'";
		for(Tempolyee emp : emps){
			uids += ",'"+emp.getUid()+"'";
		}
		String hql ="from ScoreDetails s inner join fetch s.indicate inner join fetch s.emp where version =? and s.emp.uid in ("+uids+") order by s.emp.uid,s.indicate.worder";//
		return scoreDetailsDao.select(hql, version);	
	}
		
	
	public List<ScoreDetails> doShowWithScore(List<Tempolyee> emps, String version,List<Templete> templetes) {
		// TODO Auto-generated method stub
		List<ScoreDetails> rs = this.doShow(emps, version, templetes);
		for(ScoreDetails item : rs){
			if(item.getIndicate().getCalc().equals("1")){
				calc1.doScore(item);
			}else if(item.getIndicate().getCalc().equals("2")){
				calc2.setOthers(rs);
				calc2.doScore(item);
			}else if(item.getIndicate().getCalc().equals("3")){
				calc3.setOthers(rs);
				calc3.doScore(item);
			}			
		}
		return rs;		
	}


	@Override
	public String[] getVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
