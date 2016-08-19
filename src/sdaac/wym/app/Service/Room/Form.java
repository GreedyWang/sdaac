package sdaac.wym.app.Service.Room;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.entity.Tempolyee;

/**
 * 实体类：登记表
 * @author SA1KV5
 *
 */
public class Form implements Cloneable {

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	private Tempolyee emp = new Tempolyee(); //工号
	private Integer id;
	private Room roomId = new Room();
	private String context;
	private String contact;
	private Date begintime;
	private String begin;
	private Date endtime;
	private String end;
	private Integer cycleType;//[{id:0,name:'单次'},{id:1,name:'周循环'},{id:2,name:'月循环'}]	
	private Integer accessoryid;
	private Integer phoneid;
	private Integer projector;
	private String remark;
	private String fileName;
	private Integer state;
	private Integer cycleTypeInfo;
	public static int delState = -2;
	public static int cycleTypeMonthly = 1;
	public static int cycleTypeWeekly = 2;
	public static int waittingForApprove = 1;
	public static int normal = 2;
	public static int self = 0;
	public static int notApprove = -1;
	
	//----view help----
	private String cycleTypeName;
	private String phoneidName;
	private String projectorName;
	private String stateName;
	

	public String getCycleTypeName() {
		//[{id:0,name:'单次'},{id:1,name:'周循环'},{id:2,name:'月循环'}]	
		if(state == 0) {
			return "个人状态";
		}else if(state == 1) {
			return "待审批";
		}else if(state == 2){
			return "正常";
		}else if(state == -1) {
			return "不批准";
		}else {
			return "";
		}
	}
	
	public String getStateName() {
		//[{id:0,name:'单次'},{id:1,name:'周循环'},{id:2,name:'月循环'}]	
		if(cycleType == 0) {
			return "单次";
		}else if(cycleType == 1) {
			return "周循环";
		}else if(cycleType == 2){
			return "月循环";
		}else {
			return "";
		}
	}
	
	public String getPhoneidName() {
		if(phoneid == null ||phoneid == 0) {
			return "不需要";
		}else if(phoneid == 1) {
			return "需要";
		}else {
			return "";
		}
	}

	public String getProjectorName() {
		if(projector == null || projector == 0) {
			return "不需要";
		}else if(projector == 1) {
			return "需要";
		}else {
			return "";
		}
	}
	
	public Tempolyee getEmp() {
		return emp;
	}

	public void setEmp(Tempolyee emp) {
		this.emp = emp;
	}

	/** default constructor */
	public Form() {
	}
		
	public Form(Integer id ) {
		 this.id = id;
	}
	
