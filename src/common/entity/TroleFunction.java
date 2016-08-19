package common.entity;

/**
 * TroleFunction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TroleFunction implements java.io.Serializable {

	private Integer id;
	private Trole trole;
	private Tfunction tfunction = new Tfunction();
	private String operate;

	// Constructors

	/** default constructor */
	public TroleFunction() {
	}

	/** full constructor */
	public TroleFunction(Trole trole, Tfunction tfunction, String operate) {
		this.trole = trole;
		this.tfunction = tfunction;
		this.operate = operate;
	}

	// Property accessors

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public Tfunction getTfunction() {
		return this.tfunction;
	}

	public void setTfunction(Tfunction tfunction) {
		this.tfunction = tfunction;
	}

	public String getOperate() {
		return this.operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}