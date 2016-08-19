package sdaac.wym.dms.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import sdaac.wym.dms.Service.i.IIndicateService;
import sdaac.wym.dms.Service.i.ISocreDetailsService;
import sdaac.wym.dms.biz.BonusBiz;
import sdaac.wym.dms.biz.EmpBiz;
import sdaac.wym.dms.biz.VersionBiz;
import sdaac.wym.dms.entity.Bonus;
import sdaac.wym.dms.entity.Indicate;
import sdaac.wym.dms.entity.Score;
import sdaac.wym.dms.entity.ScoreDetails;
import sdaac.wym.dms.entity.ScoreShow;
import sdaac.wym.dms.entity.Ver;
import app.entity.Tempolyee;

/**
 * 打分：继续打分、创建新版本打分
 * 
 * @author SA1KV5
 * 
 */
public class DMSManager {
	private IIndicateService indicateService;
	private EmpBiz empBiz;
	private ISocreDetailsService ss;
	private ScoreSnapBiz ssBiz;
	private BonusBiz bonusBiz;
	private VersionBiz verBiz;
	
	public VersionBiz getVerBiz() {
		return verBiz;
	}

	public void setVerBiz(VersionBiz verBiz) {
		this.verBiz = verBiz;
	}

	public BonusBiz getBonusBiz() {
		return bonusBiz;
	}

	public void setBonusBiz(BonusBiz bonusBiz) {
		this.bonusBiz = bonusBiz;
	}

	public ScoreSnapBiz getSsBiz() {
		return ssBiz;
	}

	public void setSsBiz(ScoreSnapBiz ssBiz) {
		this.ssBiz = ssBiz;
	}

	public ISocreDetailsService getSs() {
		return ss;
	}

	public void setSs(ISocreDetailsService ss) {
		this.ss = ss;
	}

	public IIndicateService getIndicateService() {
		return indicateService;
	}

	public void setIndicateService(IIndicateService indicateService) {
		this.indicateService = indicateService;
	}

	/**
	 * show all of indicates with Map type
	 * 
	 * @return
	 */
	public Map<String, List<Indicate>> doShow(Tempolyee emp) {
		// TODO Auto-generated method stub
		return indicateService.doShowWithForm(emp);
	}

	/**
	 * 按条件查询指标
	 * 
	 * @param emp
	 * @return
	 */
	public List<Indicate> doQueryIndicate(Tempolyee emp) {
		Indicate item = new Indicate();
		return indicateService.doShowByCondition(item);
	}

	/**
	 * 批量更新权重
	 * 
	 * @param ids
	 * @param weights
	 */
	public void doUpdateWeights(Integer[] ids, Integer[] weights) {
		if (ids == null || weights == null)
			return;
		for (int i = 0; i < ids.length; i++) {
			indicateService.doSelectOneANDUpdate(ids[i],weights[i]);
//			Indicate indicate = indicateService.doSelectOne(ids[i]);
//			String hql2 = "from Indicate where typeName = ? and state = 0 and groupID=? and group=? and area=?";
//			if(indicateDao.select(hql2, new Object[]{typeName,one.getGroupID(),one.getGroup(),one.getArea()}).isEmpty()){
		}
		
//		String hql4serach = "from Indicate where id = ?";
////		if(state == 0) return 0;//如果是启用指标直接返回0
//		Indicate one = indicateDao.selectOne(hql4serach, key);
//		//查询该类型的指标是否全部禁用
//		String hql2 = "from Indicate where typeName = ? and state = 0 and groupID=? and group=? and area=?";
//		if(indicateDao.select(hql2, new Object[]{typeName,one.getGroupID(),one.getGroup(),one.getArea()}).isEmpty()){

	}
	
//	public void doUpdateWeights(String[] typeName, Integer[] weights) {
//		if (ids == null || weights == null)
//			return;
//		for (int i = 0; i < ids.length; i++) {
//			indicateService.doUpdateWeight(weights[i], ids[i]);
//		}
//	}
	

