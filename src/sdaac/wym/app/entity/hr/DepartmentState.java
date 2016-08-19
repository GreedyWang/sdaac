package sdaac.wym.app.entity.hr;

import app.entity.Tdepartment;
// default package



/**
 * DepartmentState entity. @author MyEclipse Persistence Tools
 */

public class DepartmentState  implements java.io.Serializable {


    // Fields    
	 private int id;
     private Tdepartment department=new Tdepartment();
     private String context;
     private int state=0;//0 未通过,1 定义完成,2 评分完成
     private String stateName;
     public static final int UNPASS=0,DEFINED=1,SCORED=2;

    // Constructors

    public String getStateName() {
    	if(state==0){
    		return "未批准";
    	}else if(state==1){
    		return "完成指标定义";
    	}else if(state==2){		
    		return "完成指标评定";
    	}else{
    		return "error";
    	}
 	
	}


	/** default constructor */
    public DepartmentState() {
    }


	public Tdepartment getDepartment() {
		return department;
	}


	public void setDepartment(Tdepartment department) {
		this.department = department;
	}


	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setResver(int aState){
		if(aState==0){
			state=1;
		}else{
			state=0;
		}
	}
}