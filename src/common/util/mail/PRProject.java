package common.util.mail;

import java.util.List;

import app.entity.epor.PrPrForm;

import common.dao.CommonDAO;
import common.entity.MyMail;

/**
 * 查询需要邮件通知
 * 项目经理
 */
public class PRProject extends DataCollectioner {
	private String sql = "select c.name,ssid,e.uid,applicant_date,e.email,remark,remark,amount from pr_prform f inner join dbo.PR_project p on f.projectid = p.id inner join dbo.tEmpolyee e on e.uid =  p.manageruid inner join dbo.tEmpolyee e2 on e2.uid =  f.applicantid where state =12 and (info in (0,5) or info is null)";
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
