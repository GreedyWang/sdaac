package app.biz.impl.epor;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import app.entity.Tdepartment;
import app.entity.Tempolyee;
import app.entity.epor.PrBuyContext;
import app.entity.epor.PrPrForm;
import app.entity.epor.PrProject;
import app.entity.epor.StateManager;
import app.entity.epor.forms.SingleForm;
import common.dao.CommonDAO;


/**
 * 2010-8-4
 * @author sa1kv5
 * @version 1
 * 处理PR表单;pr表单内容
 *
 */
public class FormService {

	private CommonDAO<PrPrForm> prFormDao ;
	private CommonDAO<PrBuyContext> buyContext ;
	private CommonDAO<SingleForm> singleFormDao;
 	
	/**
	 * 提案提交
	 * @param 提案表，物品清单表//,List
	 * <PrBuyContext> buyContext
	 */
	public void doInsert(PrPrForm item) {
		this.prFormDao.insert(item);
	}

	/**
	 * 保存PR
	 * @param item
	 */
	public void savePrform(PrPrForm item) {
		item.saveState();
		this.doInsert(item);
	}
	

	/**
	 * 查询 用于PR导出数据 用于导出数据
	 */
	public List<PrPrForm> doSelectSS(PrPrForm item) {
		// TODO Auto-generated method stub in(8,9,10,11)
		String[] idss = {
		};
		String ids="" ;
		for(String temp:idss) {
			 ids += "'"+temp+"',";
		}		
//		 pf.source = 1 and pf.id in("+ids+"'a') 
//		   and pf.applicantDate >='2012-1-1'
//		and pf.tempolyeeByApplicantId.tdepartment.id = 10044 and year(pf.applicantDate)=2012 and month(pf.applicantDate)=12
		Calendar ca = Calendar.getInstance();
		String hql = "from PrPrForm as pf inner join fetch pf.tempolyeeByApplicantId "
				+ " join fetch pf.prProject "
				+ " join fetch pf.prCostCenter cc"
				+ " join fetch pf.tempolyeeByApplicantId.tdepartment "//
				+ " where pf.state =11 and pf.source = 1 and pf.id in("+ids+"'a')"//pf.tempolyeeByApplicantId.tdepartment.id = 10044 and pf.state > 0 "//  and source = 1"// " //"//pf.tempolyeeByApplicantId.tdepartment = 10026 and pf.applicantDate >='2012-1-1' pf.arno = 'sdp0804' and pf.state = 11"// //and pf.applicantDate >='2012-4-1' and state >=1"//"state >=1 and pf.applicantDate >= '2011-12-01' and pf.tempolyeeByApplicantId.tdepartment.id =10044  "////and year(pf.applicantDate)=2012" pf.id in("+ids+"'a')"//pf.id in("+ids+"'a')  and year(pf.applicantDate)=2012 and pf.state = 11 and pf.tempolyeeByApplicantId.tdepartment.id =10044  and pf.state > 0 " // and pf.id in("+ids+"'a')"and pf.state =11  and year(pf.applicantDate)=2012 " // and month(pf.applicantDate)<4";
				+ " order by pf.applicantDate";
		List<Object> vParams = new ArrayList<Object>();
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		if (vParams.isEmpty()) {
			list = this.prFormDao.select(hql, null);
		} else {
			list = this.prFormDao.select(hql, vParams.toArray(new Object[1]));
		}
		return list;
	}
	
	/**
	 * 查询用于PR导出数据的SQL版本
	 * PR流水号","购买理由","申请人","部门","成本中心","项目编号","总账科目","AR_NO","PR_SN","总价","采购员",
	 * "申请日期","资本化/费用化","计划内/计划外","状态","实际供应商","供应商是否在系统内",
	 * "用于那台设备","用于那个车间",
	 * "19 购买物品","数量","订单号","PO日期","到货日期","IO","Remark"};
	 */
	public List<Object[]> doSelectSSSQLVersion(PrPrForm item) {
		String sql = "select ssid,pf.remark,e.name,t.name,c.cost_center_name,p.sapno,totalCategroy,pf.arno,prsn,total,pf.buyerid applicantdate,iscapital,isplan,state,acutalSupplier,isInTheSap facilityToUse,workshopToUse,description,quantity,order_no,PODate,finishDate,bc.io,bc.remark from Pr_PrForm as pf  inner join tempolyee e on e.uid = pf.applicantid  inner join pr_project p on p.id = pf.projectid inner join pr_costcenter c on c.id = pf.cost_centerID inner join tdepartment t on t.id = e.departmentid inner join dbo.PR_buyContext bc on bc.prformid = pf.id where pf.source = 3 and pf.state=11";
		return prFormDao.selectBySql(sql);
	}
	
