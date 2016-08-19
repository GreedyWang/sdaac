package sdaac.wym.app.entity.lab;
// default package

import java.util.Date;

import app.entity.Tempolyee;


/**
 * LabForm entity. @author MyEclipse Persistence Tools
 */

public class LabForm  implements java.io.Serializable {


    // Fields    
	 public static String innerType = "inner";
	 public static String outype = "outype";
     private Integer ssid;
     private Tempolyee applicant = new Tempolyee();;
     private Integer state;
     private Date applicantDate;
     private String stringApplicantDate;
     private String type;
     private String goodsName;
     private String projectName;
     private String innerSN;

    // Constructors

    public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getInnerSN() {
		return innerSN;
	}


	public void setInnerSN(String innerSN) {
		this.innerSN = innerSN;
	}


	/** default constructor */
    public LabForm() {
    }

    
    /** full constructor */
    public LabForm(Integer state, Date applicantDate, String type) {
        this.state = state;
        this.applicantDate = applicantDate;
        this.type = type;
    }

   
    // Property accessors



    public Tempolyee getApplicant() {
		return applicant;
	}


	public void setApplicant(Tempolyee applicant) {
		this.applicant = applicant;
	}


	public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }

    public Date getApplicantDate() {
        return this.applicantDate;
    }
    
    public void setApplicantDate(Date applicantDate) {
        this.applicantDate = applicantDate;
    }

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getSsid() {
		return ssid;
	}


	public void setSsid(Integer ssid) {
		this.ssid = ssid;
	}

    
}