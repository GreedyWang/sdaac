/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action.cw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.util.DownLoad;
import common.util.FileUpload;
import common.util.OrdinaryTxtReader;
import common.util.TxtReader;

import sdaac.wym.app.Service.cw.GmTaxManager;
import sdaac.wym.app.Service.cw.TaxManager;
import sdaac.wym.app.Service.cw.TaxReader;
import sdaac.wym.app.entity.cw.CwLogs;
import sdaac.wym.app.entity.cw.Tax;
import sdaac.wym.app.entity.cw.Tax2;
import sdaac.wym.app.entity.cw.TaxItem;
import app.entity.Logs;
import app.entity.Tuser;
import app.web.struts.action.BaseAction;

/**
 * MyEclipse Struts Creation date: 08-27-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/cw" name="cwForm" input="/form/cw.jsp"
 *                parameter="actionType" scope="request" validate="true"
 */
public class CwAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	private String uid;

	/**
	 * 财务第二版本，处理sap导出数据和金穗导出数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward processTxt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CwForm cwForm = (CwForm) form;
		//String tip= request.getParameter("tip");
		String fileName = request.getRealPath("/uploadFolder/cw")
				+ "/0804a.txt";

		FormFile file2 = cwForm.getFile2();
		TaxManager taxM = this.getGmTaxManager();
		// List<Tax> rs = null;
		List<Tax2> rs2 = null;
		try {
			// rs = taxM.getContext(file.getInputStream());
			rs2 = taxM.getContext2(file2.getInputStream());
//			System.out.print("xxxxx="+taxM.getFirstRow2().split("~~")[1]);
			String tip = "发票";//taxM.getFirstRow2().split("~~")[1];
			taxM.writeToFile(rs2, fileName, taxM.getFirstRow2(),tip);
			DownLoad dl = new DownLoad();
			dl.dLoad(request, response, fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 财务第二版本旧，处理sap导出数据和金穗导出数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward processTxtOld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CwForm cwForm = (CwForm) form;
		String fileName = request.getRealPath("/uploadFolder/cw")
				+ "/0804a.txt";
		FormFile file = cwForm.getFile();
		FormFile file2 = cwForm.getFile2();
		TaxManager taxM = this.getGmTaxManager();
		List<Tax> rs = null;
		List<Tax2> rs2 = null;
		try {
			rs = taxM.getContext(file.getInputStream());
			rs2 = taxM.getContext2(file2.getInputStream());
			taxM.writeToFileOld(rs2.get(0), fileName, rs, taxM.getFirstRow());
			DownLoad dl = new DownLoad();
			dl.dLoad(request, response, fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上传txt 显示txt文件内容
	 */
	public ActionForward showSap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CwForm cwForm = (CwForm) form;// TODO Auto-generated method stub
		Tuser user=(Tuser)request.getSession().getAttribute("logineduser");
		uid=user.getEmp().getUid();
		FormFile file = cwForm.getFile();
		//String filePath = request.getRealPath("/uploadFolder/cw");// 上传到指定的upload包中
		//String fileName = FileUpload.upLoad(file, filePath);
		TaxManager taxM = new TaxManager();
		List<Tax> rs=null;
		try {
			rs = taxM.getContext(file.getInputStream());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("firstRow", taxM.getFirstRow());
		request.getSession().setAttribute("cwrs", rs);
		return mapping.findForward("cwMainframe");
	}
	
	
	/**
	 * 合并sap发票-按照excel合并
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// upload
		CwForm cwForm = (CwForm) form;// TODO Auto-generated method stub
		Tuser user = (Tuser) request.getSession().getAttribute("logineduser");
		String fileName = request.getRealPath("/uploadFolder/cw")
				+ "/newSAP.txt";
		// uid=user.getEmp().getUid();
		String type = request.getParameter("type");
		TaxManager taxM;
		FormFile file2 = cwForm.getFile2();// 发票参照号
		if (type != null && type.equals("gm")) {
			// file2 = cwForm.getFile2();
			taxM = this.getGmTaxManager();
		} else if(type != null && type.equals("lm")){
			taxM = this.getLmTaxManager();
		}else if(type != null && type.equals("xdy")){
			taxM = this.getXdyTaxManager();
		}else if(type != null && type.equals("5500")){
			taxM = this.getPoTaxManager();
		}else if(type != null && type.equals("BS")){
			taxM = this.getBSTaxManager();
		}else if(type != null && type.equals("DY")){
			taxM = this.getDYTaxManager();
		}else {
			taxM = this.getOrdinaryTaxManager();
		}
		taxM.uid = user.getEmp().getUid();
		FormFile file = cwForm.getFile();

		List<Tax> rs = null;
		try {
			rs = taxM.getContext(file.getInputStream());
			taxM.go(file2.getInputStream(), rs,file2.getFileName());
			taxM.writeToFile2(fileName, rs, taxM.getFirstRow());
			DownLoad dl = new DownLoad();
			dl.dLoad(request, response, fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 单条记录详细信息
	 */
	public ActionForward showDetials(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String next = request.getParameter("next");
		TaxManager taxM = new TaxManager();
		int index;
		List<Tax> taxs = (List<Tax>) request.getSession().getAttribute("cwrs");
		// 通过no得到指定
		if (no != null && !no.equals("")) {
			index = taxM.searchByNo(no, taxs);
		} else {

			index = Integer.parseInt(request.getParameter("index"));
			if (next.equals("next")) {
				index++;
			} else {
				index--;
			}
		}
		Tax item = taxs.get(index);
		request.setAttribute("rs", item);
		request.setAttribute("index", new Integer[] { index, taxs.size() });
		return mapping.findForward("DetailShow");
	}

	/**
	 * 单条记录详细修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward detailUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Tax> taxs = (List<Tax>) request.getSession().getAttribute("cwrs");
		String no = request.getParameter("no");
		String remarks = request.getParameter("remarks");
		int index = Integer.parseInt(request.getParameter("index"));// 当前第几页信息
		CwForm cwForm = (CwForm) form;
		// 通过no得到指定
		TaxManager taxM = new TaxManager();
		Tax resource = taxM.getTaxByNo(no, taxs);// 原数据
		resource.setBuyer(cwForm.getTax().getBuyer());// 修改买家信息
		List<TaxItem> taxItems = new ArrayList<TaxItem>();
		resource.getSeller().setRemarks(remarks);
		// 收集修改后的taxItems
		String[] produceNames = request
				.getParameterValues("tax.taxItems.produceName");
		String[] standardTypes = request
				.getParameterValues("tax.taxItems.standardType");
		String[] units = request.getParameterValues("tax.taxItems.unit ");
		String[] quantitys = request
				.getParameterValues("tax.taxItems.quantity");
		String[] prices = request.getParameterValues("tax.taxItems.price");
		String[] sumPrices = request
				.getParameterValues("tax.taxItems.sumPrice");
		String[] faxs = request.getParameterValues("tax.taxItems.fax");
		String[] faxPrices = request
				.getParameterValues("tax.taxItems.faxPrice");
		for (int i = 0; i < produceNames.length; i++) {
			TaxItem taxItem = new TaxItem(produceNames[i], standardTypes[i],
					units[i], quantitys[i], prices[i], sumPrices[i], faxs[i],
					faxPrices[i]);
			taxItems.add(taxItem);
		}
		resource.setTaxItems(taxItems);

		this.getLogBiz().doInsert(new Logs(uid, no, "财务", "修改"));
		request.setAttribute("rs", resource);
		request.setAttribute("index", new Integer[] { index, taxs.size() });
		return mapping.findForward("DetailShow");
	}

	/**
	 * 查询，替换
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// String remark=request.getParameter("remark");//备注
		CwForm cwForm = (CwForm) form;
		Tax tax = cwForm.getTax();
		List<Tax> taxs = (List<Tax>) request.getSession().getAttribute("rs");
		TaxManager taxM = new TaxManager();
		request.setAttribute("rs", taxM.searchByCondititon(tax, taxs));
		this.getLogBiz().doInsert(new Logs(uid, tax.toString(), "财务", "修改"));
		return mapping.findForward("cwMainframe");
	}
	
	/**
	 * 清除备注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward clearRemark(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Tax> taxs = (List<Tax>) request.getSession().getAttribute("cwrs");
		TaxManager taxM = new TaxManager();
		request.setAttribute("cwrs", taxM.updateRemarktoBlank(taxs));
		this.getLogBiz().doInsert(new Logs(uid, "清空备忘", "财务", "修改"));
		return mapping.findForward("cwMainframe");
	}
	
	/**
	 * 删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Tax> taxs = (List<Tax>) request.getSession().getAttribute("cwrs");
		String[] nos = request.getParameterValues("no");		
		TaxManager taxM = new TaxManager();
		taxM.removeBulk(nos, taxs);
		//request.setAttribute("rs", taxM.updateRemarktoBlank(taxs));
		this.getLogBiz().doInsert(new Logs(uid, nos.toString(), "财务", "删除"));
		return mapping.findForward("cwMainframe");
	}
	
	/**
	 * 合并
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward Merge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CwForm cwForm = (CwForm) form;
		Tax newTax = cwForm.getTax();// 合并的新表头
		List<Tax> rs = (List<Tax>) request.getSession().getAttribute("rs");
		TaxManager taxM = new TaxManager();
		String[] nos = request.getParameterValues("no");
		taxM.Merge(nos, rs);
		// 日志
		StringBuffer sb = null;
		for (int i = 0; i < nos.length; i++) {
			sb = new StringBuffer();
			sb.append(nos[i] + ",");
		}

		this.getLogBiz().doInsert(new Logs(uid, sb.toString(), "财务", "合并"));

		request.getSession().setAttribute("rs", rs);
		return mapping.findForward("cwMainframe");
	}

	/**
	 * 查询日志
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CwForm cwForm = (CwForm) form;
		TaxManager taxManager = this.getGmTaxManager();		
		taxManager.cwlogs=new ArrayList<CwLogs>();
		List<CwLogs> rs = taxManager.selectLogs(cwForm.getCwLogs());
		if(rs!=null&&!rs.isEmpty()){
			Map<String, List<CwLogs>> map2 = new HashMap<String, List<CwLogs>>();
			for (CwLogs item : rs) {
				if (item.getJinsuiNo() != null) {
					map2.put(item.getMergeTaxNo(), new ArrayList<CwLogs>());
				}
			}
	
			for (String key : map2.keySet()) {
				CwLogs sumLog = new CwLogs();
				sumLog.setTaxno("合计");
				float a = 0.00f;
				for (CwLogs item : rs) {
					if (key.equals(item.getMergeTaxNo())) {
						map2.get(key).add(item);
						a += item.getMoney();
					}
				}
				sumLog.setMoney(a);
				BigDecimal b = new BigDecimal(a / 1.17
						- map2.get(key).get(0).getJinsuiMoney());
				java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
				String temp = df.format(b.setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue());
				sumLog.setCustomerNo(temp);
				map2.get(key).add(sumLog);
			}
	
			request.setAttribute("map", map2);
		}
		return mapping.findForward("logs");
	}

	/**
	 * 下载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward Download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Tax> rs = (List<Tax>) request.getSession().getAttribute("cwrs");
		String firstRow = (String) request.getSession()
				.getAttribute("firstRow");
		String fileName = request.getRealPath("/uploadFolder/cw")
				+ "/0804a.txt";
		TaxManager taxM = new TaxManager();
		taxM.writeToFile2(fileName, rs, firstRow);
		DownLoad dl = new DownLoad();
		dl.dLoad(request, response, fileName);
		return null;
	}

	/**
	 * 删除日志
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deldeteLogs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String mergeTaxNo = request.getParameter("mergeTaxNo");
		TaxManager taxM = this.getGmTaxManager();
		taxM.delete(mergeTaxNo);
		return mapping.findForward("logs");
	}
	
	/**
	 * 回馈新的金穗号日志
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward feedbackLogs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CwForm cwForm = (CwForm) form;
		this.getGmTaxManager().feedback(cwForm.getCwLogs());
		
		return mapping.findForward("logs");
	}
	
	
	/**
	 * 回馈新的金穗号日志
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showFeedbackLogs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CwForm cwForm = (CwForm) form;
		CwLogs cwLogs = cwForm.getCwLogs();
		String jinisuiNo = request.getParameter("feedbackNo");
		if (!jinisuiNo.equals("")) {
			cwLogs.setJinsuiNo(jinisuiNo);
			List<CwLogs> rs = this.getGmTaxManager().selectLogs(cwLogs);
			// Map<CwLogs, List<CwLogs>> map = new HashMap<CwLogs,
			// List<CwLogs>>();
			Map<String, List<CwLogs>> map2 = new HashMap<String, List<CwLogs>>();
			for (CwLogs item : rs) {
				if (item.getJinsuiNo() != null) {
					map2.put(item.getMergeTaxNo(), new ArrayList<CwLogs>());
				}
			}

			for (String key : map2.keySet()) {
				CwLogs sumLog = new CwLogs();
				sumLog.setTaxno("合计");
				float a = 0.00f;
				for (CwLogs item : rs) {
					if (key.equals(item.getMergeTaxNo())) {
						map2.get(key).add(item);
						a += item.getMoney();
					}
				}
				sumLog.setMoney(a);
				BigDecimal b = new BigDecimal(a / 1.17
						- map2.get(key).get(0).getJinsuiMoney());
				java.text.DecimalFormat df = new java.text.DecimalFormat(
						"#0.00");
				String temp = df.format(b.setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue());
				sumLog.setCustomerNo(temp);
				map2.get(key).add(sumLog);
			}

			request.setAttribute("map", map2);
		}
		return mapping.findForward("logs");
	}
	
	/**
	 * 批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward batchDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("cb");
		for(String id : ids) {
			TaxManager taxM = this.getGmTaxManager();
			taxM.delete(id);
		//	System.out.println(id);
		}
		return mapping.findForward("logs");
	}
}