package sdaac.wym.app.Service.Room;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

import common.util.download.UploadFile;


/**
 * 业务类：会议室管理类
 * @author SA1KV5
 *
 */
public class RoomManager {
	
	private FormBiz formBiz;
	private AccessoryBiz accBiz;
	private FormCancelBiz formCancelBiz;
	private String uploadUrl = "D:\\Apache Software Foundation\\Tomcat 6.0\\webapps\\uploadFolder\\room";
	/**
	 * 登记房间
	 * @param item
	 */
	public String doCheckIn( Form item,FormFile file) { 
		if(doIsInUse(item)) {
			int flag = doCheckAccessory(item);
			if(flag == 1) return "没有可提供的电话机";
			if(flag == 2) return "没有可以供的投影仪";
			
			if(file.getFileSize()>0) {//如果有附件
				String filePath =uploadUrl;// request.getRealPath("/uploadFolder");//上传到指定的upload包中			
				UploadFile uf = new UploadFile();
				item.setFileName(uf.uFile(file, filePath, item.getId()+""));
				item.setState(Form.waittingForApprove);
			}
			if(item.getCycleType()!=null && item.getCycleType() == 1) {
				Calendar ca = Calendar.getInstance();
				Date s = item.getBegintime();
				ca.setTime(s);
				item.setCycleTypeInfo(ca.get(ca.DAY_OF_WEEK));
			}else if(item.getCycleType()!=null && item.getCycleType() == 2) {
				Calendar ca = Calendar.getInstance();
				Date s = item.getBegintime();
				ca.setTime(s);						
				item.setCycleTypeInfo(ca.get(ca.DAY_OF_MONTH));
			
			}
			formBiz.doInsert(item);
			//更新配件状态
			//accBiz.doUpdateState(item);
			return "预定成功";
		}else {
			return "该会议室已经有人登记";
		}
		
	}
	
