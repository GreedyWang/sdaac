package app.web.struts.action.vave;

import org.springframework.web.struts.DispatchActionSupport;
import sdaac.wym.app.Service.vave.GoodsManager;

public class VaveGoodsBaseAction extends DispatchActionSupport {
	
	private GoodsManager goodsManager;
	
	public GoodsManager getGoodsManager() {
		goodsManager=(GoodsManager)this.getWebApplicationContext().getBean("goodsManager");
		return goodsManager;
	}
	
//	public GoodsManager getConfmanager() {
//		confmanager=(ConfManager)this.getWebApplicationContext().getBean("confManager");
//		return confmanager;
//	}
}
