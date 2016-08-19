package app.biz.impl.epor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.security.biz.impl.MailManager;

import sdaac.wym.app.Hr.EmpSourceManager;
import sdaac.wym.common.Service.PrRightsManager;

import common.dao.CommonDAO;
import common.entity.MyMail;
import common.entity.TuserRole;

import app.biz.epor.PrApprovedFormBiz;
import app.entity.Tdepartment;
import app.entity.Tempolyee;
import app.entity.epor.PrApprovedForm;
import app.entity.epor.PrBuyContext;
import app.entity.epor.PrBuyer;
import app.entity.epor.PrPrForm;
import app.entity.epor.PrPrFormState;
import app.entity.epor.PrProject;
import app.entity.epor.StateManager;

/**
 * 2010-8-3
 * 
 * @author sa1kv5
 * @version 1 pr审批类
 * 
 */
public class PrApprovedFormBizImpl {

	protected CommonDAO<PrApprovedForm> prApprovedFormDao = null;
	protected FormService formService;
	protected PrBuyerManager buyerManager;
	protected PrRightsManager rightsManager;
	protected DoaManager dm = new DoaManager();
	protected String BuyerManagerRoleName = "PRBuyerManager";
	protected String ITManager = "5003;";
	public CommonDAO<PrApprovedForm> getPrApprovedFormDao() {
		return prApprovedFormDao;
	}

	public void setPrApprovedFormDao(CommonDAO<PrApprovedForm> prApprovedFormDao) {
		this.prApprovedFormDao = prApprovedFormDao;
	}

	public List<Object[]> getApprovedList(String sql){
		return prApprovedFormDao.selectBySql(sql);
		
	}
	
	/**
	 * 更新审批单
	 * 
	 * @param item
	 */
	protected void doUpdate(PrApprovedForm item) {
		String hql = "update PrApprovedForm set context=?,isApproved=?,datetime=? where version=? and prPrForm.id=? and tempolyee.uid=? ";
		prApprovedFormDao.bulkUpdate(hql, new Object[] { item.getContext(),
				item.getIsApproved(), item.getDatetime(), item.getVersion(),
				item.getPrPrForm().getId(), item.getTempolyee().getUid() });
	}

	/**
	 * 批审提案单
	 */
	private static Logger logger = Logger.getLogger(PrApprovedFormBizImpl.class);
	
	public void doApprovePR(PrApprovedForm approvedForm) {
		StateManager stateManager = approvedForm.getPrPrForm()
				.getStateManager();
		//approvedForm.doSetVersion();
		String methodName = stateManager.condition;
		if (methodName.equals("approve1")) {
			approve1(approvedForm);
		} else if (methodName.equals("approveWithUpdate")) {
			approveWithUpdate(approvedForm);
		}else{
			approve1(approvedForm);
		}
	}

	/**
	 * update form 
	 * @param approvedForm
	 * @category[JBPM]
	 */
	public void approveWithUpdate(PrApprovedForm approvedForm){
		formService.updatePrForm(approvedForm.getPrPrForm());
		this.prApprovedFormDao.insert(approvedForm);
	}

	
	/**
	 * cost center approval
	 * @param approvedForm
	 */
	protected void approveCostCenter(PrApprovedForm approvedForm){
		
		String costCenterOwner = approvedForm.getPrPrForm().getPrCostCenter().getOwner();
		String costcenterRemark = approvedForm.getPrPrForm().getPrCostCenter().getRemark();
		int prState = nextStep(approvedForm);
		//&& (costcenterRemark.startsWith("DT02A") || costcenterRemark.startsWith("DT02B"))
		if(costCenterOwner!=null && !"".equals(costCenterOwner)&& !approvedForm.getTempolyee().getUid().equals(costCenterOwner) ) { //costcenter和部门不一致
			this.prApprovedFormDao.insert(approvedForm);
			if (prState >= 88) {
				return ;
			}else {
				//update prForm's otherCostCentre
				PrPrForm form = new PrPrForm();
				form.setId(approvedForm.getPrPrForm().getId());
				form.setIsOtherCostCenter("Y");
				formService.updatePrForm(form);
				formService.updatePrState(approvedForm.getPrPrForm(),prState);
			}
		}else {
			if(approvedForm.getIsApproved() == 1) {
				approvedForm.getPrPrForm().setState(19); //jump to next step
				approvedForm.getPrPrForm().setIsOtherCostCenter(""); //clear costcenter
			}		
			approvePmManager(approvedForm);
			return;
		}
	}

