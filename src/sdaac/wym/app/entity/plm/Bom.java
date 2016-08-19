package sdaac.wym.app.entity.plm;

import java.util.Date;

public class Bom {

	protected String id;
	protected String designno;
	protected String wkaid;
	protected String state;
	protected Byte del;
	protected String alteruser;
	protected String duser;
	protected String owner;
	protected String muser;
	protected String chkusr;
	protected String creator;
	protected Date deltime;
	protected Date mtime;
	protected Date ctime;
	protected String msym;
	protected Double bnum = 1.0; // 数量
	protected Integer bompst;// 节点优先级
	public Part cpart = new Part();
	protected Part fpart = new Part();
	// protected String level;
	protected String ctn;
	protected String ptn;
	protected String smemo;
	protected String asmemo;
	protected Date aduser01;
	protected Date aduser02;
	protected Date aduser03;
	protected Date aduser04;
	protected Date aduser05;
	protected Long afuser01;
	protected Long afuser02;
	protected Long afuser03;
	protected Long afuser04;
	protected Long afuser05;
	protected String asuser01;
	protected String asuser02;
	protected String asuser03;
	protected String asuser04;
	protected String asuser06;
	protected String asuser07;
	protected String asuser08;
	protected String asuser09;
	protected String asuser10;

	// Constructors

	/** default constructor */
	public Bom() {
	}

	/** minimal constructor */
	public Bom(String id, Byte del) {
		this.id = id;
		this.del = del;
	}

