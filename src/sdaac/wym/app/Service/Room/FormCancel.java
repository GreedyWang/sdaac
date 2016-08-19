package sdaac.wym.app.Service.Room;

import java.util.Date;

public class FormCancel {
	private Integer id;
	private Integer cid;
	private String uid;
	private Date b;
	private Date e;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Date getB() {
		return b;
	}
	public void setB(Date b) {
		this.b = b;
	}
	public Date getE() {
		return e;
	}
	public void setE(Date e) {
		this.e = e;
	}
	public void loadDate(Form item) {
		// TODO Auto-generated method stub
		this.cid = item.getId();
		this.uid = item.getEmp().getUid();
		this.b = item.getBegintime();
		this.e = item.getEndtime();
	}
	
	public boolean check(Form item) {
		return this.cid - item.getId() == 0 && b.compareTo(item.getBegintime())== 0 && e.compareTo(item.getEndtime())==0;
	}
}
