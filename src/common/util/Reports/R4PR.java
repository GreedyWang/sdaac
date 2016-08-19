package common.util.Reports;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import app.common.EprExcel4Finance;

public class R4PR extends PCLPostingDate {

	@Override
	protected StringBuffer dobusiness(String condition) {
		// TODO Auto-generated method stub
		//return super.dobusiness();
		//query
		StringBuffer sql = new StringBuffer("select ssid,pf.remark,e.name as n1,t.name as n2,c.cost_center_name,p.sapno,totalCategroy,arno,prsn,total,");
		sql.append("pf.buyerid ,applicant_date,iscapital,isplan,info,acutalSupplier,");
		sql.append("isInTheSap, facilityToUse,workshopToUse,description,quantity,order_no,PODate,finishDate,bc.io,bc.remark,pf.id ");
		sql.append("from Pr_PrForm as pf "); 
		sql.append("inner join tempolyee e on e.uid = pf.applicantid ");
		sql.append("inner join pr_project p on p.id = pf.projectid "); 
		sql.append("inner join pr_costcenter c on c.id = pf.cost_centerID ");
		sql.append("inner join tdepartment t on t.id = e.departmentid ");
		sql.append("inner join dbo.PR_buyContext bc on bc.prformid = pf.id ");
		sql.append("where pf.state>=0 ");// and pf.arno in ('SDP1019','SDP0804')
		sql.append(condition);//
		sql.append("order by pf.id");
		System.out.println(sql);
		return sql;
	}



}
