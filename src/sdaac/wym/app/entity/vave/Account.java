package sdaac.wym.app.entity.vave;
// default package

import java.util.Date;

import app.entity.Tempolyee;


/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account  implements java.io.Serializable {


    // Fields    

     private Integer id;
     //private Goods goods;
     private Tempolyee emp= new Tempolyee();
     private Date datetime ;
    // private Integer quantity;
     private String beginTime;
     private String endTime;
     private int state;
     private String stateNames[]={"购物车","订单","完成"};

    // Constructors
	 /**
	  * 状态中文名
	  */
    public String getStateNames() {
		return stateNames[state];
	}

	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	/** default constructor */
    public Account() {
    }

    
    /** full constructor */
    public Account( Date datetime) {
       // this.goods = goods;
       // this.empUid = empUid;
        this.datetime = datetime;
       // this.quantity = quantity;
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


	public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }


}