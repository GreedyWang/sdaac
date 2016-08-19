package app.web.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.struts.DispatchActionSupport;
import sdaac.wym.app.Hr.BounceFigureImpl;
import sdaac.wym.app.Hr.BounceInboundImpl;
import sdaac.wym.app.Hr.BouncePostImpl;
import sdaac.wym.app.Hr.DepartmentIndexStateManager;
import sdaac.wym.app.Hr.OrganizationManager;
import sdaac.wym.app.Hr.OvertimeManager;
import sdaac.wym.app.Service.ConfManager;
import sdaac.wym.app.Service.IOSManager;
import sdaac.wym.app.Service.audit.AuditBizImpl;
import sdaac.wym.app.Service.audit.AuditDailyBizImpl;
import sdaac.wym.app.Service.audit.AuditManager;
import sdaac.wym.app.Service.cw.BSTaxManager;
import sdaac.wym.app.Service.cw.DYTaxManager;
import sdaac.wym.app.Service.cw.GmTaxManager;
import sdaac.wym.app.Service.cw.LmTaxManager;
import sdaac.wym.app.Service.cw.OrdinaryTaxManager;
import sdaac.wym.app.Service.cw.PoTaxManager;
import sdaac.wym.app.Service.cw.XdyTaxManager;
import sdaac.wym.app.Service.plm.PlmManager;
import sdaac.wym.app.Service.vave.ApprovedRateManager;
import sdaac.wym.app.Service.vave.BusinessTopicManager;
import sdaac.wym.app.Service.vave.PointsDetailsManager;
import sdaac.wym.common.Service.RoleManager;

import com.security.biz.RoleBiz;
import com.security.biz.RoleFunctionBiz;
import com.security.biz.UserRoleBiz;
import com.security.biz.impl.MailManager;
import common.util.MyUtil;
import common.util.StringsToFloats;
import app.biz.DepartmentBiz;
import app.biz.DepartmentIndexBiz;
import app.biz.EmpBiz;
import app.biz.EmpIndexBiz;
import app.biz.IPTmp;
import app.biz.IndexTargetBiz;
import app.biz.PersonalPBiz;
import app.biz.PostBiz;
import app.biz.PostIdBiz;
import app.biz.PostTypeBiz;
import app.biz.UserBiz;
import app.biz.impl.LogsBizImpl;
import app.biz.impl.PersonalPBizJdbcImpl;
import app.biz.vave.CompanySuggestionBiz;
import app.biz.vave.MainSuggestionBiz;
import app.biz.vave.OtherSuggestionBiz;
import app.biz.vave.ProgramBiz;
import app.biz.vave.ProjectScheduleBiz;
import app.biz.vave.ProposalBiz;
import app.biz.vave.ProposalMoreBiz;
import app.biz.vave.ProposalStateBiz;
import app.biz.vave.StatisticsScheduleBiz;
import app.biz.vave.SupplyBiz;
import app.biz.vave.TeamWorkBiz;
import app.biz.vave.TeamWorkProjectBiz;
import app.biz.vave.ThemeBiz;
import app.biz.vave.VaveInfoBiz;
import app.biz.vave.impl.ProposalStateBizImpl;
import app.biz.vave.impl.ThemeBizImpl;
import app.entity.Tdepartment;
import app.entity.Tproposal;
import app.entity.vave.Schedule;
import app.entity.vave.VaveProjectSchedule;
import app.entity.vave.VaveProposalMore;
import app.entity.vave.VaveProposalState;
import app.entity.vave.VaveProposalStateId;
import app.entity.vave.VaveSchedule;
import app.entity.vave.VaveStatisticsSchedule;

