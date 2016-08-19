package sdaac.wym.dms.Service.i;

import java.util.List;
import java.util.Map;

import app.entity.Tempolyee;

import sdaac.wym.dms.entity.Indicate;

/**
 * @description About Indicate methods
 * @author SA1KV5
 * @version 2012-8-1
 * @change on 2012-12-5 
 * 		 context： remove the function 'doShow',add the function 'doShowWithForm' for replacement
 * 	
 * 				   
 */
public interface IIndicateService {
	
	public void doNewIndicate(Indicate item);
	public void doNewIndicates(List<Indicate> items);
	//public List<Indicate> doShow(Tempolyee emp);
	public Map<String,List<Indicate>> doShowWithForm(Tempolyee emp);
	public List<Indicate> doShowByCondition(Indicate item);
	public List<Indicate> getModel(String area,String group,String leaderID);
	public void doUpdateWeight(int value,int key);
	public void doSelectOneANDUpdate(int key,int weight);
}