	/** full constructor */
	public Bom(String id, String designno, String wkaid, String state,
			Byte del, String alteruser, String duser, String owner,
			String muser, String chkusr, String creator, Date deltime,
			Date mtime, Date ctime, String msym, Double bnum, Integer bompst,
			String cid, String ctn, String pid, String ptn, String smemo,
			String asmemo, Date aduser01, Date aduser02, Date aduser03,
			Date aduser04, Date aduser05, Long afuser01, Long afuser02,
			Long afuser03, Long afuser04, Long afuser05, String asuser01,
			String asuser02, String asuser03, String asuser04, String asuser06,
			String asuser07, String asuser08, String asuser09, String asuser10) {
		this.id = id;
		this.designno = designno;
		this.wkaid = wkaid;
		this.state = state;
		this.del = del;
		this.alteruser = alteruser;
		this.duser = duser;
		this.owner = owner;
		this.muser = muser;
		this.chkusr = chkusr;
		this.creator = creator;
		this.deltime = deltime;
		this.mtime = mtime;
		this.ctime = ctime;
		this.msym = msym;
		this.bnum = bnum;
		this.bompst = bompst;

		this.ctn = ctn;

		this.ptn = ptn;
		this.smemo = smemo;
		this.asmemo = asmemo;
		this.aduser01 = aduser01;
		this.aduser02 = aduser02;
		this.aduser03 = aduser03;
		this.aduser04 = aduser04;
		this.aduser05 = aduser05;
		this.afuser01 = afuser01;
		this.afuser02 = afuser02;
		this.afuser03 = afuser03;
		this.afuser04 = afuser04;
		this.afuser05 = afuser05;
		this.asuser01 = asuser01;
		this.asuser02 = asuser02;
		this.asuser03 = asuser03;
		this.asuser04 = asuser04;
		this.asuser06 = asuser06;
		this.asuser07 = asuser07;
		this.asuser08 = asuser08;
		this.asuser09 = asuser09;
		this.asuser10 = asuser10;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesignno() {
		return this.designno;
	}

	public void setDesignno(String designno) {
		this.designno = designno;
	}

	public String getWkaid() {
		return this.wkaid;
	}

	public void setWkaid(String wkaid) {
		this.wkaid = wkaid;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Byte getDel() {
		return this.del;
	}

	public void setDel(Byte del) {
		this.del = del;
	}

	public String getAlteruser() {
		return this.alteruser;
	}

	public void setAlteruser(String alteruser) {
		this.alteruser = alteruser;
	}

	public String getDuser() {
		return this.duser;
	}

	public void setDuser(String duser) {
		this.duser = duser;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMuser() {
		return this.muser;
	}

	public void setMuser(String muser) {
		this.muser = muser;
	}

	public String getChkusr() {
		return this.chkusr;
	}

	public void setChkusr(String chkusr) {
		this.chkusr = chkusr;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDeltime() {
		return this.deltime;
	}

	public void setDeltime(Date deltime) {
		this.deltime = deltime;
	}

	public Date getMtime() {
		return this.mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getMsym() {
		return this.msym;
	}

	public void setMsym(String msym) {
		this.msym = msym;
	}

	public Double getBnum() {
		return this.bnum;
	}

	public void setBnum(Double bnum) {
		this.bnum = bnum;
	}

	public Integer getBompst() {
		return this.bompst;
	}

	public void setBompst(Integer bompst) {
		this.bompst = bompst;
	}

	public String getCtn() {
		return this.ctn;
	}

	public void setCtn(String ctn) {
		this.ctn = ctn;
	}

	public String getPtn() {
		return this.ptn;
	}

	public void setPtn(String ptn) {
		this.ptn = ptn;
	}

	public String getSmemo() {
		return this.smemo;
	}

	public void setSmemo(String smemo) {
		this.smemo = smemo;
	}

	public String getAsmemo() {
		return this.asmemo;
	}

	public void setAsmemo(String asmemo) {
		this.asmemo = asmemo;
	}

	public Date getAduser01() {
		return this.aduser01;
	}

	public void setAduser01(Date aduser01) {
		this.aduser01 = aduser01;
	}

	public Date getAduser02() {
		return this.aduser02;
	}

	public void setAduser02(Date aduser02) {
		this.aduser02 = aduser02;
	}

	public Date getAduser03() {
		return this.aduser03;
	}

	public void setAduser03(Date aduser03) {
		this.aduser03 = aduser03;
	}

	public Date getAduser04() {
		return this.aduser04;
	}

	public void setAduser04(Date aduser04) {
		this.aduser04 = aduser04;
	}

	public Date getAduser05() {
		return this.aduser05;
	}

	public void setAduser05(Date aduser05) {
		this.aduser05 = aduser05;
	}

	public Long getAfuser01() {
		return this.afuser01;
	}

	public void setAfuser01(Long afuser01) {
		this.afuser01 = afuser01;
	}

	public Long getAfuser02() {
		return this.afuser02;
	}

	public void setAfuser02(Long afuser02) {
		this.afuser02 = afuser02;
	}

	public Long getAfuser03() {
		return this.afuser03;
	}

	public void setAfuser03(Long afuser03) {
		this.afuser03 = afuser03;
	}

	public Long getAfuser04() {
		return this.afuser04;
	}

	public void setAfuser04(Long afuser04) {
		this.afuser04 = afuser04;
	}

	public Long getAfuser05() {
		return this.afuser05;
	}

	public void setAfuser05(Long afuser05) {
		this.afuser05 = afuser05;
	}

	public String getAsuser01() {
		return this.asuser01;
	}

	public void setAsuser01(String asuser01) {
		this.asuser01 = asuser01;
	}

	public String getAsuser02() {
		return this.asuser02;
	}

	public void setAsuser02(String asuser02) {
		this.asuser02 = asuser02;
	}

	public String getAsuser03() {
		return this.asuser03;
	}

	public void setAsuser03(String asuser03) {
		this.asuser03 = asuser03;
	}

	public String getAsuser04() {
		return this.asuser04;
	}

	public void setAsuser04(String asuser04) {
		this.asuser04 = asuser04;
	}

	public String getAsuser06() {
		return this.asuser06;
	}

	public void setAsuser06(String asuser06) {
		this.asuser06 = asuser06;
	}

	public String getAsuser07() {
		return this.asuser07;
	}

	public void setAsuser07(String asuser07) {
		this.asuser07 = asuser07;
	}

	public String getAsuser08() {
		return this.asuser08;
	}

	public void setAsuser08(String asuser08) {
		this.asuser08 = asuser08;
	}

	public String getAsuser09() {
		return this.asuser09;
	}

	public void setAsuser09(String asuser09) {
		this.asuser09 = asuser09;
	}

	public String getAsuser10() {
		return this.asuser10;
	}

	public void setAsuser10(String asuser10) {
		this.asuser10 = asuser10;
	}

	public Part getCpart() {
		return cpart;
	}

	public void setCpart(Part cpart) {
		this.cpart = cpart;
	}

	public Part getFpart() {
		return fpart;
	}

	public void setFpart(Part fpart) {
		this.fpart = fpart;
	}

	// public String getLevel() {
	// return level;
	// }
	//
	// public void setLevel(String level) {
	// this.level = level;
	// }

}
