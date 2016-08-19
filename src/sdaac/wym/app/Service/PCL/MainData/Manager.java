package sdaac.wym.app.Service.PCL.MainData;

import java.util.Date;
import java.util.List;

/**
 * <tr>
 * 	<td>
 *  	PCL 物料主数据申请 业务管理类 实现类
 *  </td>
 * </tr>
 * @version 1.0
 * @author SA1KV5
 * @since 2011-11-9
 */
public class Manager implements iManager {
	private FormService formService;
	private final String PACKAGE_BUYER="aaa";
	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	@Override
	public void doApply(MDForm item) {
		// TODO Auto-generated method stub
		item.setNeedExplain(BaseForm.NORMAL);
		item.setApplicateDate(new Date());
		if(item.getIsPackage()!=null &&item.getIsPackage() ==1) {
			item.setState(1);
			item.setBuyerName(PACKAGE_BUYER);
		}else{
			item.setState(1);
		}
		if(item.getIsSave()!=null && item.getIsSave()==1) { //如果是保存
			item.setState(0);
		}
		if(item.getIsSave()!=null && item.getIsSave()==1) { //如果是保存
//			item.setState(0);
		}
		if(item.getUuid()!=null && item.getUuid()!=0 ) { //如果已经有ID 
			formService.doUpdateMD(item);
		}else {
			formService.doAdd(item);
		}
		
	}	
	
	@Override
	public void doApprove(MDForm item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doCancel(Integer[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doCancelById(int formId) {
		// TODO Auto-generated method stub
		BaseForm item = new BaseForm();
		item.setUuid(formId);
		item.setState(-1);
		formService.doUpdate(item);
	}

	@Override
	public List<BaseForm> doShow(MDForm item) {
		// TODO Auto-generated method stub
		return formService.doShow(item);
	}

	@Override
	public BaseForm doShowDetails(int id) {
		// TODO Auto-generated method stub
		return formService.doShowDetails(id);
	}

	@Override
	public void doUpdate(MDForm item) {
		// TODO Auto-generated method stub
		formService.doUpdateMD(item);
	}
	
}
