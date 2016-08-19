package com.sdaac.pr.tools;

import com.sdaac.pr.approve.PRProcessEngine;

/**
 * tools for maintain the new PR
 * @author sa1kv5
 *
 */
public class Tools implements iTools{
	
	private PRProcessEngine processEng;

	/**
	 * 修改当前审批人
	 */
	@Override
	public void doChangeApprovor(String id, String newApprovor,String stepName) {
		// TODO Auto-generated method stub
		processEng.setVariable(id,newApprovor,stepName);
	}
	
	public void doChangeRoute(String id,String stepName){
		processEng.addOutTransition(id, stepName);
	}
	
	
	public PRProcessEngine getProcessEng() {
		return processEng;
	}
	public void setProcessEng(PRProcessEngine processEng) {
		this.processEng = processEng;
	}
}