	/**
	 * 保存记录并根据指标公式计算得分
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 */
	float lastResult = 0;
    List<Float> totalDays ;
	public void doSaveScore(String[] uids, Float[] target, Float[] xishu,
			Float[] actual, Integer[] indicateid, Float[] weight,
			Float[] calcMethod, String version, String district, String group,
			String leaderUid, String[] groups, Float[] dmsratios) {
		List<ScoreDetails> sds = new ArrayList<ScoreDetails>();
		Calc calc = new Calc();
		int indicateSize = indicateid.length;// 指标总数
		int uidSize = uids.length;

		for (int i = 0; i < uids.length; i++) {
			for (int j = 0; j < indicateid.length; j++) {
				ScoreDetails item = new ScoreDetails();
				Tempolyee emp = new Tempolyee(uids[i]);
				emp.setGroup(groups[i]);
				emp.setDmsratio(dmsratios[i]);
				item.setEmp(emp);
				item.setIndicate(new Indicate(indicateid[j]));
				item.setActual(actual[indicateSize * i + j]);
				item.setXishu(xishu[indicateSize * i + j]);
				item.setTarget(target[indicateSize * i + j]);
				item.setVersion(version);
				item.setWight(weight[j]);
				item.setLeaderUid(leaderUid);
				item.setArea(district);
				item.setWorkgroup(group);
				// ---------------------------------------------------------------
				// 区域->大组长
				// ---------------------------------------------------------------
				if (calcMethod[j] == 1) {// 如果指标公式为1
					item.setScore(calc.doCalc1(
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j]));
				} else if (calcMethod[j] == 2) {
					item.setScore(calc.doCalc22(
							actual[indicateSize * i + j - 2],
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j], actual,
							target[indicateSize * i + j], indicateSize,
							uidSize, j,xishu,xishu[indicateSize * i + j]));
				}
				else if (calcMethod[j] == 6) {
					// lastResult = calc.doCalc6(actual[indicateSize * i + j],
					// target[indicateSize * i + j], weight[j], actual,
					// indicateSize, uidSize, j);
					// item.setScore(lastResult);
					// lastResult = ;
					item.setScore(calc.doCalc6(actual[indicateSize * i + j],
							target[indicateSize * i + j], weight[j], actual,
							indicateSize, uidSize, j));
				} else if (calcMethod[j] == 7) {
					item.setScore(calc.doCalc7(actual[indicateSize * i + j],
							target[indicateSize * i + j], xishu[indicateSize
									* i + j], weight[j]));
				} else if (calcMethod[j] == 8) {
					item.setScore(calc.doCalc8(actual[indicateSize * i + j]));
				} else if (calcMethod[j] == 9) {
					item.setScore(calc.doCalc9(actual[indicateSize * i + j],
							actual[indicateSize * i + j + 1],
							actual[indicateSize * i + j + 2]));
				}
				// ---------------------------------------------------------------
				// 大组长->小组
				// ---------------------------------------------------------------
				else if (calcMethod[j] == 21) {
					item.setScore(calc.doCalc21(
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j], actual,
							indicateSize, uidSize, j));
				} else if (calcMethod[j] == 22) {
					item.setScore(calc.doCalc22(
							actual[indicateSize * i + j - 2],
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j], actual,
							target[indicateSize * i + j], indicateSize,
							uidSize, j,xishu,xishu[indicateSize * i + j]));
				} else if (calcMethod[j] == 23) {
					item.setScore(calc.doCalc23(
							actual[indicateSize * i + j - 2],
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j], actual,
							indicateSize, uidSize, j));
				} else if (calcMethod[j] == 24) {
					totalDays = new ArrayList<Float>();
					item.setScore(calc.doCalc24(
							actual[indicateSize * i + j - 2],
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j], actual,
							indicateSize, uidSize, j));
					totalDays.add(actual[indicateSize * i + j - 2]+actual[indicateSize * i + j - 1]+actual[indicateSize * i + j]);

				}
				if (calcMethod[j] == 25) {
					item.setScore(calc.doCalc25(actual[indicateSize * i + j],
							weight[j], actual, indicateSize, uidSize, j));
				}
				// ---------------------
				// -------小组到员工------
				// ---------------------
				if (calcMethod[j] == 41) {
					item.setScore(calc.doCalc41(
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j]));
				} else if (calcMethod[j] == 42) {
					item.setScore(calc.doCalc42(
							actual[indicateSize * i + j - 2],
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j], actual,
							target[indicateSize * i + j], indicateSize,
							uidSize, j));
				} else if (calcMethod[j] == 43) {
					item.setScore(calc.doCalc43(actual[indicateSize * i + j],
							target[indicateSize * i + j], weight[j]));
				} else if (calcMethod[j] == 44) {
					
					item.setScore(calc.doCalc44(
							actual[indicateSize * i + j - 2],
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j]));
				} else if (calcMethod[j] == 45) {
					item.setScore(calc.doCalc45(
							actual[indicateSize * i + j - 2],
							actual[indicateSize * i + j - 1],
							actual[indicateSize * i + j], weight[j]));
				} else if (calcMethod[j] == 46) {
					item.setScore(calc.doCalc46(actual[indicateSize * i + j],
							weight[j]));
				}
				sds.add(item);
			}
		}
		ss.doScores(sds, version);
		doSaveScore4Snap(sds, version, district, group, leaderUid);
	}

	/**
	 * 把得分结果保存的 DB.DMS_ScoreSnap 用于保存历史记录
	 * 
	 * @param sds
	 * @param version
	 * @param district
	 * @param group
	 */
	private void doSaveScore4Snap(List<ScoreDetails> sds, String version,
			String district, String group, String leaderUid) {
		// 记录到打分表内 记录奖金
		List<ScoreShow> rs = Util.CtoB(sds);
		// initial the totalCount to 0
		empBiz.totalCount = 0;
		empBiz.doGetMemberCount(new Tempolyee(leaderUid));// 得到全部人数
		int genMemberCount = empBiz.getGenMemberCount(district,group); //得到综合组人数
//		int HC = empBiz.totalCount;
		List<Score> scores = new ArrayList<Score>();
		float zgma = 0;
		for (int k =0;k<rs.size();k++) {
			ScoreShow item2 = rs.get(k);
			empBiz.totalCount = 0;
			empBiz.doGetMemberCount(item2.getEmp());
			if (empBiz.totalCount == 0) {
				zgma += item2.getTotalScore(totalDays,k);
			} else {
				zgma += item2.getTotalScore(totalDays,k) * (empBiz.totalCount + 1);
			}
		}

		for (int l =0;l<rs.size();l++) {
			ScoreShow item = rs.get(l);
			Score scoreItem = new Score();
			scoreItem.setArea(district);
			scoreItem.setWorkgroup(group);
			scoreItem.setTotalScore(item.getTotalScore(totalDays,l));// 计算总得分
			empBiz.totalCount = 1;
			empBiz.doGetMemberCount(item.getEmp());
			scoreItem.setPeopelCount(empBiz.totalCount+genMemberCount);// HC
			scoreItem.setLeaderUid(leaderUid);
			// scoreShowItem.totalBouns = 0;//得到上级给的奖金
			scoreItem.setTotalBouns(item.getTotalScore(totalDays,l)
					* scoreItem.getPeopelCount() / zgma);// (HC'-奖金系数)/HC
			scoreItem.setVersion(version);
			// scoreItem.setUid(item.getEmp().getUid());
			scoreItem.setEmp(new Tempolyee(item.getEmp().getUid()));
			scoreItem.setUname(item.getEmp().getName());
			// 存入ScoreSnap
			int size = item.getResults().size();
			scoreItem.setScore1(item.getResults().get(0).getScore());
			scoreItem.setScore2(item.getResults().get(1).getScore());
			scoreItem.setScore3(item.getResults().get(2).getScore());
			scoreItem.setScore4(item.getResults().get(3).getScore());
			scoreItem.setScore5(item.getResults().get(4).getScore());
			scoreItem.setScore6(item.getResults().get(5).getScore());
			scoreItem.setScore7(item.getResults().get(6).getScore());
			scoreItem.setScore8(item.getResults().get(7).getScore());
			scoreItem.setScore9(item.getResults().get(8).getScore());
			if (item.getResults().get(9) != null) {
				scoreItem.setScore10(item.getResults().get(9).getScore());
				if (item.getResults().get(10) != null) {
					scoreItem.setScore11(item.getResults().get(10).getScore());
					if (size >= 12) {
						scoreItem.setScore12(item.getResults().get(11)
								.getScore());
						if (size >= 13) {
							scoreItem.setScore13(item.getResults().get(12)
									.getScore());
							if (size >= 14) {
								scoreItem.setScore14(item.getResults().get(13)
										.getScore());
								if (size >= 15) {
									scoreItem.setScore15(item.getResults().get(
											14).getScore());
								}
							}
						}
					}
				}
			}
			scores.add(scoreItem);
		}
		// 增加排名
		float[] total_scores = new float[scores.size()];

		for (int i = 0; i < rs.size(); i++) {
			total_scores[i] = rs.get(i).getTotalScore(totalDays,i);
		}
		Arrays.sort(total_scores);
		int rank = total_scores.length;
		for (int j = 0; j < scores.size(); j++) {
			for (int i = 0; i < rank; i++) {
				if (rs.get(j).getTotalScore(totalDays,j) == total_scores[i]) {
					scores.get(j).setRanking(rank - i);
					break;

				}
			}
		}
		ssBiz.doSaveSnap(scores);
	}

	/**
	 * 显示打分界面
	 * 
	 * @param item
	 * @param version
	 * @return
	 */
	public List<ScoreShow> doReadyScoring(Tempolyee item, String version) {
		int flag = checkVersion(item, version);
		if (flag ==1) { // check version is exist
			return doNow(item, version);
		} else if(flag == 3){
			return CreateScoring(item, version);
		}else{
			return doOld(version,item.getUid());
		}
	}
	
	private int checkVersion(Tempolyee item, String version) {
		List<Tempolyee> emps = empBiz.doShowMyTeam(item);
		List<ScoreDetails> sds = ss.doShow(emps, version, null);
		if(sds.isEmpty()) return 3;//go to create
		if(verBiz.isFinish(new Ver(version,item.getDistrict(),item.getSelectedGroup(),item.getUid()))) return 2;//old
		else {
			return 1;//doNow 
		}
	}

	/**
	 * 新建新版本打分
	 * 
	 * @param item
	 * @return
	 */
	private List<ScoreShow> CreateScoring(Tempolyee item, String version) {
		List<Tempolyee> emps = empBiz.doShowMyTeam(item);
		List<Indicate> indicates = indicateService.getModel(item.getDistrict(),
				item.selectedGroup, item.getUid());
		return Util.AtoB(emps, indicates);

	}
	
	/**
	 * 查询旧打分记录
	 * @param version
	 * @param leaderUid
	 * @return
	 */
	public List<ScoreShow> doOld(String version,String leaderUid) {
		List<ScoreDetails> sds = ss.doShow(version,leaderUid);
		return Util.CtoB4F(sds);
	}

	// -----------------------not use -------------------------------
	// /**
	// * 为新增加的员工穿件打分模板
	// * @param item
	// * @return
	// */
	// private List<ScoreShow> CreateNewEmployeesScoring(Tempolyee leader
	// ,List<Tempolyee> emps, String version) {
	// List<Indicate> indicates = indicateService.getModel(leader.getDistrict(),
	// leader.selectedGroup, leader.getUid());
	// return Util.AtoB(emps, indicates);
	//
	// }

	private List<ScoreShow> doNow(Tempolyee item, String version) {
		List<ScoreShow> nowVersion = CreateScoring(item, version);
		// 上次版本的数据
		List<Tempolyee> emps = empBiz.doShowMyTeam(item);
		List<ScoreDetails> sds = ss.doShow(emps, version, null);
		// 将上次的数据填充到新的格式内

		for (ScoreShow ss : nowVersion) {
			for (ScoreDetails sd : sds) {
				if (ss.getEmp().getUid().equals(sd.getEmp().getUid())) {

					for (int i = 0; i < ss.getIndicates().size(); i++) {

						if (ss.getIndicates().get(i).getId().equals(
								sd.getIndicate().getId())) {
							Result ars = new Result();
							ars.setScore(sd.getScore());
							ars.setTarget(sd.getTarget());
							ars.setXushu(sd.getXishu());
							ars.setActual(sd.getActual());
							ss.getResults().set(i, ars);
						}
					}
				}
			}
		}
		// if(sd.getEmp().getUid().equals(lastuid)){
		// rs.get(rs.size()-1).indicates.add(sd.getIndicate());
		// Result ars = new Result();
		// ars.setScore(sd.getScore());
		// ars.setTarget(sd.getTarget());
		// ars.setXushu(sd.getXishu());
		// ars.setActual(sd.getActual());
		// rs.get(rs.size()-1).getResults().add(ars);
		// }else{
		// ScoreShow ss = new ScoreShow();
		// ss.setEmp(sd.getEmp());
		// ss.indicates.add(sd.getIndicate());
		// Result ars = new Result();
		// ars.setScore(sd.getScore());
		// ars.setTarget(sd.getTarget());
		// ars.setXushu(sd.getXishu());
		// ars.setActual(sd.getActual());
		// ss.getResults().add(ars);
		// lastuid = sd.getEmp().getUid();
		// rs.add(ss);
		// }

		return nowVersion;
	}

	// ------------------------------not use
	// -------------------------------------
	// /**
	// * 继续打分
	// *
	// * @param item
	// * @return
	// */
	// private List<ScoreShow> doContinue(Tempolyee item, String version) {
	// List<Tempolyee> emps = empBiz.doShowMyTeam(item);
	// //原有数据-已离职的数据
	// List<ScoreDetails> sds = ss.doShow(emps, version, null);
	// //加入新员工
	// //找出先加的员工
	// List<Tempolyee> newEmps = emps;
	// for(ScoreDetails scoreDetial :sds){
	// for(int i = 0;i<emps.size();i++){
	// if(emps.get(i).getUid().equals(scoreDetial.getEmp().getUid())){
	// newEmps.remove(i);
	// break;
	// }
	// }
	// }
	// List<ScoreShow> sds2 =CreateNewEmployeesScoring(item, newEmps, version);
	// sds2.addAll(Util.CtoB(sds));
	// return sds2;
	// }

	public EmpBiz getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(EmpBiz empBiz) {
		this.empBiz = empBiz;
	}

	public List<Score> doShowLastVersionResultAll(Tempolyee item, String version,String group) {
		if(group!=null && group.equals("0")){
			return getGenTeamAomount(item.getDistrict(),item.getUid(),version);
		}else{
			return doShowLastVersionResult(item, version);
		}
	}
	
	/**
	 * 显示指定版本的打分结果(score card)
	 * @param item
	 *            领导信息
	 * @param version
	 * @return
	 */
	public List<Score> doShowLastVersionResult(Tempolyee item, String version) {
		Score score = new Score();
		score.setArea(item.getDistrict());
		score.setWorkgroup(item.getSelectedGroup());
		score.setVersion(version);
		score.setLeaderUid(item.getUid());
		// 查询版本奖金
		Bonus b = new Bonus();
		b.setVersion(version);
		b.setArea(item.getDistrict());
		float bouns = bonusBiz.getBonusByLevel(b);
		if (item.getSelectedGroup().equals("3")) {// 小组的奖金 = 大组长的得分*总奖金-大组长的奖金
			Score score2 = new Score();
			score2.setArea(item.getDistrict());
			score2.setWorkgroup("4");
			score2.setVersion(version);
			score2.setLeaderUid(item.getUid());
			Score dzx = ssBiz.doSelectOne(score2);
			if(dzx != null){
				bouns = dzx.getTotalBouns() * bouns - dzx.getTotalBouns() * bouns
						/ dzx.getPeopelCount() * 2;
			}
		}
		if (item.getSelectedGroup().equals("2")) {
			Score score2 = new Score();
			score2.setArea(item.getDistrict());
			score2.setWorkgroup("3");
			score2.setVersion(version);
			score2.setLeaderUid(item.getUid());
			Score dzx = ssBiz.doSelectOne(score2);
			if(dzx != null){
				bouns = (float) (dzx.getTotalBouns() * bouns - dzx.getTotalBouns()
						* bouns / dzx.getPeopelCount() * 1.5);
			}
		}
		List<Score> rs = ssBiz.doSelect(score);
		for (Score itemscore : rs) {
			itemscore.setTotalBouns(bouns * itemscore.getTotalBouns());
		}
		return rs;
		
		//--

	}

	// ---计算综合组奖金

	public List<Score> getGenTeamAomount(String area,String bigLeaderUid,String version) {
		Tempolyee item = new Tempolyee();
		item.setDistrict(area);
		item.selectedGroup = "1";// 综合组group值
		item.setUid(bigLeaderUid);
		List<Tempolyee> emps = empBiz.doShowMyTeam(item);
		//大组长奖金
		Score score = new Score();
		score.setArea(area);
		score.setWorkgroup("4");
		score.setLeaderUid(bigLeaderUid);
		score.setVersion(version);
		float bigLeaderScore = ssBiz.getAreaAvgBonus(score);
		List<Score> rs = new ArrayList<Score>();
		for(Tempolyee emp: emps){
			Score sc = new Score();
			sc.setEmp(emp);
			sc.setScore1(bigLeaderScore/2*emp.getDmsratio());//给综合组打分
			rs.add(sc);
		}
		return rs;
	}

	/**
	 * 计算综合组1系数得分
	 * 
	 * @param area
	 * @param leaderUid
	 * @return
	 */
	// public float aa(String area,String leaderUid){
	// BonusBiz bbiz = new BonusBiz();
	// float totalBonus = bbiz.doQueryBonusByVersionNArea("", area);
	// Tempolyee item = new Tempolyee();
	// int HC = empBiz.doGetMemberCount(item);
	// float rs = totalBonus/HC*empBiz.doCount(area, "", "");
	// float rs2 = rs/empBiz.doCountGen();
	// return rs2;
	// }
}
