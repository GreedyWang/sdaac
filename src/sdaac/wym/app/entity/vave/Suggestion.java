package sdaac.wym.app.entity.vave;
// default package

import java.util.Date;
import app.entity.Tproposal;


/**
 * suggestion entity. @author MyEclipse Persistence Tools
 */

public class Suggestion  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Tproposal tproposal;
     private String options;
     private Date submitTime;
     private String approveName;
     private String aproveDepartName;
     private String implementDepartName;
     private String type;
     private String suggestionType;


    // Constructors

    /** default constructor */
    public Suggestion() {
    }

	/** minimal constructor */
    public Suggestion(Tproposal tproposal, Date submitTime, String approveName, String aproveDepartName, String implementDepartName, String type) {
        this.tproposal = tproposal;
        this.submitTime = submitTime;
        this.approveName = approveName;
        this.aproveDepartName = aproveDepartName;
        this.implementDepartName = implementDepartName;
        this.type = type;
    }
    
    /** full constructor */
    public Suggestion(Tproposal tproposal, String options, Date submitTime, String approveName, String aproveDepartName, String implementDepartName, String type, String suggestionType) {
        this.tproposal = tproposal;
        this.options = options;
        this.submitTime = submitTime;
        this.approveName = approveName;
        this.aproveDepartName = aproveDepartName;
        this.implementDepartName = implementDepartName;
        this.type = type;
        this.suggestionType = suggestionType;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Tproposal getTproposal() {
        return this.tproposal;
    }
    
    public void setTproposal(Tproposal tproposal) {
        this.tproposal = tproposal;
    }

    public String getOptions() {
        return this.options;
    }
    
    public void setOptions(String options) {
        this.options = options;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }
    
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getApproveName() {
        return this.approveName;
    }
    
    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getAproveDepartName() {
        return this.aproveDepartName;
    }
    
    public void setAproveDepartName(String aproveDepartName) {
        this.aproveDepartName = aproveDepartName;
    }

    public String getImplementDepartName() {
        return this.implementDepartName;
    }
    
    public void setImplementDepartName(String implementDepartName) {
        this.implementDepartName = implementDepartName;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getSuggestionType() {
        return this.suggestionType;
    }
    
    public void setSuggestionType(String suggestionType) {
        this.suggestionType = suggestionType;
    }
   








}