	/**
	 * 检测是否可用,在某时段内,指定会议室是否可用
	 * @param item
	 * @return
	 */
	private boolean doIsInUse(Form item) {
		Form param = new Form();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			param.setBegintime(df.parse(df.format(item.getBegintime())));
			Calendar ca = Calendar.getInstance();
			ca.setTime(param.getBegintime());
			ca.add(Calendar.DAY_OF_YEAR, 1);
			param.setEndtime(ca.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		param.setBegintime(item.getBegintime());
//		param.setEndtime(item.getEndtime());
		param.setRoomId(item.getRoomId());
		for(Form aa : doShow(param)){
			System.out.println(aa.getBegintime()+"..."+item.getBegintime()+"..."+item.getEndtime()+"..."+(aa.getBegintime().after(item.getBegintime()) && aa.getBegintime().before(item.getEndtime())) );
			if(aa.getRoomId().getId()-item.getRoomId().getId()==0 && 
					((aa.getBegintime().before(item.getBegintime()) && aa.getEndtime().after(item.getBegintime())) 
					|| (!aa.getBegintime().before(item.getBegintime()) && aa.getBegintime().before(item.getEndtime()))) ) {
				System.out.print("XXXXXXXXX="+aa.getId());
				return false;
			}
		}
	
//		if(this.doShow(item).isEmpty()){
			return true;
//		}else {
//			return false;
//		}
		
	}
	
	/**
	 * 查询是否有配件
	 * @param item
	 * @return
	 */
	private int doCheckAccessory(Form item) {
//		if(item.getPhoneid()!=null && item.getPhoneid()==1) {
//			if(accBiz.doSelectAvailableByType(Accessory.types[0]).isEmpty()) {
//				return 1;//"没有可提供的电话机";
//			}
//		}
		if(item.getProjector()!=null && item.getProjector()==1) {	
			Form item2 = new Form();
			item2.setBegintime(item.getBegintime());
			item2.setEndtime(item.getEndtime());
			item2.setProjector(1);
			int inUse = doShow(item2).size();
			//如果正在使用的数目大于设备数
			if(inUse >= accBiz.doSelectAvailableByType(Accessory.types[1]).size()) {
				return 2;//"没有可以供的投影仪";
			}
		}
		return 3;
	}
	
	/**
	 * 更新
	 */
	public void doUpdate(Form item){
		
	}
	
	/**
	 * 取消
	 * @param item
	 */
	public void doCancle(Form item) {
		formBiz.doUpdateState(Form.delState,item.getId());
		
	}
	
	/**
	 * 取消
	 * @param item
	 */
	public void doCancle(FormCancel item) {
		formCancelBiz.doAdd(item);
	}
	
	/**
	 * 显示详细记录
	 * @param item
	 */
	public Form doShowSingle(Form item) {
		return formBiz.doSelectById(item.getId());
	}
	
	/**
	 * 查询
	 * @param item
	 */
	public List<Form> doShow(Form item) {
		List<Form> temps = new ArrayList<Form>();	
		FormCancel fc = new FormCancel();
		fc.loadDate(item);
		List<FormCancel> rs = formCancelBiz.doSelect(fc);
		List<Form> rs2 = formBiz.doSelect(item);
		temps.addAll(rs2);
		for( Form aa :rs2) {	
			if(item.getBegintime()!=null) {
				if( aa.getBegintime().before(item.getBegintime()) || aa.getEndtime().after(item.getEndtime())) {
					temps.remove(aa);
				}
			}
		}		
		for( FormCancel bb :rs) {	
			for( Form aa :rs2) {
				if(bb.check(aa)) {
					temps.remove(aa);
				}
			}
		}		
		return temps;
	}
	
	/**
	 * 查询
	 * @param item
	 */
	public List<Form> doShow(Form item,int a) {
		List<Form> temps = new ArrayList<Form>();	
		FormCancel fc = new FormCancel();
		fc.loadDate(item);
		List<FormCancel> rs = formCancelBiz.doSelect(fc);
		List<Form> rs2 = formBiz.doSelect(item);
		temps.addAll(rs2);
		for( Form aa :rs2) {	
			System.out.println(aa.getBegintime()+":"+item.getBegintime()+"="+ aa.getBegintime().before(item.getBegintime()) );
			System.out.println(aa.getBegintime()+":"+item.getEndtime()+"="+aa.getBegintime().after(item.getEndtime()));
			System.out.println("......................");
			if( aa.getBegintime().before(item.getBegintime()) || aa.getBegintime().after(item.getEndtime())) {
				temps.remove(aa);
			}
		}		
		for( FormCancel bb :rs) {	
			for( Form aa :rs2) {
				if(bb.check(aa)) {
					temps.remove(aa);
				}
			}
		}		
		return temps;
	}
	
	//-----审批操作-------
	/**
	 * 查询待审批的
	 * @param item
	 */
	public List<Form> doShowApproved(Form item) {
		item.setState(Form.waittingForApprove);
		return formBiz.doSelectInner(item,3);
	}
	
	/**
	 * 批准重要客户申请
	 * @param id
	 */
	public void doApprove(int state ,int id) {
		formBiz.doUpdateState(state, id);
	}
	
	//-----配件操作-------
	/**
	 * 查看所有配件
	 */
	public List<Accessory> doShow(){
		return accBiz.doSelectAll();
	}
	
	/**
	 * 查看所有配件
	 */
	public Accessory doSelectById(int id){
		return accBiz.doSelect(id);
	}
	
	/**
	 * 添加配件
	 * @param item
	 */
	public void doAddAccessory(Accessory item) {
		accBiz.doAdd(item);
	}
	//--------------get&set--------------
	public FormBiz getFormBiz() {
		return formBiz;
	}

	public void setFormBiz(FormBiz formBiz) {
		this.formBiz = formBiz;
	}

	public AccessoryBiz getAccBiz() {
		return accBiz;
	}

	public void setAccBiz(AccessoryBiz accBiz) {
		this.accBiz = accBiz;
	}

	public FormCancelBiz getFormCancelBiz() {
		return formCancelBiz;
	}

	public void setFormCancelBiz(FormCancelBiz formCancelBiz) {
		this.formCancelBiz = formCancelBiz;
	}
	
	
}
