/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action.vave;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sdaac.wym.app.entity.vave.Account;
import sdaac.wym.app.entity.vave.AccountGoods;
import sdaac.wym.app.entity.vave.Goods;

import app.entity.Tuser;
import app.web.struts.form.vave.AccountForm;

/** 
 * MyEclipse Struts
 * Creation date: 03-16-2010
 * 
 * XDoclet definition:
 * @struts.action path="/account" name="accountForm" input="/form/account.jsp" parameter="actionType" scope="request" validate="true"
 */
public class AccountAction extends VaveGoodsBaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * 显示历史订单
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Tuser user=(Tuser)request.getSession().getAttribute("logineduser");
		Account account = new Account();
		account.setEmp(user.getEmp());
		List<Account> rs= this.getGoodsManager().showMyAccount(account);
		request.setAttribute("rs", rs);
		return mapping.findForward("showAccount");
	}
	
	/** 
	 * 显示单条订单记录
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showAccountGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int accountId=Integer.parseInt(request.getParameter("accountId"));
		List<AccountGoods> rs=this.getGoodsManager().showAccountGoods(accountId);
		request.setAttribute("account", rs.get(0).getAccount());
		request.setAttribute("rs", rs);
		return mapping.findForward("showAccountGoods");
	}
	
	/** 
	 * 购买
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward buy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Tuser user=(Tuser)request.getSession().getAttribute("logineduser");
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		Goods goods = new Goods(goodsId);
		Account ac=this.getGoodsManager().showShopCar(user.getEmp());
		if(ac==null){
			ac = new Account(); 
			ac.setEmp(user.getEmp());
			ac.setDatetime(Calendar.getInstance().getTime());
			ac.setState(0);
		}
		AccountGoods ag=new AccountGoods();
		ag.setAccount(ac);
		ag.setGoods(goods);
		ag.setQuantity(quantity);
		this.getGoodsManager().insertAccount(ag);
//		List<AccountGoods> rs=this.getGoodsManager().showAccountGoods(accountId);
//		request.setAttribute("rs", rs);
		return mapping.findForward("");
	}
	
	/** 
	 * 确认购买
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward confirm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//Tuser user=(Tuser)request.getSession().getAttribute("logineduser");
		int accountId=Integer.parseInt(request.getParameter("accountId"));
		
		return mapping.findForward("");
	}
	
}