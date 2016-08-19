package sdaac.wym.app.entity.cw;
// default package

import java.util.Date;


/**
 * CwLogs entity. @author MyEclipse Persistence Tools
 */

public class CwLogs  implements java.io.Serializable {


    // Fields    
	 private int id;
     private String taxno;
     private Date createDate;
     private String operator;
     private float money;
     private String mergeTaxNo;
     private String jinsuiNo;
     private float jinsuiMoney;
     private String begin;
     private String end;
     private String customerNo;//顾客号
     private String fenpeiNo;//分配好
     private String jinsuiFaxMoney;//金穗发票税额
     private Integer type;//0 is true,1 is false;
     private String feedbackNo;//回馈号
    // Constructors

    public String getJinsuiFaxMoney() {
		return jinsuiFaxMoney;
	}



	public void setJinsuiFaxMoney(String jinsuiFaxMoney) {
		this.jinsuiFaxMoney = jinsuiFaxMoney;
	}



	public String getCustomerNo() {
		return customerNo;
	}



	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}



	public String getFenpeiNo() {
		return fenpeiNo;
	}



	public void setFenpeiNo(String fenpeiNo) {
		this.fenpeiNo = fenpeiNo;
	}



	public String getBegin() {
		return begin;
	}



	public void setBegin(String begin) {
		this.begin = begin;
	}



	public String getEnd() {
		return end;
	}



	public void setEnd(String end) {
		this.end = end;
	}



	/** default constructor */
    public CwLogs() {
    }


    
    // Property accessors

    public String getTaxno() {
        return this.taxno;
    }
    
    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOperator() {
        return this.operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }



    public float getMoney() {
		return money;
	}



	public void setMoney(float money) {
		this.money = money;
	}



	public String getMergeTaxNo() {
        return this.mergeTaxNo;
    }
    
    public void setMergeTaxNo(String mergeTaxNo) {
        this.mergeTaxNo = mergeTaxNo;
    }

    public String getJinsuiNo() {
        return this.jinsuiNo;
    }
    
    public void setJinsuiNo(String jinsuiNo) {
        this.jinsuiNo = jinsuiNo;
    }



	public float getJinsuiMoney() {
		return jinsuiMoney;
	}



	public void setJinsuiMoney(float jinsuiMoney) {
		this.jinsuiMoney = jinsuiMoney;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public String getFeedbackNo() {
		return feedbackNo;
	}



	public void setFeedbackNo(String feedbackNo) {
		this.feedbackNo = feedbackNo;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		if(obj instanceof CwLogs){
//			CwLogs obj2 =(CwLogs) obj;
//			System.out.println("aaa");
//			if(this.mergeTaxNo.equals(obj2.getMergeTaxNo())){
//				return true;
//			}else{
//				return false;
//			}
//		}else{
//			return false;
//		}
//		
//	}
   








}