package app.biz.impl;



import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import app.biz.AuthorizationUtil;

public class AuthorizationUtilImpl extends TagSupport implements AuthorizationUtil {

//	private CommonDAO<TroleFunction> roleFunDao=null;
//	
//	public CommonDAO<TroleFunction> getRoleFunDao() {
//		return roleFunDao;
//	}
//	
//	public void setRoleFunDao(CommonDAO<TroleFunction> roleFunDao) {
//		this.roleFunDao = roleFunDao;
//	}
	
//	private CommonDAO<TuserRole> userRoleDao;
//	
//	public CommonDAO<TuserRole> getUserRoleDao() {
//		return userRoleDao;
//	}
//
//	public void setUserRoleDao(CommonDAO<TuserRole> userRoleDao) {
//		this.userRoleDao = userRoleDao;
//	}
	private List<String> hasRoles;
	private String needRoleID;

	//private static Object context;
	public int is()
	{
		int flag=0;
		for(String theRole:hasRoles)
		{
			if(theRole.contains(needRoleID))
			{
				flag=1;
			}
		}
		
		return flag;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return is();
		
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		 return is();
	}



	public String getNeedRoleID() {
		return needRoleID;
	}

	public void setNeedRoleID(String needRoleID) {
		this.needRoleID = needRoleID;
	}
	
//	public boolean is(String roleID) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	public boolean is(String roleID, String operation) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	public boolean is(String roleID, String functionID, String operation) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	public static void main(String[] args) {
//	
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		AuthorizationUtilImpl dd=(AuthorizationUtilImpl)context.getBean("rightTag");
//		try {
//			dd.setUid("8002");
//			dd.setNeedRoleID("admin");
//			int aaa=dd.doStartTag();
//			System.out.println(aaa);
//		} catch (JspException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public List getHasRoles() {
		return hasRoles;
	}

	public void setHasRoles(List hasRoles) {
		this.hasRoles = hasRoles;
	}

}