	/**
	 * update 2014/04/20
	 * DM->Buyer->BuyerManager->CC
	 * @param approvedForm
	 */
	protected void approveNewDM(PrApprovedForm approvedForm){		

		this.prApprovedFormDao.insert(approvedForm);
//		int prState = nextStep(approvedForm);
//		//如果bu是项目
//		if(approvedForm.getPrPrForm().getPrProject().getId()==1){
//			
//			if(approvedForm.getIsApproved() == 1) {
//				prState = 4;
//				
//			}
//		}
//		if (prState < 88) {
//			formService.updatePrState(approvedForm.getPrPrForm(),
//					prState);
//		}
	}	
	
	protected void approveOTSManager(PrApprovedForm approvedForm) {

		this.prApprovedFormDao.insert(approvedForm);
		int prState = nextStep(approvedForm);
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}
	
	/**
	 * 财务经理DOA后审批
	 * @param approvedForm
	 */
	protected void approve5(PrApprovedForm approvedForm) {
//		approvedForm.setIsApproved(1);
		approve1(approvedForm);
		Integer nowstate = null;
		approvedForm.getPrPrForm().setState(nowstate);
		if(approvedForm.getIsApproved() == 1){
			if( approvedForm.getPrPrForm().getIsInTheSap()==null|| approvedForm.getPrPrForm().getIsInTheSap()!=1){
				nowstate = StateManager.PRNO ;
				approvedForm.getPrPrForm().setState(nowstate);
			}
			
		}
		formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR
	}
	
	
	/**
	 * 回填信息
	 * 
	 * @param approvedForm
	 */
	protected void approveFeedBack(PrApprovedForm approvedForm) {
//		approvedForm.setIsApproved(1);
		approve1(approvedForm);
		Integer nowstate = null;
		approvedForm.getPrPrForm().setState(null);
		if(approvedForm.getIsApproved() == 1){
			if( approvedForm.getPrPrForm().getIsInTheSap()==null|| approvedForm.getPrPrForm().getIsInTheSap()!=1){
				nowstate = StateManager.PRNO ;
				approvedForm.getPrPrForm().setState(nowstate);
			}
			//如果是capital
			if(approvedForm.getPrPrForm().getIsCapital() == 0) {
				nowstate = 18;
				approvedForm.getPrPrForm().setState(nowstate);
			}
		}
		
		
		formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR
	}
	
	/**
	 * 普通批审提案单
	 */
	public void approveExplan(PrApprovedForm approvedForm) {
		// StateManager stateManager = approvedForm.getPrPrForm()
		// .getStateManager();
		if(approvedForm.getHelpExplain()!=null && approvedForm.getHelpExplain() == 2) {//需要采购解释
			formService.updatePrState(approvedForm.getPrPrForm(),StateManager.BuyConfig);
		}else if (approvedForm.getHelpExplain()!=null && approvedForm.getHelpExplain() == 3) {//需要财务解释
			formService.updatePrState(approvedForm.getPrPrForm(), StateManager.CWCONTROL);
		}else if (approvedForm.getHelpExplain()!=null && approvedForm.getHelpExplain() == 4) {//需要财务解释
			formService.updatePrState(approvedForm.getPrPrForm(), StateManager.OTS);
		}
		this.prApprovedFormDao.insert(approvedForm);
		for (String uid : approvedForm.getRalate()) {
			if(uid!=null && !uid.equals("")){
				PrApprovedForm item = new PrApprovedForm();
				item.setType(PrApprovedForm.RelationType);
				item.setTempolyee(new Tempolyee(uid));
				item.setVersion(approvedForm.getVersion());
				item.setPrPrForm(approvedForm.getPrPrForm());
				prApprovedFormDao.insert(item);
			}
		}
//		if(approvedForm.getPrPrForm().getState()==12 &&( approvedForm.getRelateUids()==null || approvedForm.getRelateUids().equals(""))) {
//			formService.updatePrState(approvedForm.getPrPrForm(), StateManager.BuyConfig);
//		}
	}
	
