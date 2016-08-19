package com.sdaac.pr.approve;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpm.api.ProcessInstance;

import app.entity.epor.PrPrForm;

import com.sdaac.common.org.OrgManager;
import com.sdaac.pr.approve.element.ApproveMembersElement;
import com.sdaac.pr.approve.element.ElementHelper;
import com.sdaac.pr.approve.element.FormControlElement;
import com.sdaac.pr.iapprove.IprocessEngine;
import com.sdaac.pr.tools.TaskDelegateCmd;
import common.util.PropertiesRead;

import org.jbpm.api.Configuration;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;

public class PRProcessEngine //implements IprocessEngine
{
	
	protected RepositoryService repositoryService; //������̷���
	
	protected ExecutionService executionService; //���ִ�з��� 
	
	protected TaskService taskService ; //�������
	
	protected HistoryService historyServcie; //��ʷ����
	
	protected ManagementService managermentService; //��̹������
	
	protected IdentityService identityService; //�����֤����
	
	protected ElementHelper elementHelper;
	protected ProcessEngine processEngine;
	public PRProcessEngine(){
		System.out.println("======================================init=======================================");
		//processEngine =  Configuration.getProcessEngine();
	}
	private boolean flag = false;
	
	public void init(){
		if(!flag){
			repositoryService = processEngine.getRepositoryService();
			executionService = processEngine.getExecutionService();
			taskService = processEngine.getTaskService();
			historyServcie = processEngine.getHistoryService();
			managermentService = processEngine.getManagementService();
			identityService = processEngine.getIdentityService();
			flag = true;
		}
	}
	//private static PRProcessEngine instance = new PRProcessEngine();
	
//	public static PRProcessEngine newInstance(){
//		if(instance!=null){
//			return instance;
//		}else{
//			return new PRProcessEngine();
//		}
//	}
	

	/**
	 * 发起申请
	 * @return
	 */
	public String apply(PrPrForm form,String[] roles){	
		//jbpm engine start
//		System.out.print("1234567");
//		Map<String,Object> variable = new HashMap<String,Object>();
//		ApproveMembersElement approveMembers = new ApproveMembersElement();;
//		approveMembers.setDm("dm1");
//		approveMembers.setOwner("wym");
//		variable.put("approveMembers", approveMembers);	
		//init();
		elementHelper = new ElementHelper(form,roles);
		
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("test", elementHelper.createElement());//
//		System.out.println("============================================>1234567");
//		
//		Map<String,Object> variable = new HashMap<String,Object>();
//		ApproveMembersElement approveMembers = new ApproveMembersElement();
//		approveMembers.setDm("dm1");
//		approveMembers.setOwner("wym");
//		variable.put("approveMembers", approveMembers);		
//		ProcessInstance processInstance = executionService.startProcessInstanceByKey("test",variable);
		
		return processInstance.getId();
		//
	}
	
	public void setVariable(PrPrForm form,String[] roles){
		String pid = form.getProcessInstanceId();
		ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
		ElementHelper elementHelper = new ElementHelper(form,roles);
//		approveMembersElement = elementHelper.getApproveMembers();
		executionService.setVariable(pid, ElementHelper.Approve_Members, elementHelper.getApproveMembers());
		executionService.setVariable(pid, ElementHelper.Control_Element, elementHelper.getFormControlElement());
	}
	/**
	 * set from control element[next fin]
	 * @description after Finance operator approve if total_category is something then go to the role [Next Fin] 
	 * @param form
	 * @param roles
	 */
	public void setControlELementVariableWithNextFin(PrPrForm form) {
		String pid = form.getProcessInstanceId();
		FormControlElement formControlElement = (FormControlElement) executionService
				.getVariable(pid, ElementHelper.Control_Element);
		if (formControlElement.getNextFin() == null) {
			formControlElement.setNextFin("true");
			executionService.setVariable(pid, ElementHelper.Control_Element,
					formControlElement);
		}

	}
	
	/**
	 * 修改审批人
	 * @param pid
	 * @param netid
	 * @param stepName
	 */
	public void setVariable(String pid,String netid,String stepName){
		//Start
//		ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
//		approveMembersElement.changeApprovor(netid, stepName);
//		executionService.setVariable(pid, ElementHelper.Approve_Members, approveMembersElement);
		//END
		Task task = taskService.createTaskQuery().processInstanceId(pid).uniqueResult();
		TaskDelegateCmd taskDelegateCmd = new TaskDelegateCmd(task.getId(),netid);
		processEngine.execute(taskDelegateCmd);
	}

//	//@Override
//	public String approve(int target,String pid) {
//		//String id;
//		// TODO Auto-generated method stub    test.150001
////		executionService.signalExecutionById(pid,"to DM");
//		Task task = getCurrectActivity(pid);
//		String setp = task.getActivityName();
//		String taskid = task.getId();
//		taskService.completeTask(taskid,"next");
//		return setp;
//	}

