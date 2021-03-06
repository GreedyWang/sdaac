/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.StringsToFloats;

import app.entity.Tpost;
import app.entity.TpostId;
import app.entity.TpostType;
import app.web.struts.form.PostForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-19-2008
 * 
 * XDoclet definition:
 * @struts.action path="/post" name="postForm" input="/form/post.jsp" parameter="actionType" scope="request" validate="true"
 */
public class PostAction extends BaseAction {
	/*
	 * Generated Methods
	 */	
	/**
	 * 插入岗位信息,同时把id插入postID表
	 */	
	public ActionForward doInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		PostForm postform=(PostForm) form;
		String[] postids=postform.getItem().getId().split("_");
		if(postids.length==3)
		{
			TpostId postID=new TpostId();
			postID.setFirstName(postids[0]);
			postID.setMidName(postids[1]);
			postID.setLastName(postids[2]);
			this.getPostIDBiz().doInsert(postID);
		}
		this.getPostBiz().doInsert(postform.getItem());
		return mapping.findForward("postAdd");
	}
	
	
	
	public ActionForward doSelectPost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PostForm postForm = (PostForm) form;// TODO Auto-generated method stub
		Tpost post= this.getPostBiz().doSelectById(postForm.getItem().getId());
		if(post==null)
		{
		//	request.setAttribute("postList", arg1);
		}
		else
		{
			request.setAttribute("postList", post);
		}
		return mapping.findForward("PostUpdate");
	}
	/**
	 * 更新图号/岗位
	 * */
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PostForm postForm = (PostForm) form;// TODO Auto-generated method stub
		this.getPostBiz().doUpdate(postForm.getItem());	
		return mapping.findForward("postManger");
	}
	/**
	 * 岗位工资架构修改
	 */
	public ActionForward doUpdatePost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	//	PostForm postForm = (PostForm) form;// TODO Auto-generated method stub
	//	this.getPostBiz().doUpdate(postForm.getItem());
		String[] type=request.getParameterValues("item.type");
	Float[] maxValue=StringsToFloats.toTransForm(request.getParameterValues("item.maxValue"));
	Float[] midValue=StringsToFloats.toTransForm(request.getParameterValues("item.midValue"));
	Float[] minValue=StringsToFloats.toTransForm(request.getParameterValues("item.minValue"));
	for (int i = 0; i < 4; i++) {
		TpostType item=new TpostType();
		item.setMaxValue(maxValue[i]);
		item.setMidValue(midValue[i]);
		item.setMinValue(minValue[i]);
		item.setType(type[i]);
		this.getPostTypeBiz().doUpdate(item);
		
	}
		this.getEmpBiz().updateEmpInfo(minValue);
		return mapping.findForward("bockPostType");
	}
	
	public ActionForward doSelectPostAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<TpostType>list=this.getPostTypeBiz().doSelect();	
		request.setAttribute("postType", list);
		return mapping.findForward("postType");
	}
}