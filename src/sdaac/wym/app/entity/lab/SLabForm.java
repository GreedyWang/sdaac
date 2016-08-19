package sdaac.wym.app.entity.lab;
// default package

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.entity.Tempolyee;


/**
 * LabForm entity. @author MyEclipse Persistence Tools
 */

public class SLabForm  implements java.io.Serializable {


    // Fields    
	 public static String innerType = "inner";
	 public static String outype = "outype";
     private Integer id;
     private Tempolyee applicant = new Tempolyee();;
     private Integer state;
     private Date applicantDate;
     private String type;
     private String goodsName;
     private String projectName;
     private String innerSN;
     private String stringApplicantDate;

    // Constructors

    public String getStringApplicantDate() {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	return df.format(applicantDate);
	}


	public void setStringApplicantDate(String stringApplicantDate) {
		this.stringApplicantDate = stringApplicantDate;
	}


	/** default constructor */
    public SLabForm() {
    }

    
    /** full constructor */
    public SLabForm(Integer state, Date applicantDate, String type) {
        this.state = state;
        this.applicantDate = applicantDate;
        this.type = type;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

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

    
}