package app.entity.epor;

import java.util.HashSet;
import java.util.Set;

/**
 * PrRejectType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrRejectType implements java.io.Serializable {

	private Integer id;
	private String des;

	// Constructors

	/** default constructor */
	public PrRejectType() {
	}
	
	public PrRejectType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	

}