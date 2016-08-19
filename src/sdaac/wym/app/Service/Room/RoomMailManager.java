package sdaac.wym.app.Service.Room;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.entity.MyMail;

public class RoomMailManager {
	private RoomManager rm ;
	
	public List<MyMail> doSelectToMailTip() {
		Form item = new Form();
		List<MyMail> mailList = new ArrayList();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
			item.setBegintime(new Date());
		
		Calendar ca = Calendar.getInstance();
		ca.setTime(item.getBegintime());
		ca.add(Calendar.MINUTE,30);
		item.setEndtime(ca.getTime());
		List<Form> rs = rm.doShow(item,2);
		if(rs.size()<1) {
			return mailList;
		}
		
		String context = "<tr><td>申请人姓名</td><td>申请人部门</td><td>概述</td><td>联系方式</td><td>会议室</td><td>时间</td><td>备注</td></tr>";
		for(Form form : rs) {
			
			context +="<tr><td>" +form.getEmp().getName()+"</td><td>"+form.getEmp().getTdepartment().getName()+"</td><td>"+form.getContext()+"</td><td>"+form.getContact()+"</td><td>"+form.getRoomId().getName()+"</td><td>"+form.getBegintime()+" - "+form.getEndtime()+ "</td><td>"+form.getRemark();
			
		}

		MyMail mail = new MyMail("wang.yongmin@sdaac.com", "会议室预定邮件提示"
				, context, 0);
		mailList.add(mail);
		return mailList;
	}

	public RoomManager getRm() {
		return rm;
	}

	public void setRm(RoomManager rm) {
		this.rm = rm;
	}
}
