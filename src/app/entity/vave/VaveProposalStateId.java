package app.entity.vave;

import app.entity.Tproposal;

/**
 * VaveProposalStateId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveProposalStateId implements java.io.Serializable {

	// Fields

	private Tproposal tproposal=new Tproposal();
	private String owner;

	// Constructors

	/** default constructor */
	public VaveProposalStateId() {
	}

	/** full constructor */
	public VaveProposalStateId(Tproposal tproposal, String owner) {
		this.tproposal = tproposal;
		this.owner = owner;
	}

	// Property accessors

	public Tproposal getTproposal() {
		return this.tproposal;
	}

	public void setTproposal(Tproposal tproposal) {
		this.tproposal = tproposal;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VaveProposalStateId))
			return false;
		VaveProposalStateId castOther = (VaveProposalStateId) other;

		return ((this.getTproposal() == castOther.getTproposal()) || (this
				.getTproposal() != null
				&& castOther.getTproposal() != null && this.getTproposal()
				.equals(castOther.getTproposal())))
				&& ((this.getOwner() == castOther.getOwner()) || (this
						.getOwner() != null
						&& castOther.getOwner() != null && this.getOwner()
						.equals(castOther.getOwner())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTproposal() == null ? 0 : this.getTproposal().hashCode());
		result = 37 * result
				+ (getOwner() == null ? 0 : this.getOwner().hashCode());
		return result;
	}

}