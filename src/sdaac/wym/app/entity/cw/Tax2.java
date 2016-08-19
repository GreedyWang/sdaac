package sdaac.wym.app.entity.cw;

import java.util.ArrayList;
import java.util.List;

public class Tax2 {
	private String comment;
	private String info;
	private List<Tax> tax = new ArrayList<Tax>();
	private String no;
	private String faxMoney;
	
	/**
	 * 得到税额
	 * @return
	 */
	public String getFaxMoney() {
		String infos[] = info.split("~~");
		return infos[11];
	}

	/**
	 * 得到金穗号
	 * @return
	 */
	public String getJinSuiNo(){
		String infos[] = info.split("~~");
		return infos[4];
	}
	
	/**
	 * 得到金穗总金额
	 * @return
	 */
	public float getJinSuiMoney(){
		String infos[] = info.split("~~");
		return Float.parseFloat(infos[9]);
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public List<Tax> getTax() {
		return tax;
	}
	public void setTax(List<Tax> tax) {
		this.tax = tax;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public void addTax(String no){
		Tax a = new Tax();
		a.setNo(no);
		tax.add(a);
	}
}
