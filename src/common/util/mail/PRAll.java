package common.util.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sdaac.wym.common.Service.PrRightsManager;
import app.entity.epor.PrPrForm;
import app.entity.epor.StateManager;
import common.dao.CommonDAO;
import common.entity.MyMail;
import common.entity.TuserRole;

public class PRAll extends DataCollectioner{
	private CommonDAO<PrPrForm> prFormDao;
	private PrRightsManager rightsManager;

	/**
	 * 查询需要邮件通知
	 * 查询各状态的请购单(除了部门经理，采购员，相关部门)
	 * @param item
	 */
	//
	private String sql = "select e.name,ssid,applicant_date,state,e.email,e.departmentid,p.remark,total,cost_center_name,isCapital from dbo.PR_prForm p inner join dbo.tEmpolyee e on p.applicantid = e.uid inner join dbo.PR_costCenter c on c.id = p.cost_centerID where ((state = 17) or (state = 24) or (state >= 2 and state < 10 and state <> 4)) and (info in (0,5) or info is null) ";
	private PrPrForm form = new PrPrForm();
	public List<MyMail> doSelectToMailTip() {						
		List<Object[]> rs1 = prFormDao.selectBySql(sql);
		int index = 0;
		for (Object[] item : rs1) {
			//对副总状态的单子进行区分
			String roleName = form.scheduleMap.get((Integer) item[3]).role;			
			List<TuserRole> useRoles = rightsManager
					.doSelectByRoleName(roleName);
			for (TuserRole useRole : useRoles) {
				if(roleName.equals("PRDeputyGeneralManager") && useRole.getTempolyee().getUid().equals(StateManager.U_CIO)){
					if(StateManager.CIO.contains(item[5].toString())){
						dealData(item, useRole,index);
						index++;
					}
				}else if(roleName.equals("PRDeputyGeneralManager") && useRole.getTempolyee().getUid().equals(StateManager.U_CIO2)){
					if(StateManager.CIO2.contains(item[5].toString())){
						dealData(item, useRole,index);
						index++;
					}
				}else if(roleName.equals("PRDeputyGeneralManager") && useRole.getTempolyee().getUid().equals(StateManager.U_DGM1)){
					if(StateManager.DGM1.contains(item[5].toString())){
						dealData(item, useRole,index);
						index++;
					}
				}else if(roleName.equals("PRDeputyGeneralManager") && useRole.getTempolyee().getUid().equals(StateManager.U_DGM2)){
					if("10025,10026,10041,10038,10039".contains(item[5].toString())){
						dealData(item, useRole,index);
						index++;
					}
				} else if(roleName.equals("PRDeputyGeneralManager") && useRole.getTempolyee().getUid().equals(StateManager.U_DGM3)){
					if(StateManager.DGM3.contains(item[5].toString())){
						dealData(item, useRole,index);
						index++;
					}
				}else if(roleName.equals("PRDeputyGeneralManager") && useRole.getTempolyee().getUid().equals("1382")){
			
				}else {
					dealData(item, useRole,index);
					index++;
				}
			}
		}		
		return mailList;
	}
//	
//	/**
//	 * 查询需要邮件通知2
//	 * 
//	 * @param item
//	 */
//	public List<MyMail> doSelectToMailTip2() {
//		PrPrForm form = new PrPrForm();
//		List<MyMail> mailList = new ArrayList();
//		Map<String, MyMail> mailMap = new HashMap<String, MyMail>();
//		// 查看状态为采购员的单子
//		String sql4 = "select e.uid,c.name,ssid,applicant_date,e.email from dbo.PR_prForm p inner join dbo.PR_buyer b on b.buyID = SUBSTRING ( p.buyerID , 0 , 7 ) inner join dbo.tEmpolyee e on b.uid =  e.uid  inner join dbo.tEmpolyee c on p.applicantid = c.uid where state in (4,10) and (info in (0,5) or info is null) ";
//		Map<String, MyMail> mailMap2 = new HashMap<String, MyMail>();
//		for (Object[] item : prFormDao.selectBySql(sql4)) {
//			if (mailMap2.get(item[0].toString()) == null) {
//				String context = "<tr><td>流水号/SSID</td><td>申请日期/application date</td><td>申请人/applicant</td></tr>";
//				context += "<tr><td>" + item[2].toString() + "</td><td>"
//						+ item[3].toString() + "</td><td>" + item[1].toString()
//						+ "</td></tr>";
//				if (item[4] != null) {
//					MyMail mail = new MyMail(item[4].toString(), "ePR邮件提示"
//							+ item[0].toString(), context, 0);
//					mailMap2.put(item[0].toString(), mail);
//				}
//			} else {
//				MyMail mail = mailMap2.get(item[0].toString());
//				String context = mail.getContext();
//				context += "<tr><td>"
//						+ getSapNO(Integer.parseInt(item[2].toString()))
//						+ "</td><td>" + item[3].toString() + "</td><td>"
//						+ item[1].toString() + "</td></tr>";
//				mail.setContext(context);
//			}
//		}
//		for (String key : mailMap2.keySet()) {
//			mailList.add(mailMap2.get(key));
//		}
//		return mailList;
//	}
//	
//	/**
//	 * 查询需要邮件通知3 - 相关部门
//	 * 
//	 * @param item
//	 */
//	public List<MyMail> doSelectToMailTip3() {
//		List<MyMail> mailList = new ArrayList<MyMail>();
//		// 查看状态为相关部门的单子
//		String sql4 = "select e.uid,e2.name,ssid,applicant_date,e.email from pr_prform f inner join dbo.PR_project p on f.projectid = p.id inner join dbo.tEmpolyee e on e.uid =  p.manageruid inner join dbo.tEmpolyee e2 on e2.uid =  f.applicantid where state =12 and (info in (0,5) or info is null) ";
//		Map<String, MyMail> mailMap2 = new HashMap<String, MyMail>();
//		for (Object[] item : prFormDao.selectBySql(sql4)) {
//			if (mailMap2.get(item[0].toString()) == null) {
//				String context = "<tr><td>流水号/SSID</td><td>申请日期/application date</td><td>申请人/applicant</td></tr>";
//				//如果没有项目经理，那么就是IT经理的单子
//				if(item[0]==null || item[0].toString().equals("")) {
//					item[0] = "1470";
//					item[4] = "Cong.Chen@delphi.com";
//				}
//				context += "<tr><td>" + getSapNO(Integer.parseInt(item[2].toString())) + "</td><td>"
//						+ item[3].toString() + "</td><td>" + item[1].toString()
//						+ "</td></tr>";
//				if (item[4] != null) {
//					MyMail mail = new MyMail(item[4].toString(), "ePR邮件提示"
//							+ item[0].toString(), context, 0);
//					mailMap2.put(item[0].toString(), mail);
//				}
//			}else {
//				MyMail mail = mailMap2.get(item[0].toString());
//				String context = mail.getContext();
//				context += "<tr><td>"
//						+ getSapNO(Integer.parseInt(item[2].toString()))
//						+ "</td><td>" + item[3].toString() + "</td><td>"
//						+ item[1].toString() + "</td></tr>";
//				mail.setContext(context);
//			} 
//		}
//		for (String key : mailMap2.keySet()) {
//			mailList.add(mailMap2.get(key));
//		}
//		// 查看状态为沈阳的单子(采购经理和副总)
//		String sql5 = "select state,e2.name,ssid,applicant_date,ssid from pr_prform f inner join dbo.tEmpolyee e2 on e2.uid =  f.applicantid where (state =3 or state = 6) and source = 2 and (info in (0,5) or info is null)";
//		Map<String, MyMail> mailMap5 = new HashMap<String, MyMail>();
//		for (Object[] item : prFormDao.selectBySql(sql5)) {
//			if (mailMap2.get(item[0].toString()) == null) {
//				String context = "<tr><td>流水号/SSID</td><td>申请日期/application date</td><td>申请人/applicant</td></tr>";
////				//如果没有项目经理，那么就是IT经理的单子
////				if(item[0]!=null && item[0].toString().equals("3")) {
////					item[0] = "0116";
////					item[4] = "gao.chengzhong@sdaac.com";
////				}else if(item[0]!=null && item[0].toString().equals("6")){
////					item[0] = "sy025";
////					item[4] = "wang.zhiyong@sdaac.com";
////				}
//				context += "<tr><td>" + getSapNO(Integer.parseInt(item[2].toString())) + "</td><td>"
//						+ item[3].toString() + "</td><td>" + item[1].toString()
//						+ "</td></tr>";
//				if (item[4] != null) {
//					MyMail mail = new MyMail(item[4].toString(), "ePR邮件提示"
//							+ item[0].toString(), context, 0);
//					mailMap2.put(item[0].toString(), mail);
//				}
//			} 
//		}
//		for (String key : mailMap5.keySet()) {
//			mailList.add(mailMap5.get(key));
//		}
//		return mailList;
//	}
//	
//	/**
//	 * 得到ssid
//	 * @param ssid
//	 * @return
//	 */
//	public int getSapNO(int ssid) {
//		int newSsid = ssid;
//		int tenYearTotal = 963;
//		if (ssid <= tenYearTotal) {
//			return 10 * 100000000 + newSsid;
//		} else if (ssid > tenYearTotal) {
//			newSsid = ssid - tenYearTotal;
//			return 11 * 100000000 + newSsid;
//		}
//		return 0;
//	}

//	public static void main(String[] args) {
//		ApplicationContext ac = new ClassPathXmlApplicationContext(
//				"applicationContext.xml");
//		DataCollectioner manager1 = (PRAll) ac.getBean("PRAll");
//		DataCollectioner manager2 = (PRDepartment) ac.getBean("PRDepartment");
//		List<MyMail> rs1 = manager1.doALL();
//		List<MyMail> rs2 = manager2.doALL();
//	}

	public CommonDAO<PrPrForm> getPrFormDao() {
		return prFormDao;
	}

	public void setPrFormDao(CommonDAO<PrPrForm> prFormDao) {
		this.prFormDao = prFormDao;
	}

	public PrRightsManager getRightsManager() {
		return rightsManager;
	}

	public void setRightsManager(PrRightsManager rightsManager) {
		this.rightsManager = rightsManager;
	}
}
