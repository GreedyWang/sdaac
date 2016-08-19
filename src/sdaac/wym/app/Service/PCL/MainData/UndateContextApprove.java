package sdaac.wym.app.Service.PCL.MainData;

public class UndateContextApprove extends OrdinaryApprove implements IApprove {

	@Override
	public BaseForm doAgree(ApproveForm item) {
		// TODO Auto-generated method stub
		int curState = item.getItem().getState();
		int nextState = MDStateManager.states.get(curState).nextState;	
		MDForm form = new MDForm();
		form.setUuid(item.getItem().getUuid());
		form.setState(nextState);
		MDForm item2 = (MDForm)item.getItem();
		if(item2.getBuyerName()!=null && !"".equals(item2.getBuyerName())) {
			form.setBuyerName(item2.getBuyerName());
		}
		if(item2.getType3()!=null && !"".equals(item2.getType3())) {
			form.setType3(item2.getType3());
		}
		if(item2.getMaterialSn()!=null && !"".equals(item2.getMaterialSn())) {
			form.setMaterialSn(item2.getMaterialSn());
		}
		if(item2.getSupplyContact()!=null && !"".equals(item2.getSupplyContact())) {
			form.setSupplyContact(item2.getSupplyContact());
		}
		if(item2.getSapContract()!=null && !"".equals(item2.getSapContract())) {
			form.setSapContract(item2.getSapContract());	
		}
		if(item2.getLeadTime()!=null && !"".equals(item2.getLeadTime())) {
			form.setLeadTime(item2.getLeadTime());
		}
		if(item2.getSupplier()!=null && !"".equals(item2.getSupplier())) {
			form.setSupplier(item2.getSupplier());
		}
		if(item2.getSupplysn()!=null && !"".equals(item2.getSupplysn())) {
			form.setSupplysn(item2.getSupplysn());
		}
		if(item2.getSapSupplyCode()!=null && !"".equals(item2.getSapSupplyCode())) {
			form.setSapSupplyCode(item2.getSapSupplyCode());
		}
		//-----------------------------
		if(item2.getCheckStorage()!=null && !"".equals(item2.getCheckStorage())) {
			form.setCheckStorage(item2.getCheckStorage());
		}
		if(item2.getMRPType()!=null && !"".equals(item2.getMRPType())) {
			form.setMRPType(item2.getMRPType());
		}
		if(item2.getReorderPlace()!=null && !"".equals(item2.getReorderPlace())) {
			form.setReorderPlace(item2.getReorderPlace());
		}
		if(item2.getND()!=null && !"".equals(item2.getND())) {
			form.setND(item2.getND());
		}
		if(item2.getBluk()!=null && !"".equals(item2.getBluk())) {
			form.setBluk(item2.getBluk());	
		}
		if(item2.getIfFX()!=null && !"".equals(item2.getIfFX())) {
			form.setIfFX(item2.getIfFX());
		}
		//----
		if(item2.getIfHB()!=null && !"".equals(item2.getIfHB())) {
			form.setIfHB(item2.getIfHB());
		}
		if(item2.getStandardPackage()!=null && !"".equals(item2.getStandardPackage())) {
			form.setStandardPackage(item2.getStandardPackage());
		}
		if(item2.getStoragePlace()!=null && !"".equals(item2.getStoragePlace())) {
			form.setStoragePlace(item2.getStoragePlace());
		}
		if(item2.getMaxPeriod()!=null && !"".equals(item2.getMaxPeriod())) {
			form.setMaxPeriod(item2.getMaxPeriod());
		}
		
		if(item2.getManger()!=null && !"".equals(item2.getManger())) {
			form.setManger(item2.getManger());
		}
		if(item2.getManagerPhone()!=null && !"".equals(item2.getManagerPhone())) {
			form.setManagerPhone(item2.getManagerPhone());
		}
		if(item2.getManagerDate()!=null && !"".equals(item2.getManagerDate())) {
			form.setManagerDate(item2.getManagerDate());
		}
		if(item2.getIsStorageManage()!=null ) {
			form.setIsStorageManage(item2.getIsStorageManage());
		}
		if(item2.getIsExpense()!=null ) {
			form.setIsExpense(item2.getIsExpense());
		}
		if(item2.getType2()!=null ) {
			form.setType2(item2.getType2());
		}		
		
		form.setNeedExplain(BaseForm.NORMAL);
		return form;
	}
	
}
