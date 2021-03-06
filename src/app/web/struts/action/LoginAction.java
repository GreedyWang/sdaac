/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ldapBiz.ILdap;
import ldapBizImpl.LdapConnector;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import app.entity.Logs;
import app.entity.Tempolyee;
import app.entity.Tuser;

/**
 * MyEclipse Struts Creation date: 12-18-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/login" name="loginForm" input="/form/login.jsp"
 *                parameter="actionType" scope="request" validate="true"
 */
public class LoginAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/**
	 * 选择语言
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward selectLanguage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// LoginForm loginForm = (LoginForm) form;// TODO Auto-generated method
		// stub
		String lang = request.getParameter("lang");
		Locale locale = Locale.getDefault();
		if ("zh".equals(lang)) {
			locale = new Locale("zh", "CN");
		} else if ("en".equals(lang)) {
			locale = new Locale("en", "US");
		}
		this.setLocale(request, locale);
		return mapping.findForward("changeLang");
	}

	/**
	 * 注销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward loginout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return mapping.findForward("index");
	}

	public ActionForward doLoginByBPM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		String upass = request.getParameter("upass");
		String OU = request.getParameter("OU");
		ActionMessages messages = new ActionMessages();
		String url = "";
		HttpSession session = request.getSession();
		if (uid != null) {

			Tuser user = this.getUserBiz().checkUser(uid);// get
			user.setUserName("sa1kv5");
			user.setPassWD(upass);
			if (user == null) {
				user = new Tuser();
				user.setUid(uid);
			}
			// Tuser by NO.
			Tempolyee temp = (Tempolyee) this.getEmpBiz()
					.doSelectGetEmpDetails(uid);// get Tempolyee by key
			if (temp != null) {
				user.setEmp(temp);
				List<String> roleids = this.getUserRoleBiz().doSelectByUid(uid);
				temp.setRoleids_b(roleids);
				session.setAttribute("logineduser", user);
				url = "success";
				if (roleids != null) {
					temp.setRoleids("yes");
					for (int i = 0; i < roleids.size(); i++) {
						if (roleids.get(i).matches("/hr*")) {
							url = "success";
						}
					}
				} else {
					temp.setRoleids("no");
					List<String> tmp = new ArrayList<String>();
					tmp.add("everyone");
					temp.setRoleids_b(tmp);
				}

				Logs item = new Logs(temp.getName(), new Date(), "登录");
				this.getLogBiz().doInsert(item);
			} else {
				ActionMessage message = new ActionMessage("loginerror_db");
				messages.add("userLoginError", message);
				this.saveErrors(request, messages);
				url = "index";
			}
		} else {
			ActionMessage message = new ActionMessage("loginerror_ldap");
			messages.add("userLoginError", message);
			this.saveErrors(request, messages);
			url = "index";
		}
		if (request.getParameter("temp") != null) {
			url = "company";
		}
		if (request.getParameter("type") != null
				&& request.getParameter("type").equals("PRMail")) {
			request.setAttribute("no", request.getParameter("prFormID"));
			url = "mailCheck";
		}
		return mapping.findForward(url);
	}

	/**
	 * Login -----WHEN------WHO-------WHAT---------------------------
	 * ---2013/12/17-SA!KV5-----Change SDAAC domain to Delphi--
	 * --------------------------------------------------------
	 */
	public ActionForward doLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// --------CAS Single Login--------------
		// HttpSession session=request.getSession();
		// AssertionImpl key =
		// (AssertionImpl)session.getAttribute("_const_cas_assertion_");
		// String name = key.getPrincipal().getName();
		// ---------END--------------------
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String OU = request.getParameter("OU");
		ActionMessages messages = new ActionMessages();
		String url = "";
		HttpSession session = request.getSession();
		ILdap ldap = new LdapConnector(uname, upass,OU);
		DirContext ctx = null;
		try {
			ctx = ldap.getConnect();

		} catch (NamingException e) {
			// TODO �Զ���� catch ��
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ���� catch ��
			// e.printStackTrace();
		}
		if (ctx == null) {
			ActionMessage message = new ActionMessage("user.not.found", uname);
			messages.add("userLoginError", message);
			this.saveErrors(request, messages);
			url = "index";
		} else {
			String uid = ldap.searchByFilter(uname, OU);// get NO. by													
			if (uid != null) {
				Tuser user = this.getUserBiz().checkUser(uid);// get
				if (user == null) {
					user = new Tuser();
					user.setUid(uid);
				}
				// Tuser by NO.
				Tempolyee temp = (Tempolyee) this.getEmpBiz().doQueryByNetID(
						uid);// get Tempolyee by key
				
				if (temp != null) {
					user.setUid(temp.getUid());
					user.setEmp(temp);
					List<String> roleids = this.getUserRoleBiz().doSelectByUid(
							user.getUid());
					temp.setRoleids_b(roleids);
					session.setAttribute("logineduser", user);
					url = "success";
					if (roleids != null) {
						temp.setRoleids("yes");
						for (int i = 0; i < roleids.size(); i++) {
							if (roleids.get(i).matches("/hr*")) {
								url = "success";
							}
						}
					} else {
						temp.setRoleids("no");
						List<String> tmp = new ArrayList<String>();
						tmp.add("everyone");
						temp.setRoleids_b(tmp);
					}

					Logs item = new Logs(temp.getName(), new Date(), "登录");
					this.getLogBiz().doInsert(item);
				} else {
					ActionMessage message = new ActionMessage("loginerror_db");
					messages.add("userLoginError", message);
					this.saveErrors(request, messages);
					url = "index";
				}
			} else {
				ActionMessage message = new ActionMessage("loginerror_ldap");
				messages.add("userLoginError", message);
				this.saveErrors(request, messages);
				url = "index";
			}
		}// end}
		if (request.getParameter("temp") != null) {
			url = "company";
		}
		if (request.getParameter("type") != null
				&& request.getParameter("type").equals("PRMail")) {
			request.setAttribute("no", request.getParameter("prFormID"));
			url = "mailCheck";
		}
		return mapping.findForward(url);
	}

	/**
	 * 手机版登录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doLoginWithMobile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String uid = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String OU = request.getParameter("OU");
		ActionMessages messages = new ActionMessages();
		String url = "";
		HttpSession session = request.getSession();
		// Itest ldap=new Test(uname,upass);
		// DirContext ctx = null;
		// try {
		// ctx=ldap.getConnect();
		//					
		// } catch (NamingException e) {
		// // TODO �Զ���� catch ��
		// e.printStackTrace();
		// }catch (IOException e) {
		// // TODO �Զ���� catch ��
		// e.printStackTrace();
		// }
		// if(ctx==null)
		// {
		// ActionMessage message=new ActionMessage("user.not.found", uname);
		// messages.add("userLoginError",message);
		// this.saveErrors(request, messages);
		// url="index";
		// }
		// else
		// {
		// String uid=ldap.searchByFilter(uname,OU);// get NO. by [uid,password]
		if (uid != null) {

			Tuser user = this.getUserBiz().checkUser(uid);// get
			if (user == null) {
				user = new Tuser();
				user.setUid(uid);
			}
			user.setUid(uid);

			// Tuser by NO.
			Tempolyee temp = (Tempolyee) this.getEmpBiz()
					.doSelectGetEmpDetails(uid);// get Tempolyee by key
			if (temp != null) {
				user.setEmp(temp);
				List<String> roleids = this.getUserRoleBiz().doSelectByUid(uid);
				temp.setRoleids_b(roleids);
				session.setAttribute("logineduser", user);
				url = "success";
				if (roleids != null) {
					temp.setRoleids("yes");
					for (int i = 0; i < roleids.size(); i++) {
						if (roleids.get(i).matches("/hr*")) {
							url = "success";
						}
					}
				} else {
					temp.setRoleids("no");
					List<String> tmp = new ArrayList<String>();
					tmp.add("everyone");
					temp.setRoleids_b(tmp);
				}

				Logs item = new Logs(temp.getName(), new Date(), "登录");
				this.getLogBiz().doInsert(item);
			} else {
				ActionMessage message = new ActionMessage("loginerror_db");
				messages.add("userLoginError", message);
				this.saveErrors(request, messages);
				url = "index";
			}
		} else {
			ActionMessage message = new ActionMessage("loginerror_ldap");
			messages.add("userLoginError", message);
			this.saveErrors(request, messages);
			url = "index";
		}
		// }//end}
		if (request.getParameter("temp") != null) {
			url = "company";
		}
		if (request.getParameter("type") != null
				&& request.getParameter("type").equals("PRMail")) {
			request.setAttribute("no", request.getParameter("prFormID"));
			url = "mailCheck";
		}
		return mapping.findForward(url);
	}

	/**
	 * 登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doLoginByNetID(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// --------CAS Single Login--------------
		// HttpSession session=request.getSession();
		// AssertionImpl key =
		// (AssertionImpl)session.getAttribute("_const_cas_assertion_");
		// String name = key.getPrincipal().getName();
		// ---------END--------------------
		String uid = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String OU = request.getParameter("OU");
		ActionMessages messages = new ActionMessages();
		String url = "";
		HttpSession session = request.getSession();

			if (uid != null) {
				Tuser user = this.getUserBiz().checkUser(uid);// get
				if (user == null) {
					user = new Tuser();
					user.setUid(uid);
				}
				// Tuser by NO.
				Tempolyee temp = (Tempolyee) this.getEmpBiz().doQueryByNetID(
						uid);// get Tempolyee by key
				user.setUid(temp.getUid());
				if (temp != null) {
					user.setEmp(temp);
					List<String> roleids = this.getUserRoleBiz().doSelectByUid(
							user.getUid());
					temp.setRoleids_b(roleids);
					session.setAttribute("logineduser", user);
					url = "success";
					if (roleids != null) {
						temp.setRoleids("yes");
						for (int i = 0; i < roleids.size(); i++) {
							if (roleids.get(i).matches("/hr*")) {
								url = "success";
							}
						}
					} else {
						temp.setRoleids("no");
						List<String> tmp = new ArrayList<String>();
						tmp.add("everyone");
						temp.setRoleids_b(tmp);
					}

					Logs item = new Logs(temp.getName(), new Date(), "登录");
					this.getLogBiz().doInsert(item);
				} else {
					ActionMessage message = new ActionMessage("loginerror_db");
					messages.add("userLoginError", message);
					this.saveErrors(request, messages);
					url = "index";
				}
			} else {
				ActionMessage message = new ActionMessage("loginerror_ldap");
				messages.add("userLoginError", message);
				this.saveErrors(request, messages);
				url = "index";
			}
		if (request.getParameter("temp") != null) {
			url = "company";
		}
		if (request.getParameter("type") != null
				&& request.getParameter("type").equals("PRMail")) {
			request.setAttribute("no", request.getParameter("prFormID"));
			url = "mailCheck";
		}
		return mapping.findForward(url);
	}

	/**
	 * 登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doLoginByUid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// --------CAS Single Login--------------
		// HttpSession session=request.getSession();
		// AssertionImpl key =
		// (AssertionImpl)session.getAttribute("_const_cas_assertion_");
		// String name = key.getPrincipal().getName();
		// ---------END--------------------
		String uid = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String OU = request.getParameter("OU");
		ActionMessages messages = new ActionMessages();
		String url = "";
		HttpSession session = request.getSession();

			if (uid != null) {
				Tuser user = this.getUserBiz().checkUser(uid);// get
				if (user == null) {
					user = new Tuser();
					user.setUid(uid);
				}
				// Tuser by NO.
				Tempolyee temp = (Tempolyee) this.getEmpBiz().doSelectGetEmpDetails(uid);// get Tempolyee by key
				user.setUid(temp.getUid());
				if (temp != null) {
					user.setEmp(temp);
					List<String> roleids = this.getUserRoleBiz().doSelectByUid(
							user.getUid());
					temp.setRoleids_b(roleids);
					session.setAttribute("logineduser", user);
					url = "success";
					if (roleids != null) {
						temp.setRoleids("yes");
						for (int i = 0; i < roleids.size(); i++) {
							if (roleids.get(i).matches("/hr*")) {
								url = "success";
							}
						}
					} else {
						temp.setRoleids("no");
						List<String> tmp = new ArrayList<String>();
						tmp.add("everyone");
						temp.setRoleids_b(tmp);
					}

					Logs item = new Logs(temp.getName(), new Date(), "登录");
					this.getLogBiz().doInsert(item);
				} else {
					ActionMessage message = new ActionMessage("loginerror_db");
					messages.add("userLoginError", message);
					this.saveErrors(request, messages);
					url = "index";
				}
			} else {
				ActionMessage message = new ActionMessage("loginerror_ldap");
				messages.add("userLoginError", message);
				this.saveErrors(request, messages);
				url = "index";
			}
		if (request.getParameter("temp") != null) {
			url = "company";
		}
		if (request.getParameter("type") != null
				&& request.getParameter("type").equals("PRMail")) {
			request.setAttribute("no", request.getParameter("prFormID"));
			url = "mailCheck";
		}
		return mapping.findForward(url);
	}
	
	public ActionForward mailCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		request.setAttribute("prFormID", no);
		// session.setAttribute("logineduser", user);
		HttpSession session = request.getSession();
		if (session.getAttribute("logineduser") != null) {
			return mapping.findForward("showPR");
		} else {
			return mapping.findForward("prMailCheck");
		}
	}

	/**
	 * 首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward weclome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request
				.setAttribute("totleSum", getConfmanager()
						.showTheAmountofTour());
		return mapping.findForward("welcome");
	}
}