	public List<Object[]> doSelectBySQLVersion(String sql) {		
		return prFormDao.selectBySql(sql);
	}
	/**
	 * 查询
	 */
	public List<PrPrForm> doSelect(PrPrForm item) {
		// TODO Auto-generated method stub
		Calendar ca = Calendar.getInstance();
		String hql = "from PrPrForm as pf inner join fetch pf.tempolyeeByApplicantId "
				+ "  join fetch pf.prProject "
				+ "  join fetch pf.prCostCenter "
				+ " join fetch pf.tempolyeeByApplicantId.tdepartment "
				+ " where 1=1";
		List<Object> vParams = new ArrayList<Object>();
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		if (item.getId() != null && !item.getId().equals("")) {
			hql += " and pf.id=?";
			vParams.add(item.getId());
		}else if(0 == item.getSsid()){
			hql += " and pf.state>=0"; 
			if (item.getEndTime() != null && !item.getEndTime().equals("")) {
				hql += "and DATEDIFF(day, pf.applicantDate, ?) >=0";
				vParams.add(item.getEndTime());
			}else if(!item.flag){
				hql += "and year(pf.applicantDate)="+ca.get(Calendar.YEAR)+" and month(pf.applicantDate) > "+(ca.get(Calendar.MONTH)-4);
			}
			if (item.getBegintime() != null && !item.getBegintime().equals("")) {
				hql += "and DATEDIFF(day, pf.applicantDate, ?) <= 0 ";
				vParams.add(item.getBegintime());
			}
		}
		if(0!= item.getSsid()) {
			hql += " and pf.ssid=?";
			vParams.add(item.getSsid());
		}else {
			if(item.stateLz!=null && item.stateLz.equals("y")){
				hql += " and pf.state>=1 ";
			}
		}
		if(item.getSource()!=null) {
			hql += " and pf.source=?";
			vParams.add(item.getSource());
		}
		if (item.getState() != null) {
			hql += " and pf.state=?";
			vParams.add(item.getState());
		} 
		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
			hql += " and pf.state <> "+ StateManager.FINISH;
		}
		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
			hql += " and pf.state in ("+item.getQuertState()+")";
			//vParams.add(item.getQuertState().to);
		}
		if (item.getPrNo() != null && !item.getPrNo().equals("")) {
			hql += " and pf.prNo=?";
			vParams.add(item.getState());
		}
		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql += " and pf.tempolyeeByApplicantId.uid=?";
			vParams.add(item.getTempolyeeByApplicantId().getUid());
		}else {
			if (item.getTempolyeeByBuyerId()!= null
					&& !item.getTempolyeeByBuyerId().equals("")) {
				hql += " and pf.tempolyeeByBuyerId like ?";
				vParams.add("%" + item.getTempolyeeByBuyerId() + "%");
			}
		}


		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql += " and pf.tempolyeeByApplicantId.tdepartment.id=?";
			vParams.add(item.getTempolyeeByApplicantId().getTdepartment()
					.getId());
		}
		if (vParams.isEmpty()) {
			list = this.prFormDao.select(hql, null);
		} else {
			list = this.prFormDao.select(hql, vParams.toArray(new Object[1]));
		}
		return list;
	}
	
	/**
	 * 查询 
	 * 很多ID 用 in语句
	 */
	public List<PrPrForm> doSelectByIds(PrPrForm item) {
		// TODO Auto-generated method stub
		Calendar ca = Calendar.getInstance();
		String hql = "from PrPrForm as pf inner join fetch pf.tempolyeeByApplicantId "
				+ "  join fetch pf.prProject "
				+ "  join fetch pf.prCostCenter "
				+ " join fetch pf.tempolyeeByApplicantId.tdepartment "
				+ " where 1=1";
		List<Object> vParams = new ArrayList<Object>();
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		if (item.getId() != null && !item.getId().equals("")) {
			hql += " and pf.id in ("+item.getId()+")";
			
		}else if(0 == item.getSsid()){
			hql += " and pf.state>=0"; 
			if (item.getEndTime() != null && !item.getEndTime().equals("")) {
				hql += "and DATEDIFF(day, pf.applicantDate, ?) >=0";
				vParams.add(item.getEndTime());
			}else if(!item.flag){
				hql += "and year(pf.applicantDate)="+ca.get(Calendar.YEAR)+" and month(pf.applicantDate) > "+(ca.get(Calendar.MONTH)-4);
			}
			if (item.getBegintime() != null && !item.getBegintime().equals("")) {
				hql += "and DATEDIFF(day, pf.applicantDate, ?) <= 0 ";
				vParams.add(item.getBegintime());
			}
		}
		if(0!= item.getSsid()) {
			hql += " and pf.ssid=?";
			vParams.add(item.getSsid());
		}else {
			if(item.stateLz!=null && item.stateLz.equals("y")){
				hql += " and pf.state>=1 ";
			}
		}
		if(item.getSource()!=null) {
			hql += " and pf.source=?";
			vParams.add(item.getSource());
		}
		if (item.getState() != null) {
			hql += " and pf.state=?";
			vParams.add(item.getState());
		} 
		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
			hql += " and pf.state <> "+ StateManager.FINISH;
		}
		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
			hql += " and pf.state in ("+item.getQuertState()+")";
			//vParams.add(item.getQuertState().to);
		}
		if (item.getPrNo() != null && !item.getPrNo().equals("")) {
			hql += " and pf.prNo=?";
			vParams.add(item.getState());
		}
		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql += " and pf.tempolyeeByApplicantId.uid=?";
			vParams.add(item.getTempolyeeByApplicantId().getUid());
		}else {
			if (item.getTempolyeeByBuyerId()!= null
					&& !item.getTempolyeeByBuyerId().equals("")) {
				hql += " and pf.tempolyeeByBuyerId like ?";
				vParams.add("%" + item.getTempolyeeByBuyerId() + "%");
			}
		}


		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql += " and pf.tempolyeeByApplicantId.tdepartment.id=?";
			vParams.add(item.getTempolyeeByApplicantId().getTdepartment()
					.getId());
		}
		if (vParams.isEmpty()) {
			list = this.prFormDao.select(hql, null);
		} else {
			list = this.prFormDao.select(hql, vParams.toArray(new Object[1]));
		}
		return list;
	}
	
	/**
	 * 
	 * @param item
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<PrPrForm> doSelectLightByPagingIn(PrPrForm item,String start, String limit) {
		boolean flag = true;
		int Nlimit = 30,Nstart=0;
		if(start!=null && !start.equals("")) {
			if(start.endsWith("NaN")){Nstart=2;}else{
				Nstart= Integer.parseInt(start);
			}
			
		}
		int x = 30*Nstart/Nlimit;
		StringBuffer sql = new StringBuffer(" and ssid not in (select top "+x+"ssid from  pr_prform pf ");
					sql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					sql.append("inner join pr_project p on pf.projectid = p.id ");
					sql.append("inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
					sql.append("inner join dbo.tDepartment d on d.id = e.departmentid where 1=1 and state >=0 ");
		
		StringBuffer hql = new StringBuffer("select top 30 pf.id,e.name,d.name as dn,applicant_date,pf.remark,ssid,pf.info,pf.state,p.sapNo,isplan,iscapital,d.id as did from  pr_prform pf ");
					 hql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					 hql.append("inner join pr_project p on pf.projectid = p.id ");
					 hql.append("inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
					 hql.append("inner join dbo.tDepartment d on d.id = e.departmentid where 1=1 and state >=0 ");
		if (item.getPrCostCenter().getOwner() != null && !item.getPrCostCenter().getOwner().equals("")) {

			hql.append(" and c.owner like '%"+item.getPrCostCenter().getOwner()+"%'");
			sql.append(" and c.owner like '%"+item.getPrCostCenter().getOwner()+"%'");
		}	
		if(item.getDepartments()!=null && !"".equals(item.getDepartments())){
			if(item.getDepartments().contains("others")){
				hql.append(" and e.departmentid not in ("+item.getDepartments().split("-")[1]+")");
				sql.append(" and e.departmentid not in ("+item.getDepartments().split("-")[1]+")");
			}else{
				hql.append(" and e.departmentid in ("+item.getDepartments()+")");
				sql.append(" and e.departmentid in ("+item.getDepartments()+")");
			}
		}
		if (item.getId() != null && !item.getId().equals("")) {
			hql.append(" and pf.id in ("+item.getId()+")");
			sql.append(" and pf.id in ("+item.getId()+")");
		}
		if(0!= item.getSsid()) {
			hql.append(" and pf.ssid="+item.getSsid());
			sql.append(" and pf.ssid="+item.getSsid());
			flag = false;
		}
		if(item.getSourceRange()!=null && !"".equals(item.getSourceRange())) {
			hql.append(" and pf.source in ("+item.getSourceRange()+")");
			sql.append(" and pf.source in ("+item.getSourceRange()+")");
		}
		if (item.getState() != null) {
			hql.append(" and pf.state="+item.getState());
			sql.append(" and pf.state="+item.getState());
		} 
		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
			hql.append( " and pf.state <> "+ StateManager.FINISH);
			sql.append( " and pf.state <> "+ StateManager.FINISH);
		}
		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
			hql.append(" and pf.state in ("+item.getQuertState()+")");
			sql.append(" and pf.state in ("+item.getQuertState()+")");
			//vParams.add(item.getQuertState().to);
		}

		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
			sql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
		}
		if(item.stateLz!=null && item.stateLz.equals("y")){
			hql.append(" and pf.state>=1 ");
			sql.append(" and pf.state>=1 ");
		}
		if (item.getTempolyeeByBuyerId()!= null
				&& !item.getTempolyeeByBuyerId().equals("")) {
			hql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
			sql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
		}
		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());
			sql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());

		}

		if (item.getEndTime() != null && !item.getEndTime().equals("")) {
			
			hql.append(" and DATEDIFF(day, pf.applicant_date, '"+item.getEndTime()+"') >= 0");
			sql.append(" and DATEDIFF(day, pf.applicant_date, '"+item.getEndTime()+"') >= 0");
			flag = false;
		}
		if (item.getBegintime() != null && !item.getBegintime().equals("")) {
			hql.append(" and DATEDIFF(day, '"+item.getBegintime()+"',pf.applicant_date) >=0 ");
			sql.append(" and DATEDIFF(day, '"+item.getBegintime()+"',pf.applicant_date) >=0 ");
			flag = false;
		}
	
		sql.append("order by applicant_date desc)");
		hql.append(sql);
		hql.append(" order by applicant_date desc");
		
		List<Object[]> rs = this.prFormDao.selectBySql(hql.toString());
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		for(Object[] aa : rs) {
			PrPrForm item2 = new PrPrForm();
			Tempolyee emp = new Tempolyee("",aa[1].toString());
			emp.setTdepartment(new Tdepartment((Integer)aa[11],aa[2].toString()));
			item2.setTempolyeeByApplicantId(emp);
			item2.setId(aa[0].toString());
			item2.setInfo(aa[6]==null?0:(Integer)aa[6]);
			item2.setSsid(aa[5]==null?0:(Integer)aa[5]);
			String applicantDate = aa[3].toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				item2.setApplicantDate(df.parse(applicantDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			item2.setState(aa[7]==null?0:(Integer)aa[7]);
			item2.setRemark(aa[4]==null?"":aa[4].toString());
			PrProject project = new PrProject();		
			project.setNumber(aa[8].toString());
			project.setId(1);
			item2.setPrProject(project);
			item2.setIsPlan((Integer)aa[9]);
			item2.setIsCapital((Integer)aa[10]);
			list.add(item2);
		}
		
		return list;
	}
	
	public List<PrPrForm> doSelectLightByPaging(PrPrForm item,String start, String limit) {
		boolean flag = true;
		int Nlimit = 30,Nstart=0;
		if(start!=null && !start.equals("")) {
			if(start.endsWith("NaN")){Nstart=2;}else{
				Nstart= Integer.parseInt(start);
			}
			
		}
		int x = 30*Nstart/Nlimit;
		StringBuffer sql = new StringBuffer(" and ssid not in (select top "+x+"ssid from  pr_prform pf ");
					sql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					sql.append("inner join pr_project p on pf.projectid = p.id ");
					sql.append("inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
					sql.append("inner join dbo.tDepartment d on d.id = e.departmentid where 1=1 and state >=0 ");
		
		StringBuffer hql = new StringBuffer("select top 30 pf.id,e.name,d.name as dn,applicant_date,pf.remark,ssid,pf.info,pf.state,p.sapNo,isplan,iscapital,d.id as did,processInstanceId from pr_prform pf ");
					 hql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					 hql.append("inner join pr_project p on pf.projectid = p.id ");
					 hql.append("inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
					 hql.append("inner join dbo.tDepartment d on d.id = e.departmentid where 1=1 and state >=0 ");
		if (item.getPrCostCenter().getOwner() != null && !item.getPrCostCenter().getOwner().equals("")) {

			hql.append(" and c.owner like '%"+item.getPrCostCenter().getOwner()+"%'");
			sql.append(" and c.owner like '%"+item.getPrCostCenter().getOwner()+"%'");
		}	
		if(item.getDepartments()!=null && !"".equals(item.getDepartments())){
			if(item.getDepartments().contains("others")){
				hql.append(" and e.departmentid not in ("+item.getDepartments().split("-")[1]+")");
				sql.append(" and e.departmentid not in ("+item.getDepartments().split("-")[1]+")");
			}else{
				hql.append(" and e.departmentid in ("+item.getDepartments()+")");
				sql.append(" and e.departmentid in ("+item.getDepartments()+")");
			}
		}
		if (item.getId() != null && !item.getId().equals("")) {
			hql.append(" and pf.id="+item.getId());
			sql.append(" and pf.id="+item.getId());
		}
		if(0!= item.getSsid()) {
			hql.append(" and pf.ssid="+item.getSsid());
			sql.append(" and pf.ssid="+item.getSsid());
			flag = false;
		}
		if(item.getTempolyeeByApplicantId()!= null && "".equals(item.getTempolyeeByApplicantId().getUid()) && item.getSourceRange()!=null && !"".equals(item.getSourceRange())) {
			hql.append(" and pf.source in ("+item.getSourceRange()+")");
			sql.append(" and pf.source in ("+item.getSourceRange()+")");
		}
		if (item.getState() != null) {
			hql.append(" and pf.state="+item.getState());
			sql.append(" and pf.state="+item.getState());
		}
		if (item.getInfo() != null && -2!=item.getInfo()) {
			hql.append(" and pf.info="+item.getInfo());
			sql.append(" and pf.info="+item.getInfo());
		}
		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
			hql.append( " and pf.state <> "+ StateManager.FINISH);
			sql.append( " and pf.state <> "+ StateManager.FINISH);
		}
		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
			hql.append(" and pf.state in ("+item.getQuertState()+")");
			sql.append(" and pf.state in ("+item.getQuertState()+")");
			//vParams.add(item.getQuertState().to);
		}

		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
			sql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
		}

		if(item.getCapitalcategroy()!=null ){
			hql.append(" and pf.capitalcategroy="+item.getCapitalcategroy());
			sql.append(" and pf.capitalcategroy="+item.getCapitalcategroy());
		}
		if(item.getIsCapital()!=null ){
			hql.append(" and pf.isCapital="+item.getIsCapital());
			sql.append(" and pf.isCapital="+item.getIsCapital());
		}
		if (item.getTempolyeeByBuyerId()!= null
				&& !item.getTempolyeeByBuyerId().equals("")) {
			hql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
			sql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
		}
		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());
			sql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());

		}

		if (item.getEndTime() != null && !item.getEndTime().equals("")) {
			
			hql.append(" and DATEDIFF(day, pf.applicant_date, '"+item.getEndTime()+"') >= 0");
			sql.append(" and DATEDIFF(day, pf.applicant_date, '"+item.getEndTime()+"') >= 0");
			flag = false;
		}
		if (item.getBegintime() != null && !item.getBegintime().equals("")) {
			hql.append(" and DATEDIFF(day, '"+item.getBegintime()+"',pf.applicant_date) >=0 ");
			sql.append(" and DATEDIFF(day, '"+item.getBegintime()+"',pf.applicant_date) >=0 ");
			flag = false;
		}
		if (item.getTotal() != null ) {
			hql.append(" and total >= 500000 and state >=5 ");
			sql.append(" and total >= 500000 and state >=5 ");
			flag = false;
		}
		//增加项目经理
		if(item.getPrProject()!=null && item.getPrProject().getManagerUid()!=null && !"".equals(item.getPrProject().getManagerUid())){
			hql.append(" and p.managerUid ='"+item.getPrProject().getManagerUid()+"'");
			sql.append(" and p.managerUid ='"+item.getPrProject().getManagerUid()+"'");
		}
		sql.append("order by applicant_date desc)");
		hql.append(sql);
		hql.append(" order by applicant_date desc");
		
		List<Object[]> rs = this.prFormDao.selectBySql(hql.toString());
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		for(Object[] aa : rs) {
			PrPrForm item2 = new PrPrForm();
			Tempolyee emp = new Tempolyee("",aa[1].toString());
			emp.setTdepartment(new Tdepartment((Integer)aa[11],aa[2].toString()));
			item2.setTempolyeeByApplicantId(emp);
			item2.setId(aa[0].toString());
			item2.setInfo(aa[6]==null?0:(Integer)aa[6]);
			item2.setSsid(aa[5]==null?0:(Integer)aa[5]);
			String applicantDate = aa[3].toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				item2.setApplicantDate(df.parse(applicantDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			item2.setState(aa[7]==null?0:(Integer)aa[7]);
			item2.setRemark(aa[4]==null?"":aa[4].toString());
			PrProject project = new PrProject();
			project.setNumber(aa[8]==null?"":aa[8].toString());
			project.setId(1);
			item2.setPrProject(project);
			item2.setIsPlan((Integer)aa[9]);
			item2.setIsCapital((Integer)aa[10]);
			item2.setProcessInstanceId(aa[12]==null?"":aa[12].toString());
			list.add(item2);
		}
		
		return list;
	}
	
	public List<PrPrForm> doSelectLight(PrPrForm item,String ids) {
		// TODO Auto-generated method stub
		boolean flag = true;
		StringBuffer hql = new StringBuffer("select pf.id,e.name,d.name as dn,applicant_date,pf.remark,ssid,pf.info,pf.state,p.sapNo,isplan,iscapital,d.id as did from  pr_prform pf ");
					 hql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					 hql.append("inner join pr_project p on pf.projectid = p.id ");
					 hql.append("inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
					 hql.append("inner join dbo.tDepartment d on d.id = e.departmentid where state>=0 ");
	    if(ids!=null && !"".equals(ids)){		
	    	hql.append(" and pf.id in ("+ids+")");
	    }
		if (item.getPrCostCenter().getOwner() != null && !item.getPrCostCenter().getOwner().equals("")) {
			hql.append(" and c.owner like '%"+item.getPrCostCenter().getOwner()+"%'");
			
		}	
		if(item.getDepartments()!=null && !"".equals(item.getDepartments())){
			if(item.getDepartments().contains("others")){
				hql.append(" and e.departmentid not in ("+item.getDepartments().split("-")[1]+")");
			}else{
				hql.append(" and e.departmentid in ("+item.getDepartments()+")");
			}
		}
		if (item.getId() != null && !item.getId().equals("")){
			hql.append(" and pf.id="+item.getId());
		}
		
		if (item.getIsCapital() != null ){
			hql.append(" and pf.isCapital="+item.getIsCapital());
		}
		if(0!= item.getSsid()) {
			hql.append(" and pf.ssid="+item.getSsid());
			flag = false;
		}
		//上海权限人员审批某几部其他来源的PR
//		if(0== item.getSsid() && item.getQuertState()!= null&&!item.getQuertState().contains("52")&&!item.getQuertState().contains("58")&&!item.getQuertState().contains("55")&&!item.getQuertState().contains("59")&&!item.getQuertState().contains("57")){
//			if(item.getSourceRange()!=null && !"".equals(item.getSourceRange())) {
//				hql.append(" and pf.source in ("+item.getSourceRange()+")");
//			}
//		}
		//项目经理
//		if(item.getQuertState()!=null && item.getQuertState().contains("12")){
//			hql.append(" and p.managerUid like '%"+item.getRemark()+"%'");
//		}
//		if(item.getRelateuid()!=null && !"".endsWith(item.getRelateuid())){
//			hql.append(" and pf.relateuid like '%"+item.getRelateuid()+"%'");
//		}
//		if ( item.getState() != null) {
//			hql.append(" and pf.state="+item.getState());
//		} 
//		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
//			hql.append( " and pf.state <> "+ StateManager.FINISH);
//		}
//		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
//			hql.append(" and pf.state in ("+item.getQuertState()+")");
//		}

		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
		}
		if (item.getTempolyeeByApplicantId().getSupervisor() != null
				&& !item.getTempolyeeByApplicantId().getSupervisor().equals("")) {
			hql.append(" and e.supervisor='"+item.getTempolyeeByApplicantId().getSupervisor()+"'");
		}
		if(item.stateLz!=null && item.stateLz.equals("y")){
			hql.append(" and pf.state>=1 ");
		}
		if (item.getTempolyeeByBuyerId()!= null
				&& !item.getTempolyeeByBuyerId().equals("")) {
			hql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
		}
		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());

		}

		if (item.getEndTime() != null && !item.getEndTime().equals("")) {
			
			hql.append(" and DATEDIFF(day, pf.applicant_date, "+item.getEndTime()+") <= 0");
			flag = false;
		}
		if (item.getBegintime() != null && !item.getBegintime().equals("")) {
			hql.append(" and DATEDIFF(day, "+item.getBegintime()+",pf.applicant_date) >=0");
			flag = false;
		}

		hql.append(" order by applicant_date desc");
		
		List<Object[]> rs = this.prFormDao.selectBySql(hql.toString());
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		for(Object[] aa : rs) {
			PrPrForm item2 = new PrPrForm();
			Tempolyee emp = new Tempolyee("",aa[1].toString());
			emp.setTdepartment(new Tdepartment((Integer)aa[11],aa[2].toString()));
			item2.setTempolyeeByApplicantId(emp);
			item2.setId(aa[0].toString());
			item2.setInfo(aa[6]==null?0:(Integer)aa[6]);
			item2.setSsid(aa[5]==null?0:(Integer)aa[5]);
			String applicantDate = aa[3].toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				item2.setApplicantDate(df.parse(applicantDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			item2.setState(aa[7]==null?0:(Integer)aa[7]);
			item2.setRemark(aa[4]==null?"":aa[4].toString());
			PrProject project = new PrProject();		
			project.setNumber(aa[8]==null?"":aa[8].toString());
			project.setId(1);
			item2.setPrProject(project);
			item2.setIsPlan((Integer)aa[9]);
			item2.setIsCapital((Integer)aa[10]);
			list.add(item2);
		}
		
		return list;
	}
	
	
	/**
	 * 查询light
	 * @since 2012-7-29
	 * @<p> add one more condition : isCaptial</p>
	 */
	public List<PrPrForm> doSelectLight(PrPrForm item) {
		// TODO Auto-generated method stub
		boolean flag = true;
		StringBuffer hql = new StringBuffer("select pf.id,e.name,d.name as dn,applicant_date,pf.remark,ssid,pf.info,pf.state,p.sapNo,isplan,iscapital,d.id as did from  pr_prform pf ");
					 hql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					 hql.append("inner join pr_project p on pf.projectid = p.id ");
					 hql.append("inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
					 hql.append("inner join dbo.tDepartment d on d.id = e.departmentid where 1=1 and state >=0 ");
		if (item.getPrCostCenter().getOwner() != null && !item.getPrCostCenter().getOwner().equals("")) {
			hql.append(" and c.owner like '%"+item.getPrCostCenter().getOwner()+"%'");
			
		}	
		if(item.getDepartments()!=null && !"".equals(item.getDepartments())){
			if(item.getDepartments().contains("others")){
				hql.append(" and e.departmentid not in ("+item.getDepartments().split("-")[1]+")");
			}else{
				hql.append(" and e.departmentid in ("+item.getDepartments()+")");
			}
		}
		if (item.getId() != null && !item.getId().equals("")){
			hql.append(" and pf.id="+item.getId());
		}
		
		if (item.getIsCapital() != null ){
			hql.append(" and pf.isCapital="+item.getIsCapital());
		}
		if(0!= item.getSsid()) {
			hql.append(" and pf.ssid="+item.getSsid());
			flag = false;
		}
		//上海权限人员审批某几部其他来源的PR
		if(0== item.getSsid() && item.getQuertState()!= null&&!item.getQuertState().contains("52")&&!item.getQuertState().contains("58")&&!item.getQuertState().contains("55")&&!item.getQuertState().contains("59")&&!item.getQuertState().contains("57")){
			if(item.getSourceRange()!=null && !"".equals(item.getSourceRange())) {
				hql.append(" and pf.source in ("+item.getSourceRange()+")");
			}
		}
		//项目经理
		if(item.getQuertState()!=null && item.getQuertState().contains("12")){
			hql.append(" and p.managerUid like '%"+item.getRemark()+"%'");
		}
		if(item.getRelateuid()!=null && !"".endsWith(item.getRelateuid())){
			hql.append(" and pf.relateuid like '%"+item.getRelateuid()+"%'");
		}
		if ( item.getState() != null) {
			hql.append(" and pf.state="+item.getState());
		} 
		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
			hql.append( " and pf.state <> "+ StateManager.FINISH);
		}
		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
			hql.append(" and pf.state in ("+item.getQuertState()+")");
		}

		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
		}
		if (item.getTempolyeeByApplicantId().getSupervisor() != null
				&& !item.getTempolyeeByApplicantId().getSupervisor().equals("")) {
			hql.append(" and e.supervisor='"+item.getTempolyeeByApplicantId().getSupervisor()+"'");
		}
		if(item.stateLz!=null && item.stateLz.equals("y")){
			hql.append(" and pf.state>=1 ");
		}
		if (item.getTempolyeeByBuyerId()!= null
				&& !item.getTempolyeeByBuyerId().equals("")) {
			hql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
		}
		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());

		}

		if (item.getEndTime() != null && !item.getEndTime().equals("")) {
			
			hql.append(" and DATEDIFF(day, pf.applicant_date, "+item.getEndTime()+") <= 0");
			flag = false;
		}
		if (item.getBegintime() != null && !item.getBegintime().equals("")) {
			hql.append(" and DATEDIFF(day, "+item.getBegintime()+",pf.applicant_date) >=0");
			flag = false;
		}

		hql.append(" order by applicant_date desc");
		
		List<Object[]> rs = this.prFormDao.selectBySql(hql.toString());
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		for(Object[] aa : rs) {
			PrPrForm item2 = new PrPrForm();
			Tempolyee emp = new Tempolyee("",aa[1].toString());
			emp.setTdepartment(new Tdepartment((Integer)aa[11],aa[2].toString()));
			item2.setTempolyeeByApplicantId(emp);
			item2.setId(aa[0].toString());
			item2.setInfo(aa[6]==null?0:(Integer)aa[6]);
			item2.setSsid(aa[5]==null?0:(Integer)aa[5]);
			String applicantDate = aa[3].toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				item2.setApplicantDate(df.parse(applicantDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			item2.setState(aa[7]==null?0:(Integer)aa[7]);
			item2.setRemark(aa[4]==null?"":aa[4].toString());
			PrProject project = new PrProject();		
			project.setNumber(aa[8]==null?"":aa[8].toString());
			project.setId(1);
			item2.setPrProject(project);
			item2.setIsPlan((Integer)aa[9]);
			item2.setIsCapital((Integer)aa[10]);
			list.add(item2);
		}
		
		return list;
	}
	
	/**
	 * search total count by SQL
	 */
	public int doSelectLightCountIn(PrPrForm item) {
		// TODO Auto-generated method stub
		boolean flag = true;
		StringBuffer hql = new StringBuffer("select count(*) from  pr_prform pf ");
					 hql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					 hql.append("inner join pr_project p on pf.projectid = p.id ");
					 hql.append("inner join dbo.tDepartment d on d.id = e.departmentid where 1=1 and state >=0 ");
					 
		if (item.getId() != null && !item.getId().equals("")) {
			hql.append(" and pf.id in ("+item.getId()+")");
		}
		if(0!= item.getSsid()) {
			hql.append(" and pf.ssid="+item.getSsid());
			flag = false;
		}
		if(item.getSourceRange()!=null && !"".equals(item.getSourceRange())) {
			hql.append(" and pf.source in ("+item.getSourceRange()+")");
		}
		if (item.getState() != null) {
			hql.append(" and pf.state="+item.getState());
		} 
		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
			hql.append( " and pf.state <> "+ StateManager.FINISH);
		}
		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
			hql.append(" and pf.state in ("+item.getQuertState()+")");
			//vParams.add(item.getQuertState().to);
		}

		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
		}
		if(item.stateLz!=null && item.stateLz.equals("y")){
			hql.append(" and pf.state>=1 ");
		}
		if (item.getTempolyeeByBuyerId()!= null
				&& !item.getTempolyeeByBuyerId().equals("")) {
			hql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
		}
		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());

		}

		if (item.getEndTime() != null && !item.getEndTime().equals("")) {
			
			hql.append(" and DATEDIFF(day, pf.applicant_date, '"+item.getEndTime()+"') >= 0");
			flag = false;
		}
		if (item.getBegintime() != null && !item.getBegintime().equals("")) {
			hql.append(" and DATEDIFF(day, '"+item.getBegintime()+"',pf.applicant_date) >=0");
			flag = false;
		}	
		List rs = this.prFormDao.selectBySql(hql.toString());		
		return (Integer)rs.get(0);
	}
	
	
	/**
	 * search total count by SQL
	 */
	public int doSelectLightCount(PrPrForm item) {
		// TODO Auto-generated method stub
		boolean flag = true;
		StringBuffer hql = new StringBuffer("select count(*) from  pr_prform pf ");
					 hql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
					 hql.append("inner join pr_project p on pf.projectid = p.id ");
					 hql.append("inner join dbo.tDepartment d on d.id = e.departmentid where 1=1 and state >=0 ");
					 
		if (item.getId() != null && !item.getId().equals("")) {
			hql.append(" and pf.id="+item.getId());
		}
		if (item.getInfo() != null && -2!=item.getInfo()) {
			hql.append(" and pf.info="+item.getInfo());
			//sql.append(" and pf.info="+item.getInfo());
		}
		if(0!= item.getSsid()) {
			hql.append(" and pf.ssid="+item.getSsid());
			flag = false;
		}
		if(item.getTempolyeeByApplicantId()!= null && "".equals(item.getTempolyeeByApplicantId().getUid()) && item.getSourceRange()!=null && !"".equals(item.getSourceRange())) {
			hql.append(" and pf.source in ("+item.getSourceRange()+")");
		}
		if (item.getState() != null) {
			hql.append(" and pf.state="+item.getState());
		} 
		if(null !=item.getIsFin() && item.getIsFin().equals("n") && item.getState() == null) {
			hql.append( " and pf.state <> "+ StateManager.FINISH);
		}
		if (item.getQuertState() != null&&!"".equals(item.getQuertState())) {
			hql.append(" and pf.state in ("+item.getQuertState()+")");
			//vParams.add(item.getQuertState().to);
		}

		if (item.getTempolyeeByApplicantId().getUid() != null
				&& !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql.append(" and e.uid='"+item.getTempolyeeByApplicantId().getUid()+"'");
		}
		if (item.getTempolyeeByBuyerId()!= null
				&& !item.getTempolyeeByBuyerId().equals("")) {
			hql.append( " and pf.buyerID like "+"'%" + item.getTempolyeeByBuyerId() + "%'");
		}
		if (item.getTempolyeeByApplicantId().getTdepartment().getId() != null) {
			hql.append(" and d.id="+item.getTempolyeeByApplicantId().getTdepartment()
					.getId());

		}

		if (item.getEndTime() != null && !item.getEndTime().equals("")) {
			
			hql.append(" and DATEDIFF(day, pf.applicant_date, '"+item.getEndTime()+"') >= 0");
			flag = false;
		}
		if (item.getBegintime() != null && !item.getBegintime().equals("")) {
			hql.append(" and DATEDIFF(day, '"+item.getBegintime()+"',pf.applicant_date) >=0");
			flag = false;
		}	
		if (item.getTotal() != null) {
			hql.append(" and total >=500000 and state >=5");
			flag = false;
		}
		//增加项目经理
		if(item.getPrProject()!=null && item.getPrProject().getManagerUid()!=null){
			hql.append(" and p.managerUid ='"+item.getPrProject().getManagerUid()+"'");
			//sql.append(" and p.managerUid ="+item.getPrProject().getManagerUid());
		}
		List rs = this.prFormDao.selectBySql(hql.toString());		
		return (Integer)rs.get(0);
	}
	
	
	public void updatePrBuyer(int ssid,String buyerUid){
		String hql="update PrPrForm set tempolyeeByBuyerId =? where ssid = ?";
		prFormDao.bulkUpdate(hql, new Object[]{buyerUid,ssid});
	}
	
	/**
	 * 更新pr
	 */
	public void updatePrForm(PrPrForm prForm) {
		String hql = "update PrPrForm set ";
		List params = new ArrayList();
		if (prForm.getId() != null&&!"".equals(prForm.getId())) {
			
			if (prForm.getBigCountNo() != null) {
				hql += " bigCountNo=?,";
				params.add(prForm.getBigCountNo());
			}
			if (prForm.getWorkshopToUse() != null) {
				hql += " workshopToUse=?,";
				params.add(prForm.getWorkshopToUse());
			}
			if (prForm.getCapitalcategroy() != null) {
				hql += " capitalcategroy=?,";
				params.add(prForm.getCapitalcategroy());
			}
			if (prForm.getIsOtherCostCenter() != null) {
				hql += " isOtherCostCenter=?,";
				params.add(prForm.getIsOtherCostCenter());
			}if (prForm.getRelateuid() != null && !"".equals(prForm.getRelateuid())) {
				hql += " relateuid=?,";
				params.add(prForm.getRelateuid());
			}if (prForm.getSinglesourceFile() != null&&!"".equals(prForm.getSinglesourceFile())) {
				hql += " singlesourceFile=?,";
				params.add(prForm.getSinglesourceFile());
			}if (prForm.getUrgentFile() != null&&!"".equals(prForm.getUrgentFile())) {
				hql += " urgentFile=?,";
				params.add(prForm.getUrgentFile());
			}if (prForm.getBigcountFile() != null&&!"".equals(prForm.getBigcountFile())) {
				hql += " bigcountFile=?,";
				params.add(prForm.getBigcountFile());
			}if (prForm.getFileName() != null&&!"".equals(prForm.getFileName())) {
				hql += " fileName=?,";
				params.add(prForm.getFileName());
			}if (prForm.getBuyerFile() != null&&!"".equals(prForm.getBuyerFile())) {
				hql += " buyerFile=?,";
				params.add(prForm.getBuyerFile());
			}
			if (prForm.getInfo() != null) {
				hql += " info=?,";
				params.add(prForm.getInfo());
			}
			if (prForm.getType() != null) {
				hql += " type=?,";
				params.add(prForm.getType());
			}
//			if (prForm.getBuyerfileName() != null && !"".equals(prForm.getBuyerfileName()) ) {
//				hql += " buyerfileName=?,";
//				params.add(prForm.getBuyerfileName());
//			}
			if (prForm.getSource() != null && !"".equals(prForm.getSource()) ) {
				hql += " source=?,";
				params.add(prForm.getSource());
			}
			if (prForm.getArno() != null&&!"".equals(prForm.getArno())) {
				hql += " arno=?,";
				params.add(prForm.getArno());
			}
			if (prForm.getPrCostCenter().getId() != null) { //如果需要改变成本中心，要记录下当前状态待审批完后跳转回来
				hql += " prCostCenter.id=?,lastState=?,";
				params.add(prForm.getPrCostCenter().getId());
				params.add(prForm.getLastState());
			}
			if (prForm.getPrProject().getId() != null) {
				hql += " prProject.id=?,";
				params.add(prForm.getPrProject().getId());
			}
			if (prForm.getPrsn() != null) {
				hql += " prsn=?,";
				params.add(prForm.getPrsn());
			}
			if (prForm.getIsAssignation() != null) {
				hql += " isAssignation=?,";
				params.add(prForm.getIsAssignation());
			}
			if (prForm.getIsAssignationB() != null) {
				hql += " isAssignationB=?,";
				params.add(prForm.getIsAssignationB());
			}
			if (prForm.getIsUrgency() != null) {
				hql += " isUrgency=?,";
				params.add(prForm.getIsUrgency());
			}
			if (prForm.getIsUrgencyB() != null) {
				hql += " isUrgencyB=?,";
				params.add(prForm.getIsUrgencyB());
			}
//			if (prForm.getVersion() != null) {
//				hql += " version=?,";
//				params.add(prForm.getVersion());
//			}
			if (prForm.getTempolyeeByBuyerId()!= null && !"".equals(prForm.getTempolyeeByBuyerId())) {
				hql += " tempolyeeByBuyerId=?,";
				params.add(prForm.getTempolyeeByBuyerId().trim());
			}
			if (prForm.getState() != null) {
				hql += " state=?,";
				params.add(prForm.getState());
			}
			if (prForm.getIo() != null) {
				hql += " io=?,";
				params.add(prForm.getIo());
			}
//			if (prForm.getSource() != null) {
//
//					hql += " source=?,";
//					params.add(prForm.getSource());
//				
//			}
			if (prForm.getRecommendedSupplier() != null) {
				hql += " recommendedSupplier=?,";
				params.add(prForm.getRecommendedSupplier());
			}
			if (prForm.getAcutalSupplier() != null&&!"".equals(prForm.getAcutalSupplier())) {
				hql += " acutalSupplier=?,";
				params.add(prForm.getAcutalSupplier());
			}
			if (prForm.getTelephone() != null) {
				hql += " telephone=?,";
				params.add(prForm.getTelephone());
			}
			if (prForm.getTotal() != null) {
				hql += " total=?,";
				params.add(prForm.getTotal());
			}
			if (prForm.getTotalCategroy() != null) {
				hql += " totalCategroy=?,";
				params.add(prForm.getTotalCategroy());
			}
			if (prForm.getIsPlan() != null) {
				hql += " isPlan=?,";
				params.add(prForm.getIsPlan());
			}
			if (prForm.getIsCapital() != null) {// bug
				hql += " isCapital=?,";
				params.add(prForm.getIsCapital());
			}
			if (prForm.getNorth_AmericaFileName() != null
					&& !prForm.getNorth_AmericaFileName().equals("")) {
				hql += " north_AmericaFileName=?,";
				params.add(prForm.getNorth_AmericaFileName());
			}
			if (prForm.getReceivingPlacePerpoleTel() != null
					&& !prForm.getReceivingPlacePerpoleTel().equals("")) {
				hql += " receivingPlacePerpoleTel=?,";
				params.add(prForm.getReceivingPlacePerpoleTel());
			}
			if (prForm.getIsInTheSap() != null) {
				hql += " isInTheSap=?,";
				params.add(prForm.getIsInTheSap());
			}
			if (prForm.getRemark() != null) {
				hql += " remark=?,";
				params.add(prForm.getRemark());
			}
			if (!params.isEmpty()) {
				hql = hql.substring(0, hql.length() - 1);
				hql += " where id=?";
				params.add(prForm.getId());
				prFormDao.bulkUpdate(hql, params.toArray(new Object[1]));
			}
		}
	}

	/**
	 * 更新pr的状态
	 */
	public boolean updatePrState(PrPrForm prForm, Integer state) {
		boolean flag = true;
		String hql = "update PrPrForm as pr set pr.state=? ,info=?,lastState=? where pr.id=? ";
		this.prFormDao.bulkUpdate(hql, new Object[] { state,prForm.getInfo(),prForm.getLastState(), prForm.getId() });
		return flag;
	}
	
	/**
	 * 删除PR
	 */
	public boolean doDelete(PrPrForm item) {
		boolean flag = false;
		//if (item.getState() == StateManager.SELF) {
			PrPrForm a = new PrPrForm();//状态为-1 为删除
			a.setId(item.getId());
			a.setState(StateManager.DELETE);
			updatePrForm(a);
			flag = true;
	//	}
		return flag;
	}
	
	/**
	 * 更新PR
	 */
	private void doUpdateBuyContext(PrPrForm item) {
		for (PrBuyContext con : item.getPrBuyContexts())
			updatePrContext(con);
	}
	
	public void doUpdateBuyContext(List<PrBuyContext> items) {
		for (PrBuyContext con : items)
			updatePrContextSimple(con);
	}
	
	/**
	 * 更新采购内容
	 */
	public void updatePrContextSimple(PrBuyContext item) {
		String hql = "update PrBuyContext set";
		List params = new ArrayList();
		if (item.getId() != null && !item.getId().equals("")) {
			if (item.getRemark() != null) {
				hql += " remark=?,";
				params.add(item.getRemark());
			}
			if (!params.isEmpty()) {
				hql = hql.substring(0, hql.length() - 1);
				hql += " where id=?";
				params.add(item.getId());
				buyContext.bulkUpdate(hql, params.toArray(new Object[1]));
			}
		}
	}
	
	/**
	 * 更新采购内容
	 */
	public void updatePrContext(PrBuyContext item) {
		String hql = "update PrBuyContext set";
		List params = new ArrayList();
		if (item.getId() != null && !item.getId().equals("")) {
			if (item.getDescription() != null) {
				hql += " description=?,";
				params.add(item.getId());
			}
			if (item.getQuantity() != null) {
				hql += " quantity=?,";
				params.add(item.getQuantity());
			}
			if (item.getPODate() != null) {
				hql += " PODate=?,";
				params.add(item.getPODate());
			}
			if (item.getFinishDate() != null) {
				hql += " finishDate=?,";
				params.add(item.getFinishDate());
			}
			if (item.getUnit() != null) {
				hql += " unit=?,";
				params.add(item.getUnit());
			}
			if (item.getIo() != null && !"".equals(item.getIo())) {
				hql += " io=?,";
				params.add(item.getIo());
			}
			if (item.getExpectDeliveryDate() != null) {
				hql += " expectDeliveryDate=?,";
				params.add(item.getExpectDeliveryDate());
			}
			if (item.getUnitPrice() != null ) {
				hql += " unitPrice=?,";
				params.add(item.getUnitPrice());
			}
			if (item.getAmount() != null) {
				hql += " amount=?,";
				params.add(item.getAmount());
			}
			if (item.getOrderNo() != null) {
				hql += " orderNo=?,";
				params.add(item.getOrderNo());
			}if (item.getGlAccount() != null) {
				hql += " glAccount=?,";
				params.add(item.getGlAccount());
			}
			if (item.getCurrency() != null) {
				hql += " currency=?,";
				params.add(item.getCurrency());
			}
			if (!params.isEmpty()) {
				hql = hql.substring(0, hql.length() - 1);
				hql += " where id=?";
				params.add(item.getId());
				buyContext.bulkUpdate(hql, params.toArray(new Object[1]));
			}
		}
	}
	
	/**
	 * 更新pr处理Manager
	 * 
	 * @param item
	 */
	public void doUpdatePr(PrPrForm item, List<PrBuyContext> pItem,
			Integer[] ids) {
		item.versionUp();
		item.stateUp();
		item.setTotal(0f);
		updatePrForm(item);
		doUpdateBuyContext(item);
		doAddBuyContext(pItem);
		dodeleteBuyContext(ids);
	}
	
	/**
	 * re fill in
	 * @category[JBPM]
	 * @param item
	 * @param pItem
	 */
	public void doUpdatePr(PrPrForm item, List<PrBuyContext> pItem) {
		//item.versionUp();
		//item.stateUp();
		//item.setTotal(0f);
		updatePrForm(item);
		//doUpdateBuyContext(item);
		dodeleteBuyContext(item.getId());
		doAddBuyContext(pItem);

	}
	
	/**
	 * 添加物品
	 */
	public void doAddBuyContext(List<PrBuyContext> pItem) {
		buyContext.blukFlushInsert(pItem);
	}
	
	/**
	 * 添加物品
	 */
	public void doAddBuyContextSingle(PrBuyContext pItem) {
		buyContext.insert(pItem);
	}
	
	/**
	 * 删除物品
	 */
	public void dodeleteBuyContext(Integer[] ids) {
		if (ids != null) {
			for (Integer id : ids) {
				if(id != null){
					buyContext.deleteByPK(id);
				}				
			}
		}
	}
	
	/**
	 * 删除物品
	 */
	public void dodeleteBuyContext(String formid) {
		String hql="delete from PrBuyContext where prFormID=?";
		buyContext.bulkUpdate(hql, formid);
	}
	
	/**
	 * 通过PR号查询采购内容
	 * @param prId
	 * @return
	 */
	public List<PrBuyContext> doSelectByPRId(String prId){
		String hql="from PrBuyContext where prPrForm.id=?";
		return buyContext.select(hql, prId);
	}
	
	/**
	 * pi liang shan chu
	 * @param prPrForm
	 */
	public void doDeletePrBuyContextByFormId(Integer[] ids){
		//prPrForm
		String hql="delete from PrBuyContext where prFormID=";
		//buyContext.blukDelete(hql);
	}
	
	public CommonDAO<PrPrForm> getPrFormDao() {
		return prFormDao;
	}

	public void setPrFormDao(CommonDAO<PrPrForm> prFormDao) {
		this.prFormDao = prFormDao;
	}

	public CommonDAO<PrBuyContext> getBuyContext() {
		return buyContext;
	}

	public void setBuyContext(CommonDAO<PrBuyContext> buyContext) {
		this.buyContext = buyContext;
	}
	
	/**
	 * @category[JBPM]
	 * @param item
	 * @return
	 */
	public List<PrPrForm> doSelectByProcessEngIds(String ids) {
//		String hql ="from PrPrForm pf join fetch pf.tempolyeeByApplicantId e join fetch e.tdepartment d where pf.processInstanceId in ("+ids+")";
//		System.out.println("--->"+ids);
//		
//		String title = "<tr><td>Initiator</td>" +
//				"<td>Dept.</td>" +
//		"<td>Description</td>" +
//		"<td>Amount</td>" +
//		"<td>Cap./Exp.</td>" +
//		"<td>Detail</td>" +
//		"<td>Date</td>"+
//		"<td>PR No.</td>"+
//		"</tr>";
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sql = new StringBuffer("select e.name,d.name as dn,pf.remark,total,iscapital,applicant_date,ssid,info from pr_prform pf ");
		sql.append("inner join tEmpolyee e on pf.applicantid = e.uid ");
		sql.append("inner join pr_project p on pf.projectid = p.id ");
		sql.append("inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
		sql.append("inner join dbo.tDepartment d on d.id = e.departmentid where pf.state>=0 and pf.processInstanceId in ("+ids+")");
		List<Object[]> rs = prFormDao.selectBySql(sql.toString());
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		for(Object[] item: rs){
			PrPrForm form = new PrPrForm();
			Tempolyee tempolyeeByApplicantId = new Tempolyee();
			tempolyeeByApplicantId.setName(item[0].toString());
			
			Tdepartment tdepartment = new Tdepartment(item[1].toString());
			tempolyeeByApplicantId.setTdepartment(tdepartment);
			form.setTempolyeeByApplicantId(tempolyeeByApplicantId);
			form.setRemark(item[2].toString());
			form.setTotal(Float.parseFloat(item[3].toString()));
			form.setIsCapital(Integer.parseInt(item[4].toString()));
			//form.setApplicantDate(new Date());
			
			
			try {
				form.setShowDate(item[5].toString().split(" ")[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				form.setShowDate(item[5].toString());
			}
			form.setSsid(Integer.parseInt(item[6].toString()));
			form.setInfo(Integer.parseInt(item[7].toString()));
			list.add(form);
		}
		return list;		
	}

	public CommonDAO<SingleForm> getSingleFormDao() {
		return singleFormDao;
	}

	public void setSingleFormDao(CommonDAO<SingleForm> singleFormDao) {
		this.singleFormDao = singleFormDao;
	}
	
	/**
	 * 单一供应商
	 */
	public String saveSingleForm(SingleForm form){
		Serializable id = singleFormDao.insert(form);
		
		SingleForm result = singleFormDao.selectByPk(id);
		String updateHql = "update SingleForm set sno = ? where id =?";

		
		String selectCount = "select count(*) from SingleForm where sname is not null and id <= "+result.getId();// single form number
		List count = singleFormDao.selectBySql(selectCount);
		int number;
		if(result.getFormType().equals(SingleForm.SINGLE)){
			number = (Integer) count.get(0);
		}else{
			number = result.getId() - (Integer) (Integer) count.get(0);
		}
		//更新sno
		singleFormDao.bulkUpdate(updateHql, new Object[]{result.getSNO(number),result.getId()});
		return result.getSNO(number);
		
	}
	
}
