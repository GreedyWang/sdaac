package common.util.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.entity.MyMail;
import common.entity.TuserRole;

/**
 * 收集需要发送的Mail数据
 * @author SA1KV5
 *
 */
public abstract class DataCollectioner {
	
	private boolean checkIsNeed(String type){
		return true;
	}
	
	protected List<MyMail> mailList;
	protected Map<String, MyMail> mailMap;
	private String style1 = "style='background-color: #0087cc;font-size: 24px;color: white;font-family: Arial'";
	private String style2 = "style='background-color: #C0C0C0;font-family: Arial'";
	
	public DataCollectioner(){

	}
	
	/**
	 * 处理查询结果，给数据加上HTML格式
	 * SSID = 1
	 * 申请人姓名 = 0
	 * 日期 = 2
	 * remark = 6
	 * amount = 7
	 * @param item
	 * @return
	 */
	private String dealDataWithTitle(Object[] item,int index){
		index = index % 2;
		String remark = item[6]==null?"":item[6].toString();		
		StringBuffer context = new StringBuffer("<tr "+style1+"><td>流水号/SSID</td><td>申请人/Applicant</td><td>申请日期/Application date</td><td>描述/Description</td><td>总价/Amount(RMB)</td></tr>");;
		context = context.append("<tr "+style2+"><td>");	
		context.append(getSapNO(Integer.parseInt(item[1].toString())));
		context.append("</td><td>" + item[0].toString() + "</td><td>");
		context.append(item[2].toString() + "</td><td>"+remark+"</td><td>"+item[7]+"</td></tr>");	
		return context.toString();
	}
	
	/**
	 * 处理查询结果，给数据加上HTML格式
	 * @param item
	 * @return
	 */
	private String dealDataWithoutTitle(Object[] item,int index){
		index = index % 2;
		String remark = item[6]==null?"":item[6].toString();				
		StringBuffer context = new StringBuffer();
		//if(index == 0){
		//	context = context.append("<tr><td>");	
		//}else{
			context = context.append("<tr "+style2+"><td>");
	//	}		 				
		context.append(getSapNO(Integer.parseInt(item[1].toString())));
		context.append("</td><td>" + item[0].toString() + "</td><td>");
		context.append(item[2].toString() + "</td><td>"+remark+"</td><td>"+item[7]+"</td>");
		if(item[8]!=null){
			context.append("<td>"+item[8].toString() + "</td>");
			if(item[9] != null && item[9].equals("1")){
				context.append("<td>"+item[8].toString() + "</td><td>费用化</td>");
			}
			if(item[9] != null && item[9].equals("0")){
				context.append("<td>"+item[8].toString() + "</td><td>资本化</td>");
			}
		}
			context.append("</tr>");
		
				
		return context.toString();
	}
	
	/**
	 * all
	 * @param item
	 * @param useRole
	 * @param index
	 */
	protected void dealData(Object[] item,TuserRole useRole,int index){
		
		if (mailMap.get(useRole.getTempolyee().getUid()) == null) {
			
			String context = dealDataWithTitle(item,index);
			
			
			
			if(useRole.getTempolyee()!=null){	
				System.out.print("===>"+useRole.getTempolyee().getUid());
				System.out.print("===>"+useRole.getTempolyee().getMail());
				System.out.println("===>"+useRole.getTempolyee().getEmailcc());
				MyMail mail = new MyMail(useRole.getTempolyee().getMail(),useRole.getTempolyee().getEmailcc(), "ePR邮件提示"
						+ useRole.getTempolyee().getUid(), context, 0);
				mailMap.put(useRole.getTempolyee().getUid(), mail);
			}
		} else {
			MyMail mail = mailMap.get(useRole.getTempolyee().getUid());
			String context = mail.getContext();
			context += dealDataWithoutTitle(item,index);
			mail.setContext(context);
		}
	}

	
	/**
	 * Depart
	 * @param rs
	 */
	protected void dealData(List<Object[]> rs){

		int index = 0;
		for (Object[] item : rs) {
			
			if (mailMap.get(item[3].toString()) == null) {
				String context = dealDataWithTitle(item,index);
				if (item[4] != null) {
					MyMail mail = new MyMail(item[4].toString(), "ePR邮件提示"
							+ item[3].toString(), context, 0);
					mailMap.put(item[3].toString(), mail);
				}
			} else {
				MyMail mail = mailMap.get(item[3].toString());
				String context = mail.getContext();
				context += dealDataWithoutTitle(item,index);
				mail.setContext(context);
			}
			index++;
		}
	}
	
	/**
	 * Buyer
	 * @param rs
	 */
	protected void dealDataBuyer(List<Object[]> rs){

		int index = 0;
		for (Object[] item : rs) {
			
			if (mailMap.get(item[3].toString()) == null) {
				String context = dealDataWithTitle(item,index);
				if (item[4] != null) {
					MyMail mail = new MyMail(item[4].toString(), "ePR邮件提示"
							+ item[3].toString(), context, 0);
					mailMap.put(item[3].toString(), mail);
				}
			} else {
				MyMail mail = mailMap.get(item[3].toString());
				String context = mail.getContext();
				context += dealDataWithoutTitle(item,index);
				mail.setContext(context);
			}
			index++;
		}
	}
	
	private void doEnd(){
		for (String key : mailMap.keySet()) {
			mailList.add(mailMap.get(key));
		}
	}
	//业务方法给子类实现
	protected abstract List<MyMail> doSelectToMailTip();
	
	public List<MyMail> doALL(){
		mailList = new ArrayList<MyMail>();
		mailMap = new HashMap<String, MyMail>();
		doSelectToMailTip();
		doEnd();
		return mailList;
	}
	/**
	 * 得到ssid
	 * @param ssid
	 * @return
	 */
	private int getSapNO(int ssid) {
		int newSsid = ssid;
		int tenYearTotal = 963;
		int elYearTotal = 5273;
		int TWYearTotal = 9775;	
		if(ssid >= TWYearTotal){
			newSsid = ssid - TWYearTotal;
			return 13 * 100000000 + newSsid;
		}else if(ssid >= elYearTotal){
			newSsid = ssid - elYearTotal;
			return 12 * 100000000 + newSsid;
		}else if(ssid >= tenYearTotal){
			newSsid = ssid - tenYearTotal;
			return 11 * 100000000 + newSsid;
		}else{
			return 10 * 100000000 + newSsid;
		}
	}


}
