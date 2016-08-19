package app.biz.epor;
import java.util.List;

import app.entity.epor.PrBuyContext;
public interface PrBuyContextBiz {
public List<PrBuyContext> doSelectContextByPrFormID(Integer ID);
//更新
public String doUpdateWithCheck(String finishdate,String order_no);
}
