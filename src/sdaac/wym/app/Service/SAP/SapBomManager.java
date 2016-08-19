package sdaac.wym.app.Service.SAP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * deal with SapBom table
 * fill in each material's price
 * @author SA1KV5
 *
 */
public class SapBomManager {
	
	private PriceBiz priceBiz;
	private BomBiz bomBiz;
	private MPBiz mpBiz;
	
	public MPBiz getMpBiz() {
		return mpBiz;
	}
	public void setMpBiz(MPBiz mpBiz) {
		this.mpBiz = mpBiz;
	}
	public PriceBiz getPriceBiz() {
		return priceBiz;
	}
	public void setPriceBiz(PriceBiz priceBiz) {
		this.priceBiz = priceBiz;
	}
	public BomBiz getBomBiz() {
		return bomBiz;
	}
	public void setBomBiz(BomBiz bomBiz) {
		this.bomBiz = bomBiz;
	}
	/**
	 * fill in the data
	 */
	public void fillIn() {
		//s1: select null price material
		for(SrMaterialPrice item :mpBiz.doSelect()) {
			//s2: select BOM table get total price
			getTotalPrice(item.getMaterial());
			//s3: feedback to SR_MaterialPrice table
			mpBiz.doUpdate(item.getMaterial(), totalPrice, null);
			totalPrice = 0f;
		}
		
		
	}
	
	private Float totalPrice = 0f;
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * get total price
	 * @param materialSN
	 * @return
	 */
	public void getTotalPrice(String materialSN) {
		List<SapBom> rs =  bomBiz.doSelect(materialSN);
		System.out.println("======>"+totalPrice);
		for(SapBom item : rs) {
			Price tmp = priceBiz.doSelect(item.getComponent(), null);
			if(tmp!=null) { //if is a leaf ,return
				if(tmp.getPrice()!=null) { //if this root has price,return
					System.out.println("CCCCCCCCCCCCC"+item.getComp_Quantity()+"---------"+tmp.getPrice());
					totalPrice += item.getComp_Quantity() * tmp.getPrice();

				}		
				
			}	else{
			getTotalPrice(item.getComponent());
			}
		}
//		return null;
	}
	

}
