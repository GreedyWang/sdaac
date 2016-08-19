package app.entity.vave;
import app.entity.Tproposal;

/**
 * VaveStatisticsScheduleId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveStatisticsScheduleId implements java.io.Serializable {

	// Fields

	private String item;
	private Integer version;
	private Tproposal tproposal;

	// Constructors

	/** default constructor */
	public VaveStatisticsScheduleId() {
	}

	/** full constructor */
	public VaveStatisticsScheduleId(String item, Integer version,
			Tproposal tproposal) {
		this.item = item;
		this.version = version;
		this.tproposal = tproposal;
	}

	// Property accessors

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
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
		if (!(other instanceof VaveStatisticsScheduleId))
			return false;
		VaveStatisticsScheduleId castOther = (VaveStatisticsScheduleId) other;

		return ((this.getItem() == castOther.getItem()) || (this.getItem() != null
				&& castOther.getItem() != null && this.getItem().equals(
				castOther.getItem())))
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
				+ (getItem() == null ? 0 : this.getItem().hashCode());
		result = 37 * result
				+ (getVersion() == null ? 0 : this.getVersion().hashCode());
		result = 37 * result
				+ (getTproposal() == null ? 0 : this.getTproposal().hashCode());
		return result;
	}

}