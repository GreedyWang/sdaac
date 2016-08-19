package sdaac.wym.app.Service.PCL.MainData;
// default package

import java.util.Date;
/**
 * FormaId entity. @author MyEclipse Persistence Tools
 */

public class MDForm  extends BaseForm implements java.io.Serializable {

    // Fields    
//     private Integer id;
	 private Integer isSave;
     private String materialSn;
     private String remark;
     private String isBulk; //是否批量
     private String formid;//如果是批量导入，formid会记录申请单的号码
//     private Date applicateDate;
//     private Tempolyee applicantor = new Tempolyee();
     private String phone;
     private String costCenter;
     private Integer feedbackType;
     private String factory;
     private String storageAddress;
     private Integer isCreate;
     private String oldMaterialSn;
     private String description;
     private String description2;
     private String attachFilePath;
     private String manufacturerSn;
     private String manufacturerName;
     private String unit;
     private String ersa;
     private Byte isDanger;
     private String fid;
     
     private String amin;
     private String amax;
     private String annual;
     
     private Float unitPrice;
     private Integer isSelfMade;
     private Integer isStorageManage;
     private Integer isExpense;
     private String type2;
     
     private String height;
     private String width;
     private String longa;
     
     private String type="物料主数据";
     private String buyerName;//采购员
     private Integer isPackage ;// 是否为包装
     //buyer info
     private String type3;//单一采购or拉动
     private String sapSupplyCode;
     private String supplysn;
     private String supplier;
     private String supplyContact;
     private String sapContract;
     private String leadTime;
     private String minOrder;
     private String standardPackage;//标准包装
     //MRP properties
     private String checkStorage; //库存检查
     private String MRPType;//MRP类型
     private String reorderPlace;//再订购点 
     private String ND;//ND(无计划)
     private String bluk;//批量
     private String ifFX;//修正批量(如果选择FX)： 
     private String ifHB;//最高库存水平(如果选择HB):
     private String storagePlace;//库存地点
     private String maxPeriod;//最高库存期限
     private String manger;//管理名字:  
     private String managerPhone;// 电话号码:
     private String managerDate; //日期:   
     //附件
     private String file1;
     private String file2;
     private String file3;        
     //------       
     private String typeName;
     
     
    public static String[] types = {"辅料","劳防用品","办公用品","低值工装、工具、模具","包装物",
    		 "修理费","设施修理","机器设备修理","备品备件","费用化模具维修","工具、工装维修费","不用"};
     
	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getIsBulk() {
		return isBulk;
	}

