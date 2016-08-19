package app.biz.impl.epor;

/**
 * 2010-8-4
 * @author SA1KV5
 * @version 1
 * PR业务类: 表单，基础信息，审批，状态和mail处理
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.jbpm.api.task.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sdaac.common.org.OrgManager;
import com.sdaac.pr.approve.PRProcessEngine;
import com.sdaac.pr.approve.element.FormControlElement;
import com.security.biz.impl.MailManager;

import sdaac.wym.app.Hr.BounceFigureImpl;
import sdaac.wym.common.Service.PrRightsManager;

import common.dao.CommonDAO;
import common.entity.MyMail;
import common.entity.TuserRole;
import common.util.SdaacMail;
import app.biz.EmpBiz;
import app.biz.impl.EmpBizImpl;
import app.common.EprExcel;
import app.entity.Tempolyee;
import app.entity.epor.BuyContextLogs;
import app.entity.epor.PRSupply;
import app.entity.epor.Paydetails;
import app.entity.epor.PrApprovedForm;
import app.entity.epor.PrBuyContext;
import app.entity.epor.PrBuyer;
import app.entity.epor.PrCostCenter;
import app.entity.epor.PrForm;
import app.entity.epor.PrFormLogs;
import app.entity.epor.PrPrForm;
import app.entity.epor.PrPrFormState;
import app.entity.epor.PrProject;
import app.entity.epor.StateManager;
import app.entity.epor.Subject;
import app.entity.epor.forms.SingleForm;

public class PrFormBizImpl {
	private PRProcessEngine processEng ;//= PRProcessEngine.newInstance();
	private CommonDAO<PrPrFormState> prFromState;
	private PrBuyerManager buyerManager;
	private FormService formService;
	private PrApprovedFormBizImpl prApprovedManager;
	private PrProjectManager projectManager;
	private CostCentreManager costCentreManager;
	private SubjectManager subjectManager;
	private CommonDAO<PrFormLogs> logsDao;
	private CommonDAO<BuyContextLogs> buyContextLogs;
//	private MailManager mailBiz;
	private PrRightsManager rightsManager;
	private EmpBiz empBiz;
	private PaydetailsService paydetailsService;
	private OrgManager orgManager;
	private CommonDAO<PRSupply> supplyDao;
	
	/**
	 * Query All Suppliers In The SAP
	 * @return
	 */
	public List<PRSupply> doSelectAllSuppliesInTheSap(){
		return supplyDao.selectAll();
	}
	
	public CommonDAO<PRSupply> getSupplyDao() {
		return supplyDao;
	}

	public void setSupplyDao(CommonDAO<PRSupply> supplyDao) {
		this.supplyDao = supplyDao;
	}

	public PaydetailsService getPaydetailsService() {
		return paydetailsService;
	}

	public void setPaydetailsService(PaydetailsService paydetailsService) {
		this.paydetailsService = paydetailsService;
	}

	// /业务日志
	/**
	 * 请购单业务日志
	 */
	public void doInsertLogs(PrPrForm item2, String name) {
		PrFormLogs logs = new PrFormLogs(item2, name, new Date());
		logsDao.insert(logs);
	}

	/**
	 * 采购内容业务日志
	 * 
	 * @param item2
	 * @param name
	 */
	public void doInsertBuyContextLogs(PrBuyContext item2, String name) {
		BuyContextLogs logs = new BuyContextLogs(item2, name, new Date());
		buyContextLogs.insert(logs);
	}

	/**
	 * 采购内容业务日志批量
	 * 
	 * @param set
	 * @param name
	 */
	public void doInsertBuyContextLogsEX(Set<PrBuyContext> set, String name) {
		List<BuyContextLogs> logs = new ArrayList<BuyContextLogs>();
		for (PrBuyContext item : set) {
			BuyContextLogs log = new BuyContextLogs(item, name, new Date());
			logs.add(log);
		}
		buyContextLogs.blukFlushInsert(logs);
	}

	/**
	 * 得到所有采购员
	 */
	public List<PrBuyer> doSelectBuyers() {
		return buyerManager.doSelectAll();
	}
	
	public List<PrBuyer> doSelectAllWithNetId() {
		Set<String>  result = new HashSet<String>();
		List<PrBuyer> temp = buyerManager.doSelectAllWithNetId();
		//buyerManager.doSelectAllWithNetId()
		Iterator<PrBuyer> it = temp.iterator();
		while(it.hasNext()){
			String name = it.next().getBuyName();
			if(result.contains(name)){
				it.remove();
			}else{
				result.add(name);
			}
			
		}		
		return temp;
	}
	
	public List<PrBuyer> doSelectBuyers2(String area) {
		List<PrBuyer> rs = buyerManager.doSelectAll(area);
		for(PrBuyer item : rs){
			item.setUuid(item.getUid().getUid());
		}
		return rs;
	}

	// /表单
	/**
	 * 提交PR单
	 * 
	 * @param prForm
	 */
	public void doInsert(PrPrForm prForm) {
		// TODO Auto-generated method stub
		formService.doInsert(prForm);
		doInsertLogs(prForm, prForm.getTempolyeeByApplicantId().getUid());
	}
	
	private void setFormByUid(PrPrForm prForm){
		if(prForm.getPm()!=null && !"".equals(prForm.getPm())){
			Tempolyee emp = empBiz.doSelectByUid(prForm.getPm());
			if(emp!=null){
				prForm.setPm(emp.getNetid());
			}
		}
		if(prForm.getCcowner()!=null && !"".equals(prForm.getCcowner())){
			Tempolyee emp = empBiz.doSelectByUid(prForm.getCcowner());
			if(emp!=null){
				prForm.setCcowner(emp.getNetid());
			}
		}
	}
	
	/**
	 * @since 2012-4-17
	 * @category JBPM-重写提交功能
	 * @since 2012-6-1
	 * @author K3n
	 * @category new function
	 * <p>
	 * 	 YanTai plant approval process(department degree)
	 *   total expense <100,000 ignore DM 
	 * </p>
	 * ---2014/03/02---WYM---Add Supervisor Approve.
	 */
	public void doApply(PrPrForm prForm) {
		setFormByUid(prForm);
		if(prForm.getTempolyeeByBuyerId()!=null){
			String buyerNetid = empBiz.doSelectByUid(prForm.getTempolyeeByBuyerId()).getNetid();			
			prForm.setBuyerNetid(buyerNetid);
		}
		String processInstanceId = processEng.apply(prForm,orgManager.getRoles(prForm));
		prForm.setProcessInstanceId(processInstanceId);
		doInsert(prForm);
		processEng.approve(1, processInstanceId);//skip fill in step
	}
	
	private boolean isExistSupervisor(int departID){
		//search the trole is Supervisor
		String roleName = "PRSupervisor";
		List<TuserRole> trs = rightsManager.doSelectByRoleName(roleName,departID);
		if(trs==null || trs.isEmpty()){
			return false;
		}else{
			return true;
		}		
	}
	
	/**
	 * 保存PR单
	 * 
	 * @param prForm
	 */
	public void savePrform(PrPrForm prForm) {
		// TODO Auto-generated method stub
		formService.savePrform(prForm);
		doInsertLogs(prForm, prForm.getTempolyeeByApplicantId().getUid());
	}

	/**
	 * 更新采购单采购内容 used to js
	 */
	public void doUpdatePRContext(PrBuyContext pContext) {
		formService.updatePrContext(pContext);

	}

	/**
	 * 添加采购内容
	 * 
	 * @param pItem
	 */
	public void doAddBuyContext(List<PrBuyContext> pItem, String name) {
		
		formService.doAddBuyContext(pItem);
//		for (PrBuyContext item2 : pItem) {
//			doInsertBuyContextLogs(item2, name);
//		}

	}
	
	/**
	 * 更新采购内容
	 * 2013-04-19
	 */
	public void updateBuycontext(Integer[] ids,List<PrBuyContext> pItem, String name){
		formService.dodeleteBuyContext(ids);
		formService.doAddBuyContext(pItem);
	}

	/**
	 * 更新PR单及内容
	 * 
	 * @param prForm
	 * @param newBuyContexts
	 * @param deleteContextIds
	 */
	public void doUpdatePr(PrPrForm prForm, List<PrBuyContext> newBuyContexts,
			Integer[] deleteContextIds) {
		// TODO Auto-generated method stub
		formService.doUpdatePr(prForm, newBuyContexts, deleteContextIds);
		doInsertLogs(prForm, prForm.getTempolyeeByApplicantId().getUid());
	}
	
	public void doUpdatePr(PrPrForm prForm, List<PrBuyContext> newBuyContexts) {
		// TODO Auto-generated method stub
		prForm.setInfo(0);
		this.setFormByUid(prForm);
		if(prForm.getTempolyeeByBuyerId()!=null){
			String buyerNetid = empBiz.doSelectByUid(prForm.getTempolyeeByBuyerId()).getNetid();			
			prForm.setBuyerNetid(buyerNetid);
		}
		formService.doUpdatePr(prForm, newBuyContexts);
		PrApprovedForm approvedForm = new PrApprovedForm();
		approvedForm.setPrPrForm(prForm);
		approvedForm.setContext(prForm.Mailer);
		approvedForm.setTempolyee(prForm.getTempolyeeByApplicantId());
		prApprovedManager.approve1(approvedForm);
		processEng.setVariable(prForm,orgManager.getRoles(prForm));
		processEng.approve(1, prForm.getProcessInstanceId());
		//doInsertLogs(prForm, prForm.getTempolyeeByApplicantId().getUid());
	}

	/**
	 * 更新PR单
	 * @param prForm
	 */
	public void doUpdatePRFrom(PrPrForm prForm, Tempolyee emp) {
		//如果更新了CostCenter,状态跳转到costcenter（状态大于部门经理）
		if(prForm.getIS_CHANGED() && prForm.getState()>1){
			prForm.setLastState(prForm.getState());
			prForm.setState(StateManager.CostCenter);
		}
		formService.updatePrForm(prForm);
	}
	
	/**
	 * 更新PR单
	 * @param prForm
	 */
	public void doUpdatePRFromByNewPR(PrApprovedForm approvedForm,List<PrBuyContext> newBuyContexts) {
		formService.doUpdateBuyContext(newBuyContexts);
//		PrPrForm newForm = new PrPrForm();
//		newForm.setId(prForm.getId());
//		newForm.setInfo(0);
//		formService.updatePrForm(prForm);
//		PrApprovedForm approvedForm = new PrApprovedForm();
//		approvedForm.setTempolyee(emp);
		approvedForm.setType("owner");
//		approvedForm.setContext(context);
//		approvedForm.setPrPrForm(prForm);
		approvedForm.getPrPrForm().setInfo(0);
		prApprovedManager.approve1(approvedForm);
	}
	
	/**
	 * update pr_form buyer 
	 * @param approvedForm
	 * @param newBuyContexts
	 */
	public void doUpdatePRFromBuyerId(int ssid,String buyerNetId) {
		PrBuyer item = buyerManager.doSelectBuyerId2(buyerNetId);
		if(item!=null && item.getUid()!=null){
			formService.updatePrBuyer(ssid,item.getUid().getUid()+"");
		}
	}

	/**
	 * 删除PR单
	 * 
	 * @param item
	 * @return
	 */
	public boolean doDelete(PrPrForm item) {
		// TODO Auto-generated method stub
		return formService.doDelete(item);
	}

	/**
	 * 按条件查询PR
	 * 
	 * @param prPrForm
	 * @return
	 */
	public List<PrPrForm> doSelect(PrPrForm prPrForm) {
		// TODO Auto-generated method stub
		return formService.doSelect(prPrForm);
	}
	
	/**
	 * 显示单张PR
	 * @param prPrForm
	 * @return
	 */
	public PrPrForm doSelectByID(String id) {
		// TODO Auto-generated method stub
		List<PrPrForm> prFormList = formService.doSelect(new PrPrForm(id));
		if (prFormList != null && !prFormList.isEmpty()) {
			PrPrForm prform = prFormList.get(0);
// 临时禁用
//			if(prform.getState() == StateManager.PRNO){
//				List<Paydetails> rs = paydetailsService.showAll(prform.getPrBuyContexts());
//				prform.setPaydetails(rs);
//			}
			return prform;
		}return null;
			
	}
	
	
	public PrPrForm doSelectByIDWithJBPM(String id) {
		// TODO Auto-generated method stub
		List<PrPrForm> prFormList = formService.doSelect(new PrPrForm(id));
		if (prFormList != null && !prFormList.isEmpty()) {
			PrPrForm item = prFormList.get(0);
			Task task = processEng.getCurrectActivity(item.getProcessInstanceId());
			if(task!=null){
				String formUrl = task.getFormResourceName();
				if(formUrl!=null && !"".equals(formUrl)){
					item.setFormURL(formUrl.split("/")[formUrl.split("/").length-1]);

				}else{
					item.setFormURL("ordinaryApprove.jsp");
				}
				String step = task.getActivityName();
				//String assignee = task.getAssignee();
				if(task.getAssignee()!=null){
					Tempolyee emp = this.getEmpBiz().doSelectByNetid(task.getAssignee());
					if(emp!=null){
						String assigneeName = emp.getName();
						item.setNewStateName(step+"/"+assigneeName);
						item.setAssignee(assigneeName);
					}
				}
			}else{
				item.setNewStateName("finish");
			}
			return item;
		}return null;
			
	}
	

	public List<PrPrForm> doSelectSS(PrPrForm prPrForm) {
		// TODO Auto-generated method stub
		return formService.doSelectSS(prPrForm);
	}
	
	public List<Object[]> doSelectSSSQL(PrPrForm prPrForm) {
		// TODO Auto-generated method stub
		return formService.doSelectSSSQLVersion(prPrForm);
	}
	
	public List<Object[]> doSelectBySQL(String sql) {
		// TODO Auto-generated method stub
		return formService.doSelectBySQLVersion(sql);
	}
	/**
	 * 按条件查询PR Light
	 * 
	 * @param prPrForm
	 * @return
	 */
	public List<PrPrForm> doSelectLight(PrPrForm prPrForm) {
		// TODO Auto-generated method stub
		return formService.doSelectLight(prPrForm);
	}
	
	/**
	 * @category JBPM-审批人查询带审批记录
	 */
	public List<PrPrForm> doQueryApprovingList(String apporver,PrPrForm condition){
		List<FormControlElement> rs = processEng.doQueryApprovingList(apporver);
		String noid ="'-1'";
		StringBuffer sb = new StringBuffer();
		for(FormControlElement e:rs){
			if(e!=null){
				sb.append("'");
				sb.append(e.getPrformid());
				sb.append("',");
			}
		}
		sb.append(noid);
		List<PrPrForm> forms = formService.doSelectLight(condition,sb.toString());
		//setting the task name & assignee		 
		for(PrPrForm form:forms){
			for(FormControlElement e:rs){
				if(e.getPrformid()!=null && e.getPrformid().equals(form.getId())){
					Tempolyee emp = empBiz.doSelectByNetid(e.getAssignee());
					form.setAssignee(emp.getName());
					form.setNewStateName(e.getState());
				}
			}
		}
		return forms;
	}
	
	/**
	 * @category JBPM-审批人查询带审批记录
	 */
	public List<PrPrForm> doQueryApprovingList(String apporver,String apporverName,PrPrForm condition){
		List<FormControlElement> rs = processEng.doQueryApprovingList(apporver);
		String noid ="'-1'";
		StringBuffer sb = new StringBuffer();
		for(FormControlElement e:rs){
			if(e!=null){
				sb.append("'");
				sb.append(e.getPrformid());
				sb.append("',");
			}
		}
		sb.append(noid);
		List<PrPrForm> forms = formService.doSelectLight(condition,sb.toString());
		//setting the task name & assignee		 
		for(PrPrForm form:forms){
			for(FormControlElement e:rs){
				if(e.getPrformid()!=null && e.getPrformid().equals(form.getId())){
					//Tempolyee emp = empBiz.doSelectByNetid(e.getAssignee());
					form.setAssignee(apporverName);
					form.setNewStateName(e.getState());
				}
			}
		}
		return forms;
	}
	
	/**
	 * @category 添加任务状态信息
	 */
	public void plusProcessInfo(List<PrPrForm> forms){	 
		for(PrPrForm form:forms){
			Task task =processEng.getCurrectActivity(form.getProcessInstanceId());
			if(task!=null){
				form.setAssignee(task.getAssignee());
				form.setNewStateName(task.getActivityName());
			}
		}
	}
	
	/**
	 * @category JBPM-查看我的申请
	 * @param prPrForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<PrPrForm> doSelectLightByPaging(PrPrForm prPrForm,String start, String limit) {
		// TODO Auto-generated method stub
		List<PrPrForm> rs = formService.doSelectLightByPaging(prPrForm, start,limit);
		for(PrPrForm form :rs){		
			if(form.getProcessInstanceId()!=null && !"".equals(form.getProcessInstanceId())){				
				Task task = processEng.getCurrectActivity(form.getProcessInstanceId());	
				if(task!=null){
					Tempolyee emp = empBiz.doSelectByNetid(task.getAssignee());
					if(emp!=null){
						form.setAssignee(emp.getName());
					}
					if((task.getActivityName().equals("Fin_check") || task.getActivityName().equals("PCL_check")||task.getActivityName().equals("Buyer_check")) 
							&& form.getInfo() == 0){
						form.setInfo(3);
					}
					form.setNewStateName(task.getActivityName());
				}else{
					form.setNewStateName("finished");
				}
				
				
			}
		}
		return rs;
	}
	
	/**
	 * search total count by SQL
	 */
	public int doSelectLightCount(PrPrForm item){
		return formService.doSelectLightCount(item);
	}
	
	// /审批
	/**
	 * 采购员确认
	 * 
	 * @param prForm
	 */
	public void doBuyerConfirm(PrPrForm prForm, Tempolyee emp,
			PrApprovedForm appForm, String isSubmit) {
		// TODO Auto-generated method stub
		// 设置状态为等待解释
		if (appForm.getIsApproved() != null && appForm.getIsApproved() == 2) {
			prForm.setInfo(1);
		}

		prApprovedManager.doBuyerConfirm(prForm, emp, appForm, isSubmit);
		doInsertLogs(prForm, emp.getUid());
//		doInsertBuyContextLogsEX(prForm.getPrBuyContexts(), emp.getUid());
	}

	/**
	 * 是否在相关部门内
	 * 
	 * @param emp
	 * @param item
	 * @return
	 */
	public boolean isRelation(Tempolyee emp, PrPrForm item) {
		// TODO Auto-generated method stub
		return prApprovedManager.isRelation(emp, item);
	}

	/**
	 * 查询在相关部门内的PR
	 * 
	 * @param emp
	 * @param item
	 * @return
	 */
	public List<PrPrForm> relations(Tempolyee emp, PrPrForm item,String start, String limit,int count) {
		// TODO Auto-generated method stub
		return prApprovedManager.relations(emp, item,start, limit,count);
	}
	
	public int relationsCount(Tempolyee emp, PrPrForm item) {
		// TODO Auto-generated method stub
		return prApprovedManager.relationsCount(emp, item);
	}
	
	
	/**
	 * 查询在相关部门内待批的PR
	 * 
	 * @param emp
	 * @param item
	 * @return
	 */
	public List<PrPrForm> relationings(Tempolyee emp, PrPrForm item) {
		// TODO Auto-generated method stub
		return prApprovedManager.relationingsNew(emp, item);
	}
	
	private void sendMail(PrApprovedForm approvedPR){
		Tempolyee emp = approvedPR.getPrPrForm().getTempolyeeByApplicantId();
		MyMail mail = new MyMail();
		mail.setEmp(emp);
		StringBuffer sb = new StringBuffer("<tr><td>your PR(");
		sb.append(approvedPR.getPrPrForm().getSapNO());
		sb.append(") has been rejected By ");
		sb.append(approvedPR.getTempolyee().getName()+"</tr></td>");
		mail.setContext(sb.toString());
		try {
			SdaacMail.send(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * PR单审批
	 * 
	 * @param approvedPR
	 */
	public void doApprovePR(PrApprovedForm approvedPR) {		
		String step = null;
		String assgin = approvedPR.getTempolyee().getNetid();
		
		approvedPR.getPrPrForm().getTotalCategroy();
		
		
		if (approvedPR.getIsApproved() != null
				&& approvedPR.getIsApproved() == 1){// 同意)
			if(approvedPR.getPrPrForm().getTotalCategroy()!=null && "S011720000".equalsIgnoreCase(approvedPR.getPrPrForm().getTotalCategroy())){//todo: may be TotalCategroy equals something
				processEng.setControlELementVariableWithNextFin(approvedPR.getPrPrForm());
			}
			step = processEng.approve(approvedPR.getIsApproved(),approvedPR.getPrPrForm().getProcessInstanceId(),assgin);
		}else if(approvedPR.getIsApproved() != null
				&& approvedPR.getIsApproved() == 0){ //不批准
			approvedPR.getPrPrForm().setInfo(-1);
			step = processEng.refuse(approvedPR.getPrPrForm().getProcessInstanceId());
			//send reject mail
			sendMail(approvedPR);
		} else if(approvedPR.getIsApproved() != null
				&& approvedPR.getIsApproved() == 2){
			approvedPR.getPrPrForm().setInfo(1);
			step = processEng.needExplain(approvedPR.getPrPrForm().getProcessInstanceId());
			sendMail(approvedPR);
		}	
		if(step != null){
			approvedPR.setType(step);
			otherStep(approvedPR);
			prApprovedManager.doApprovePR(approvedPR);
		}			
	}
	
	protected void otherStep(PrApprovedForm approvedPR){
		if(approvedPR.getType().equals("Buyer_check") && approvedPR.getIsApproved() == 1){
			approvedPR.getPrPrForm().setInfo(6);//set info finished.
		}else if(approvedPR.getType().equals("Fin_check") && approvedPR.getIsApproved() == 1){
			approvedPR.getPrPrForm().setInfo(3);
		}
	}
	
	public void doBack(String pid){
		String sourceName = "fin Mgr";
		String destName = "fill in";
		//processEng.addOutTransition( pid, sourceName, destName);
	}
	
	//邮件
	public void doSelectMailInfo(){
		// formService.doSelect(item);
	}
	
	// /基础信息
	/**
	 * 添加采购员
	 * 
	 * @param pItem
	 */
	public void doAddBuyer(PrBuyer pItem) {
		buyerManager.doAdd(pItem);
	}

	/**
	 * 删除采购员
	 * 
	 * @param key
	 */
	public void doDelBuyer(String key) {
		buyerManager.doDelete(key);
	}

	/**
	 * 查询PR项目
	 * 
	 * @return
	 */
	public List<PrProject> doSelectAllProject() {
		return projectManager.doSelectAll();
	}
	
	public List<PrProject> doSelectAllarno() {
		return projectManager.doSelectAllarno();
	}

	public PrProject doSelectProject(int id) {
		return projectManager.doSelect(id);
	}

	public void doAddProject(PrProject pItem) {
		String uname = empBiz.doSelectById(pItem.getManagerUid());
		pItem.setNumber(uname);
		projectManager.doInsert(pItem);
	}

	public void doUpdateProject(PrProject pItem) {
		// String uname = empBiz.doSelectById(pItem.getManagerUid());
		// pItem.setNumber(uname);
		projectManager.doUpdate(pItem);
	}

	public void doDelProject(PrProject pItem) {
		projectManager.doDelete(pItem);
	}

	/**
	 * 查询PRCostcentre
	 * 
	 * @return
	 */
	public List<PrCostCenter> doSelectAllCostcentre() {
		return costCentreManager.doSelectAll();
	}
	
	public List<PrCostCenter> doSelectAllCostcentre2(String area) {
		return costCentreManager.doSelectAll(area);
	}
	
	public List<PrCostCenter> doSelectAllDprt(String area) {
		return costCentreManager.doSelectAllDprt(area);
	}
	
	public List<PrCostCenter> doSelectByDprt(String area,String dprt) {
		return costCentreManager.doSelectByDprt(area,dprt);
	}
	
	/**
	 * 添加成本中心
	 * 
	 * @param pItem
	 */
	public void doAdd(PrCostCenter pItem) {
		if(pItem.getArea()==null || "".equals(pItem.getArea())) {
			costCentreManager.doAddAllSites(pItem);
		}else {
			costCentreManager.doAdd(pItem);
		}
	}

	/**
	 * 更新成本中心
	 * 
	 * @param pItem
	 */
	public void doUpdate(PrCostCenter pItem) {
		costCentreManager.doUpdate(pItem);
	}

	/**
	 * 删除成本中心
	 * 
	 * @param pItem
	 */
	public void doDelCostCenter(PrCostCenter pItem) {
		costCentreManager.doDel(pItem);
	}

	/**
	 * 查询所有总账科目
	 * 
	 * @return
	 */
	public List<Subject> doSelectAllSubjecet() {
		return subjectManager.doSelectAll();
	}
	
	public List<Subject> doSelectAllSubjecet2(String area) {
		return subjectManager.doSelectAll(area);
	}
	
	public Subject doSelectSubjecet(int id) {
		return subjectManager.doSelect(id);
	}

	public void doAddSubjecet(Subject pItem) {
		if(pItem.getArea()==null || "".equals(pItem.getArea())) {
			subjectManager.doAddAllSite(pItem);
		}else {
			subjectManager.doAdd(pItem);
		}
		
	}

	public void doUpdateSubjecet(Subject pItem) {
		subjectManager.doUpdate(pItem);
	}

	public void doDelSubjecet(Subject pItem) {
		subjectManager.doDel(pItem);
	}

	// /状态和信息
	/**
	 * 添加pr的email状态信息
	 */
	public boolean addPrEmail(PrPrFormState item) {
		boolean flag = true;
		this.prFromState.insert(item);
		return flag;
	}

	// /get and set

	public PrProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(PrProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public CommonDAO<PrPrFormState> getPrFromState() {
		return prFromState;
	}

	public void setPrFromState(CommonDAO<PrPrFormState> prFromState) {
		this.prFromState = prFromState;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public PrBuyerManager getBuyerManager() {
		return buyerManager;
	}

	public void setBuyerManager(PrBuyerManager buyerManager) {
		this.buyerManager = buyerManager;
	}

	public PrApprovedFormBizImpl getPrApprovedManager() {
		return prApprovedManager;
	}

	public void setPrApprovedManager(PrApprovedFormBizImpl prApprovedManager) {
		this.prApprovedManager = prApprovedManager;
	}

	public CostCentreManager getCostCentreManager() {
		return costCentreManager;
	}

	public void setCostCentreManager(CostCentreManager costCentreManager) {
		this.costCentreManager = costCentreManager;
	}

	public SubjectManager getSubjectManager() {
		return subjectManager;
	}

	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}

	public CommonDAO<PrFormLogs> getLogsDao() {
		return logsDao;
	}

	public void setLogsDao(CommonDAO<PrFormLogs> logsDao) {
		this.logsDao = logsDao;
	}

	public CommonDAO<BuyContextLogs> getBuyContextLogs() {
		return buyContextLogs;
	}

	public void setBuyContextLogs(CommonDAO<BuyContextLogs> buyContextLogs) {
		this.buyContextLogs = buyContextLogs;
	}

	/**
	 * 提交PR单
	 * 
	 * @param prForm
	 */
	public void doInsertTest(PrPrForm prForm) {
		// TODO Auto-generated method stub
		doInsertLogs(prForm, prForm.getTempolyeeByApplicantId().getUid());
		formService.doInsert(prForm);

	}

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		PrFormBizImpl ma = (PrFormBizImpl) ac.getBean("prFormBiz");
		PrPrForm prForm = new PrPrForm();
		prForm.setBegintime("2011-01-01");
		prForm.setEndTime("2011-08-31");
		List<PrPrForm> rs = ma.doSelect(prForm);
		EprExcel excel = new EprExcel("e:\\prDownload2011.xls");
		excel.write(rs);
		
	}

//	public MailManager getMailBiz() {
//		return mailBiz;
//	}
//
//	public void setMailBiz(MailManager mailBiz) {
//		this.mailBiz = mailBiz;
//	}

	public PrRightsManager getRightsManager() {
		return rightsManager;
	}

	public void setRightsManager(PrRightsManager rightsManager) {
		this.rightsManager = rightsManager;
	}

	public EmpBiz getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(EmpBiz empBiz) {
		this.empBiz = empBiz;
	}

	public OrgManager getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

	public PRProcessEngine getProcessEng() {
		return processEng;
	}

	public void setProcessEng(PRProcessEngine processEng) {
		this.processEng = processEng;
	}
	
	public String saveSingleForm(SingleForm form){
		return formService.saveSingleForm(form);
	}

}
