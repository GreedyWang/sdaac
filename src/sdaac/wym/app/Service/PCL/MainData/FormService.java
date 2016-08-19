package sdaac.wym.app.Service.PCL.MainData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.entity.Tdepartment;
import app.entity.Tempolyee;

import common.dao.CommonDAO;
import common.util.ExcelReader2007;

public class FormService {
	protected CommonDAO formDao;

	public CommonDAO<BaseForm> getFormDao() {
		return formDao;
	}

	public void setFormDao(CommonDAO<BaseForm> formDao) {
		this.formDao = formDao;
	}
	
	/**
	 * 申请
	 * @param item
	 */
	public void doAdd(BaseForm item){
	
		formDao.insert(item);
	}
	
	/**
	 * 查询概述条目
	 * @param item
	 * @return
	 */
	public List<BaseForm> doShow(MDForm item) {
		List<BaseForm> rs = new ArrayList<BaseForm>();
		StringBuffer hql = new StringBuffer("select tempolyee1_.name,tdepartmen2_.name as dn,applicatedate,state,remark,needexplain,baseform0_.type,baseform0_.id ");
		hql.append("from PCL_MainData baseform0_ "); 
		hql.append("inner join tEmpolyee tempolyee1_ on baseform0_.applicantor=tempolyee1_.uid "); 
		hql.append("inner join tDepartment tdepartmen2_ on tempolyee1_.departmentid=tdepartmen2_.id "); 
		hql.append("where state>=0 and state <> 9");
		List<Object> params = new ArrayList<Object>();	
		if(item.getTempolyeeByApplicantId().getUid()!=null && !item.getTempolyeeByApplicantId().getUid().equals("")) {
			hql.append(" and tempolyee1_.uid = '"+item.getTempolyeeByApplicantId().getUid()+"'" );
		}
		if(item.getState()!=null) {
			hql.append(" and baseform0_.state="+item.getState());
		}
		if(item.getStateRange()!=null && !"".equals(item.getStateRange())) {
			hql.append(" and baseform0_.state in ("+item.getStateRange()+")");
		}
		if(item.getBuyerName()!=null) {
			hql.append(" and baseform0_.buyerName like '%"+item.getBuyerName()+"%'");
		}
		if(item.getTempolyeeByApplicantId().getTdepartment()!=null && item.getTempolyeeByApplicantId().getTdepartment().getId()!=null ) {
			hql.append(" and tempolyee1_.departmentid = "+ item.getTempolyeeByApplicantId().getTdepartment().getId());
		}
		List<Object[]> temp = formDao.selectBySql(hql.toString());
		for(Object[] aa : temp) {
			BaseForm e = new BaseForm();
			Tempolyee emp = new Tempolyee("",aa[0].toString());
			emp.setTdepartment(new Tdepartment(aa[1].toString()));
			e.setTempolyeeByApplicantId(emp);
			e.setState((Integer)aa[3]);
			e.setRemark(aa[4]==null?"":aa[4].toString());
			e.setNeedExplain((Integer)aa[5]);
			e.setUuid((Integer)aa[7]);
			e.setSsidTip(aa[7]==null?"":aa[7].toString());
			if(aa[2]!=null){
			String applicantDate = aa[2].toString();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					e.setApplicateDate(df.parse(applicantDate));
				} catch (ParseException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			if(aa[6]!=null && aa[6].toString().equals("MDForm")) {
				e.setType("物料主数据");
			}
			
			rs.add(e);
		}
		
		return rs;

	}
	
	/**
	 * 查询详细信息
	 * @param pk
	 * @return
	 */
	public BaseForm doShowDetails(int pk){
		String hql="from BaseForm bf join fetch bf.tempolyeeByApplicantId e join fetch e.tdepartment where state>=0 and bf.uuid=?";
		List<BaseForm> rs = formDao.select(hql, pk);
		if(rs!=null && rs.size()>0) {
			return rs.get(0);
		}else {
			return null;
		}
	}
	
	public void doUpdate(BaseForm item) {
		StringBuffer hql= new StringBuffer("update BaseForm set ");
		List params = new ArrayList();
		if(item.getState()!=null){
			hql.append("state =?,");
			params.add(item.getState());
		}
		if(item.getNeedExplain()!=null) {
			hql.append("needExplain =?,");
			params.add(item.getNeedExplain());
		}
	
		if(item.getUuid()!=null) {
			hql.delete(hql.length()-1,hql.length());
			hql.append(" where uuid=?");
			params.add(item.getUuid());
			formDao.bulkUpdate(hql.toString(), params.toArray());
		}
		
	}
	
	/**
	 * 更新MD表
	 * @param item
	 */
	public void doUpdateMD(MDForm item) {
		StringBuffer hql= new StringBuffer("update BaseForm set ");
		List params = new ArrayList();
		if(item.getState()!=null){
			hql.append("state =?,");
			params.add(item.getState());
		}
		if(item.getTypeName()!=null && !item.getTypeName().equals("")){
			hql.append("typeName =?,");
			params.add(item.getTypeName());
		}
		if(item.getNeedExplain()!=null) {
			hql.append("needExplain =?,");
			params.add(item.getNeedExplain());
		}
		if(item.getBuyerName()!=null && !"".equals(item.getBuyerName())) {
			hql.append("buyerName =?,");
			params.add(item.getBuyerName());
		}
		if(item.getType3()!=null && !"".equals(item.getType3())) {
			hql.append("type3 =?,");
			params.add(item.getType3());
		}
		if(item.getMaterialSn()!=null && !"".equals(item.getMaterialSn())) {
			hql.append("materialSn =?,");
			params.add(item.getMaterialSn());
		}
		if(item.getSupplyContact()!=null && !"".equals(item.getSupplyContact())) {
			hql.append("supplyContact =?,");
			params.add(item.getSupplyContact());
		}
		if(item.getSapContract()!=null && !"".equals(item.getSapContract())) {
			hql.append("sapContract =?,");
			params.add(item.getSapContract());
		}
		if(item.getLeadTime()!=null && !"".equals(item.getLeadTime())) {
			hql.append("leadTime =?,");
			params.add(item.getLeadTime());
		}
		if(item.getSupplier()!=null && !"".equals(item.getSupplier())) {
			hql.append("supplier =?,");
			params.add(item.getSupplier());
		}
		if(item.getSupplysn()!=null && !"".equals(item.getSupplysn())) {
			hql.append("supplysn =?,");
			params.add(item.getSupplysn());
		}
		if(item.getSapSupplyCode()!=null && !"".equals(item.getSapSupplyCode())) {
			hql.append("sapSupplyCode =?,");
			params.add(item.getSapSupplyCode());
		}
		//-------------
		if(item.getIfHB()!=null && !"".equals(item.getIfHB())) {
			hql.append("ifHB =?,");
			params.add(item.getIfHB());
		}
		if(item.getStandardPackage()!=null && !"".equals(item.getStandardPackage())) {
			hql.append("standardPackage =?,");
			params.add(item.getStandardPackage());
		}
		if(item.getStoragePlace()!=null && !"".equals(item.getStoragePlace())) {
			hql.append("storagePlace =?,");
			params.add(item.getStoragePlace());
		}
		if(item.getMaxPeriod()!=null && !"".equals(item.getMaxPeriod())) {
			hql.append("maxPeriod =?,");
			params.add(item.getMaxPeriod());
		}
		
		if(item.getManger()!=null && !"".equals(item.getManger())) {
			hql.append("manger =?,");
			params.add(item.getManger());
		}
		if(item.getManagerPhone()!=null && !"".equals(item.getManagerPhone())) {
			hql.append("managerPhone =?,");
			params.add(item.getManagerPhone());
		}
		if(item.getManagerDate()!=null && !"".equals(item.getManagerDate())) {
			hql.append("managerDate = ?,");
			params.add(item.getManagerDate());
		}

	 	if(item.getAmin()!=null && !"".equals(item.getAmin())) {
			hql.append("amin = ?,");
			params.add(item.getAmin());
		}
		if(item.getAmax()!=null && !"".equals(item.getAmax())) {
			hql.append("amax = ?,");
			params.add(item.getAmax());
		}
		if(item.getAnnual()!=null && !"".equals(item.getAnnual())) {
			hql.append("annual = ?,");
			params.add(item.getAnnual());
		}
		if(item.getOldMaterialSn()!=null && !"".equals(item.getOldMaterialSn())) {
			hql.append("oldMaterialSn = ?,");
			params.add(item.getOldMaterialSn());
		}
		if(item.getDescription()!=null && !"".equals(item.getDescription())) {
			hql.append("description = ?,");
			params.add(item.getDescription());
		}
		if(item.getDescription2()!=null && !"".equals(item.getDescription2())) {
			hql.append("description2 = ?,");
			params.add(item.getDescription2());
		}
		if(item.getAttachFilePath()!=null && !"".equals(item.getAttachFilePath())) {
			hql.append("attachFilePath = ?,");
			params.add(item.getAttachFilePath());
		}
		if(item.getManufacturerSn()!=null && !"".equals(item.getManufacturerSn())) {
			hql.append("manufacturerSn = ?,");
			params.add(item.getManufacturerSn());
		}
		if(item.getUnit()!=null && !"".equals(item.getUnit())) {
			hql.append("unit = ?,");
			params.add(item.getUnit());
		}
		//-----
		if(item.getPhone()!=null && !"".equals(item.getPhone())) {
			hql.append("phone = ?,");
			params.add(item.getPhone());
		}
		if(item.getCostCenter()!=null && !"".equals(item.getCostCenter())) {
			hql.append("costCenter = ?,");
			params.add(item.getCostCenter());
		}
		if(item.getFeedbackType()!=null && !"".equals(item.getFeedbackType())) {
			hql.append("feedbackType = ?,");
			params.add(item.getFeedbackType());
		}
		if(item.getFactory()!=null && !"".equals(item.getFactory())) {
			hql.append("factory = ?,");
			params.add(item.getFactory());
		}
		if(item.getStorageAddress()!=null && !"".equals(item.getStorageAddress())) {
			hql.append("storageAddress = ?,");
			params.add(item.getStorageAddress());
		}
		if(item.getIsCreate()!=null && !"".equals(item.getIsCreate())) {
			hql.append("isCreate = ?,");
			params.add(item.getIsCreate());
		}
		
		if(item.getIsStorageManage()!=null && !"".equals(item.getIsStorageManage())) {
			hql.append("isStorageManage = ?,");
			params.add(item.getIsStorageManage());
		}
		if(item.getIsExpense()!=null && !"".equals(item.getIsExpense())) {
			hql.append("isExpense = ?,");
			params.add(item.getIsExpense());
		}
		if(item.getType2()!=null && !"".equals(item.getType2())) {
			hql.append("type2 = ?,");
			params.add(item.getType2());
		}
		if(item.getUuid()!=null) {
			hql.delete(hql.length()-1,hql.length());
			hql.append(" where uuid=?");
			params.add(item.getUuid());
			formDao.bulkUpdate(hql.toString(), params.toArray());
		}
		
	}
	
	/**
	 * 更新状态
	 * @param key,info
	 */
	public void doNeedExplain(int key,int info){
		String hql="update BaseForm set needExplain = ? where uuid =?";
		formDao.bulkUpdate(hql, new Object[]{info,key});
	}
	
	/**
	 * 退回
	 * @param key,info
	 */
	public void doRefuse(int key){
		String hql="update BaseForm set needExplain = ?,state=? where uuid =?";
		formDao.bulkUpdate(hql, new Object[]{BaseForm.RETURN,0,key});
	}
	
	/**
	 * 批量导入
	 * @not testing
	 */
	public void doBulkAdd(MDForm item) {
		ExcelReader2007 er = new ExcelReader2007();
		List<ArrayList<String>> rs = er.read("");
		List<MDForm> forms = new ArrayList<MDForm>();
		for(ArrayList<String> temp :rs) {
			MDForm md = new MDForm();
			md.setBluk(item.getUuid()+"");
			md.setTempolyeeByApplicantId(item.getTempolyeeByApplicantId());
			md.setApplicateDate(item.getApplicateDate());
			md.setBuyerName(temp.get(1));
			md.setFactory(temp.get(1));
			md.setPhone(temp.get(1));
			md.setCostCenter(temp.get(1));
			md.setOldMaterialSn(temp.get(1));
			md.setDescription(temp.get(1));
			md.setDescription2(temp.get(1));
			md.setManufacturerSn(temp.get(1));
			md.setManufacturerName(temp.get(1));
			md.setUnit(temp.get(1));
			md.setErsa(temp.get(1));
			md.setFid(temp.get(1));
			md.setAmin(temp.get(1));
			md.setAmax(temp.get(1));
			md.setAnnual(temp.get(1));
			md.setLonga(temp.get(1));
			md.setWidth(temp.get(1));
			md.setHeight(temp.get(1));
			md.setIsSelfMade(Integer.parseInt(temp.get(1)));
			md.setUnitPrice(Float.parseFloat(temp.get(1)));
			forms.add(md);
		}
		formDao.blukFlushInsert(rs);
	}
}
