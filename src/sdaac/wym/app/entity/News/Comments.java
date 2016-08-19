package sdaac.wym.app.entity.News;
// default package

import java.util.Date;

import app.entity.Tempolyee;


/**
 * Comments entity. @author MyEclipse Persistence Tools
 */

public class Comments  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private News news;
     private Date commentsTime;
     private Tempolyee uid=new Tempolyee();
     private String context;


    // Constructors

    /** default constructor */
    public Comments() {
    }

    
    /** full constructor */
    public Comments(News news, Date commentsTime, Tempolyee uid, String context) {
        this.news = news;
        this.commentsTime = commentsTime;
        this.uid = uid;
        this.context = context;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public News getNews() {
        return this.news;
    }
    
    public void setNews(News news) {
        this.news = news;
    }

    public Date getCommentsTime() {
        return this.commentsTime;
    }
    
    public void setCommentsTime(Date commentsTime) {
        this.commentsTime = commentsTime;
    }

    public Tempolyee getUid() {
		return uid;
	}


	public void setUid(Tempolyee uid) {
		this.uid = uid;
	}


	public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }
   








}