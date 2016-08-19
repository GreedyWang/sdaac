package sdaac.wym.dms.Service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sdaac.wym.dms.Service.i.IAllService;
import sdaac.wym.dms.Service.i.ISocreDetailsService;
import sdaac.wym.dms.Service.i.IWorkerService;
import sdaac.wym.dms.entity.ScoreDetails;

import app.entity.Tempolyee;
/**
 * 区域经理
 * @author SA1KV5
 *
 */
public class AreaManagerService implements IAllService{

	private IWorkerService iWorkerService;
	private ISocreDetailsService iSocreDetailsService;
	
	
	/**
	 * 得到打分信息
	 */ 
	@Override
	public List<ScoreDetails> getScoreCard(Tempolyee item) {
		// TODO Auto-generated method stub
		List<Tempolyee> emps = iWorkerService.doGetUnderlings(item);
		String version = null;
		
		
		
		return iSocreDetailsService.doShow(emps, version, null);
		
	}
	/**
	 * 转换成输出格式
	 */
//	private void 
	
	@Override
	public void getScoreCard() {
		// TODO Auto-generated method stub
		
	}
	


}