	/**
	 * 普通批审提案单
	 */
	protected void approve1(PrApprovedForm approvedForm) {
		formService.updatePrForm(approvedForm.getPrPrForm());
		this.prApprovedFormDao.insert(approvedForm);
	}
	
	/**
	 * 总部技术总监审批
	 */
	protected void approveCTO(PrApprovedForm approvedForm) {
		this.prApprovedFormDao.insert(approvedForm);
		int DepartID = approvedForm.getPrPrForm().getTempolyeeByApplicantId().getTdepartment().getId();		
		int PE_ID = 0,ME_ID = 0;
		int prState = 88;
		//if departID is PE,ME
		if(DepartID!=0 && (DepartID ==PE_ID || DepartID ==ME_ID)){
			//need CTO 
			prState = nextStep(approvedForm);
		}else{
			prState = nextStep(approvedForm);
		}
		
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}	
	
	/**
	 * 上海财务审批烟台PR
	 */
	protected void approve11(PrApprovedForm approvedForm) {
		this.prApprovedFormDao.insert(approvedForm);
		int prState = nextStep(approvedForm);
		if(approvedForm.getPrPrForm().getIsInTheSap()==0 && prState == 9){//if not in the sap & state = 9 goto 10
			prState = 10;
		}
		approvedForm.getPrPrForm().setState(prState);
		if (prState < 88) {
			formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR
		}		
	}

	/**
	 * 总部财务审批
	 */
	protected void zongbuCWApprove(PrApprovedForm approvedForm) {
		this.prApprovedFormDao.insert(approvedForm);
		int prState = nextStep(approvedForm);
		if (approvedForm.getRelateUids() != null && !approvedForm.getRelateUids().equals("")){
			prState = 33;
			approvedForm.getPrPrForm().setRelateuid(approvedForm.getRelateUids());
		}
		approvedForm.getPrPrForm().setState(prState);
		if (prState < 88) {
			formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR
		}		
	}
	
	/**
	 * OperationM
	 */
	protected void approveOperationM(PrApprovedForm approvedForm) {
		this.prApprovedFormDao.insert(approvedForm);
		int prState = nextStep(approvedForm);
		
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
		if(approvedForm.getIsApproved() == 1){
			String temp = "10047,10049,10031,10048,10040,10044,10046,10058";
			if(!temp.contains(approvedForm.getPrPrForm().getTempolyeeByApplicantId().getTdepartment().getId()+"")){
				approvedForm.getPrPrForm().setState(prState);
				approvedForm.setType("副总");
				this.approveDOA(approvedForm);
			}	
		}
	}
	
