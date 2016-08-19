package sdaac.wym.app.entity.hr;
// default package



/**
 * BouncePost entity. @author MyEclipse Persistence Tools
 */

public class BouncePost  implements java.io.Serializable {


    // Fields    
	private Integer id;
     private String postId;
     private String postName;
     private Integer deviceNum;
     private String remark;


    // Constructors

    /** default constructor */
    public BouncePost() {
    }

	/** minimal constructor */
    public BouncePost(String postId, String postName) {
        this.postId = postId;
        this.postName = postName;
    }
    
    /** full constructor */
    public BouncePost(String postId, String postName, Integer deviceNum, String remark) {
        this.postId = postId;
        this.postName = postName;
        this.deviceNum = deviceNum;
        this.remark = remark;
    }

   
    // Property accessors

    public String getPostId() {
        return this.postId;
    }
    
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return this.postName;
    }
    
    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getDeviceNum() {
        return this.deviceNum;
    }
    
    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
   








}