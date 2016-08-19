package sdaac.wym.app.entity.hr;

import app.entity.Tempolyee;

// default package



/**
 * EmpSource entity. @author MyEclipse Persistence Tools
 */

public class EmpSource  implements java.io.Serializable {


    // Fields    

     private EmpSourceId id = new EmpSourceId();

//     
//     public Tempolyee getEmp(){
//    	 Tempolyee item = new Tempolyee();
//    	 item.setUid(uid);
//    	 item.setName(name);
//    	 item.setLeaderID(leaderID);
//    	 item.sett
//    	 return 
//     }
     
     
    // Constructors

    /** default constructor */
    public EmpSource() {
    }

    
    /** full constructor */
    public EmpSource(EmpSourceId id) {
        this.id = id;
    }

   
    // Property accessors

    public EmpSourceId getId() {
        return this.id;
    }
    
    public void setId(EmpSourceId id) {
        this.id = id;
    }

}