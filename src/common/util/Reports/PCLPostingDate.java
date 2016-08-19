package common.util.Reports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import app.biz.epor.PrBuyContextBiz;
import app.biz.impl.epor.PrFormBizImpl;
import app.common.EprExcel4Finance;
import common.util.download.UploadFile;
import common.util.io.ExcelReader2007;
import common.util.io.PostingDateExcelReader;
import common.util.io.PostingDateExcelReaderQin;

public class PCLPostingDate {
	protected ExcelReader2007 reader = new PostingDateExcelReaderQin();
	protected PrBuyContextBiz	buyContextBiz;
	protected PrFormBizImpl prFormBiz;
	protected String REPORT_NAME = "c:\\prDownload41.xls";
	protected UploadFile dl = new UploadFile();
	public PrFormBizImpl getPrFormBiz() {
		return prFormBiz;
	}
	public void setPrFormBiz(PrFormBizImpl prFormBiz) {
		this.prFormBiz = prFormBiz;
	}
	public PrBuyContextBiz getBuyContextBiz() {
		return buyContextBiz;
	}
	public void setBuyContextBiz(PrBuyContextBiz buyContextBiz) {
		this.buyContextBiz = buyContextBiz;
	}
	
	protected StringBuffer dobusiness(InputStream in,String fileName){
		//read
		List<ArrayList<String>> rs = reader.readByIos(in, fileName);
		//List<String> fids =new ArrayList<String>();//id号 用于查询
		String fids = "'a'";
		//update
		for(ArrayList<String> item : rs){
			String fid = buyContextBiz.doUpdateWithCheck(item.get(0), item.get(1));
			if(fid!=null && !fid.equals("")){
				fids+=",'"+fid+"'";
			}
		}
		//query
		StringBuffer sql = new StringBuffer("select ssid,pf.remark,e.name as n1,t.name as n2,c.remark as ccid,p.sapno,totalCategroy,pf.arno,prsn,total,");
		sql.append("pf.buyerid ,applicant_date,iscapital,isplan,info,acutalSupplier,");
		sql.append("isInTheSap, facilityToUse,workshopToUse,description,quantity,order_no,PODate,finishDate,bc.io,bc.remark,pf.id ");
		sql.append("from Pr_PrForm as pf "); 
		sql.append("inner join tempolyee e on e.uid = pf.applicantid ");
		sql.append("inner join pr_project p on p.id = pf.projectid "); 
		sql.append("inner join pr_costcenter c on c.id = pf.cost_centerID ");
		sql.append("inner join tdepartment t on t.id = e.departmentid ");
		sql.append("inner join dbo.PR_buyContext bc on bc.prformid = pf.id ");
		sql.append("where pf.source = 1 and pf.id in (");
		sql.append(fids);
		sql.append(")");
		sql.append("order by pf.id");
		return sql;
	}
	
	public void getData(InputStream in,String fileName,HttpServletResponse response) throws Exception{
		StringBuffer sql = null;
		if(fileName ==null){
			 sql = dobusiness("");
		}else{
			 sql = dobusiness(in, fileName);
		}		
		doend(response, sql);
	}
	
	public void getData(InputStream in,String fileName,HttpServletResponse response,String condition) throws Exception{
		StringBuffer sql = null;
		if(fileName ==null){
			 sql = dobusiness(condition);
		}else{
			 sql = dobusiness(in, fileName);
		}	
		System.out.println("=================");
		System.out.println(sql);
		doend(response, sql);
	}
	
	protected StringBuffer dobusiness(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	protected void doend(HttpServletResponse response,StringBuffer sql) throws Exception{
		List<Object[]> rs2 = prFormBiz.doSelectBySQL(sql.toString());
		//4Excel
		EprExcel4Finance excel = new EprExcel4Finance(REPORT_NAME);
		excel.writeByArray(rs2);
		//download
		dl.getFile(response, REPORT_NAME);
	}
}
