package sdaac.wym.app.entity.plm;

import java.util.HashSet;
import java.util.Set;

public class Part {
	private String id;
	private String no;// 零件号
	private String name;// 中文姓名
	private String wkaid;
	private String state;// 状态
	private String ename;// 英文名
	private String unit;// 单位
	private String source;// 来源
	// private String epartid;
	private String level;// 树中的级别
	// 缺少
	private String customerID;
	private String designno;
	private String drawingNO;// 图号
	private String quant = "1";// 数量
	// private Double quantity;
	private String specificationNO;// 材料规格
	private String specificationName;// 材料名称
	private String supplyDimension;// 供应尺寸
	private String processResponsibleGroup;// 工艺责任组
	private String responsibleWorkShop;// 生产责任车间
	private String supply;// 供应商
	private String GYDE;// 工艺定额
	private String type;
	private Set boms =null;

	public Set getBoms() {
		return boms;
	}

	public void setBoms(Set boms) {
		this.boms = boms;
	}

	public String getDrawingNO() {
		return drawingNO;
	}

	public void setDrawingNO(String drawingNO) {
		this.drawingNO = drawingNO;
	}

	public String getQuant() {
		return quant;
	}

	public void setQuant(Double quant) {
		this.quant = quant.toString();
	}

	public String getSpecificationNO() {
		return specificationNO;
	}

	public void setSpecificationNO(String specificationNO) {
		this.specificationNO = specificationNO;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	public String getSupplyDimension() {
		return supplyDimension;
	}

	public void setSupplyDimension(String supplyDimension) {
		this.supplyDimension = supplyDimension;
	}

	public String getProcessResponsibleGroup() {
		return processResponsibleGroup;
	}

	public void setProcessResponsibleGroup(String processResponsibleGroup) {
		this.processResponsibleGroup = processResponsibleGroup;
	}

	public String getResponsibleWorkShop() {
		return responsibleWorkShop;
	}

	public void setResponsibleWorkShop(String responsibleWorkShop) {
		this.responsibleWorkShop = responsibleWorkShop;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	// Constructors

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/** default constructor */
	public Part() {
	}

	public Part(String name) {
		this.name = name;
	}

	/** minimal constructor */
	public Part(String id, String wkaid, String state, Byte del) {
		this.id = id;
		this.wkaid = wkaid;
		this.state = state;
		// this.del = del;
	}

	/** full constructor */
	public Part(String id, String no, String name, String wkaid, String state,
			String ename, String source, String unit, String epartid) {
		this.id = id;
		this.no = no;
		this.name = name;
		// this.designno = designno;
		this.wkaid = wkaid;
		this.state = state;
		// this.del = del;
		// this.alteruser = alteruser;
		// this.duser = duser;
		// this.owner = owner;
		// this.muser = muser;
		// this.chkusr = chkusr;
		// this.creator = creator;
		// this.deltime = deltime;
		// this.mtime = mtime;
		// this.ctime = ctime;
		// this.msym = msym;
		// this.ver = ver;
		// this.chktime = chktime;
		// this.degree = degree;
		// this.dsnweight = dsnweight;
		// this.duser01 = duser01;
		// this.duser02 = duser02;
		// this.duser03 = duser03;
		// this.duser04 = duser04;
		// this.duser05 = duser05;
		this.ename = ename;
		// this.etime = etime;
		// this.fuser01 = fuser01;
		// this.fuser02 = fuser02;
		// this.fuser03 = fuser03;
		// this.fuser04 = fuser04;
		// this.fuser05 = fuser05;
		// this.mtlmark = mtlmark;
		// this.origin = origin;
		// this.ptype = ptype;
		// this.smemo = smemo;
		this.source = source;
		// this.stime = stime;
		// this.suser01 = suser01;
		// this.suser02 = suser02;
		// this.suser03 = suser03;
		// this.suser04 = suser04;
		// this.suser05 = suser05;
		// this.suser06 = suser06;
		// this.suser07 = suser07;
		// this.suser08 = suser08;
		// this.suser09 = suser09;
		// this.suser10 = suser10;
		// this.suser11 = suser11;
		// this.suser12 = suser12;
		// this.suser13 = suser13;
		// this.suser14 = suser14;
		// this.suser15 = suser15;
		// this.suser16 = suser16;
		// this.suser17 = suser17;
		// this.suser18 = suser18;
		// this.suser19 = suser19;
		// this.suser20 = suser20;
		this.unit = unit;
		// this.standardno = standardno;
		// this.specs = specs;
		// this.colorno = colorno;
		// this.itemno = itemno;
		// this.stndcost = stndcost;
		// this.eoriginno = eoriginno;
		// this.admsmemo = admsmemo;
		// this.msmemo = msmemo;
		// this.epartid = epartid;
		// this.prdid = prdid;
		// this.nweight = nweight;
		// this.facearea = facearea;
		// this.cubage = cubage;
		// this.barycenterx = barycenterx;
		// this.barycentery = barycentery;
		// this.barycenterz = barycenterz;
		// this.cusno = cusno;
		// this.mtlname = mtlname;
		// this.gysize = gysize;
		// this.kznum = kznum;
		// this.gyde = gyde;
		// this.gyteam = gyteam;
		// this.scshop = scshop;
		// this.supplier = supplier;
		// this.bnum = bnum;
		// this.gysxz = gysxz;
		// this.partxz = partxz;
		// this.ycost = ycost;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWkaid() {
		return this.wkaid;
	}

	public void setWkaid(String wkaid) {
		this.wkaid = wkaid;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	// public String getEpartid() {
	// return this.epartid;
	// }
	//
	// public void setEpartid(String epartid) {
	// this.epartid = epartid;
	// }

	public String getGYDE() {
		return GYDE;
	}

	public void setGYDE(String gyde) {
		GYDE = gyde;
	}

	public String getDesignno() {
		return designno;
	}

	public void setDesignno(String designno) {
		this.designno = designno;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