public class BaseAction extends DispatchActionSupport {
	private EmpBiz empBiz=null;
	private PostBiz postBiz=null;
	private UserBiz userBiz=null;
	private PersonalPBiz ppBiz=null;
	private PersonalPBizJdbcImpl personalPjdbc=null;
	private IPTmp ipTmpBiz=null;
	private DepartmentBiz departBiz=null;
	private IndexTargetBiz indexBiz=null;
	private DepartmentIndexBiz departIndexBiz=null;
	private PostTypeBiz postTypeBiz=null;
	private EmpIndexBiz empIndexBiz=null;
	private PostIdBiz postIDBiz=null;
	private LogsBizImpl logBiz=null;
	private ProgramBiz programBiz=null;
	private ProposalBiz proposalBiz=null;
	private ThemeBiz themeBiz=null;
	private MainSuggestionBiz mainSugBiz=null;
	private OtherSuggestionBiz otherSugBiz=null;
	private CompanySuggestionBiz companySugBiz=null;
	private TeamWorkProjectBiz teamWorkProjectBiz=null;
	private TeamWorkBiz teamWorkBiz=null;
	private ProjectScheduleBiz  projectScheduleBiz=null;
	private StatisticsScheduleBiz statisticsScheduleBiz=null; 
	private	ProposalMoreBiz proposalMore=null;
	private ProposalStateBiz proposalStateBiz=null;
	private VaveInfoBiz vaveInfoBiz=null;
	private PersonalPBizJdbcImpl jdbcTemplateImpl=null;
	private SupplyBiz supplyBiz=null;
	private RoleFunctionBiz roleFunBiz=null;
	private UserRoleBiz userRoleBiz=null;
	private RoleBiz roleBiz=null;
	private IOSManager osManager=null;
	private BounceFigureImpl bfManager=null;
	private BouncePostImpl bpManager=null;
	private BounceInboundImpl	biManager;
	private PlmManager mPlmManager=null;
	private PlmManager ePlmManager=null;
	private MailManager mailManager=null;
	private AuditBizImpl auditBizImpl;
	private AuditDailyBizImpl auditDailyBizImpl;
	private DepartmentIndexStateManager departmentIndexStateManager;
	private OrganizationManager ogManager;
	private ConfManager confmanager;
	private PointsDetailsManager pointsDetailsManager; 
	private BusinessTopicManager businessTopicManager;
	private GmTaxManager gmTaxManager;
	private OrdinaryTaxManager ordinaryTaxManager;
	private LmTaxManager lmTaxManager;
	private XdyTaxManager xdyTaxManager;
	private PoTaxManager poTaxManager;
	private ApprovedRateManager approveRateManager;
	private RoleManager roleManager;
	private OvertimeManager overtimeManager;
	private BSTaxManager bSTaxManager;
	private DYTaxManager dYTaxManager;
	
	public BSTaxManager getDYTaxManager() {
		bSTaxManager=(BSTaxManager)this.getWebApplicationContext().getBean("bSTaxManager");
		return bSTaxManager;
	}
	public DYTaxManager getBSTaxManager() {
		dYTaxManager=(DYTaxManager)this.getWebApplicationContext().getBean("dYTaxManager");
		return dYTaxManager;
	}
	
	public OvertimeManager getOvertimeManager() {
		overtimeManager=(OvertimeManager)this.getWebApplicationContext().getBean("overtimeManager");
		return overtimeManager;
	}
	
	public RoleManager getRoleManager() {
		roleManager=(RoleManager)this.getWebApplicationContext().getBean("roleManager");
		return roleManager;
	}
	
	public ApprovedRateManager getApproveRateManager() {
		approveRateManager=(ApprovedRateManager)this.getWebApplicationContext().getBean("ApproveRateManager");
		return approveRateManager;
	}
	
	public LmTaxManager getLmTaxManager() {
		lmTaxManager=(LmTaxManager)this.getWebApplicationContext().getBean("lmTaxManager");
		return lmTaxManager;
	}
	
	public XdyTaxManager getXdyTaxManager() {
		xdyTaxManager=(XdyTaxManager)this.getWebApplicationContext().getBean("xdyTaxManager");
		return xdyTaxManager;
	}
	
	public PoTaxManager getPoTaxManager() {
		poTaxManager=(PoTaxManager)this.getWebApplicationContext().getBean("poTaxManager");
		return poTaxManager;
	}
	
	public GmTaxManager getGmTaxManager() {
		gmTaxManager=(GmTaxManager)this.getWebApplicationContext().getBean("gmTaxManager");
		return gmTaxManager;
	}
	
	public OrdinaryTaxManager getOrdinaryTaxManager() {
		ordinaryTaxManager=(OrdinaryTaxManager)this.getWebApplicationContext().getBean("ordinaryTaxManager");
		return ordinaryTaxManager;
	}

	public BusinessTopicManager getBusinessTopicManager() {
		businessTopicManager=(BusinessTopicManager)this.getWebApplicationContext().getBean("businessTopicManager");
		return businessTopicManager;
	}
	
	public PointsDetailsManager getPointsDetailsManager() {
		pointsDetailsManager=(PointsDetailsManager)this.getWebApplicationContext().getBean("pointsDetailsManger");
		return pointsDetailsManager;
	}

	public ConfManager getConfmanager() {
		confmanager=(ConfManager)this.getWebApplicationContext().getBean("confManager");
		return confmanager;
	}
	
