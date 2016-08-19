package sdaac.wym.dms.entity;

import java.util.List;

import app.entity.Tempolyee;

/**
 * 记录得分数据
 * @author SA1KV5
 *
 */
public class Score {
	private int id;
	private String uid;
	
	private Tempolyee emp = new Tempolyee();
	
	private String uname;
	private String area;
	private String workgroup;
	private String version;
	private int ranking;
	private String leaderUid;
	
	private float score1;
	private float score2;
	private float score3;
	private float score4;
	private float score5;
	
	private float score6;
	private float score7;
	private float score8;
	private float score9;
	
	private float score10;
	private float score11;
	private float score12;
	private float score13;
	private float score14;
	
	private float score15;
	private float score16;
//	private float score8;
//	private float score9;
	
	
	private float totalScore;
	private int peopelCount;
	private float totalBouns;
	
	public float getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}
	public int getPeopelCount() {
		return peopelCount;
	}
	public void setPeopelCount(int peopelCount) {
		this.peopelCount = peopelCount;
	}
	public float getTotalBouns() {
		return totalBouns;
	}
	public void setTotalBouns(float totalBouns) {
		this.totalBouns = totalBouns;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getWorkgroup() {
		return workgroup;
	}
	public void setWorkgroup(String workgroup) {
		this.workgroup = workgroup;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public float getScore1() {
		return score1;
	}
	public void setScore1(float score1) {
		this.score1 = score1;
	}
	public float getScore2() {
		return score2;
	}
	public void setScore2(float score2) {
		this.score2 = score2;
	}
	public float getScore3() {
		return score3;
	}
	public void setScore3(float score3) {
		this.score3 = score3;
	}
	public float getScore4() {
		return score4;
	}
	public void setScore4(float score4) {
		this.score4 = score4;
	}
	public float getScore5() {
		return score5;
	}
	public void setScore5(float score5) {
		this.score5 = score5;
	}
	public float getScore6() {
		return score6;
	}
	public void setScore6(float score6) {
		this.score6 = score6;
	}
	public float getScore7() {
		return score7;
	}
	public void setScore7(float score7) {
		this.score7 = score7;
	}
	public float getScore8() {
		return score8;
	}
	public void setScore8(float score8) {
		this.score8 = score8;
	}
	public float getScore9() {
		return score9;
	}
	public void setScore9(float score9) {
		this.score9 = score9;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	

	private List<String> indicates;
	private List<Float> scores;

	public float getScore10() {
		return score10;
	}
	public void setScore10(float score10) {
		this.score10 = score10;
	}
	public float getScore11() {
		return score11;
	}
	public void setScore11(float score11) {
		this.score11 = score11;
	}
	public float getScore12() {
		return score12;
	}
	public void setScore12(float score12) {
		this.score12 = score12;
	}
	public float getScore13() {
		return score13;
	}
	public void setScore13(float score13) {
		this.score13 = score13;
	}
	public float getScore14() {
		return score14;
	}
	public void setScore14(float score14) {
		this.score14 = score14;
	}
	public float getScore15() {
		return score15;
	}
	public void setScore15(float score15) {
		this.score15 = score15;
	}
	public float getScore16() {
		return score16;
	}
	public void setScore16(float score16) {
		this.score16 = score16;
	}
	public List<String> getIndicates() {
		return indicates;
	}
	public void setIndicates(List<String> indicates) {
		this.indicates = indicates;
	}
	public String getLeaderUid() {
		return leaderUid;
	}
	public void setLeaderUid(String leaderUid) {
		this.leaderUid = leaderUid;
	}
	public Tempolyee getEmp() {
		return emp;
	}
	public void setEmp(Tempolyee emp) {
		this.emp = emp;
	}
	
}
