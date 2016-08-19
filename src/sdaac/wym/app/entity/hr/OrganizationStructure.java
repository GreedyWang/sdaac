package sdaac.wym.app.entity.hr;

import app.entity.Tdepartment;
// default package



/**
 * OrganizationStructure entity. @author MyEclipse Persistence Tools
 */

public class OrganizationStructure  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String context;
     private String uname;
     private String type;
     private Integer postionAx;
     private Integer postionAy;
     private Integer fatherid;
     private Tdepartment department=new Tdepartment();
     private String uid;


    // Constructors



	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	/** default constructor */
    public OrganizationStructure() {
    }

	/** minimal constructor */
    public OrganizationStructure(String context, String uname, String type, Integer postionAx, Integer postionAy) {
        this.context = context;
        this.uname = uname;
        this.type = type;
        this.postionAx = postionAx;
        this.postionAy = postionAy;
    }
    
    /** full constructor */
    public OrganizationStructure(String context, String uname, String type, Integer postionAx, Integer postionAy, Integer fatherid) {
        this.context = context;
        this.uname = uname;
        this.type = type;
        this.postionAx = postionAx;
        this.postionAy = postionAy;
        this.fatherid = fatherid;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public String getUname() {
        return this.uname;
    }
    
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Integer getPostionAx() {
        return this.postionAx;
    }
    
    public void setPostionAx(Integer postionAx) {
        this.postionAx = postionAx;
    }

    public Integer getPostionAy() {
        return this.postionAy;
    }
    
    public void setPostionAy(Integer postionAy) {
        this.postionAy = postionAy;
    }

    public Integer getFatherid() {
        return this.fatherid;
    }
    
    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

	public Tdepartment getDepartment() {
		return department;
	}

	public void setDepartment(Tdepartment department) {
		this.department = department;
	}
   








}