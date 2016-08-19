package app.web.struts.action.news;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.JDOMException;

import org.springframework.web.struts.DispatchActionSupport;

import common.util.FileUpload;
import common.util.XmlParse;

import sdaac.wym.app.Service.news.NewsManager;
import sdaac.wym.app.entity.News.CatagoryItem;
import sdaac.wym.app.entity.News.News;

import app.common.FileUpLoad;

import app.entity.Tuser;
import app.web.struts.form.news.NewsForm;

public class NewsAction extends DispatchActionSupport {

	private NewsManager newsManager = null;
	private Tuser user;

	private NewsManager getNewsManager() {
		newsManager = (NewsManager) this.getWebApplicationContext().getBean(
				"newsManager");
		return newsManager;
	}

	private Tuser getNowUser(HttpServletRequest request) {
		return (Tuser) request.getSession().getAttribute("logineduser");
	}

	/**
	 * 发布新闻
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward release(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm newsFrom = (NewsForm) form;
		News news = newsFrom.getItem();
		user = getNowUser(request);
		news.setReleaseEmployeeId(user.getEmp());
		news.setState(Integer.parseInt(request.getParameter("item.state")));
		getNewsManager().release(news);
		return null;
	}

	/**
	 * 显示单条新闻
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int pk = Integer.parseInt(request.getParameter("pk"));
		News item = getNewsManager().show(pk);
		request.setAttribute("item", item);
		String destinyUrl="";
		if(item.getState()==1){
			destinyUrl="detailShow";
		}else{
			destinyUrl="release2";
		}
		return mapping.findForward(destinyUrl);
	}

	/**
	 * upload no use
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// baseDir=getInitParameter("baseDir");
		// debug=(new Boolean(getInitParameter("debug"))).booleanValue();
		// if(baseDir==null)
		// String baseDir="UserFiles/";
		String realBaseDir = getServletContext().getRealPath("/");
		System.out.println(realBaseDir);
		return null;
	}

	/**
	 * 显示多行
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		News news = new News();
		news.setReleaseEmployeeId(getNowUser(request).getEmp());
		request.setAttribute("rs", getNewsManager().search(news));
		// List aa=getNewsManager().search(news);
		return mapping.findForward("personalShowList");
	}

	/**
	 * 显示公司新闻
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showCompanyList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		News news = new News();
		news.setState(1);//已发布
		request.setAttribute("rs", getNewsManager().searchHotNews(news));
		return mapping.findForward("company");
	}
	/**
	 * 修改新闻
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		NewsForm newsFrom = (NewsForm) form;
		News news = newsFrom.getItem();
		getNewsManager().modify(news);
		PrintWriter os= response.getWriter();
		os.print("<h3>OK</h3>");
		return null;
	}
	
	/**
	 * 显示个部门菜单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
		request.setCharacterEncoding("UTF-8");

	
		String id = new String(request.getParameter("id")
				.getBytes("ISO-8859-1"), "UTF-8");
		String url;
		String introduce;
		CatagoryItem ci = new CatagoryItem();
		ci.setMethod(request.getParameter("m"));	
		int xmlName=getNowUser(request).getEmp().getTdepartment().getId();
		ci.setFileName(request.getRealPath("/")+"company/data/"+xmlName+".xml");
		
		
		if(request.getParameter("m").equals("add")){
			ci.setPid(id);
		}else if(request.getParameter("m").equals("modify")){
			ci.setId(id);
			String name = new String(request.getParameter("name").getBytes(
			"ISO-8859-1"), "UTF-8");
			ci.setName(name);
			ci.setId(id);
			if (request.getParameter("url") != null) {
				url = new String(
						request.getParameter("url").getBytes("ISO-8859-1"), "UTF-8");
				ci.setUrl(url);
				if (request.getParameter("introduce") != null) {
					introduce = new String(request.getParameter("introduce")
							.getBytes("ISO-8859-1"), "UTF-8");
					ci.setIntroduce(introduce);
				}
			} else {
				ci.setIsMenu("Y");
			}
		}else if(request.getParameter("m").equals("delete")){
			ci.setId(id);
			XmlParse xml=new XmlParse(ci);
			xml.delete();
		}		
		request.setAttribute("item", ci);
		return mapping.findForward("MenuManager");
	}
	
}
