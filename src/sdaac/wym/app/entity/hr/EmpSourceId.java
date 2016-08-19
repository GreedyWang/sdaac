package sdaac.wym.app.entity.hr;
// default package

import java.util.Date;


/**
 * EmpSourceId entity. @author MyEclipse Persistence Tools
 */

public class EmpSourceId  implements java.io.Serializable {


    // Fields    

     private String status;
     private String staffNo;
     private String departmentId;
     private String supervisorName;
     private String staffName;
     private String EMail;
     private String branch;
  


    // Constructors

    public String getBranch() {
		return branch;
	}




	public void setBranch(String branch) {
		this.branch = branch;
	}




	/** default constructor */
    public EmpSourceId() {
    }



   
    // Property accessors

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStaffNo() {
        return this.staffNo;
    }
    
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }  

    public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getEMail() {
        return this.EMail;
    }
    
    public void setEMail(String EMail) {
        this.EMail = EMail;
    }




	public String getDepartmentId() {
		return departmentId;
	}




	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}




	public String getStaffName() {
		return staffName;
	}




	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

}