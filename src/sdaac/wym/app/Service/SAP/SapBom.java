package sdaac.wym.app.Service.SAP;
/**
 * SAP BOM Class
 * used to Finance Report
 * this class is a material product Routing,like ID->sonID
 * @author SA1KV5
 *
 */
import java.math.*;
import java.util.Date;

public class SapBom {
	
//	 private String altBom;
//     private String altUoM;
//     private String assembly;
//     private String baseUnit;
//     private String bomdate;
//     private String bommon;
//     private String bomyear;
//     private String changeNo;
//     private Float compQuantity;
//     private String component;
//     private Float compQty(altUoM);
//     private Integer id;
//     private Float item;
//     private String level;
//     private String materialType;
//     private String mrpcontroller;
//     private String oldmaterialNumber;
//     private String plnt;
//     private String procurementType;
//     private String specProcurement;
//     private Date validFrom;
//     private Date validTo;
	
	private int id;
	private String assembly; // 组件ID
	private String component; //零件ID
	private char procurement_Type; //E is self-made;F is buy
	private int specProcurement;  // - ;30 is assembly ; 90 is component
	private Float comp_Quantity;
	private String base_Unit;
	private Float CompQty_UoM;
	private String AltUoM;
	private Date validate_time;
	
	public String getAssembly() {
		return assembly;
	}
	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public char getProcurement_Type() {
		return procurement_Type;
	}
	public void setProcurement_Type(char procurement_Type) {
		this.procurement_Type = procurement_Type;
	}
	public int getSpecProcurement() {
		return specProcurement;
	}
	public void setSpecProcurement(int specProcurement) {
		this.specProcurement = specProcurement;
	}
	public Float getComp_Quantity() {
		return comp_Quantity;
	}
	public void setComp_Quantity(Float comp_Quantity) {
		this.comp_Quantity = comp_Quantity;
	}
	public String getBase_Unit() {
		return base_Unit;
	}
	public void setBase_Unit(String base_Unit) {
		this.base_Unit = base_Unit;
	}
	public Float getCompQty_UoM() {
		return CompQty_UoM;
	}
	public void setCompQty_UoM(Float compQty_UoM) {
		CompQty_UoM = compQty_UoM;
	}
	public String getAltUoM() {
		return AltUoM;
	}
	public void setAltUoM(String altUoM) {
		AltUoM = altUoM;
	}
	public Date getValidate_time() {
		return validate_time;
	}
	public void setValidate_time(Date validate_time) {
		this.validate_time = validate_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
