package app.entity.vave;
import app.entity.Tproposal;
/**
 * VaveProjectScheduleId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveProjectScheduleId implements java.io.Serializable {

	// Fields

	private String project;
	private Integer version;
	private Tproposal tproposal;

	// Constructors

	/** default constructor */
	public VaveProjectScheduleId() {
	}

	/** full constructor */
	public VaveProjectScheduleId(String project, Integer version,
			Tproposal tproposal) {
		this.project = project;
		this.version = version;
		this.tproposal = tproposal;
	}

	// Property accessors

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Tproposal getTproposal() {
		return this.tproposal;
	}

	public void setTproposal(Tproposal tproposal) {
		this.tproposal = tproposal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VaveProjectScheduleId))
			return false;
		VaveProjectScheduleId castOther = (VaveProjectScheduleId) other;

		return ((this.getProject() == castOther.getProject()) || (this
				.getProject() != null
				&& castOther.getProject() != null && this.getProject().equals(
				castOther.getProject())))
				&& ((this.getVersion() == castOther.getVersion()) || (this
						.getVersion() != null
						&& castOther.getVersion() != null && this.getVersion()
						.equals(castOther.getVersion())))
				&& ((this.getTproposal() == castOther.getTproposal()) || (this
						.getTproposal() != null
						&& castOther.getTproposal() != null && this
						.getTproposal().equals(castOther.getTproposal())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProject() == null ? 0 : this.getProject().hashCode());
		result = 37 * result
				+ (getVersion() == null ? 0 : this.getVersion().hashCode());
		result = 37 * result
				+ (getTproposal() == null ? 0 : this.getTproposal().hashCode());
		return result;
	}

}