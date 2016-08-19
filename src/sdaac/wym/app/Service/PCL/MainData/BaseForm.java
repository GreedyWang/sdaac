package sdaac.wym.app.Service.PCL.MainData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.entity.Tempolyee;
import app.entity.epor.PrPrForm;
import app.entity.epor.StateManager;

/**
 * <tr>
 * 	<td>
 *  	PCL 物料主数据申请 表单公用类
 *  </td>
 * </tr>
 * @version 1.0
 * @author SA1KV5
 * @since 2011-11-10
 */
public class BaseForm {
	protected Integer state;
	protected Integer needExplain; // 1 is needExplain ,
	protected String type;
	protected Integer uuid;
	protected String key;
	protected Date applicateDate;
	public static int NEEDEXPLAIN = 1,HASEXPLAIN=2,NORMAL=0,FINISH=3,RETURN=4;
	protected Tempolyee tempolyeeByApplicantId=new Tempolyee();
	protected String remark;
	protected String remark2;
	protected List approveForm = new ArrayList(0);
	protected String stateRange;//用于权限中，一个角色可以查看多个状态的申请单。
	protected String stateName;
	protected String assignee;
	protected String typeName;//用于显示ePR的类型
	protected String ssidTip; // 用于显示单号的类型
    // Constructors

    public String getStateName() {
    	 
    	if(type=="ePR") {
//    		if(state == StateManager.DeputyGeneral) {//if the state is DeputyGeneral
//    			if(StateManager.CIO.contains(tempolyeeByApplicantId.getTdepartment().getId()+"")){
//    				return "技术总监_李钢";			
//    			}else if(StateManager.DGM1.contains(tempolyeeByApplicantId.getTdepartment().getId()+"")){
//    				return "副总_马琦雷";
//    			}else if(StateManager.CIO2.contains(tempolyeeByApplicantId.getTdepartment().getId()+"")){
//    				return "技术总监_Arnaud Gandon";
//    			}
//    		}    		
    		return stateName;
    	}
		return MDStateManager.states.get(state).curStateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	//用于JSON显示时间
	private String jsonData;
	
	public String getJsonData() {
		if(applicateDate==null) return "";
		return applicateDate.toString();
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String toJSONString() {
		// TODO Auto-generated method stub
		return "{needExplain:'"+needExplain+"',type:'"+type+"',uname:'"+tempolyeeByApplicantId.getName()
				+"',state:'"+state+"}";
	}
	
	public Tempolyee getTempolyeeByApplicantId() {
		return tempolyeeByApplicantId;
	}

	public void setTempolyeeByApplicantId(Tempolyee tempolyeeByApplicantId) {
		this.tempolyeeByApplicantId = tempolyeeByApplicantId;
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getNeedExplain() {
		return needExplain;
	}

	public void setNeedExplain(Integer needExplain) {
		this.needExplain = needExplain;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getApplicateDate() {
		return applicateDate;
	}

	public void setApplicateDate(Date applicateDate) {
		this.applicateDate = applicateDate;
	}
	
	public static List<BaseForm> AddOtherForms(List<PrPrForm> rs){
		List<BaseForm> result = new ArrayList<BaseForm>();
		for(PrPrForm item2:rs){
			BaseForm bf = new BaseForm();
			bf.setTempolyeeByApplicantId(item2.getTempolyeeByApplicantId());
			bf.setKey(item2.getId());
			bf.setNeedExplain(item2.getInfo());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				bf.setApplicateDate(df.parse(df.format(item2.getApplicantDate())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			bf.setState(item2.getState());
			bf.setAssignee(item2.getAssignee());
			bf.setStateName(item2.getNewStateName());
			bf.setType("ePR");
			bf.setRemark(item2.getRemark());
			bf.setTypeName(item2.getIsPlanName()+";"+item2.getIsCapitalName());
			bf.setRemark2("projectID : " +item2.getPrProject().getNumber());
			bf.setSsidTip(item2.getSapNO()+"");
			result.add(bf);
		}
		return result;
	}
	

	public String getSsidTip() {
		return ssidTip;
	}

	public void setSsidTip(String ssidTip) {
		this.ssidTip = ssidTip;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List getApproveForm() {
		return approveForm;
	}

	public void setApproveForm(List approveForm) {
		this.approveForm = approveForm;
	}

	public String getStateRange() {
		return stateRange;
	}

	public void setStateRange(String stateRange) {
		this.stateRange = stateRange;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
}