	public OrganizationManager getOgManager() {
		ogManager=(OrganizationManager)this.getWebApplicationContext().getBean("organizationManager");
		return ogManager;
	}

	public MailManager getMailManager() {
		mailManager=(MailManager)this.getWebApplicationContext().getBean("mailBiz");
		return mailManager;
	}

	public PlmManager getMPlmManager() {
		mPlmManager=(PlmManager)this.getWebApplicationContext().getBean("MpratplmManager");
		return mPlmManager;
	}
	
	public PlmManager getEPlmManager() {
		ePlmManager=(PlmManager)this.getWebApplicationContext().getBean("EpratplmManager");
		return ePlmManager;
	}

	public IOSManager getOsManager() {
		osManager=(IOSManager)this.getWebApplicationContext().getBean("osManager");
		return osManager;
	}

	public RoleBiz getRoleBiz() {
		roleBiz=(RoleBiz)this.getWebApplicationContext().getBean("roleBiz");
		return roleBiz;
	}

	public RoleFunctionBiz getRoleFunBiz() {
		roleFunBiz=(RoleFunctionBiz)this.getWebApplicationContext().getBean("roleFunctionBiz");
		return roleFunBiz;
	}

	public UserRoleBiz getUserRoleBiz() {
		userRoleBiz=(UserRoleBiz)this.getWebApplicationContext().getBean("userRoleBiz");
		return userRoleBiz;
	}

	public SupplyBiz getSupplyBiz() {
		supplyBiz=(SupplyBiz)this.getWebApplicationContext().getBean("supplyBiz");
		return supplyBiz;
	}

	public PersonalPBizJdbcImpl getJdbcTemplateImpl() {
		jdbcTemplateImpl=(PersonalPBizJdbcImpl) this.getWebApplicationContext().getBean("personalPjdbc");
		return jdbcTemplateImpl;
	}
	
	public VaveInfoBiz getVaveInfoBiz() {
		vaveInfoBiz=(VaveInfoBiz)this.getWebApplicationContext().getBean("vave_infoBiz");
		return vaveInfoBiz;
	}
	public ProposalStateBiz getProposalStateBiz() {
		proposalStateBiz=(ProposalStateBiz)this.getWebApplicationContext().getBean("proposalStateBiz");
		return proposalStateBiz;
	}
	public ProposalMoreBiz getProposalMore() {
		proposalMore=(ProposalMoreBiz)this.getWebApplicationContext().getBean("proposalMoreBiz");
		return proposalMore;
	}
	public ProjectScheduleBiz getProjectScheduleBiz() {
		projectScheduleBiz=(ProjectScheduleBiz)this.getWebApplicationContext().getBean("projectScheduleBiz");
		return projectScheduleBiz;
	}
	public StatisticsScheduleBiz getStatisticsScheduleBiz() {
		statisticsScheduleBiz=(StatisticsScheduleBiz)this.getWebApplicationContext().getBean("statisticsScheduleBiz");
		return statisticsScheduleBiz;
	}
	public CompanySuggestionBiz getCompanySugBiz() {
		companySugBiz=(CompanySuggestionBiz)this.getWebApplicationContext().getBean("companySuggestionBiz");
		return companySugBiz;
	}
	public TeamWorkProjectBiz getTeamWorkProjectBiz() {
		teamWorkProjectBiz=(TeamWorkProjectBiz)this.getWebApplicationContext().getBean("teamWorkProjectBiz");
		return teamWorkProjectBiz;
	}
	public TeamWorkBiz getTeamWorkBiz() {
		teamWorkBiz=(TeamWorkBiz)this.getWebApplicationContext().getBean("teamWorkBiz");
		return teamWorkBiz;
	}

	public ProposalBiz getProposalBiz() {
		proposalBiz=(ProposalBiz) this.getWebApplicationContext().getBean("proposalBiz");
		return proposalBiz;
	}



	public ThemeBiz getThemeBiz() {
		themeBiz=(ThemeBizImpl)this.getWebApplicationContext().getBean("themeBiz");
		return themeBiz;
	}



	public EmpIndexBiz getEmpIndexBiz() {
		empIndexBiz=(EmpIndexBiz) this.getWebApplicationContext().getBean("empindexBiz");
		return empIndexBiz;
	}

	public IPTmp getIpTmpBiz() {
		ipTmpBiz=(IPTmp) this.getWebApplicationContext().getBean("ipTmpBiz");
		return ipTmpBiz;
	}

	public PersonalPBizJdbcImpl getPersonalPjdbc() {
		personalPjdbc=(PersonalPBizJdbcImpl) this.getWebApplicationContext().getBean("personalPjdbc");
		return personalPjdbc;
	}

