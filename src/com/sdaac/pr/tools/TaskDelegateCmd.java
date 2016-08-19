package com.sdaac.pr.tools;

import org.jbpm.api.TaskService;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.api.task.Participation;
import org.jbpm.api.task.Task;

public class TaskDelegateCmd implements Command {
		
	private String taskid;
	private String delegateUserId;
	
	public TaskDelegateCmd(String taskid,String delegateUserId){
		this.taskid = taskid;
		this.delegateUserId = delegateUserId;
	}
	@Override
	public Object execute(Environment env) throws Exception {
		// TODO Auto-generated method stub
		TaskService taskService = env.get(TaskService.class);
		Task task = taskService.getTask(taskid);
		
		taskService.addTaskParticipatingUser(taskid, task.getAssignee(), Participation.REPLACED_ASSIGNEE);
		task.setAssignee(delegateUserId);
		taskService.saveTask(task);
		return null;
	}

}
