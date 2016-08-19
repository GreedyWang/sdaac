package app.entity.vave;

/**
 * VaveInfoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveInfoId implements java.io.Serializable {

	// Fields

	private String costomCode;
	private String vehicles;
	private Long volume;

	// Constructors

	/** default constructor */
	public VaveInfoId() {
	}

	/** full constructor */
	public VaveInfoId(String costomCode, String vehicles, Long volume) {
		this.costomCode = costomCode;
		this.vehicles = vehicles;
		this.volume = volume;
	}

	// Property accessors

	public String getCostomCode() {
		return this.costomCode;
	}

	public void setCostomCode(String costomCode) {
		this.costomCode = costomCode;
	}

	public String getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(String vehicles) {
		this.vehicles = vehicles;
	}

	public Long getVolume() {
		return this.volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VaveInfoId))
			return false;
		VaveInfoId castOther = (VaveInfoId) other;

		return ((this.getCostomCode() == castOther.getCostomCode()) || (this
				.getCostomCode() != null
				&& castOther.getCostomCode() != null && this.getCostomCode()
				.equals(castOther.getCostomCode())))
				&& ((this.getVehicles() == castOther.getVehicles()) || (this
						.getVehicles() != null
						&& castOther.getVehicles() != null && this
						.getVehicles().equals(castOther.getVehicles())))
				&& ((this.getVolume() == castOther.getVolume()) || (this
						.getVolume() != null
						&& castOther.getVolume() != null && this.getVolume()
						.equals(castOther.getVolume())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCostomCode() == null ? 0 : this.getCostomCode()
						.hashCode());
		result = 37 * result
				+ (getVehicles() == null ? 0 : this.getVehicles().hashCode());
		result = 37 * result
				+ (getVolume() == null ? 0 : this.getVolume().hashCode());
		return result;
	}

}