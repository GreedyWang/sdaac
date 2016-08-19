package sdaac.wym.app.entity.conf;
// default package



/**
 * Conf entity. @author MyEclipse Persistence Tools
 */

public class Conf  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Long totleSum;


    // Constructors

    /** default constructor */
    public Conf() {
    }

    
    /** full constructor */
    public Conf(Long totleSum) {
        this.totleSum = totleSum;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTotleSum() {
        return this.totleSum;
    }
    
    public void setTotleSum(Long totleSum) {
        this.totleSum = totleSum;
    }
   








}