package app.entity;

/**
 * TstatisticsSchedule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TstatisticsSchedule implements java.io.Serializable {

	// Fields

	private String id;
	private float cycle;
	private float progress;
	private float oneTimeCosts;
	private String item;
	private Integer version;
	private String proposalId;

	// Constructors

	/** default constructor */
	public TstatisticsSchedule() {
	}

	/** minimal constructor */
	public TstatisticsSchedule(String id) {
		this.id = id;
	}

	/** full constructor */
	public TstatisticsSchedule(String id, float cycle, float progress,
			float oneTimeCosts, String item, Integer version, String proposalId) {
		this.id = id;
		this.cycle = cycle;
		this.progress = progress;
		this.oneTimeCosts = oneTimeCosts;
		this.item = item;
		this.version = version;
		this.proposalId = proposalId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getCycle() {
		return this.cycle;
	}

	public void setCycle(float cycle) {
		this.cycle = cycle;
	}

	public float getProgress() {
		return this.progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	public float getOneTimeCosts() {
		return this.oneTimeCosts;
	}

	public void setOneTimeCosts(float oneTimeCosts) {
		this.oneTimeCosts = oneTimeCosts;
	}

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

	public String getProposalId() {
		return this.proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}

}