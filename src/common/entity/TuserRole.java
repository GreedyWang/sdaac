package common.entity;

import app.entity.Tempolyee;

/**
 * TuserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TuserRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trole trole = new Trole();
	private Tempolyee tempolyee = new Tempolyee();
	
	private String capitalType;
	private String dprts;
	
	// Constructors

	public String getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}

	public String getDprts() {
		return dprts;
	}

	public void setDprts(String dprts) {
		this.dprts = dprts;
	}

	/** default constructor */
	public TuserRole() {
	}

	/** full constructor */
	public TuserRole(Integer id, Trole trole, Tempolyee tempolyee) {
		this.id = id;
		this.trole = trole;
		this.tempolyee = tempolyee;
	}
	
	public TuserRole(Trole trole, Tempolyee tempolyee) {
		this.trole = trole;
		this.tempolyee = tempolyee;
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public Tempolyee getTempolyee() {
		return this.tempolyee;
	}

	public void setTempolyee(Tempolyee tempolyee) {
		this.tempolyee = tempolyee;
	}

}