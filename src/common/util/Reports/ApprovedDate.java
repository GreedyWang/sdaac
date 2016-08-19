package common.util.Reports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import app.biz.epor.PrApprovedFormBiz;
import app.biz.epor.PrBuyContextBiz;
import app.biz.impl.epor.PrApprovedFormBizImpl;
import app.biz.impl.epor.PrFormBizImpl;
import app.common.EprExcel4Approve;
import app.common.EprExcel4Finance;

import common.util.download.UploadFile;
import common.util.io.ExcelReader2007;
import common.util.io.PostingDateExcelReaderQin;

public class ApprovedDate extends PCLPostingDate{

	protected ExcelReader2007 reader = new PostingDateExcelReaderQin();
	//protected PrBuyContextBiz	buyContextBiz;
	//protected PrFormBizImpl prFormBiz;
	protected PrApprovedFormBizImpl approveBiz;
	protected String REPORT_NAME = "c:\\prDownload41.xls";
	protected UploadFile dl = new UploadFile();

	

	
	
	protected StringBuffer dobusiness(String condition) {
		// TODO Auto-generated method stub
		// create SQL
		StringBuffer sql = new StringBuffer("select prformid,e.name,datetime,applicant_date,a.type,datetime-applicant_date as det from dbo.PR_approvedForm a ");
		sql.append("inner join dbo.tEmpolyee e on e.uid = a.approvedID ");
		sql.append("inner join dbo.pr_prform f on f.id = a.prformid ");
		sql.append("where 1=1 "+condition);
		sql.append("order by f.id,datetime");

//--where applicant_date = ''

		return sql;
	}
	protected void doend(HttpServletResponse response,StringBuffer sql) throws Exception{
		List<Object[]> rs2 = approveBiz.getApprovedList(sql.toString());
		//4Excel
		EprExcel4Approve excel = new EprExcel4Approve(REPORT_NAME);
		excel.writeByArray(rs2);
		//download
		dl.getFile(response, REPORT_NAME);
	}

	public PrApprovedFormBizImpl getApproveBiz() {
		return approveBiz;
	}

	public void setApproveBiz(PrApprovedFormBizImpl approveBiz) {
		this.approveBiz = approveBiz;
	}


}