	/**
	 * 财务协调员环节审批
	 * @param approvedForm
	 * @param approvedForms
	 */
	protected void approve2(PrApprovedForm approvedForm) {

		List<PrApprovedForm> list = new ArrayList<PrApprovedForm>();
		list.add(approvedForm);
		int prState;
		formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR

		prState = nextStep(approvedForm);

		if (approvedForm.getIsApproved() == 1)// 同意
		{
			// prState = stateManager.nextState;
			if (approvedForm.getRelateUids() != null
					&& !approvedForm.getRelateUids().equals("")) {
				for (String uid : approvedForm.getRalate()) {
					PrApprovedForm item = new PrApprovedForm();
					item.setType(PrApprovedForm.RelationType);
					item.setTempolyee(new Tempolyee(uid));
					item.setVersion(approvedForm.getVersion());
					item.setPrPrForm(approvedForm.getPrPrForm());
					list.add(item);
				}
			} else {
				if(approvedForm.getPrPrForm().getState()==19){
					if(approvedForm.getPrPrForm().getLastState()!=null && approvedForm.getPrPrForm().getLastState()>0 && approvedForm.getPrPrForm().getLastState()!= 19){
						//如果有历史状态
						prState = approvedForm.getPrPrForm().getLastState();
						//清空历史状态
						approvedForm.getPrPrForm().setLastState(0);
					}
				else if(approvedForm.getPrPrForm().getPrProject()!=null && approvedForm.getPrPrForm().getPrProject().getId()!=null 
							&& approvedForm.getPrPrForm().getPrProject().getId()!=1 ){
						prState = 12;
					}else{
						prState = approvedForm.getPrPrForm().getNext2Step();// 如果没有相关部门
					}
				}				
				if(prState == 4 && approvedForm.getPrPrForm().getType().contains("12")) {
					prState =16;
				}else if(prState == 4 && approvedForm.getPrPrForm().getTempolyeeByApplicantId().getTdepartment().getId()==10044 
						&& approvedForm.getPrPrForm().getPrCostCenter()!=null && approvedForm.getPrPrForm().getPrCostCenter().getRemark()!=null && approvedForm.getPrPrForm().getPrCostCenter().getRemark().startsWith("DT02")){
					prState =16;
				}
				//如果是capital跳过
				if(approvedForm.getPrPrForm().getState()==StateManager.CWCONTROL && approvedForm.getPrPrForm().getIsCapital()!=null && approvedForm.getPrPrForm().getIsCapital() == 0) {

					prState = StateManager.DeputyGeneral;
				}
				//如果是expense跳到成本中心及科目审核员
				if((approvedForm.getPrPrForm().getSource()==1||approvedForm.getPrPrForm().getSource()==2) && approvedForm.getPrPrForm().getState()==StateManager.CWCONTROL && approvedForm.getPrPrForm().getIsCapital()!=null && approvedForm.getPrPrForm().getIsCapital() == 1) {
					prState = StateManager.CWCONTROLCaptial;//Finance ZhuYiLin					
				}
				//在财务费用审核中,如果是Operation_Manager 需要审批的
				if(approvedForm.getPrPrForm().getSource()<=2 && approvedForm.getPrPrForm().getState()==StateManager.CWCONTROLCaptial){
						prState = StateManager.FinanceManager;// go to FinanceManager

				}
				
			}
		}
		// 保存审批表和相关评审
		prApprovedFormDao.blukFlushInsert(list);
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}

	}
	
	protected PrBuyer doSelectBuyerId(String uname){	
		
		return buyerManager.doSelectBuyerId(uname);
	}
	
	/**
	 * 根据审批意见得到下一个状态
	 * @param approvedForm
	 * @return
	 */
	protected int nextStep(PrApprovedForm approvedForm) {
		StateManager stateManager = approvedForm.getPrPrForm()
				.getStateManager();
		// 同意
		if (approvedForm.getIsApproved() == 1){
			return stateManager.nextState;
		} else if (approvedForm.getIsApproved() == 0) {
			return stateManager.failureState;
			//退回采购员
		}else if(approvedForm.getIsApproved() == 5){ 
			return StateManager.BuyConfig;
		}else {
			return 88;
		}

	}

	/**
	 * 部门经理审批 
	 * @param approvedForm
	 */
	protected void approvePmManager(PrApprovedForm approvedForm) {
		String relateUids = "";
		if (approvedForm.getPrPrForm().getType().contains(PrPrForm.ITSingle)) {
			relateUids = ITManager;
			if(approvedForm.getPrPrForm().getSource()==2){
				relateUids="sy112;5003";
			}
		}
		approve2(approvedForm);
	}

	/**
	 * 并行审批
	 * 
	 * @param approvedForm
	 * @param approvedForms
	 */
	protected void approve3(PrApprovedForm approvedForm) {

		// 保存审批表
		// prApprovedFormDao.insert(approvedForm);
		doUpdate(approvedForm);
		// 查询相关审批意见
		String hql = "from PrApprovedForm where version=? and type=? and prPrForm.id=? ";
		List<PrApprovedForm> list = prApprovedFormDao.select(hql, new Object[] {
				approvedForm.doGetVersion(), PrApprovedForm.RelationType ,approvedForm.getPrPrForm().getId()});

		int prState, flag = 1;// 审批意见;
		boolean flag2 = true;// 是否全部审批
		for (PrApprovedForm item : list) {
			if (item.getIsApproved() == null || item.getIsApproved()==2) {
				flag2 = false;
				break;
			} else {
				flag = flag * item.getIsApproved();
			}
		}
		if (flag2) {
			PrApprovedForm approvedForm2 = new PrApprovedForm();
			approvedForm2.setPrPrForm(approvedForm.getPrPrForm());
			if (flag == 1)// 同意
			{
				approvedForm2.setIsApproved(1);
			} else {
				approvedForm2.setIsApproved(0);
			}

			prState = nextStep(approvedForm2);

			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}

	}

	/**
	 * 是否在相关部门内
	 * 
	 * @param pEmp
	 * @param pPrForm
	 * @return
	 */
	public boolean isRelation(Tempolyee pEmp, PrPrForm pPrForm) {
		String hql = "from PrApprovedForm as af where af.prPrForm.id=? and af.relateUids like ? and af.version =? ";
		String relateUid = "%" + pEmp.getUid() + "%";
		if (prApprovedFormDao
				.select(
						hql,
						new Object[] { pPrForm.getId(), relateUid,
								pPrForm.getVersion() }).isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * @no use
	 * 查询在相关部门内的PR
	 * @param pEmp
	 * @param pPrForm
	 * @return
	 */
	public List<PrPrForm> relationings(Tempolyee pEmp, PrPrForm pPrForm) {
		String hql = "from PrApprovedForm as af where  af.relateUids like ? ";
		String relateUid = "%" + pEmp.getUid() + "%";
		List<PrApprovedForm> rs = prApprovedFormDao.select(hql,
				new Object[] { relateUid });
		List<PrPrForm> resList = new ArrayList<PrPrForm>();
		Set<String> prFormIds = new HashSet<String>();
		for (PrApprovedForm item : rs) {//去除重复ID
			prFormIds.add(item.getPrPrForm().getId());		
		}
		for(String prFormId : prFormIds){
			pPrForm.setId(prFormId);
			pPrForm.setQuertState("13");
			//pPrForm.setVersion(item.getVersion());
			resList.addAll(formService.doSelect(pPrForm));
		}
		return resList;
	}

	
	/**
	 * 查询在相关部门内的PR
	 * @param pEmp
	 * @param pPrForm
	 * @return
	 */
	public List<PrPrForm> relationingsNew(Tempolyee pEmp, PrPrForm pPrForm) {
		StringBuffer sql = new StringBuffer("select pf.id,e.name,d.name as dn,applicant_date,pf.remark,ssid,pf.info,pf.state,p.sapNo,isplan,iscapital,d.id as did from pr_prform pf " +
				" inner join dbo.PR_approvedForm a on pf.id = a.prformid ");
		sql.append(" inner join tEmpolyee e on pf.applicantid = e.uid ");
		sql.append(" inner join pr_project p on pf.projectid = p.id ");
		sql.append(" inner join dbo.PR_costCenter c on c.id = pf.cost_centerid ");
		sql.append(" inner join dbo.tDepartment d on d.id = e.departmentid");			
		sql.append(" where a.relateUids like '%"+pEmp.getUid()+"%' and state = 13 and a.type is not null and a.version = pf.version");
		List<Object[]> rs = prApprovedFormDao.selectBySql(sql.toString());
		List<PrPrForm> list = new ArrayList<PrPrForm>();
		for(Object[] aa : rs) {
//			11111101221107	张翼翔	制造工程部ME	2011-11-11 09:01:22.203	管件清洗时，压住筐内零件用。现有筐盖立体把手，容易在装筐量高时压损零件。	4579
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
	/**
	 * 查询在相关部门内的PR
	 * @param pEmp
	 * @param pPrForm
	 * @return
	 */
	public List<PrPrForm> relations(Tempolyee pEmp, PrPrForm pPrForm,String start, String limi,int count) {
		//---------
		String sql = "select distinct prformid from dbo.PR_approvedForm where relateUids like '%"+pEmp.getUid()+"%'";
		List<Object[]> rs = prApprovedFormDao.selectBySql(sql);
		List<PrPrForm> resList = new ArrayList<PrPrForm>();

		String ids="'a'";
		for(Object prFormId : rs){		
			ids+=",'"+prFormId.toString()+"'";
		}
		pPrForm.setId(ids);
		resList.addAll(formService.doSelectLightByPagingIn(pPrForm,start,limi));
		count = formService.doSelectLightCountIn(pPrForm);
		return resList;
	}
	
	public int relationsCount(Tempolyee pEmp, PrPrForm pPrForm) {
		//---------
		String sql = "select distinct prformid from dbo.PR_approvedForm where relateUids like '%"+pEmp.getUid()+"%'";
		List<Object[]> rs = prApprovedFormDao.selectBySql(sql);

		String ids="'a'";
		for(Object prFormId : rs){		
			ids+=",'"+prFormId.toString()+"'";
		}
		pPrForm.setId(ids);

		return formService.doSelectLightCountIn(pPrForm);
	}

	/**
	 * 财务经理审批
	 * 
	 * @param approvedForm
	 */
	protected void approve4(PrApprovedForm approvedForm) {

		approve1(approvedForm);
		approvedForm.getPrPrForm().setState(null);
		approvedForm.getPrPrForm().setIsCapital(null);
		formService.updatePrForm(approvedForm.getPrPrForm());
		// 保存审批表和相关评审
		// prApprovedFormDao.insert(approvedForm);

	}

	/**
	 * 采购确认
	 * @param prForm
	 */
	public void doBuyerConfirm(PrPrForm prForm, Tempolyee emp , PrApprovedForm approvedForm ,String isSubmit) {
		formService.updatePrForm(prForm);
		boolean flag = Boolean.parseBoolean(isSubmit);//prForm.isUnFinished();
		for (PrBuyContext item : prForm.getPrBuyContexts()) {
			formService.updatePrContext(item);
		}
		if (flag) {
			StateManager stateManager = prForm.getStateManager();
			int prState = stateManager.nextState;
		//	PrApprovedForm approvedForm = new PrApprovedForm();
			approvedForm.setPrPrForm(prForm);
		//	approvedForm.setIsApproved(1);
		//	approvedForm.setContext("采购员已确认(系统默认)");
			approvedForm.setType(PrApprovedForm.BuyType);
			approvedForm.setTempolyee(emp);
			
			//如果采购员是采购经理
			//step1： 状态为下2步
			//step2： 添加系统默认批准信息
			if(approvedForm.getIsApproved() == 1 && emp.getRoleids_b().contains("PRBuyerManager")){
				prState = prForm.getNext2Step();
				if ( approvedForm.getPrPrForm().getTotal() <= PrPrForm.BUYER_LEVEL )// 同意&& approvedForm.getPrPrForm().getIsPlan()!=null&&approvedForm.getPrPrForm().getIsPlan()==0
				{
					prState = StateManager.PRNO;
				}
				
				approvedForm.setPrPrForm(prForm);
				approvedForm.setIsApproved(1);
				approvedForm.setContext(approvedForm.getContext()+ "采购经理批准(系统默认)");
				approvedForm.setTempolyee(emp);
				prApprovedFormDao.insert(approvedForm);
				prForm.setInfo(0);
				formService.updatePrState(prForm, prState);
				return ;
			}
			
		//	this.doApprovePR(approvedForm);
			approve1(approvedForm);
		}
		
	}
	
	/**
	 * 采购经理审批
	 */
	protected void approveBuyerManager(PrApprovedForm approvedForm) {

		this.prApprovedFormDao.insert(approvedForm);
		int prState = nextStep(approvedForm);
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}
	
	
	protected void approveSHBuyerManager(PrApprovedForm approvedForm) {

		this.prApprovedFormDao.insert(approvedForm);
		int prState = nextStep(approvedForm);
		String costCenterOwner = approvedForm.getPrPrForm().getPrCostCenter().getOwner();
		if(costCenterOwner!=null && !"".equals(costCenterOwner)&& !approvedForm.getTempolyee().getUid().equals(costCenterOwner) ) { //costcenter和部门不一致
			this.prApprovedFormDao.insert(approvedForm);
			if (prState >= 88) {
				return ;
			}else {
				//update prForm's otherCostCentre
				PrPrForm form = new PrPrForm();
				form.setId(approvedForm.getPrPrForm().getId());
				form.setIsOtherCostCenter("Y");
				formService.updatePrForm(form);
				formService.updatePrState(approvedForm.getPrPrForm(),19);
			}
		}else {
			if(approvedForm.getIsApproved() == 1) {
				approvedForm.getPrPrForm().setState(StateManager.CWCONTROL); //jump to next step
				approvedForm.getPrPrForm().setIsOtherCostCenter(""); //clear costcenter
				this.prApprovedFormDao.insert(approvedForm);
				formService.updatePrState(approvedForm.getPrPrForm(),StateManager.CWCONTROL);
			}		
			
			return;
		}
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}
	
	/**
	 * doa审批
	 * 
	 * @param approvedForm
	 */
	protected void approveDOA(PrApprovedForm approvedForm) {
		int step = 0;
		double total_cost = approvedForm.getPrPrForm().getTotal();// 总金额
		PrPrForm prForm = approvedForm.getPrPrForm();
		StateManager stateManager = approvedForm.getPrPrForm()
				.getStateManager();
		int curState = stateManager.curState;
		step = dm.selectDOA(total_cost, prForm.getIsPlan(), prForm
				.getIsCapital(), stateManager);
		//如果类型是
		if(prForm.getType()!=null && prForm.getType().contains("11")) step = StateManager.FEEDBACK;
		this.prApprovedFormDao.insert(approvedForm);
		int prState;
		if (approvedForm.getIsApproved() == 1)// 同意
		{
			if (step == -3) {
				prState = stateManager.nextState;
				//如果原先状态是财务经理，将更新为副总，并是成本中心为外成本中心
//				----------------------------------
				if(curState==stateManager.FinanceManager && prState == stateManager.DeputyGeneral && prForm.getIsOtherCostCenter()!=null && prForm.getIsOtherCostCenter().equals("Y") && prForm.getIsCapital()==1 ){
					
					
					
					
					if(prForm.getPrCostCenter()!=null && prForm.getPrCostCenter().getId()!=null && prForm.getPrCostCenter().getId()!=5 && prForm.getPrCostCenter().getId()!=6 && prForm.getPrCostCenter().getId()!= 10){//去除不需要李总审批的cost_center 5,6,10
						prState = stateManager.OCC;
					}
				}
			} else {
				prState = step;
			}
		} else if (approvedForm.getIsApproved() == 0){
			prState = stateManager.failureState;
		}else if (approvedForm.getIsApproved() == 3 && curState==stateManager.FinanceManager){
			prState = stateManager.BuyConfig;
		}else{
			return;
		}
		//if department is HR, turn to GM 
		if(prForm.getTempolyeeByApplicantId().getTdepartment().getId()== 10030 && prState == stateManager.DeputyGeneral){
			prState = StateManager.PRManager;
		}
		formService.updatePrState(approvedForm.getPrPrForm(), prState);
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public PrRightsManager getRightsManager() {
		return rightsManager;
	}

	public void setRightsManager(PrRightsManager rightsManager) {
		this.rightsManager = rightsManager;
	}

	public PrBuyerManager getBuyerManager() {
		return buyerManager;
	}

	public void setBuyerManager(PrBuyerManager buyerManager) {
		this.buyerManager = buyerManager;
	}
	
	/**
	 * ----------------------@author wym ----------------
	 * ----------------------@Time 20130630--------------
	 */
	
	/**
	 * Plant 财务审批
	 * isCaptial -> SH finance
	 * isExpense -> Plant Finance Manager
	 */
	protected void approveFinanceCYanTai(PrApprovedForm approvedForm) {
		this.prApprovedFormDao.insert(approvedForm);
		int prState;
		StateManager stateManager = null;
		PrPrForm form = approvedForm.getPrPrForm();
		formService.updatePrForm(form);// 更新PR
		if(form.getSource()==2){
		 stateManager = approvedForm.getPrPrForm().getSYStateManager();
		}else if(form.getSource()==3){
			stateManager = approvedForm.getPrPrForm().getYanTaiStateManager();
		}else if(form.getSource()==4){
			stateManager = approvedForm.getPrPrForm().getSHStateManager();
		}
		
		// 同意
		if (approvedForm.getIsApproved() == 1){
			int isCpatial = approvedForm.getPrPrForm().getIsCapital();
			if(isCpatial == PrPrForm.CAPTIAL){
				//isCaptial
				prState = StateManager.SH_FINANCE;
			}else{
				prState = stateManager.nextState;
			}
			
			//如果需要相关部门审批
			//跳转到相关部门（13），laststate 设为（prState）
			if(approvedForm.getPrPrForm().getState()!=null && approvedForm.getPrPrForm().getState() == 2 &&
					approvedForm.getRelateUids()!=null && !"".equals(approvedForm.getRelateUids())){
				prState = StateManager.Realtion2;//13
				approvedForm.getPrPrForm().setLastState(prState);
			}
		} else if (approvedForm.getIsApproved() == 0) {
			prState = stateManager.failureState;
			//退回采购员
		}else if(approvedForm.getIsApproved() == 5){ 
			prState = StateManager.BuyConfig;
		}else {
			prState = 88;
		}
		
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}
	
	/**
	 *<p> YanTai,SH,SY Plant DOA </p>
	 */
	protected void doApproveYanTaiDOA(PrApprovedForm approvedForm){
		PrPrForm form = approvedForm.getPrPrForm();
		float total = form.getTotal();
		StateManager stateManager = form.getYanTaiStateManager();
		//-----
		if(form.getSource()==2){
			stateManager = form.getSYStateManager();
		}
		int nextState = stateManager.curState;
		int level1 = 20000 ,level2 = 200000,level3 = 600000;
		int level3s[] = {600000,250000*6};
		String need_cto_dprtIDs = "10094,10085,10073,10044,10040";
		String[] need_cto_costcenterids = {"115","133","134","135","136","137","138"};
		//--------------------------------------------------------------
		String neednt_LY_dprtIDs = "10094,10085,10073";
		boolean flag = false;
		if (approvedForm.getIsApproved() == 1)// 同意
		{
			if(stateManager.curState == StateManager.YTGM || stateManager.curState == StateManager.SYGM) {
				//State is Plant Manager 
				//int dprt = form.getTempolyeeByApplicantId().getTdepartment().getId();
				int costcenterid = form.getPrCostCenter().getId();
				for(String ctoid : need_cto_costcenterids){
					if(ctoid.equals(costcenterid)){//如果成本中心需要总部技术总监审批
						flag = true;
					}
				}
				if(flag){
					//IF Dept is Engineering DOA is 2k
					nextState = stateManager.nextState;
				}else if(total > level1){
					nextState = StateManager.SH_FINANCE_MANAGER ;//SH_FINANCE_MANAGER
				}else{
					nextState = stateManager.FEEDBACK;
				}
				
			}else if(stateManager.curState == StateManager.SH_CTO) {
				//State is SH_CTO
				if(total > level1) {					
					nextState = stateManager.nextState;
				}else {
					nextState = stateManager.FEEDBACK;
				}
			}else if(stateManager.curState == StateManager.SH_FINANCE_MANAGER) {
				//State is SH_FINANCE_MANAGER
				if(total > level2) {					
					nextState = stateManager.nextState;
					//if source is SH Plant & DEPT is not (XXXX)
					if(form.getSource() == PrPrForm.SHPLANT){
						//如果上海厂填写，部门属于李总的跳过副总，其他继续
						int dprt = form.getTempolyeeByApplicantId().getTdepartment().getId();
						if(!StateManager.DGM1.contains(dprt+"")){
							//不是马总下面的
							if(total >  level3s[form.getIsCapital()]) {					
								nextState = stateManager.nextState;
							}else {
								nextState = stateManager.FEEDBACK;
							}
						}
					}
				}else {
					nextState = stateManager.FEEDBACK;
				}
			}else if(stateManager.curState == StateManager.DeputyGeneral) {
				//State is SDAACGM
				if(total > level3s[form.getIsCapital()]) {					
					nextState = stateManager.nextState;										
				}else {
					//if is captial go to Xue Huaqin
					if(form.getIsCapital() == 0){
						nextState = 58;
					}else{
						nextState = stateManager.FEEDBACK;
					}
				}
		
			}else if(stateManager.curState == StateManager.SDAACGM) {
				//if is captial go to Xue Huaqin
				if(form.getIsCapital() == 0){
					nextState = 58;
				}else{				
					nextState = stateManager.nextState;
				}
		
			}
		}else if(approvedForm.getIsApproved() == 0){//refuse
			nextState = stateManager.failureState;
		}else if(approvedForm.getIsApproved() == 2){
			approvedForm.getPrPrForm().setInfo(1);//需要解释
		}
		
		if(nextState == stateManager.FEEDBACK && form.getIsCapital() == 0){
			nextState = StateManager.SH_FEEDBACK;
		}
		
		formService.updatePrState(approvedForm.getPrPrForm(), nextState);
		this.prApprovedFormDao.insert(approvedForm);
	}

}
