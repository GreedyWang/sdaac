package sdaac.wym.app.Hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sdaac.wym.app.entity.hr.EmpSource;
import app.entity.Tdepartment;
import app.entity.Tempolyee;
import common.Logger;
import common.dao.CommonDAO;

public class EmpSourceManager {
	public CommonDAO<Tempolyee> empDao;
	public CommonDAO<EmpSource> empSourceDao;
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	public EmpSourceManager(){
		map.put("D001",10014 );
		map.put("D002",10053 );
		map.put("D003",10046 );
		map.put("D004", 1);
		map.put("D005",10017 );
		map.put("D006", 10054);
		map.put("D007",10014 );
		map.put("D008",10055 );
		map.put("D009", 10056);
		map.put("D010",10047 );
		map.put("D011", 10048);
		map.put("D012", 10040);
		map.put("D013", 10031);
		map.put("D014", 10057);
		map.put("D015", 10041);
		map.put("D016",10030 );
		map.put("D017", 10058);
		map.put("D018",1 );
		map.put("D019", 10049);
		map.put("D020",10025 );//pcl
		map.put("D021", 10036);
		map.put("D022", 1);
		map.put("D023", 1);
		map.put("D024",10026 );
		map.put("D025",10044 );
		map.put("D026", 1);
		map.put("D027",1 );
		map.put("D028", 1);
		map.put("D029", 10065);
	}
	Logger logger = Logger.getLogger(EmpSourceManager.class);
	
	public void synMail(){
		String hql="from EmpSource where staff_no like ?";
		List<EmpSource> empSources = empSourceDao.select(hql, "%sy%");
		for(EmpSource item:empSources){
			System.out.print(item.getId().getEMail());
			String hql2="update Tempolyee set mail=? where uid=?" ;
			empDao.bulkUpdate(hql2,new String[]{item.getId().getEMail(),item.getId().getStaffNo()});
		}
	}
	
	/**
	 * 同步人事系统
	 */
	public void Synchronization(){
		String hql="from EmpSource where branch=? ";//and DEPARTMENT = ?";
		List<EmpSource> empSources = empSourceDao.select(hql, new String[]{"Z001"});
		//System.out.println("empSources total size="+empSources.size());
		int isSeparation;
		for(EmpSource item:empSources){
//			logger.warn(item.getId().getStaffName()+"-->"+item.getId().getSupervisorName());
			//System.out.println(item.getId().getStaffName()+"-->"+item.getId().getSupervisorName());
			
			String hql2="update Tempolyee set leaderID=?,isSeparation=?,tdepartment.id=?,uid=?  where uid=? and uid not like 'sy%'";
			//是否离职
			if(item.getId().getStatus().equals("N")){
				isSeparation=1;
			}else{
				isSeparation=0;
			}
			String sourceDepartmentId =item.getId().getDepartmentId();
			
		
			if(map.get(sourceDepartmentId.trim())!=null){
				int departId = map.get(sourceDepartmentId.trim());
				//System.out.println(departId);
				if(departId!=1){
					String hql3="from Tempolyee where uid=?";
					if(empDao.select(hql3, item.getId().getStaffNo().trim()).isEmpty()){
						Tempolyee emp = new Tempolyee();
						emp.setUid(item.getId().getStaffNo().trim());
						emp.setName(item.getId().getStaffName().trim());
						emp.setTdepartment(new Tdepartment(departId));
						emp.setIsSeparation(isSeparation);
						emp.setLeaderID(item.getId().getSupervisorName().trim());
						empDao.insert(emp);
						System.out.println("insert===>"+item.getId().getStaffNo());
					}else{
						System.out.println("修改了："+departId+":"+item.getId().getSupervisorName());
						int flag = empDao.bulkUpdate(hql2, new Object[]{item.getId().getSupervisorName(),isSeparation,departId,item.getId().getStaffNo().trim(),item.getId().getStaffNo()});
						if(flag==0){
							System.out.println(item.getId().getStaffNo());
						}
					}
					
				}
			}
		}
	}
	
	/**
	 * 同步人事系统SY
	 */
	public void SynchronizationSY(){
		String hql="from EmpSource where branch=? ";//and DEPARTMENT = ?";
		List<EmpSource> empSources = empSourceDao.select(hql, new String[]{"Z002"});
		//System.out.println("empSources total size="+empSources.size());
		int isSeparation;
		for(EmpSource item:empSources){
//			logger.warn(item.getId().getStaffName()+"-->"+item.getId().getSupervisorName());
			//System.out.println(item.getId().getStaffName()+"-->"+item.getId().getSupervisorName());
			
			String hql2="update Tempolyee set leaderID=?,isSeparation=? where uid=? and uid  like 'sy%'";
			//是否离职
			if(item.getId().getStatus().equals("N")){
				isSeparation=1;
			}else{
				isSeparation=0;
			}
			String sourceDepartmentId =item.getId().getDepartmentId();
			
		
			if(map.get(sourceDepartmentId.trim())!=null){
				int departId = map.get(sourceDepartmentId.trim());
				//System.out.println(departId);
				if(departId!=1){
					String hql3="from Tempolyee where uid=?";
					if(empDao.select(hql3, item.getId().getStaffNo().trim()).isEmpty()){
						Tempolyee emp = new Tempolyee();
						emp.setUid(item.getId().getStaffNo().trim());
						emp.setName(item.getId().getStaffName().trim());
						emp.setTdepartment(new Tdepartment(departId));
						emp.setIsSeparation(isSeparation);
						emp.setLeaderID(item.getId().getSupervisorName().trim());
						empDao.insert(emp);
						System.out.println("insert===>"+item.getId().getStaffNo());
					}else{
						System.out.println("修改了："+departId+":"+item.getId().getSupervisorName());
						int flag = empDao.bulkUpdate(hql2, new Object[]{item.getId().getSupervisorName(),isSeparation,item.getId().getStaffNo()});
						if(flag==0){
							System.out.println(item.getId().getStaffNo());
						}
					}
					
				}
			}
		}
	}
	
	public CommonDAO<Tempolyee> getEmpDao() {
		return empDao;
	}

	public void setEmpDao(CommonDAO<Tempolyee> empDao) {
		this.empDao = empDao;
	}

	public CommonDAO<EmpSource> getEmpSourceDao() {
		return empSourceDao;
	}

	public void setEmpSourceDao(CommonDAO<EmpSource> empSourceDao) {
		this.empSourceDao = empSourceDao;
	}
	
	
}
