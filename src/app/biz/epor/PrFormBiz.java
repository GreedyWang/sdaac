package app.biz.epor;
import java.util.List;

import app.entity.epor.PrApprovedForm;
import app.entity.epor.PrBuyContext;
import app.entity.epor.PrBuyer;
import app.entity.epor.PrPrForm;
public interface PrFormBiz {
	public void doInsert(PrPrForm item);
	public List<PrPrForm> doSelect(PrPrForm item);
	//public boolean doInsert(PrPrForm item,List<PrBuyContext> buyContext);
	public List<PrPrForm> dpSelectEXT(String uid,String depart,String beginTime,String endTime);
	public PrPrForm doSelectByPk(Integer pk);
	public void doApprovePR(PrApprovedForm approvedForm);
	public List<PrBuyer> doSelectBuyers();
	public List<PrPrForm> doSelectMine(PrPrForm item,String uid);
	public void approve1(PrApprovedForm approvedForm);
	public void approve2(PrApprovedForm approvedForm);
	public void approve3(PrApprovedForm approvedForm);
}
