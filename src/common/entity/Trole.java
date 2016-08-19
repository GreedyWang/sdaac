package common.entity;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONString;

/**
 * Trole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Trole implements java.io.Serializable ,JSONString{

	// Fields

	private String roleid;
	private String roleName;
	private Set<TroleFunction> troleFunctions = new HashSet<TroleFunction>(0);
	private Set<TuserRole> tuserRoles = new HashSet<TuserRole>(0);
	private boolean isOwn;// 是否拥有
	private String type;
	// Constructors

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOwn() {
		return isOwn;
	}

	public void setOwn(boolean isOwn) {
		this.isOwn = isOwn;
	}
	
	/**
	 * 查看是否有权限
	 * @param userRole
	 */
	public void check(TuserRole userRole ){
		if(roleid.equals(userRole.getTrole().getRoleid())){
			isOwn = true;
		}
	}
	
	public String toJSONString() {
		// TODO Auto-generated method stub
		return "{roleid:'"+roleid+"',roleName:'"+roleName+"',isOwn:'"+isOwn+"'}";
	}
	
	/** default constructor */
	public Trole() {
	}

	/** minimal constructor */
	public Trole(String roleid) {
		this.roleid = roleid;
	}

	/** full constructor */
	public Trole(String roleid, String roleName, Set troleFunctions,
			Set tuserRoles) {
		this.roleid = roleid;
		this.roleName = roleName;
		this.troleFunctions = troleFunctions;
		this.tuserRoles = tuserRoles;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<TroleFunction> getTroleFunctions() {
		return this.troleFunctions;
	}

	public void setTroleFunctions(Set<TroleFunction> troleFunctions) {
		this.troleFunctions = troleFunctions;
	}

	public Set getTuserRoles() {
		return this.tuserRoles;
	}

	public void setTuserRoles(Set tuserRoles) {
		this.tuserRoles = tuserRoles;
	}

}