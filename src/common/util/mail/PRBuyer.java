package common.util.mail;

import java.util.List;

import app.entity.epor.PrPrForm;

import common.dao.CommonDAO;
import common.entity.MyMail;

/**
 * 查询需要邮件通知
 * 采购员
 */
public class PRBuyer extends DataCollectioner {
	private String sql = "select c.name,ssid,applicant_date,e.uid,e.email,remark,remark,total from dbo.PR_prForm p inner join dbo.PR_buyer b on b.buyID = SUBSTRING ( p.buyerID , 0 , 7 ) inner join dbo.tEmpolyee e on b.uid =  e.uid  inner join dbo.tEmpolyee c on p.applicantid = c.uid where b.area = e.area and state in (4,10) and (info in (0,5) or info is null) ";
	private CommonDAO<PrPrForm> prFormDao;
	
	public CommonDAO<PrPrForm> getPrFormDao() {
		return prFormDao;
	}

	public void setPrFormDao(CommonDAO<PrPrForm> prFormDao) {
		this.prFormDao = prFormDao;
	}

	public List<MyMail> doSelectToMailTip() {						
		List<Object[]> rs = prFormDao.selectBySql(sql);
		dealDataBuyer(rs);		
		return mailList;
	}	
}
