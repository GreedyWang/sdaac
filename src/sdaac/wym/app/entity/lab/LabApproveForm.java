package sdaac.wym.app.entity.lab;

import java.util.Date;

/**
 * LabApproveForm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabApproveForm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String approveid;
	private Date approvedata;
	private String comments;
	private Integer opinion;
	private String formid;

	// Constructors

	/** default constructor */
	public LabApproveForm() {
	}

	/** full constructor */
	public LabApproveForm(String approveid, Date approvedata, String comments,
			Integer opinion, String formid) {
		this.approveid = approveid;
		this.approvedata = approvedata;
		this.comments = comments;
		this.opinion = opinion;
		this.formid = formid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApproveid() {
		return this.approveid;
	}

	public void setApproveid(String approveid) {
		this.approveid = approveid;
	}

	public Date getApprovedata() {
		return this.approvedata;
	}

	public void setApprovedata(Date approvedata) {
		this.approvedata = approvedata;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getOpinion() {
		return this.opinion;
	}

	public void setOpinion(Integer opinion) {
		this.opinion = opinion;
	}

	public String getFormid() {
		return this.formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

}