	public String approve(int target,String pid){
		//init();
		Task task = getCurrectActivity(pid);
		String setp = task.getActivityName();
		String assignee = task.getAssignee();
		String taskid = task.getId();
		String fin = "to fin";
		if(setp!=null && setp.equals("capital_check")){
			ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
			if(approveMembersElement.getPm()==null || "".equals(approveMembersElement.getPm())
					|| "undefined".equals(approveMembersElement.getPm())
					|| approveMembersElement.getDm().equals(approveMembersElement.getPm())){
				taskService.completeTask(taskid,fin);
				return setp;
			}
		}else if(setp!=null && setp.equals("dmsupervisor")){
			ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
			executionService.getVariable(pid, ElementHelper.Approve_Members);
			if(approveMembersElement.getCcowner()==null || approveMembersElement.getDm().equals(approveMembersElement.getCcowner()) 
					|| "".equals(approveMembersElement.getCcowner()) || OrgManager.blankOwner.equals(approveMembersElement.getCcowner())
					|| "undefined".equals(approveMembersElement.getCcowner())){
				taskService.completeTask(taskid,fin);
				return setp;
			}

		}
//		else if(setp != null && setp.equals("Plant_Mgr")){
//			ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
//			executionService.getVariable(pid, ElementHelper.Approve_Members);
//			if(approveMembersElement.getPlantMgr()==null ||
//					approveMembersElement.getPlantMgr().equals(OrgManager.blankOwner)
//					||"undefined".equals(approveMembersElement.getPlantMgr())){
//				taskService.completeTask(taskid,fin);
//				return setp;
//			}
//		}
		//4 test
//		String did =executionService.findProcessInstanceById(pid).getProcessDefinitionId();
//		ProcessDefinitionImpl pd = (ProcessDefinitionImpl)repositoryService.createProcessDefinitionQuery().processDefinitionId(did).uniqueResult();  
//		
//		ActivityImpl sourceActivity = pd.findActivity(setp);
//		sourceActivity.getOutgoingTransitions();
		//end
		taskService.completeTask(taskid,"next");

		return setp;
	}
	
	public String approve(int target,String pid,String assigner){
		//init();
		Task task = getCurrectActivity(pid);
		String assignee = task.getAssignee();
		//!!!!remove double approbe
		if(PropertiesRead.getInstance().isRelease()){
			if(assigner == null || !assignee.equals(assigner)){
				return null;
			}
		}
		String setp = task.getActivityName();
		
		String taskid = task.getId();
		String fin = "to fin";
		if(setp!=null && setp.equals("capital_check")){
			ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
			if(approveMembersElement.getPm()==null || "".equals(approveMembersElement.getPm())
					|| "undefined".equals(approveMembersElement.getPm())
					|| approveMembersElement.getDm().equals(approveMembersElement.getPm())){
				taskService.completeTask(taskid,fin);
				return setp;
			}
		}else if(setp!=null && setp.equals("dmsupervisor")){
			ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
			executionService.getVariable(pid, ElementHelper.Approve_Members);
			if(approveMembersElement.getCcowner()==null || approveMembersElement.getDm().equals(approveMembersElement.getCcowner()) 
					|| "".equals(approveMembersElement.getCcowner()) || OrgManager.blankOwner.equals(approveMembersElement.getCcowner())
					|| "undefined".equals(approveMembersElement.getCcowner())){
				taskService.completeTask(taskid,fin);
				return setp;
			}

		}
//		else if(setp != null && setp.equals("Plant_Mgr")){
//			ApproveMembersElement approveMembersElement = (ApproveMembersElement)executionService.getVariable(pid, ElementHelper.Approve_Members);
//			executionService.getVariable(pid, ElementHelper.Approve_Members);
//			if(approveMembersElement.getPlantMgr()==null ||
//					approveMembersElement.getPlantMgr().equals(OrgManager.blankOwner)
//					||"undefined".equals(approveMembersElement.getPlantMgr())){
//				taskService.completeTask(taskid,fin);
//				return setp;
//			}
//		}
		//4 test
//		String did =executionService.findProcessInstanceById(pid).getProcessDefinitionId();
//		ProcessDefinitionImpl pd = (ProcessDefinitionImpl)repositoryService.createProcessDefinitionQuery().processDefinitionId(did).uniqueResult();  
//		
//		ActivityImpl sourceActivity = pd.findActivity(setp);
//		sourceActivity.getOutgoingTransitions();
		//end
		taskService.completeTask(taskid,"next");

		return setp;
	}
	
	
	//@Override
	public String refuse(String pid) {
		/**
		 * 察看后需步骤
		 */
		String did =executionService.findProcessInstanceById(pid).getProcessDefinitionId();
		ProcessDefinitionImpl pd = (ProcessDefinitionImpl)repositoryService.createProcessDefinitionQuery().processDefinitionId(did).uniqueResult();  
		
		ActivityImpl sourceActivity = pd.findActivity("DM");  
		sourceActivity.getOutgoingTransitions();
		
//		System.out.println("=========================refuse========================");
		Task task = getCurrectActivity(pid);
		String setp = task.getActivityName();
		String taskid = task.getId();
		String reject ="to fill in";
		
		taskService.completeTask(taskid,reject);
		
		
		return setp;
		// TODO Auto-generated method stub
		//executionService.signalExecutionById("150004","to fill in");
	}
	
