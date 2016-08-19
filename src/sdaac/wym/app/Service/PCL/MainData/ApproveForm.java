package sdaac.wym.app.Service.PCL.MainData;

import java.util.Date;

import app.entity.Tempolyee;

/**
 * <tr>
 * 	<td>
 *  	PCL 物料主数据申请 审批表单
 *  </td>
 * </tr>
 * @version 1.0
 * @author SA1KV5
 * @since 2011-11-10
 */
/**
 * ---WHO----WHEN--------WHAT-----------------------------
 * ---WYM----2013-12-13--add 2 parameters for class "BuyerApprove.doAgree"
 * -----------------------------------------------------------
 */
public class ApproveForm {
	protected BaseForm item = new BaseForm();
	protected Tempolyee tempolyee=new Tempolyee();
	protected Date datetime=new Date();
	protected String context;
	protected Integer isApproved;//APPROVE = 1,REFUSE=2,NEEDEXPLAIN=3;
	protected String approvalName;//审批意见
	protected String type;
	protected Integer id;
	protected String version;	
	protected String buyerid;
	
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public String getApprovalName() {
		if(isApproved==null) return "个人解释";
		if(isApproved == 1){
			return "同意";
		}else if(isApproved == 2){
			return "拒绝";
		}else if(isApproved == 3){
			return "需解释";
		}
		return approvalName;
	}
	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Tempolyee getTempolyee() {
		return tempolyee;
	}
	public void setTempolyee(Tempolyee tempolyee) {
		this.tempolyee = tempolyee;
	}
	public BaseForm getItem() {
		return item;
	}
	public void setItem(BaseForm item) {
		this.item = item;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
