/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sdaac.wym.app.Service.vave.PointsDetailsManager;
import sdaac.wym.app.entity.vave.PointsDetails;

import common.entity.MyMail;
import common.util.MyUtil;

import app.entity.Tproposal;
import app.entity.vave.VaveCompanySuggestion;
import app.entity.vave.VaveProposalStateId;
import app.entity.vave.VaveTeamWork;
import app.entity.vave.VaveTeamWorkProject;
import app.web.struts.form.CompanySuggestionForm;

/** 
 * MyEclipse Struts
 * Creation date: 03-19-2009
 * 
 * XDoclet definition:
 * @struts.action path="/companySuggestion" name="companySuggestionForm" input="/form/companySuggestion.jsp" parameter="actionType" scope="request" validate="true"
 */
public class CompanySuggestionAction extends BaseAction {

	/*
	 * 公司审批
	 */
	public ActionForward doInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CompanySuggestionForm companySuggestionForm = (CompanySuggestionForm) form;// TODO Auto-generated method stub
		VaveTeamWork teamWork=companySuggestionForm.getTeamWork();
		VaveCompanySuggestion companySug=companySuggestionForm.getCompanySug();
		List<VaveTeamWorkProject> teamWorkProject=companySuggestionForm.getTeamWorkProject();
		String implemenets=request.getParameter("implements");
		String expectSaving=request.getParameter("expectSaving");
		Date expectFinishDate=MyUtil.formatDate(request.getParameter("expectFinishDate"));
		teamWork.setEstimatedCostSavings(Float.parseFloat(expectSaving));//预计节约金额
		teamWork.setMyPlanfinishtime(expectFinishDate);
		companySug.setSuggestionDate(new Date());
		Tproposal tp=companySug.getTproposal();
		if(companySug.getSuggestionType().equals("推荐实施"))
		{
			tp.setState(Tproposal.BEGIN_PROJECT);
			//insert TeamWork&Project			
			this.getProposalBiz().doUpdateState(tp);
			this.getTeamWorkBiz().doInsert(teamWork);
			for(VaveTeamWorkProject item:teamWorkProject)
			{
				item.setVaveTeamWork(teamWork);
				this.getTeamWorkProjectBiz().doInsert(item);
				//添加mail列表
				MyMail mail=new MyMail(teamWork.getProjectManager().getUid(),"vave新项目建立",tp.getId()+"通过公司审批,建立项目",0);				
				this.getMailManager().addMailList(mail);
			}
			//添加积分	
			Tproposal proposal = getProposalBiz().doSelectByPK(tp.getId());
			String toSb;//节约金额
			if(proposal.getSource().equals("公司")){
				toSb=proposal.getCollectionPersion().getUid();
			}else{
				toSb=proposal.getSupply().getSimpleName();
			}
			getPointsDetailsManager().add(new PointsDetails(toSb,Float.parseFloat(expectSaving),Calendar.getInstance().get(Calendar.YEAR) ));
		}
		if(companySug.getSuggestionType().equals("合理化建议"))
		{
			tp.setState(Tproposal.RETIONAL);
			this.getProposalBiz().doUpdateState(tp);
		}
		if(companySug.getSuggestionType().equals("不予推荐"))
		{
			tp.setState(Tproposal.SELF);
			this.getProposalBiz().doUpdateState(tp);
		}
		if(companySug.getSuggestionType().equals("需完善"))
		{
			tp.setState(Tproposal.DEPARTMENT);
			//删除（用户-提案表）
			this.getProposalBiz().doUpdateState(tp);
			String[] targets=implemenets.split(",");
			for(int i=0;i<targets.length;i++)
			{
				//String mamangerID=this.getDepartBiz().doSelectManagerByDepartID(Integer.parseInt(targets[i])).getPmangerid();
				String[] vaves=this.getDepartBiz().doSelectVaves(Integer.parseInt(targets[i]));
				for(int j=0;j<vaves.length;j++)
				{
					this.getProposalStateBiz().doDelete(new VaveProposalStateId(tp,vaves[j]));
				}
				
			}
			//更新isOpen
			this.doUpdateProposalState(tp);
			//this.getProposalStateBiz().doUpateOpen(tp.getId(), ownerID,0);
		}
		/**
		 * insert companySuggestion
		 */
		this.getCompanySugBiz().doInsert(companySug);	
		//更新提案表详细信息
		 String[] imValue = request.getParameterValues("imvalue"); 
		 tp.setImValue(imValue);
		String[] quantitys=request.getParameterValues("quantity");
		tp.setPp(Float.parseFloat(quantitys[0]));
		tp.setAnnualConsumption(quantitys[2]);
		tp.setShareValue(quantitys[6]);
		this.getProposalBiz().updateMoreInfo(tp);
		
		if(expectSaving!=null&&!expectSaving.equals(""))
		{
			this.doInsertSchedule(request, response, tp.getId());
			
		}
		return mapping.findForward("SearchAllCompany");
	}
}