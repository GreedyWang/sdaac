package sdaac.wym.app.entity.News;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import app.entity.Tempolyee;


/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String title;
     private Date releaseTime=new Date();
     private Tempolyee releaseEmployeeId=new Tempolyee();
     private String context;
 //    private String simpleContext;
     private Integer type; //新闻类型
     private String[] typeName={"不可回复","匿名回复","记名回复"};
     private String keyWord;
     private Integer state; //0 暂存，1 发布
     private String[] stateName={"暂存","发布"};
     private Set<Comments> commentses = new HashSet(0);
     private String startTime;
     private String endTime;

    // Constructors

    public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/** default constructor */
    public News() {
    }
    /** pk constructor */
    public News(int pk) {
    	id=pk;
    }

	/** minimal constructor */
    public News(String title, Date releaseTime, Tempolyee releaseEmployeeId, String context, Integer type) {
        this.title = title;
        this.releaseTime = releaseTime;
        this.releaseEmployeeId = releaseEmployeeId;
        this.context = context;
        this.type = type;
    }
    
    /** full constructor */
    public News(String title, Date releaseTime, Tempolyee releaseEmployeeId, String context, Integer type, String keyWord, Set commentses) {
        this.title = title;
        this.releaseTime = releaseTime;
        this.releaseEmployeeId = releaseEmployeeId;
        this.context = context;
        this.type = type;
        this.keyWord = keyWord;
        this.commentses = commentses;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseTime() {
        return this.releaseTime;
    }
    
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

 

    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public String getKeyWord() {
        return this.keyWord;
    }
    
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Set getCommentses() {
        return this.commentses;
    }
    
    public void setCommentses(Set commentses) {
        this.commentses = commentses;
    }

	public Tempolyee getReleaseEmployeeId() {
		return releaseEmployeeId;
	}

	public void setReleaseEmployeeId(Tempolyee releaseEmployeeId) {
		this.releaseEmployeeId = releaseEmployeeId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
//	public String getSimpleContext() {
//		if(context!=null&&context.length()<20){
//			return context;
//		}else{
//			return this.context.substring(0, 20);
//		}
//	}
//	public void setSimpleContext(String simpleContext) {
//		this.simpleContext = simpleContext;
//	}
	public String getTypeName() {
		return typeName[type];
	}
	public String getStateName() {
		return stateName[state];
	}
   








}