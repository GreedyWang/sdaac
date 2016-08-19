package sdaac.wym.app.Hr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.entity.Tempolyee;
import common.dao.CommonDAO;

/**
 * Staff organization relationship
 * 
 * @author wangyongmin
 * 
 */
public class OrganizationManager {
	private CommonDAO<Tempolyee> empdao;
	
	/**
	 * get someone's subordinate
	 * @param uid
	 * @return list employees
	 */
	public List<Tempolyee> getSubordinate(String uid) {
		String hql="from Tempolyee emp inner join fetch emp.tdepartment where emp.isSeparation =1 and emp.type='salary' and leaderID=?";
		return empdao.select(hql, uid);
	}
	
	/**
	 * get someone's subordinate
	 * @param Tempolyee
	 * @return list employees
	 */
	public List<Tempolyee> getSubordinate(Tempolyee emp) {
		return getSubordinate(emp.getUid());
	}
	
	/**
	 * 得到所有下属
	 * @param emp
	 * @return
	 */
	private List<Tempolyee> allSubordinate;
	public List<Tempolyee> getAllSubordinate(Tempolyee emp) {
		allSubordinate=new ArrayList<Tempolyee>();
		getAllSubordinate2(emp);
		return allSubordinate;
	}
	
	private void getAllSubordinate2(Tempolyee emp){
		List<Tempolyee> rs = getSubordinate(emp.getUid());
		if(rs!=null&&!rs.isEmpty()){
			allSubordinate.addAll(rs);
			for(Tempolyee item:rs){
				getAllSubordinate2(item);
			}
		}
	}
	
	public List<Tempolyee> selectFuzzy(Tempolyee item) {
		// TODO �
		//Զ���ɷ������
		List params=new ArrayList();
		List<Tempolyee> rs=getAllSubordinate(item);
		String param="-1";
		for(Tempolyee emp:rs){
			param+=",'"+emp.getUid()+"'";
		}
		
		String hql="from Tempolyee as emp where uid in ("+param+")";
		if(null!=item.getName()&&!item.getName().equals(""))
		{
			params.add("%"+item.getName()+"%");
			hql+=" and emp.name like ?";			
		}
		if(null!=item.getTdepartment().getId())
		{
			params.add(item.getTdepartment().getId());
			hql+=" and emp.tdepartment.id=?";
		}
		
		return empdao.select(hql, params.toArray(new Object[0]));
	
	}
	
	public CommonDAO<Tempolyee> getEmpdao() {
		return empdao;
	}

	public void setEmpdao(CommonDAO<Tempolyee> empdao) {
		this.empdao = empdao;
	}
	
}
