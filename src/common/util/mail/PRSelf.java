package common.util.mail;

import java.util.List;

import app.entity.epor.PrPrForm;

import common.dao.CommonDAO;
import common.entity.MyMail;

public class PRSelf extends DataCollectioner {

	@Override
	protected List<MyMail> doSelectToMailTip() {
		// TODO Auto-generated method stub
		List<Object[]> rs = prFormDao.selectBySql(sql);
		dealData(rs);		
		return mailList;
	}

	
	private CommonDAO<PrPrForm> prFormDao;
//	private String sql = "select e.name,ssid,applicant_date,t.pmangerid,e2.email,remark,remark,total from dbo.PR_prForm p inner join dbo.tEmpolyee e on p.applicantid = e.uid inner join dbo.tDepartment t on e.departmentid = t.id inner join dbo.tEmpolyee e2 on t.PRManager = e2.uid where state = 1 and (info in (0,5) or info is null)";
	private String sql = "select e.name,ssid,applicant_date,uid,e.email,remark,remark,total from dbo.PR_prForm p inner join dbo.tEmpolyee e on p.applicantid = e.uid  where state >0 and info in (1,-1)";
//	public List<MyMail> doSelectToMailTip() {						
//		List<Object[]> rs = prFormDao.selectBySql(sql);
//		dealData(rs);		
//		return mailList;
//	}		

	public CommonDAO<PrPrForm> getPrFormDao() {
		return prFormDao;
	}

	public void setPrFormDao(CommonDAO<PrPrForm> prFormDao) {
		this.prFormDao = prFormDao;
	}
}
