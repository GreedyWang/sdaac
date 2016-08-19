package app.entity.vave;

import java.util.HashSet;
import java.util.Set;

/**
 * VaveSupply entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveSupply implements java.io.Serializable {

	// Fields

	private String simpleName;
	private String supplyName;
	private String phone;
	private String contact;
	private Set tproposals = new HashSet(0);

	// Constructors

	/** default constructor */
	public VaveSupply() {
	}
	

	/** minimal constructor */
	public VaveSupply(String simpleName, String supplyName) {
		this.simpleName = simpleName;
		this.supplyName = supplyName;
	}

	/** full constructor */
	public VaveSupply(String simpleName, String supplyName, String phone,
			String contact, Set tproposals) {
		this.simpleName = simpleName;
		this.supplyName = supplyName;
		this.phone = phone;
		this.contact = contact;
		this.tproposals = tproposals;
	}

	// Property accessors

	public String getSimpleName() {	
			return this.simpleName;	
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Set getTproposals() {
		return this.tproposals;
	}

	public void setTproposals(Set tproposals) {
		this.tproposals = tproposals;
	}

}