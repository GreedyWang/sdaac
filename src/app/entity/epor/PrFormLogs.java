package app.entity.epor;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.entity.Tempolyee;

/**
 * PrPrForm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrFormLogs extends BasePrForm implements java.io.Serializable {

	// Fields
	private String operator;
	private Date operatorDate;
	
	public PrFormLogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrFormLogs(PrPrForm item,String pOperator,Date pOperatorDate) {
		super(item.getId(), item.getTempolyeeByApplicantId().getUid(), item.getPrCostCenter().getId()+"", item.getApplicantDate(),
				item.getPrNo(), item.getType(),
//				item.getTotal(), 
				item.getRecommendedSupplier(), item.getReceivingPlacePerpoleTel(), item.getState(), item.getFileName(),
				item.getPrPrFormStates(), item.getPrApprovedForms(), item.getPrBuyContexts(),item.getPrProject().getProjectId(),
				item.getIsCapital(),item.getIsPlan(),item.getIsInTheSap(),item.getBigCountNo(),item.getArno());
		//	Integer isCapital,Integer isPlan,Integer isInTheSap,String bigCountNo) {
		operator = pOperator;
		operatorDate = pOperatorDate;
		// TODO Auto-generated constructor stub
	}
	
	public PrFormLogs(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	
	

}