package app.entity.vave;

import java.util.Date;

/**
 * VaveProposalMore entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveProposalMore implements java.io.Serializable {

	// Fields

	private String proposalId;
	private Float expectSaving;
	private Date expectFinishDate;
	private String insPrograme;
	private Integer version;

	// Constructors

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/** default constructor */
	public VaveProposalMore() {
	}

	/** minimal constructor */
	public VaveProposalMore(String proposalId) {
		this.proposalId = proposalId;
	}

	/** full constructor */
	public VaveProposalMore(String proposalId, Float expectSaving,
			Date expectFinishDate, String insPrograme) {
		this.proposalId = proposalId;
		this.expectSaving = expectSaving;
		this.expectFinishDate = expectFinishDate;
		this.insPrograme = insPrograme;
	}

	// Property accessors

	public String getProposalId() {
		return this.proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}

	public Float getExpectSaving() {
		return this.expectSaving;
	}

	public void setExpectSaving(Float expectSaving) {
		this.expectSaving = expectSaving;
	}

	public Date getExpectFinishDate() {
		return this.expectFinishDate;
	}

	public void setExpectFinishDate(Date expectFinishDate) {
		this.expectFinishDate = expectFinishDate;
	}

	public String getInsPrograme() {
		return this.insPrograme;
	}

	public void setInsPrograme(String insPrograme) {
		this.insPrograme = insPrograme;
	}

}