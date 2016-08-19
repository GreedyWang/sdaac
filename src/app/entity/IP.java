package app.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IP {
	private String uid;
	private String uname;
	private String postid;
	private float ptworktime;
	private float ptcount;
	private float target;
	private float sumt;
	private float effect;
	public float getEffect() {
		return effect;
	}
	public void setEffect(float effect) {
		this.effect =ptworktime* ptcount/target/sumt;
	}

	public float getPtcount() {
		return ptcount;
	}
	public void setPtcount(float ptcount) {
		this.ptcount = ptcount;
	}
	public float getPtworktime() {
		return ptworktime;
	}
	public void setPtworktime(float ptworktime) {
		this.ptworktime = ptworktime;
	}
	public float getSumt() {
		return sumt;
	}
	public void setSumt(float sumt) {
		this.sumt = sumt;
	}
	public float getTarget() {
		return target;
	}
	public void setTarget(float target) {
		this.target = target;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
}