	public PersonalPBiz getPpBiz() {
		ppBiz=(PersonalPBiz) this.getWebApplicationContext().getBean("ppBiz");
		return ppBiz;
	}

	public PostBiz getPostBiz() {
		postBiz=(PostBiz) this.getWebApplicationContext().getBean("postBiz");
		return postBiz;
	}

	public EmpBiz getEmpBiz() {
		empBiz=(EmpBiz) this.getWebApplicationContext().getBean("empBiz");
		return empBiz;
	}

	public UserBiz getUserBiz() {
		userBiz=(UserBiz) this.getWebApplicationContext().getBean("userBiz");
		return userBiz;
	}

	public DepartmentBiz getDepartBiz() {
		this.departBiz=(DepartmentBiz)this.getWebApplicationContext().getBean("departBiz");
		return departBiz;
	}

	public DepartmentIndexBiz getDepartIndexBiz() {
		this.departIndexBiz=(DepartmentIndexBiz)this.getWebApplicationContext().getBean("departIndexBiz");
		return departIndexBiz;
	}

	public IndexTargetBiz getIndexBiz() {
		this.indexBiz=(IndexTargetBiz)this.getWebApplicationContext().getBean("indexBiz");
		return indexBiz;
	}

	public PostTypeBiz getPostTypeBiz() {
		this.postTypeBiz=(PostTypeBiz)this.getWebApplicationContext().getBean("ptBiz");
		return postTypeBiz;
	}

	public PostIdBiz getPostIDBiz() {
		this.postIDBiz=(PostIdBiz)this.getWebApplicationContext().getBean("postIdBiz");
		return postIDBiz;
	}

	public LogsBizImpl getLogBiz() {
		
		logBiz= (LogsBizImpl)this.getWebApplicationContext().getBean("DAOLog");
		return logBiz;
	}

	public ProgramBiz getProgramBiz() {
		programBiz=(ProgramBiz)this.getWebApplicationContext().getBean("progtambiz");
		return programBiz;
	}



	public MainSuggestionBiz getMainSugBiz() {
		mainSugBiz=(MainSuggestionBiz)this.getWebApplicationContext().getBean("mainsugBiz");
		return mainSugBiz;
	}



