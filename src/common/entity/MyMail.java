package common.entity;

import java.util.Date;

import app.entity.Tempolyee;

// default package



/**
 * MyMail entity. @author MyEclipse Persistence Tools
 */

public class MyMail  implements java.io.Serializable {


    // Fields    
	public static int unSend=0;
     private Integer id;
     //private String toSb;
     private String type;
     private String context;
     private Integer isSended;//是否发送 0：未发送，1：发送
     private Date createTime = new Date();
     private Date sendTime = new Date();
     private Tempolyee emp=new Tempolyee();

    // Constructors

    /** default constructor */
    public MyMail() {
    }

    
    /** full constructor */
    public MyMail(String toSb, String type, String context, Integer isSended) {
        this.emp = new Tempolyee(toSb);
        this.type = type;
        this.context = context;
        this.isSended = isSended;
    }
    
    public MyMail(String toSb,String cc, String type, String context, Integer isSended) {
        this.emp = new Tempolyee(toSb);
        emp.setEmailcc(cc);
        this.type = type;
        this.context = context;
        this.isSended = isSended;
    }
    
    public void addContext(String newContext) {
    	this.context += "<tr>"+newContext+"</tr>";
    }
   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

  

    public Tempolyee getEmp() {
		return emp;
	}


	public void setEmp(Tempolyee emp) {
		this.emp = emp;
	}


	public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public Integer getIsSended() {
        return this.isSended;
    }
    
    public void setIsSended(Integer isSended) {
        this.isSended = isSended;
    }


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getSendTime() {
		return sendTime;
	}


	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
   








}