	public String needExplain(String pid) {
		init();
		Task task = getCurrectActivity(pid);
		String setp = task.getActivityName();
		//String taskid = task.getId();
		//String reject ="to fill in";
		//taskService.completeTask(taskid,reject);
		return setp;
		// TODO Auto-generated method stub
		//executionService.signalExecutionById("150004","to fill in");
	}

	//@Override
	public void turnback() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 查询带审批的任务
	 * @param approver
	 * @return
	 */
	public List<FormControlElement> doQueryApprovingList(String approver){
		List<Task> tasks = taskService.findPersonalTasks(approver);
		List<FormControlElement> reslut = new ArrayList<FormControlElement>();
		for(Task task:tasks){
			FormControlElement formControlElement = (FormControlElement)executionService.getVariable(task.getExecutionId(), "key2");
			if(formControlElement!=null){
				formControlElement.setState(task.getActivityName());
				formControlElement.setAssignee(task.getAssignee());
				formControlElement.setFormUrl(task.getFormResourceName());
				reslut.add(formControlElement);
			}
		}
		return reslut;
	}
	
	public List<Task> doQueryApprovingList4Mail(String approver){
		List<Task> tasks = taskService.findPersonalTasks(approver);
		return tasks;
	}
	
//	public void query2(){
//		List<HistoryActivityInstance> list = historyServcie.createHistoryActivityInstanceQuery().list();
//		for(HistoryActivityInstance i :list){
//			System.out.println(i+":"+i.getActivityName());
//		}
//	}
//
//	public void query3(){
////		List<HistoryProcessInstance> list =historyServcie.createHistoryProcessInstanceQuery().list();
////		for(HistoryProcessInstance i :list){
////			System.out.println(i+":"+i.getProcessInstanceId());
////		}
//		init();
//		List<HistoryTask> list = historyServcie.createHistoryTaskQuery().assignee("wym").list();
//		for(HistoryTask i :list){
//			System.out.println(i.getId()+":"+i.getState());
//		}
//	}
	
	/**
	 * 查询我的未完成申请
	 */
	public Task getCurrectActivity(String processInstanceId){
		init();
		if(processInstanceId!=null){
			//Task t = taskService.createTaskQuery().executionId(processInstanceId).uniqueResult();
			return taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult();
		}else{
			return taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);
		}

	}	

