package app.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.dao.CommonDAO;
import common.util.Page;

import app.biz.IPTmp;
import app.entity.IpTmp;


public class IPTmpBizImpl implements IPTmp {
private CommonDAO<IpTmp> ipdao=null;

public CommonDAO<IpTmp> getIpdao() {
	return ipdao;
}

public void setIpdao(CommonDAO<IpTmp> ipdao) {
	this.ipdao = ipdao;
}

public void doFlushInsert(List list) {
	// TODO �Զ���ɷ������
	ipdao.blukFlushInsert(list);
}

public List<IpTmp> doSelectAll(String myDate,String uid,Page page) {
	// TODO �Զ���ɷ������
	String hql="from IpTmp as ip where 1=1";	
	String countHql="select count(*) from Ip_tmp as ip where 1=1";
	Date endDate=null;
	String beginTime=null;
	if(null!=myDate&&!myDate.equals(""))
	{
		myDate=myDate+"-24";
		SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM-dd");   
			try {
				endDate=sdf.parse(myDate);
			} catch (ParseException e) {
				// TODO �Զ���ￄ1�7 catch ��
				e.printStackTrace();
			} 	
			Calendar calendar=Calendar.getInstance(); 
			calendar.setTime(endDate);
			int month=calendar.get(Calendar.MONTH)+1;			
			int year=calendar.get(Calendar.YEAR);
			if(month==1)
			{
				year--;
				month=12;
			}
			else
			{
				month--;
			}
			beginTime=year+"-"+month+"-"+25;
		
	} 
	if(null!=endDate&&!endDate.equals("")) 
	{
		hql+="and ip.date between '"+beginTime+"' and '"+myDate+"'";
		countHql+="and ip.date between '"+beginTime+"' and '"+myDate+"'";
	}
	if(null!=uid&&!uid.equals("")) 
	{
		hql+="and ip.uid='"+uid+"'";
		countHql+=" and uid='"+uid+"'";
	}
	System.out.println(countHql);
	if(page==null)
	{
		return ipdao.select(hql, null);
	}
	else
	{
		return ipdao.selectWithPage(countHql, hql, null, page);
	}
	
	//return ipdao.selectAll();
}


}