	public void setIsBulk(String isBulk) {
		this.isBulk = isBulk;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	
	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getCheckStorage() {
		return checkStorage;
	}

	public void setCheckStorage(String checkStorage) {
		this.checkStorage = checkStorage;
	}

	public String getMRPType() {
		return MRPType;
	}

	public void setMRPType(String type) {
		MRPType = type;
	}

	public String getReorderPlace() {
		return reorderPlace;
	}

	public void setReorderPlace(String reorderPlace) {
		this.reorderPlace = reorderPlace;
	}

	public String getND() {
		return ND;
	}

	public void setND(String nd) {
		ND = nd;
	}

	public String getBluk() {
		return bluk;
	}

	public void setBluk(String bluk) {
		this.bluk = bluk;
	}

	public String getIfFX() {
		return ifFX;
	}

	public void setIfFX(String ifFX) {
		this.ifFX = ifFX;
	}

	public String getIfHB() {
		return ifHB;
	}

	public void setIfHB(String ifHB) {
		this.ifHB = ifHB;
	}

	public String getStandardPackage() {
		return standardPackage;
	}

	public void setStandardPackage(String standardPackage) {
		this.standardPackage = standardPackage;
	}

	public String getStoragePlace() {
		return storagePlace;
	}

	public void setStoragePlace(String storagePlace) {
		this.storagePlace = storagePlace;
	}

	public String getMaxPeriod() {
		return maxPeriod;
	}

	public void setMaxPeriod(String maxPeriod) {
		this.maxPeriod = maxPeriod;
	}

	public String getManger() {
		return manger;
	}

	public void setManger(String manger) {
		this.manger = manger;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getManagerDate() {
		return managerDate;
	}

	public void setManagerDate(String managerDate) {
		this.managerDate = managerDate;
	}

	public String getSapSupplyCode() {
		return sapSupplyCode;
	}

	public void setSapSupplyCode(String sapSupplyCode) {
		this.sapSupplyCode = sapSupplyCode;
	}

	public String getSupplysn() {
		return supplysn;
	}

	public void setSupplysn(String supplysn) {
		this.supplysn = supplysn;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplyContact() {
		return supplyContact;
	}

	public void setSupplyContact(String supplyContact) {
		this.supplyContact = supplyContact;
	}

	public String getSapContract() {
		return sapContract;
	}

	public void setSapContract(String sapContract) {
		this.sapContract = sapContract;
	}

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public Integer getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(Integer isPackage) {
		this.isPackage = isPackage;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** default constructor */
    public MDForm() {
    }

	/** minimal constructor */
//    public MDForm(Integer id) {
//        this.id = id;
//    }
    
    /** full constructor */
    public MDForm( String materialSn, Date applicateDate,  String phone, String costCenter, Integer feedbackType, String factory, String storageAddress, Integer isCreate, String oldMaterialSn, String description, String attachFilePath, String manufacturerSn, String manufacturerName, String unit, String ersa, Byte isDanger, String fid, Double amin, Long amax, Long annual, Float unitPrice, Integer isSelfMade, Integer isStorageManage, Integer isExpense, String type2, Long height, Long width, Long longa) {
//        this.id = id;
        this.materialSn = materialSn;
        this.applicateDate = applicateDate;
//        this.applicantor = applicantor;
        this.phone = phone;
        this.costCenter = costCenter;
        this.feedbackType = feedbackType;
        this.factory = factory;
        this.storageAddress = storageAddress;
        this.isCreate = isCreate;
        this.oldMaterialSn = oldMaterialSn;
        this.description = description;
        this.attachFilePath = attachFilePath;
        this.manufacturerSn = manufacturerSn;
        this.manufacturerName = manufacturerName;
        this.unit = unit;
        this.ersa = ersa;
        this.isDanger = isDanger;
        this.fid = fid;
        this.unitPrice = unitPrice;
        this.isSelfMade = isSelfMade;
        this.isStorageManage = isStorageManage;
        this.isExpense = isExpense;
        this.type2 = type2;

    }

    public String getMaterialSn() {
        return this.materialSn;
    }
    
    public void setMaterialSn(String materialSn) {
        this.materialSn = materialSn;
    }

    public Date getApplicateDate() {
        return this.applicateDate;
    }
    
    public void setApplicateDate(Date applicateDate) {
        this.applicateDate = applicateDate;
    }

//    public Tempolyee getApplicantor() {
//		return applicantor;
//	}
//
//	public void setApplicantor(Tempolyee applicantor) {
//		this.applicantor = applicantor;
//	}

	public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCostCenter() {
        return this.costCenter;
    }
    
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public Integer getFeedbackType() {
        return this.feedbackType;
    }
    
    public void setFeedbackType(Integer feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFactory() {
        return this.factory;
    }
    
    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getStorageAddress() {
        return this.storageAddress;
    }
    
    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public Integer getIsCreate() {
        return this.isCreate;
    }
    
    public void setIsCreate(Integer isCreate) {
        this.isCreate = isCreate;
    }

    public String getOldMaterialSn() {
        return this.oldMaterialSn;
    }
    
    public void setOldMaterialSn(String oldMaterialSn) {
        this.oldMaterialSn = oldMaterialSn;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachFilePath() {
        return this.attachFilePath;
    }
    
    public void setAttachFilePath(String attachFilePath) {
        this.attachFilePath = attachFilePath;
    }

    public String getManufacturerSn() {
        return this.manufacturerSn;
    }
    
    public void setManufacturerSn(String manufacturerSn) {
        this.manufacturerSn = manufacturerSn;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }
    
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getErsa() {
        return this.ersa;
    }
    
    public void setErsa(String ersa) {
        this.ersa = ersa;
    }

    public Byte getIsDanger() {
        return this.isDanger;
    }
    
    public void setIsDanger(Byte isDanger) {
        this.isDanger = isDanger;
    }

    public String getFid() {
        return this.fid;
    }
    
    public void setFid(String fid) {
        this.fid = fid;
    }
    
    public Float getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getIsSelfMade() {
        return this.isSelfMade;
    }
    
    public void setIsSelfMade(Integer isSelfMade) {
        this.isSelfMade = isSelfMade;
    }

    public Integer getIsStorageManage() {
        return this.isStorageManage;
    }
    
    public void setIsStorageManage(Integer isStorageManage) {
        this.isStorageManage = isStorageManage;
    }

    public Integer getIsExpense() {
        return this.isExpense;
    }
    
    public void setIsExpense(Integer isExpense) {
        this.isExpense = isExpense;
    }

    public String getType2() {
        return this.type2;
    }
    
    public void setType2(String type2) {
        this.type2 = type2;
    }

	public String getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(String minOrder) {
		this.minOrder = minOrder;
	}

	public Integer getIsSave() {
		return isSave;
	}

	public void setIsSave(Integer isSave) {
		this.isSave = isSave;
	}

	public String getAmin() {
		return amin;
	}

	public void setAmin(String amin) {
		this.amin = amin;
	}

	public String getAmax() {
		return amax;
	}

	public void setAmax(String amax) {
		this.amax = amax;
	}

	public String getAnnual() {
		return annual;
	}

	public void setAnnual(String annual) {
		this.annual = annual;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLonga() {
		return longa;
	}

	public void setLonga(String longa) {
		this.longa = longa;
	}
	
	//SDAAC定义->类型
//	HIBE	生产用辅料
//	HIBE	办公用品
//	HIBE	劳防用品
//	HIBE	低值工装、工具、模具
//	HIBE	包装材料
//	HIBE	水电费
//	ERSA	修理费
//	ERSA	设施修理
//	ERSA	机器设备修理
//	ERSA	机器设备备品备件
//	ERSA	费用化模具维修及模具备件
//	ERSA	工具、工装维修费
	String[] SDAACTYPES = {"修理费,设施修理,机器设备修理,备品备件," +
			"费用化模具维修,工具、工装维修费","辅料,办公用品,劳防用品,低值工装、工具、模具,包装物,包装材料,水电费"}; 
	public String getTypeName() {
		if(typeName!=null && !typeName.equals("")){
			if(SDAACTYPES[0].contains(typeName)) return typeName+"_"+"ERSA";
			else if(SDAACTYPES[1].contains(typeName)) return typeName+"_"+"HIBE";
			else return typeName;
		}
		return "";
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
   
	


//   public boolean equals(Object other) {
//         if ( (this == other ) ) return true;
//		 if ( (other == null ) ) return false;
//		 if ( !(other instanceof MDForm) ) return false;
//		 MDForm castOther = ( MDForm ) other; 
//         
//		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
// && ( (this.getMaterialSn()==castOther.getMaterialSn()) || ( this.getMaterialSn()!=null && castOther.getMaterialSn()!=null && this.getMaterialSn().equals(castOther.getMaterialSn()) ) )
// && ( (this.getApplicateDate()==castOther.getApplicateDate()) || ( this.getApplicateDate()!=null && castOther.getApplicateDate()!=null && this.getApplicateDate().equals(castOther.getApplicateDate()) ) )
// && ( (this.getApplicantor()==castOther.getApplicantor()) || ( this.getApplicantor()!=null && castOther.getApplicantor()!=null && this.getApplicantor().equals(castOther.getApplicantor()) ) )
// && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
// && ( (this.getCostCenter()==castOther.getCostCenter()) || ( this.getCostCenter()!=null && castOther.getCostCenter()!=null && this.getCostCenter().equals(castOther.getCostCenter()) ) )
// && ( (this.getFeedbackType()==castOther.getFeedbackType()) || ( this.getFeedbackType()!=null && castOther.getFeedbackType()!=null && this.getFeedbackType().equals(castOther.getFeedbackType()) ) )
// && ( (this.getFactory()==castOther.getFactory()) || ( this.getFactory()!=null && castOther.getFactory()!=null && this.getFactory().equals(castOther.getFactory()) ) )
// && ( (this.getStorageAddress()==castOther.getStorageAddress()) || ( this.getStorageAddress()!=null && castOther.getStorageAddress()!=null && this.getStorageAddress().equals(castOther.getStorageAddress()) ) )
// && ( (this.getIsCreate()==castOther.getIsCreate()) || ( this.getIsCreate()!=null && castOther.getIsCreate()!=null && this.getIsCreate().equals(castOther.getIsCreate()) ) )
// && ( (this.getOldMaterialSn()==castOther.getOldMaterialSn()) || ( this.getOldMaterialSn()!=null && castOther.getOldMaterialSn()!=null && this.getOldMaterialSn().equals(castOther.getOldMaterialSn()) ) )
// && ( (this.getDescription()==castOther.getDescription()) || ( this.getDescription()!=null && castOther.getDescription()!=null && this.getDescription().equals(castOther.getDescription()) ) )
// && ( (this.getAttachFilePath()==castOther.getAttachFilePath()) || ( this.getAttachFilePath()!=null && castOther.getAttachFilePath()!=null && this.getAttachFilePath().equals(castOther.getAttachFilePath()) ) )
// && ( (this.getManufacturerSn()==castOther.getManufacturerSn()) || ( this.getManufacturerSn()!=null && castOther.getManufacturerSn()!=null && this.getManufacturerSn().equals(castOther.getManufacturerSn()) ) )
// && ( (this.getManufacturerName()==castOther.getManufacturerName()) || ( this.getManufacturerName()!=null && castOther.getManufacturerName()!=null && this.getManufacturerName().equals(castOther.getManufacturerName()) ) )
// && ( (this.getUnit()==castOther.getUnit()) || ( this.getUnit()!=null && castOther.getUnit()!=null && this.getUnit().equals(castOther.getUnit()) ) )
// && ( (this.getErsa()==castOther.getErsa()) || ( this.getErsa()!=null && castOther.getErsa()!=null && this.getErsa().equals(castOther.getErsa()) ) )
// && ( (this.getIsDanger()==castOther.getIsDanger()) || ( this.getIsDanger()!=null && castOther.getIsDanger()!=null && this.getIsDanger().equals(castOther.getIsDanger()) ) )
// && ( (this.getFid()==castOther.getFid()) || ( this.getFid()!=null && castOther.getFid()!=null && this.getFid().equals(castOther.getFid()) ) )
// && ( (this.getAmin()==castOther.getAmin()) || ( this.getAmin()!=null && castOther.getAmin()!=null && this.getAmin().equals(castOther.getAmin()) ) )
// && ( (this.getAmax()==castOther.getAmax()) || ( this.getAmax()!=null && castOther.getAmax()!=null && this.getAmax().equals(castOther.getAmax()) ) )
// && ( (this.getAnnual()==castOther.getAnnual()) || ( this.getAnnual()!=null && castOther.getAnnual()!=null && this.getAnnual().equals(castOther.getAnnual()) ) )
// && ( (this.getUnitPrice()==castOther.getUnitPrice()) || ( this.getUnitPrice()!=null && castOther.getUnitPrice()!=null && this.getUnitPrice().equals(castOther.getUnitPrice()) ) )
// && ( (this.getIsSelfMade()==castOther.getIsSelfMade()) || ( this.getIsSelfMade()!=null && castOther.getIsSelfMade()!=null && this.getIsSelfMade().equals(castOther.getIsSelfMade()) ) )
// && ( (this.getIsStorageManage()==castOther.getIsStorageManage()) || ( this.getIsStorageManage()!=null && castOther.getIsStorageManage()!=null && this.getIsStorageManage().equals(castOther.getIsStorageManage()) ) )
// && ( (this.getIsExpense()==castOther.getIsExpense()) || ( this.getIsExpense()!=null && castOther.getIsExpense()!=null && this.getIsExpense().equals(castOther.getIsExpense()) ) )
// && ( (this.getType2()==castOther.getType2()) || ( this.getType2()!=null && castOther.getType2()!=null && this.getType2().equals(castOther.getType2()) ) )
// && ( (this.getHeight()==castOther.getHeight()) || ( this.getHeight()!=null && castOther.getHeight()!=null && this.getHeight().equals(castOther.getHeight()) ) )
// && ( (this.getWidth()==castOther.getWidth()) || ( this.getWidth()!=null && castOther.getWidth()!=null && this.getWidth().equals(castOther.getWidth()) ) )
// && ( (this.getLonga()==castOther.getLonga()) || ( this.getLonga()!=null && castOther.getLonga()!=null && this.getLonga().equals(castOther.getLonga()) ) );
//   }
//   
//   public int hashCode() {
//         int result = 17;
//         
//         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
//         result = 37 * result + ( getMaterialSn() == null ? 0 : this.getMaterialSn().hashCode() );
//         result = 37 * result + ( getApplicateDate() == null ? 0 : this.getApplicateDate().hashCode() );
//         result = 37 * result + ( getApplicantor() == null ? 0 : this.getApplicantor().hashCode() );
//         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
//         result = 37 * result + ( getCostCenter() == null ? 0 : this.getCostCenter().hashCode() );
//         result = 37 * result + ( getFeedbackType() == null ? 0 : this.getFeedbackType().hashCode() );
//         result = 37 * result + ( getFactory() == null ? 0 : this.getFactory().hashCode() );
//         result = 37 * result + ( getStorageAddress() == null ? 0 : this.getStorageAddress().hashCode() );
//         result = 37 * result + ( getIsCreate() == null ? 0 : this.getIsCreate().hashCode() );
//         result = 37 * result + ( getOldMaterialSn() == null ? 0 : this.getOldMaterialSn().hashCode() );
//         result = 37 * result + ( getDescription() == null ? 0 : this.getDescription().hashCode() );
//         result = 37 * result + ( getAttachFilePath() == null ? 0 : this.getAttachFilePath().hashCode() );
//         result = 37 * result + ( getManufacturerSn() == null ? 0 : this.getManufacturerSn().hashCode() );
//         result = 37 * result + ( getManufacturerName() == null ? 0 : this.getManufacturerName().hashCode() );
//         result = 37 * result + ( getUnit() == null ? 0 : this.getUnit().hashCode() );
//         result = 37 * result + ( getErsa() == null ? 0 : this.getErsa().hashCode() );
//         result = 37 * result + ( getIsDanger() == null ? 0 : this.getIsDanger().hashCode() );
//         result = 37 * result + ( getFid() == null ? 0 : this.getFid().hashCode() );
//         result = 37 * result + ( getAmin() == null ? 0 : this.getAmin().hashCode() );
//         result = 37 * result + ( getAmax() == null ? 0 : this.getAmax().hashCode() );
//         result = 37 * result + ( getAnnual() == null ? 0 : this.getAnnual().hashCode() );
//         result = 37 * result + ( getUnitPrice() == null ? 0 : this.getUnitPrice().hashCode() );
//         result = 37 * result + ( getIsSelfMade() == null ? 0 : this.getIsSelfMade().hashCode() );
//         result = 37 * result + ( getIsStorageManage() == null ? 0 : this.getIsStorageManage().hashCode() );
//         result = 37 * result + ( getIsExpense() == null ? 0 : this.getIsExpense().hashCode() );
//         result = 37 * result + ( getType2() == null ? 0 : this.getType2().hashCode() );
//         result = 37 * result + ( getHeight() == null ? 0 : this.getHeight().hashCode() );
//         result = 37 * result + ( getWidth() == null ? 0 : this.getWidth().hashCode() );
//         result = 37 * result + ( getLonga() == null ? 0 : this.getLonga().hashCode() );
//         return result;
//   }   





}