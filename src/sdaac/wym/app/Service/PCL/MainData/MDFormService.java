package sdaac.wym.app.Service.PCL.MainData;

import java.util.ArrayList;
import java.util.List;

/**
 * MDFormBiz
 * @author SA1KV5
 *
 */
public class MDFormService extends FormService {

	
	public void doUpdateDetails(BaseForm bf) {
		// TODO Auto-generated method stub
		MDForm item = (MDForm)bf;
		StringBuffer hql= new StringBuffer("update BaseForm set ");
		List params = new ArrayList();
		if(item.getState()!=null){
			hql.append("state =?,");
			params.add(item.getState());
		}
		if(item.getNeedExplain()!=null) {
			hql.append("needExplain =?,");
			params.add(item.getNeedExplain());
		}
		if(item.getNeedExplain()!=null) {
			hql.append("needExplain =?,");
			params.add(item.getNeedExplain());
		}
		if(item.getDescription()!=null) {
			hql.append("description =?,");
			params.add(item.getDescription());
		}
		if(item.getUuid()!=null) {
			hql.delete(hql.length()-1,hql.length());
			hql.append(" where uuid=?");
			params.add(item.getUuid());
			formDao.bulkUpdate(hql.toString(), params.toArray());
		}
	}
	
}