//	public void getCurrectActivity(){
//		 taskService.createTaskQuery().executionId(executionId).singleResult();
//		ProcessEngine processEngine =  Configuration.getProcessEngine();
//		ExecutionService executionService = processEngine.getExecutionService();
//		//executionService.createProcessInstanceQuery().
//		//executionService.createProcessInstanceQuery().processInstanceId("test.170001")
//		String activityName = executionService.createProcessInstanceQuery().processInstanceId("test.170001").uniqueResult().findActiveActivityNames().toString();
//		System.out.println("��ǰ�������ڽڵ�===" + activityName);
//	}
	
	public String getFormUrl(String processInstanceId){
		init();
		Task task = getCurrectActivity(processInstanceId);
		if(task!=null){
			return task.getFormResourceName();
		}else{
			return "";
		}
	}
	
	/**
	 * 查询流程当前任务坐标
	 * @param processInstanceId
	 * @return
	 */
	public int[] getProcessImageCoordinates(String processInstanceId){
		Task task = getCurrectActivity(processInstanceId);
		String activityName = task.getActivityName();
		String processDefinitionId = executionService.createProcessInstanceQuery().processInstanceId(processInstanceId).uniqueResult().getProcessDefinitionId();
		ActivityCoordinates activityCoordinates = repositoryService.getActivityCoordinates(processDefinitionId, activityName);							
		return new int[]{activityCoordinates.getX(),activityCoordinates.getY(),activityCoordinates.getHeight(),activityCoordinates.getWidth()};
	}
	
	/**
	 * 查询流程图片
	 * @param processInstanceId
	 * @return
	 */
	public InputStream getProcessImage(String processInstanceId){
		//Task task = getCurrectActivity(processInstanceId);
		//String activityName = task.getActivityName();
//		ProcessInstance processInstance = executionService.findProcessInstanceById(processInstanceId);
//		String processDefinitionId = processInstance.getProcessDefinitionId();
//		repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
//		repositoryService.getResourceAsStream(arg0, arg1);
		
		String processDefinitionId = executionService.createProcessInstanceQuery().processInstanceId(processInstanceId).uniqueResult().getProcessDefinitionId();		
		ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
		InputStream inputStream=repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), "test.png");
		return inputStream;
	}
	
	public void deploy(){
		ProcessEngine processEngine =  Configuration.getProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addResourceFromClasspath("test.jpdl.xml").addResourceFromClasspath("test.png").deploy();
	}
	
	/**
	 * 驳回
	 * @param task 当前执行的任务
	 * @param destActivityName 需要流转到的目的节点
	 * @param createTransitionName 动态生成的transition的名称
	 */
	public void reject(Task task, String destActivityName,String createTransitionName ) {
		//这里不会影响事物
		EnvironmentImpl envImpl = ((EnvironmentFactory)processEngine).openEnvironment();
		try {
			
			//动态回退到“窗口收件” 
			ExecutionImpl e = (ExecutionImpl)executionService.findExecutionById(task.getExecutionId());

			ActivityImpl clerkOpinionActivityImpl = e.getActivity();
			
			ProcessDefinitionImpl processDefinitionImpl = clerkOpinionActivityImpl.getProcessDefinition();
			
			//生成一个"经办人意见"——>"窗口收件"的transition
			ActivityImpl applyActivityImpl = processDefinitionImpl.findActivity(destActivityName);
			TransitionImpl toApply = clerkOpinionActivityImpl.createOutgoingTransition();
			toApply.setSource(clerkOpinionActivityImpl);
			toApply.setDestination(applyActivityImpl);
			toApply.setName(createTransitionName);
			this.taskService.completeTask(task.getId(),createTransitionName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			envImpl.close();
		}
	}
	
	public void addOutTransition(String pid,String destName){  
		Task task = getCurrectActivity(pid);
		//taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult();
		String sourceName;
		if(task == null){
//			HistoryActivityInstance his = historyServcie.createHistoryActivityInstanceQuery().processInstanceId(pid).list().get(0);
//			//sourceName = his.getActivityName();
//			//ExecutionImpl e = (ExecutionImpl) executionService.findExecutionById(his.getExecutionId());
//			
//			String did =executionService.findProcessInstanceById(pid).getProcessDefinitionId();
//			ProcessDefinitionImpl pd = (ProcessDefinitionImpl)repositoryService.createProcessDefinitionQuery().processDefinitionId(did).uniqueResult();  
//			ActivityImpl sourceActivity = pd.findActivity(destName);  
			
//			e.setActivity(sourceActivity);
//			//ActivityImpl clerkOpinionActivityImpl = e.getActivity();
		}else{
			sourceName = task.getActivityName();
		
		
		
		
	//		ExecutionImpl e = (ExecutionImpl) executionService.findExecutionById(task.getExecutionId());
	//		ActivityImpl clerkOpinionActivityImpl = e.getActivity();
	//		ProcessDefinitionImpl pd = clerkOpinionActivityImpl.getProcessDefinition();
			String did =executionService.findProcessInstanceById(pid).getProcessDefinitionId();
			ProcessDefinitionImpl pd = (ProcessDefinitionImpl)repositoryService.createProcessDefinitionQuery().processDefinitionId(did).uniqueResult();  
			
			ActivityImpl sourceActivity = pd.findActivity(sourceName);  
			sourceActivity.getOutgoingTransitions();
		    ActivityImpl destActivity=pd.findActivity(destName);  
		    TransitionImpl transition = sourceActivity.createOutgoingTransition();  
		    transition.setName("to " +destName);
		    transition.setDestination(destActivity);  
		    //transition.setName("back");
		    sourceActivity.addOutgoingTransition(transition); 
		    taskService.completeTask(task.getId(),"to " +destName);
		}
	}


	public ProcessEngine getProcessEngine() {
		return processEngine;
	}


	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
		init();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>setProcessEngine>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
	}
}