	public OtherSuggestionBiz getOtherSugBiz() {
		otherSugBiz=(OtherSuggestionBiz)this.getWebApplicationContext().getBean("othersugBiz");
		return otherSugBiz;
	}
	/**
	 * 插入计算附表&提案单附加部分
	 * @param request,response,String proposal
	 * */
	protected void doInsertSchedule(HttpServletRequest request, HttpServletResponse response,String proposalID) {
	//get schedule date
//		String[] processes=request.getParameterValues("process");
//		Float[] cycles=StringsToFloats.toTransForm(request.getParameterValues("cycle"));
//		String[] progresses=request.getParameterValues("progress");
//		Float[] onceSavings=StringsToFloats.toTransForm(request.getParameterValues("oneTimeCosts"));
		String[] projects=request.getParameterValues("project");
		String[] quantitys=request.getParameterValues("quantity");
		String[] units=request.getParameterValues("unit");
		this.getStatisticsScheduleBiz().doInsert( projects,quantitys,units,proposalID);
		
		//proposalMore.set
		Float expectSaving=0f;
		Date expectFinishDate=MyUtil.formatDate(request.getParameter("expectFinishDate"));
		if(request.getParameter("expectSaving")!=null&&!request.getParameter("expectSaving").equals(""))
		{
			expectSaving=Float.parseFloat(request.getParameter("expectSaving"));
		}	
		String ins_program=request.getParameter("ins_program");
		VaveProposalMore proposalMore=new VaveProposalMore(proposalID,expectSaving,expectFinishDate,ins_program);
		this.getProposalMore().doInset(proposalMore);
	}
	/**
	 * 显示计算附表
	 */
	protected List<Schedule> doSelectSchedule(String proposalID)
	{
		List<VaveStatisticsSchedule> statisticsList=this.getStatisticsScheduleBiz().doSelectByPK(proposalID);
		List<VaveProjectSchedule> projectList= this.getProjectScheduleBiz().doSelectByProposalIDMaxVersion(proposalID);
		List<Schedule> scheduleList=new ArrayList<Schedule>();
		if(projectList!=null&&!projectList.isEmpty())
		{
			for(int i=0;i<projectList.size();i++)
			{
				Schedule tmp=new Schedule();
				tmp.setProject(projectList.get(i));
				//tmp.setStatistics(statisticsList.get(i));
				scheduleList.add(tmp);
			}
		}
		return scheduleList;
	}
	/**
	 * 查询提案表附加部分
	 * @param proposalID
	 * @return
	 */
	protected VaveProposalMore doSelectProposalMore(String proposalID)
	{
		return this.getProposalMore().doSelectByProposalIDMaxVersion(proposalID);
	}
	/**
	 * 插入提案state表，
	 */	
	protected void doInsertProposalState(Tproposal item) {
		//add the proposal'state info
		Integer departmentID=this.getEmpBiz().doSelectGetEmpDetails(item.getProposalPerson().getUid()).getTdepartment().getId();
		String[] targets=this.getDepartBiz().doSelectVaves(departmentID);
		VaveProposalState state=new VaveProposalState();
		//0:未打开
		state.setIsOpen(0);
		for(int i=0;i<targets.length;i++)
		{
			if(targets[i]!=null)
			{
				state.setId(new VaveProposalStateId(item,targets[i]));
				this.getProposalStateBiz().doInsert(state);
			}		
		}
		
	}
	/**
	 * 插入提案状态表
	 * @param item
	 * @param departID
	 */
	protected void doInsertProposalState(Tproposal item,Integer departID) {
		//add the proposal'state info
		String[] targets=this.getDepartBiz().doSelectVaves(departID);
		
		
		VaveProposalState state=new VaveProposalState();
		//0:未打开
		state.setIsOpen(0);
		for(int i=0;i<targets.length;i++)
		{
			if(targets[i]!=null)
			{
				state.setId(new VaveProposalStateId(item,targets[i]));
				if(this.getProposalStateBiz().doSelectByPK(state)!=null)
				{
					this.getProposalStateBiz().doUpdate(state);
				}
				else
				{
					this.getProposalStateBiz().doInsert(state);
				}
				
			}
		}
	}
	/**
	 * 更改提案isOpen状态
	 */
	protected void doUpdateProposalState(Tproposal item) {
		VaveProposalState state=new VaveProposalState();
		state.setIsOpen(0);
		Tproposal tp=this.getProposalBiz().doSelectByPK(item.getId());
		if(item.getState()==1)
		{
			
			Integer departmentID=this.getEmpBiz().doSelectGetEmpDetails(tp.getProposalPerson().getUid()).getTdepartment().getId();
		//	Tdepartment depart=this.getDepartBiz().doSelectManagerByDepartID(departmentID);
			String[] targets=this.getDepartBiz().doSelectVaves(departmentID);
			
			//VaveProposalState state=new VaveProposalState();
			//0:未打开
			state.setIsOpen(0);
		//	String[] targets=new String[]{depart.getPmangerid(),depart.getVaveUid()};
			for(int i=0;i<targets.length;i++)
			{
				state.setId(new VaveProposalStateId(item,targets[i]));
				this.getProposalStateBiz().doUpdate(state);	
			//	this.getProposalStateBiz().doInsert(state);
			}
			//state.setId(new VaveProposalStateId(item,item.getProposalPerson().getUid()));
		}if(item.getState()==0)
		{
			state.setId(new VaveProposalStateId(item,tp.getProposalPerson().getUid()));
			this.getProposalStateBiz().doUpdate(state);	
		}			
	}

	public BounceFigureImpl getBfManager() {
		bfManager=(BounceFigureImpl)this.getWebApplicationContext().getBean("bfBiz");
		return bfManager;
	}

	public BouncePostImpl getBpManager() {
		bpManager=(BouncePostImpl)this.getWebApplicationContext().getBean("bpBiz");
		return bpManager;
	}

	public BounceInboundImpl getBiManager() {
		biManager=(BounceInboundImpl)this.getWebApplicationContext().getBean("biBiz");
		return biManager;				
	}

	public AuditBizImpl getAuditBizImpl() {
		auditBizImpl=(AuditBizImpl)this.getWebApplicationContext().getBean("auditBiz");
		return auditBizImpl;	

	}

	public AuditDailyBizImpl getAuditDailyBizImpl() {
		auditDailyBizImpl=(AuditDailyBizImpl)this.getWebApplicationContext().getBean("auditDailyBiz");
		return auditDailyBizImpl;	
	
	}

	public DepartmentIndexStateManager getDepartmentIndexStateManager() {
		departmentIndexStateManager=(DepartmentIndexStateManager)this.getWebApplicationContext().getBean("departIndexStateManager");		
		return departmentIndexStateManager;
	}
	
}
