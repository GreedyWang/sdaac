package sdaac.wym.app.entity.vave;
// default package



/**
 * PointsDetails entity. @author MyEclipse Persistence Tools
 */

public class PointsDetails  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String uid;
     private double totalSaving;
     private Integer year;


    // Constructors

    /** default constructor */
    public PointsDetails() {
    }

	/** minimal constructor */
    public PointsDetails(String uid) {
        this.uid = uid;
    }
    
    /** full constructor */
    public PointsDetails(String uid, double totalSaving, Integer year) {
        this.uid = uid;
        this.totalSaving = totalSaving;
        this.year = year;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getTotalSaving() {
		return totalSaving;
	}

	public void setTotalSaving(double totalSaving) {
		this.totalSaving = totalSaving;
	}

	public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
   








}