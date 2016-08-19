package sdaac.wym.app.entity.lab;

import java.util.Date;

/**
 * 实验室审批意见
 * @author SA1KV5
 *
 */
public class ApproveForm {
	private int opinion; //意见
	private String suggestion;//建议
	private Date approveDate;//审批时间
	private String contact;//联系方式
	
	public static int APPROVED = 1;
	public static int NOT_APPROVED = 2;
	public static int NEED_EXPLAIN = 3;
	
	private Integer id;
	private String approveid;
	private Date approvedata;
	private String comments;
	private String formid;
	
	private LabForm form = new LabForm();

	public LabForm getForm() {
		return form;
	}
	public void setForm(LabForm form) {
		this.form = form;
	}
	public int getOpinion() {
		return opinion;
	}
	public void setOpinion(int opinion) {
		this.opinion = opinion;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApproveid() {
		return approveid;
	}
	public void setApproveid(String approveid) {
		this.approveid = approveid;
	}
	public Date getApprovedata() {
		return approvedata;
	}
	public void setApprovedata(Date approvedata) {
		this.approvedata = approvedata;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	
	
}
