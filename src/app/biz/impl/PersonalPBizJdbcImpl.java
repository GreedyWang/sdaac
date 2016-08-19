package app.biz.impl;
/**
 * VAVE提案，批准率统计类
 */
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import common.dao.CommonDAO;
import common.util.MyUtil;

import sdaac.wym.app.entity.vave.ApproveRateItem;
import app.biz.DepartmentBiz;
import app.biz.EmpBiz;
import app.dao.Emppdao;
import app.entity.Tdepartment;
import app.entity.Tempolyee;
import app.entity.vave.Count;
import app.entity.vave.Pcount;


public class PersonalPBizJdbcImpl   {

	private JdbcTemplate jdbcTemplate;
	private Emppdao empdao=null;
	//private EmpBiz empBizDao;
	private DepartmentBiz departBiz;
	Connection cn = null; 
	PreparedStatement ps = null; 
	ResultSet rs = null; 
	JdbcTemplate jt;
	ResultSet uid;
	/**
	 * 使用sql存储过程
	 * @return
	 */
	public List aMethod()
	{
			jt = getJdbcTemplate();
			 List resultList = null;  

			jt.execute(new CallableStatementCreator (){

				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					// TODO �Զ���ɷ������
					CallableStatement cs;
					cs=con.prepareCall("{call hr_personalEffect(?)}");
					//cs=con.prepareCall("{call doTest()}");
					return cs;
				}
				
			}, new CallableStatementCallback (){

				public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					// TODO �Զ���ɷ������
					 List resultsMap =  null;  
				       try {  
				       cs.execute();   

				        }catch(SQLException e) {  

				          throw new RuntimeException("doInCallableStatement method error : SQLException " + e.getMessage());  

				        }  
				       return resultsMap; 
				}				
			});	
			return resultList; 
	}
	/**
	 * 通过JdbcTemplate用SQL查询
	 * 可以自己建个类实现RowMap接口，使得到的记录集，附值给一个实体类，完成一个OR功能，
	 * 见-》common.dao.impl.UserRowMap.java
	 * @param sql
	 * @return
	 */
	public List<Count> doSelectSQl(String sql)
	{
	
		List<Object[]> dd=empdao.doSelectByCondtion(sql);		
		List<Count> results=new ArrayList<Count>();
		for (Object[] element : dd) {		
			Count item=new Count();
			item.setDepartmentName(element[0].toString());
			item.setProposalNum((Integer)element[1]);
			item.setFinishNum((Integer)element[2]);
			item.setDoNum((Integer)element[3]);
			results.add(item);
		}			
		return results;
	}
	
	public List<Pcount> doSelectSpply(String sql)
	{
		List<Object[]> dd=empdao.doSelectByCondtion(sql);		
		List<Pcount> results=new ArrayList<Pcount>();
		for (Object[] element : dd) {		
			Pcount item=new Pcount();	
			item.setUname(element[0].toString());
			item.setDepartmentName("供应商");
			item.setProposalNum((Integer)element[1]);		
			item.setDoNum((Integer)element[2]);
			item.setFinishNum((Integer)element[3]);
			item.setTotal_saving((BigDecimal) element[4]);
			results.add(item);
		}			
		return results;
	}
	
	public List<Pcount> doSelectSQLPersonal(String sql)
	{
		List<Object[]> dd=empdao.doSelectByCondtion(sql);		
		List<Pcount> results=new ArrayList<Pcount>();
		for (Object[] element : dd) {		
			Pcount item=new Pcount();
			//item.setUid(element[0].toString());
			item.setProposalNum((Integer)element[0]);		
			item.setDoNum((Integer)element[1]);
			item.setFinishNum((Integer)element[2]);
			item.setDepartmentName(element[3].toString());
			item.setUname(element[4].toString());
			if(element.length>5)
			{
				item.setHlh((Integer)element[5]);
				item.setWAITCHANGE((Integer)element[6]);
				item.setDO_PROJECT((Integer)element[7]);
				item.setBEGIN_PROJECT((Integer)element[8]);
				item.setTotal_saving((BigDecimal) element[9]);
			}
		
			results.add(item);
		}			
		return results;
	}
	
	public List<Pcount> selectTeamWorks(String sql)
	{
		List<Object[]> dd=empdao.doSelectByCondtion(sql);		
		List<Pcount> results=new ArrayList<Pcount>();
		for (Object[] element : dd) {		
			Pcount item=new Pcount();	
			item.setUname(element[0].toString());
			item.setAllProjects((Integer)element[1]);
			item.setWAITCHANGE((Integer)element[2]);
			item.setDO_PROJECT((Integer)element[3]);
			item.setBEGIN_PROJECT((Integer)element[4]);
			results.add(item);
		}			
		return results;
	}
	
	/**
	 * 统计部门状态还未审批的提案
	 * @return
	 */
	public List<ApproveRateItem> doCountPM(int key){
		String sql="select d.name,count(*) al from tproposal p "+
					"inner join  dbo.tEmpolyee e on e.uid=p.proposal_person "+
					"inner join dbo.tDepartment d on e.departmentid=d.id "+
				//	"inner join tEmpolyee ff on d.pmangerid = ff.uid "+
					"where state=1 "; 
					if(key==2){
						sql+="and datediff(day,lastModifyTime,getdate())>7 ";
					}
					sql+="group by d.name";
		List<Object[]> dd=empdao.doSelectByCondtion(sql);	
		List<ApproveRateItem> results=new ArrayList<ApproveRateItem>();
		for (Object[] element : dd) {		
			ApproveRateItem item = new ApproveRateItem();
			item.setName(element[0].toString());//工号查姓名
			if(key==0){
				item.setCount((Integer)element[1]);
			}else{
				item.setCountOverC((Integer)element[1]);
			}		
			results.add(item);
		}
		return results;
	}
	

	/**
	 * 统计外部门状态还未审批的提案
	 */
	public List<ApproveRateItem> doCountOutsidePM(int key){
		mTemp = new HashMap<Integer, Integer>();
		String sql="select implementation_partment from tMainSuggestion m "+ 
				   "inner join dbo.tproposal p on p.id= m.proposalID "+
				   "inner join (select proposalID,max(suggestion_date) t from tMainSuggestion group by proposalID) v1 on m.proposalID=v1.proposalID and m.suggestion_date=v1.t "+
				   "where state=2 ";
		if(key==2){
			sql+="and datediff(day,suggestion_date,getdate())>7";
		}
		List<Object[]> dd=empdao.doSelectByCondtion(sql);	
		List<ApproveRateItem> results=new ArrayList<ApproveRateItem>();
		for (Object element : dd) {//统计					
			Integer[] uids =MyUtil.toTransFormInteger(element.toString().split(","));
			for(Integer uid:uids){
				accountKey(uid);
			}			
		}
		for(Integer mapKey:mTemp.keySet()){
			ApproveRateItem item = new ApproveRateItem();
			List<Tdepartment> rs = departBiz.getDepart(mapKey);
			if(!rs.isEmpty()){
				item.setName(rs.get(0).getName());//工号查姓名
				if(key==0){
					item.setCount(mTemp.get(mapKey));
				}else{
					item.setCountOverC(mTemp.get(mapKey));
				}		
				results.add(item);
			}
		}
		return results;
	}
	
	/**
	 * 查詢全部審批的提案數
	 * @param key
	 */
	public List<ApproveRateItem> doCountAllM(int key){
		String sql="select d.name,count(*) al from dbo.tMainSuggestion m "+	
			"inner join dbo.tProposal p on p.id=m.proposalID "+
				"inner join tempolyee e on e.name=m.approved_name "+
				"inner join tdepartment d on d.id=e.departmentid ";
		if(key==3){		
			sql+= "where datediff(day,lastModifyTime,suggestion_date) <=7 ";
		}					
		sql+="group by d.name ";
		
		List<Object[]> dd=empdao.doSelectByCondtion(sql);	
		List<ApproveRateItem> results=new ArrayList<ApproveRateItem>();
		for (Object[] element : dd) {		
			ApproveRateItem item = new ApproveRateItem();
			item.setName(element[0].toString());//工号查姓名
			if(key==3){
				item.setAll7((Integer)element[1]);
			}else{
				item.setAll((Integer)element[1]);
			}			
			results.add(item);
		}
		return results;
	}
	
	/**
	 * 查詢全部審批的提案數
	 * @param key
	 */
	public List<ApproveRateItem> doCountAllO(int key){
		String sql="select d.name,count(*) al from dbo.tOtherSuggestion o "+			
			"inner join dbo.tMainSuggestion m on m.proposalID=o.proposalID "+
			"inner join tempolyee e on e.name=m.approved_name "+
			"inner join tdepartment d on d.id=e.departmentid ";
		if(key==3){
			sql+="where datediff(day,m.suggestion_date,o.suggestion_date) >7 ";
		}					
		sql+="group by d.name ";
		
		List<Object[]> dd=empdao.doSelectByCondtion(sql);	
		List<ApproveRateItem> results=new ArrayList<ApproveRateItem>();
		for (Object[] element : dd) {		
			if(element[0]!=null){
				ApproveRateItem item = new ApproveRateItem();
				item.setName(element[0].toString());//工号查姓名
				if(key==3){
					item.setAll7((Integer)element[1]);
				}else{
					item.setAll((Integer)element[1]);
				}			
				results.add(item);
			}
		}
		return results;
	}
	
	
	//private List temp = new ArrayList();
	private Map<Integer, Integer> mTemp;
	
	/**
	 * 工具类 uid为key 累加key出现的次数 
	 */
	private void accountKey(Integer did){
		
		if(did!=null&&!did.equals("")){
			if(mTemp.get(did)==null){
				mTemp.put(did, 1);
			}else{
				int key = mTemp.get(did)+1;
				mTemp.put(did, key);
			}
		}
	}
	
	//get & set
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public Emppdao getEmpdao() {
		return empdao;
	}
	public void setEmpdao(Emppdao empdao) {
		this.empdao = empdao;
	}
//	public EmpBiz getEmpBizDao() {
//		return empBizDao;
//	}
//	public void setEmpBizDao(EmpBiz empBizDao) {
//		this.empBizDao = empBizDao;
//	}
	public DepartmentBiz getDepartBiz() {
		return departBiz;
	}
	public void setDepartBiz(DepartmentBiz departBiz) {
		this.departBiz = departBiz;
	}


}
