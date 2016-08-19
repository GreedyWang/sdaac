package sdaac.wym.app.entity.vave;
// default package

import java.util.Date;

import app.entity.Tempolyee;


/**
 * BusinessTopic entity. @author MyEclipse Persistence Tools
 */

public class BusinessTopic  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String businessTitle;
     private String description;
     private Date createTime = new Date();
     private Tempolyee publisher = new Tempolyee();
     private Integer level;

     /**
      * 把/r/n替换为回车
      */
     public void changeBR(String pDescription){
    	 description = pDescription.replace("\r\n", "<br>");
     }
    // Constructors

    /** default constructor */
    public BusinessTopic() {
    }
    
    public BusinessTopic(Integer pId) {
    	 id= pId;
    }
    
    /** full constructor */
    public BusinessTopic(String businessTitle, String description, Date createTime, Integer level) {
        this.businessTitle = businessTitle;
        this.description = description;
        this.createTime = createTime;
   //     this.publisher = publisher;
        this.level = level;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessTitle() {
        return this.businessTitle;
    }
    
    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
    	changeBR(description);
    	//this.description = description;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Tempolyee getPublisher() {
		return publisher;
	}


	public void setPublisher(Tempolyee publisher) {
		this.publisher = publisher;
	}


	public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
   








}