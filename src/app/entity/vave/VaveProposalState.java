package app.entity.vave;

/**
 * VaveProposalState entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveProposalState implements java.io.Serializable {

	// Fields

	private VaveProposalStateId id=new VaveProposalStateId();
	private Integer isOpen;
	private Integer State;

	// Constructors

	/** default constructor */
	public VaveProposalState() {
	}

	/** full constructor */
	public VaveProposalState(VaveProposalStateId id, Integer isOpen) {
		this.id = id;
		this.isOpen = isOpen;
		//this.State=state;
		
	}
	/**pk constructor*/
	public VaveProposalState(VaveProposalStateId id) {
		this.id = id;
		
		//this.State=state;
		
	}
	// Property accessors

	public VaveProposalStateId getId() {
		return this.id;
	}

	public void setId(VaveProposalStateId id) {
		this.id = id;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}



}