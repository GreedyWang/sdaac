package common.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Tfunction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tfunction implements java.io.Serializable {

	// Fields

	private Integer functionId;
	private String functionName;
	private Set troleFunctions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tfunction() {
	}

	/** minimal constructor */
	public Tfunction(Integer functionId) {
		this.functionId = functionId;
	}
	
	public Tfunction(String functionName) {
		this.functionName = functionName;
	}
	
	/** full constructor */
	public Tfunction(Integer functionId, String functionName, Set troleFunctions) {
		this.functionId = functionId;
		this.functionName = functionName;
		this.troleFunctions = troleFunctions;
	}

	// Property accessors

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Set getTroleFunctions() {
		return this.troleFunctions;
	}

	public void setTroleFunctions(Set troleFunctions) {
		this.troleFunctions = troleFunctions;
	}

}