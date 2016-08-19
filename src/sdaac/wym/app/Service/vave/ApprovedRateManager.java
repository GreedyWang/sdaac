package sdaac.wym.app.Service.vave;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.entity.TuserRole;

import sdaac.wym.app.entity.vave.ApproveRateItem;

import app.biz.impl.PersonalPBizJdbcImpl;
/**
 * 
 * @author SA1KV5
 * 批准率统计
 */
public class ApprovedRateManager {
	private PersonalPBizJdbcImpl sqlBiz;

	public PersonalPBizJdbcImpl getSqlBiz() {
		return sqlBiz;
	}

	public void setSqlBiz(PersonalPBizJdbcImpl sqlBiz) {
		this.sqlBiz = sqlBiz;
	}

	/**
	 * 合併數據
	 * @param rs1
	 * @param rs2
	 * @return
	 */
	private List<ApproveRateItem> merge(List<ApproveRateItem> rs1,List<ApproveRateItem> rs2,int key){
		for(ApproveRateItem item : rs1){
			boolean flag=true;
			for(ApproveRateItem item2 : rs2){
				if(item2.getName()!=null&&item.getName()!=null&&item2.getName().equals(item.getName())){
					switch (key) {
					case 0:
						item2.add(item.getCount());
						break;
					case 1:
						item2.addCountOverC(item.getCountOverC());
						break;
					case 2:
						item2.addAll(item.getAll());
						break;
					case 3:
						item2.addAll7(item.getAll7());
						break;
					default:
						break;
					}
					
					flag=false;
					break;
				}
			}
			if(flag){
				rs2.add(item);
			}			
		}		
		return rs2;
	}
	
	/**
	 * 合併數據
	 * @param rs1
	 * @param rs2
	 * @return
	 */
	public List<ApproveRateItem> merge2(List<ApproveRateItem> rs1,List<TuserRole> rs2,List<TuserRole> rs3){
		for(ApproveRateItem item : rs1){
			for(TuserRole item2 : rs2){
				if(item2.getTempolyee().getTdepartment().getName()!=null&&item.getName()!=null&&item2.getTempolyee().getTdepartment().getName().equals(item.getName())){
					item.setVavePmName(item2.getTempolyee().getName()+","+item.getVavePmName());		
					//item.setVavePmName2(vavePmName2)
					//break;
				}
			}		
		}
		for(ApproveRateItem item : rs1){
			for(TuserRole item2 : rs3){
				if(item2.getTempolyee().getTdepartment().getName()!=null&&item.getName()!=null&&item2.getTempolyee().getTdepartment().getName().equals(item.getName())){
					item.setVavePm(item2.getTempolyee().getName()+","+item.getVavePm());		
					//item.setVavePmName2(vavePmName2)
					//break;
				}
			}		
		}
		return rs1;
	}
	
	/**
	 * 统计批准率
	 */
	public List<ApproveRateItem> appraveRate(){
		//没有批准的提案
		List<ApproveRateItem> rs1 = sqlBiz.doCountPM(0);
		List<ApproveRateItem> rs2 =sqlBiz.doCountOutsidePM(0);
		//没有批准的提案,并延期7天
		List<ApproveRateItem> rs3 = sqlBiz.doCountPM(2);
		List<ApproveRateItem> rs4 =sqlBiz.doCountOutsidePM(2);
//		//所有批准的提案
		List<ApproveRateItem> rs5 = sqlBiz.doCountAllM(0);
		List<ApproveRateItem> rs6 =sqlBiz.doCountAllO(0);
//		//所有批准的提案<7
		List<ApproveRateItem> rs7 = sqlBiz.doCountAllM(3);
		List<ApproveRateItem> rs8 =sqlBiz.doCountAllO(3);
		//合併數據		
		merge(rs1,rs2,0);
		merge(rs3,rs2,1);
		merge(rs4,rs2,1);
		merge(rs5,rs2,2);
		merge(rs6,rs2,2);
		merge(rs7,rs2,3);
		merge(rs8,rs2,3);
		
		
		return rs2;
	}
		
//	public static void main(String[] args) {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ApprovedRateManager aa = (ApprovedRateManager)ac.getBean("ApproveRateManager");
//		List<ApproveRateItem> aaa = aa.appraveRate();
//		for(ApproveRateItem bbb:aaa){
//			System.out.println(bbb.getName()+":"+bbb.getCount()+"、"+bbb.getCountOverC()+"、"+bbb.getAll()+"、"+bbb.getAll7());
//		}
//	}
	
}
