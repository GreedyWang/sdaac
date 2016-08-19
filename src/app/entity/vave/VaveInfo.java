package app.entity.vave;

/**
 * VaveInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveInfo implements java.io.Serializable {

	// Fields

	private VaveInfoId id=new VaveInfoId();

	// Constructors

	/** default constructor */
	public VaveInfo() {
	}

	/** full constructor */
	public VaveInfo(VaveInfoId id) {
		this.id = id;
	}

	// Property accessors

	public VaveInfoId getId() {
		return this.id;
	}

	public void setId(VaveInfoId id) {
		this.id = id;
	}

}