	/** full constructor */
	public Form(Room roomId, String context, Date begintime,
			Date endtime, Integer cycleType, Integer accessoryid,
			Integer phoneid, String remark, String fileName, String uid) {
		this.roomId = roomId;
		this.context = context;
		this.begintime = begintime;
		this.endtime = endtime;
		this.cycleType = cycleType;
		this.accessoryid = accessoryid;
		this.phoneid = phoneid;
		this.remark = remark;
		this.fileName = fileName;
		
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Room getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getCycleType() {
		return this.cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}

	public Integer getAccessoryid() {
		return this.accessoryid;
	}

	public void setAccessoryid(Integer accessoryid) {
		this.accessoryid = accessoryid;
	}

	public Integer getPhoneid() {
		return this.phoneid;
	}

	public void setPhoneid(Integer phoneid) {
		this.phoneid = phoneid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getProjector() {
		return projector;
	}

	public void setProjector(Integer projector) {
		this.projector = projector;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * 设置开始结束日期
	 * @throws ParseException 
	 */
	public void addTime(String begintime,String endTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.begintime = df.parse(begintime);
			this.endtime = df.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 月循环
	 * @param item
	 * @return
	*/
	public List<Form> getOtherFormAfterMonthly(Form item ) {
		List<Form> rs = new ArrayList<Form>();
		Date begin = item.getBegintime();
		Date end = item.getEndtime();
		int i = 0;
		while(rs.size()<4) {
			
//		}
//		for(int i = 0 ;i <6;i++) {
			Form temp = null;
			try {
				temp = (Form)item.clone();
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar ca = Calendar.getInstance();
			ca.setTime(begin);
			if(i > 0) { 
				int a = ca.get(ca.MONTH);
				int week = ca.get(ca.DAY_OF_WEEK_IN_MONTH);
//				System.out.println(week);
				ca.add(Calendar.DAY_OF_MONTH, 28);
				while( week !=ca.get(ca.DAY_OF_WEEK_IN_MONTH) ) {
					ca.add(Calendar.DAY_OF_MONTH, 7);
//					System.out.println("XXXXXXX");
				}
			}
			temp.setBegintime(ca.getTime());
//			System.out.println("XXXXXXX"+ca.getTime());
			begin = ca.getTime();
			
			ca.setTime(end);
			if(i > 0) { 
				int a = ca.get(ca.MONTH);
				int week = ca.get(ca.DAY_OF_WEEK_IN_MONTH);
				ca.add(Calendar.DAY_OF_MONTH, 28);
				while( week !=ca.get(ca.DAY_OF_WEEK_IN_MONTH) ) {
					ca.add(Calendar.DAY_OF_MONTH, 7);
				}
			}
			temp.setEndtime(ca.getTime());
			end = ca.getTime();
//			System.out.println(temp.getBegintime().after(new Date()));
			if(temp.getBegintime().after(new Date())) {
				
				rs.add(temp);
			}
			i++;
		}
		return rs;
	}
	
	
	/**
	 * 月循环
	 * @param item
	 * @return
	
	public List<Form> getOtherFormAfterMonthly(Form item ) {
		List<Form> rs = new ArrayList<Form>();
		for(int i = 0 ;i <2;i++) {
			Form temp = null;
			try {
				temp = (Form)item.clone();
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar ca = Calendar.getInstance();
			ca.setTime(item.getBegintime());
			ca.get(ca.DAY_OF_MONTH);//几号
			ca.set(ca.get(ca.YEAR), ca.MONTH+i, ca.get(ca.DAY_OF_MONTH));//得到当前月份的第一天
			temp.setBegintime(ca.getTime());
			ca.setTime(item.getEndtime());
			ca.set(ca.get(ca.YEAR), ca.MONTH+i, ca.get(ca.DAY_OF_MONTH));//得到当前月份的第一天
			temp.setEndtime(ca.getTime());
			rs.add(temp);
		}
		return rs;
	}
	 */
	public List<Form> getOtherFormAfterWeekly(Form item ) {
		List<Form> rs = new ArrayList<Form>();
		int i = 5;
		while(rs.size()<7) {
//		for(int i = 5;i<=12;i++) {
			Form temp = null;
			try {
				temp = (Form)item.clone();
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp.setBegintime(getToWeekly(item.getBegintime(),i));
			temp.setEndtime(getToWeekly(item.getEndtime(),i));
			if(temp.getBegintime().after(new Date())) {
				
				rs.add(temp);
			}		
			i++;
		}
		return rs;
	}
	
	private Date getToWeekly(Date item,int i){
		Calendar ca = Calendar.getInstance();
		//System.out.println(ca.MONTH+"i="+i);
		ca.setTime(item);
		int a = ca.get(ca.DAY_OF_WEEK);//得到星期几				
		ca.set(ca.get(ca.YEAR), ca.MONTH-1, 1);//得到当前月份的第一天
		//System.out.print(ca.getTime());
		//得到当前月份的第一天是星期几,再做相减
		ca.add(Calendar.DAY_OF_MONTH, a>ca.get(ca.DAY_OF_WEEK)?a-ca.get(ca.DAY_OF_WEEK):7-ca.get(ca.DAY_OF_WEEK)+a);
		ca.add(Calendar.DAY_OF_MONTH, i*7);
		
		return ca.getTime();
		
	} 
	
	  public static void main(String[] args) {
			Calendar ca = Calendar.getInstance();
			Calendar ca2 =Calendar.getInstance();
//			ca.setTime(new Date(2011,1,6));
			ca.set(2008,3, 19,9,0,0);//得到当前月份的第一天
			System.out.println(ca.getTime());
			ca2.set(2008,0,4,11,15,0);
//			int a = ca.get(ca.DAY_OF_WEEK);
//			System.out.print(ca.get(ca.DAY_OF_WEEK) );//得到星期几
//			System.out.println(ca.getTime() );
//			ca.set(ca.YEAR, ca.MONTH, 1);//得到当前月份的第一天
//			System.out.print(ca.get(ca.DAY_OF_WEEK) );//得到当前月份的第一天是星期几
//			ca.add(Calendar.DAY_OF_MONTH, a-ca.get(ca.DAY_OF_WEEK));
//			System.out.print(ca.getTime() );
//			Calendar ca2 =Calendar.getInstance();
//			ca2.set(2011,0,4,11,15,0);
//			System.out.print(ca.compareTo(ca2));
			Form item = new Form();
			item.setBegintime(ca.getTime());
			item.setEndtime(ca2.getTime());
			List<Form> rs = item.getOtherFormAfterWeekly(item);
			for(Form item2:rs){
				System.out.println(item2.getBegintime());
//				System.out.println(item2.getEndtime());
				System.out.println("---------------");
			}
//		  Calendar ca = Calendar.getInstance();
//		  ca.setTime(new Date());
//		  System.out.println(ca.get(ca.YEAR));
	  }

	public Integer getCycleTypeInfo() {
		return cycleTypeInfo;
	}

	public void setCycleTypeInfo(Integer cycleTypeInfo) {
		this.cycleTypeInfo = cycleTypeInfo;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
